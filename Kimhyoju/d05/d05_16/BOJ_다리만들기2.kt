package d05.d05_16

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.abs

// 바다와 대륙으로 나뉘어진 문제. 예전 쿠팡에서도 비슷한 유형 본 것 같은데
// 그 때 1개의 대륙을 1개의 집합으로 인식해야하는데 어떻게 하는지를 몰랐다.
// world 배열에서 1 만나면 bfs 돌리고 visit 체크하고 넘어가고 이러면 되려나


class BOJ_다리만들기2 {
    lateinit var world: Array<IntArray>
    lateinit var continent: Array<IntArray>
    lateinit var continentLand: Array<MutableList<Pair<Int, Int>>>
    lateinit var root: IntArray
    var continentCount = 1
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val (n, m) = br.readLine().split(" ").map { it.toInt() }
        world = Array(n) { IntArray(m) }
        for (i in 0 until n) {
            world[i] = br.readLine().split(" ").map { it.toInt() }.toIntArray()
        }
        continent = Array(n) { IntArray(m) }
        continentLand = Array(7) { mutableListOf<Pair<Int, Int>>() }

        val visited = Array(n) { BooleanArray(m) }
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (world[i][j] == 1 && !visited[i][j]) {
                    val queue: Queue<Pair<Int, Int>> = LinkedList()
                    queue.offer(Pair(i, j))
                    while (queue.isNotEmpty()) {
                        val (x, y) = queue.poll()
                        if (visited[x][y]) continue
                        continent[x][y] = continentCount
                        continentLand[continentCount].add(Pair(x, y))
                        visited[x][y] = true
                        val xList = listOf(0, 0, 1, -1)
                        val yList = listOf(1, -1, 0, 0)
                        for (dir in xList.indices) {
                            val nx = x + xList[dir]
                            val ny = y + yList[dir]
                            if (nx in 0 until n && ny in 0 until m && !visited[nx][ny] && world[nx][ny] == 1) {
                                queue.offer(Pair(nx, ny))
                            }
                        }
                    }
                    continentCount++
                }
            }
        }
//        continent.forEach { println(it.toList()) }
        root = IntArray(continentCount) { it }
        val continentDistance = Array(continentCount) { IntArray(continentCount) { Int.MAX_VALUE } }
        continentDistance.forEachIndexed { index, ints -> ints[index] = 0 }

        // Minimum Distance between Continent i ~ Continent j
        // 1 대륙과 2 대륙 사이의 최소거리를 어떻게 효율적으로 구할까. 완전탐색으로 해야하나?
        // 제한사항은 가로방향, 세로방향으로 쭉 그어야함.
        // 또 거리가 1보다 커야함.
        // 1 대륙의 모든 점마다 가로 세로로 십자가로 쭉 긋고 만나는 2 대륙의 첫 땅까지의 거리를 min으로 갱신하면 되려나.
        // 중간에 다른 대륙 만나면 끝내고. continue가 아닌, break.
        // p x q 가 되는데 흠. 그래도 전체 크기가 100밖에 안되니 완탐 한번 해보자.
        for (i in 1 until continentCount) {
            for (j in i + 1 until continentCount) {
                var min = Int.MAX_VALUE
                for ((x, y) in continentLand[i]) {
                    // x 상하
                    for (nx in x - 1 downTo 0) {
                        if (continent[nx][y] != i) {
                            if (continent[nx][y] == 0) continue
                            else if (continent[nx][y] == j) {
                                if (abs(x - nx) == 2) break
                                min = minOf(min, abs(x - nx) - 1)
                                break
                            } else break
                        } else break
                    }
                    for (nx in x + 1 until n) {
                        if (continent[nx][y] != i) {
                            if (continent[nx][y] == 0) continue
                            else if (continent[nx][y] == j) {
                                if (abs(x - nx) == 2) break
                                min = minOf(min, abs(nx - x) - 1)
                                break
                            } else break
                        } else break
                    }
                    // y 좌우
                    for (ny in y - 1 downTo 0) {
                        if (continent[x][ny] != i) {
                            if (continent[x][ny] == 0) continue
                            else if (continent[x][ny] == j) {
                                if (abs(y - ny) == 2) break
                                min = minOf(min, abs(y - ny) - 1)
                                break
                            } else break
                        } else break
                    }
                    for (ny in y + 1 until m) {
                        if (continent[x][ny] != i) {
                            if (continent[x][ny] == 0) continue
                            else if (continent[x][ny] == j) {
                                if (abs(y - ny) == 2) break
                                min = minOf(min, abs(ny - y) - 1)
                                break
                            } else break
                        } else break
                    }
                }
                continentDistance[i][j] = min
            }
        }
//        continentDistance.forEach { println(it.toList()) }
        val pq = PriorityQueue<List<Int>>(compareBy { it.last() })
        for (i in 1 until continentCount) {
            for (j in i + 1 until continentCount) {
                if (continentDistance[i][j] != Int.MAX_VALUE) {
                    pq.offer(listOf(i, j, continentDistance[i][j]))
                }
            }
        }
        var answer = 0
        while (pq.isNotEmpty()) {
            val (start, end, cost) = pq.poll()
            if (isUnion(start, end)) continue
            union(start, end)
            answer += cost
        }
        var failFlag = false
        for (i in 2 until root.size) {
            if (!isUnion(root[1], root[i])) {
                failFlag = true
                break
            }
        }
        if (failFlag) bw.write("-1")
        else bw.write("$answer")
        br.close()
        bw.close()
    }

    fun union(a: Int, b: Int) {
        val (min, max) = listOf(findUnion(a), findUnion(b)).sorted()
        root[max] = min
    }

    fun isUnion(a: Int, b: Int): Boolean {
        return findUnion(a) == findUnion(b)
    }

    fun findUnion(a: Int): Int {
        if (a == root[a]) return a
        root[a] = findUnion(root[a])
        return root[a]
    }
}

fun main() {
    val c = BOJ_다리만들기2()
    c.main()
}

