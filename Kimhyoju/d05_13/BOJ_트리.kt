package d05_13

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

// 트리인지 아닌지 판별할 수는 있는데... 트리 개수는 어떻게 구하는 거지? 구글링함.
// 아 문제를 잘못 읽었다. 난 또 트리 안에서 부분 트리 개수를 찾는건줄 알았는데
// 말 그대로 그냥 트리 개수다.

// https://www.crocus.co.kr/630
// 중요한 것은, 트리가 성립하려면 간선개수/2 == 정점개수 -1 이어야 한다.
class BOJ_트리 {
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        var testCnt = 1
        while (true) {
            val (n, m) = br.readLine().split(" ").map { it.toInt() }
            if (n == 0 && m == 0) break
            val visited = BooleanArray(n + 1)
            val adj = Array(n + 1) { IntArray(n + 1) }
            repeat(m) {
                val (a, b) = br.readLine().split(" ").map { it.toInt() }
                adj[a][b] = 1
                adj[b][a] = 1
            }
            var count = 0
            for (i in 1..n) {
                val queue: Queue<Int> = LinkedList()
                queue.offer(i)
                var edgeCnt = 0
                var vertexSet = mutableSetOf<Int>()
                while (queue.isNotEmpty()) {
                    val node = queue.poll()
                    if (visited[node]) continue
                    bw.write("node: $node\n")
                    vertexSet.add(node)
                    visited[node] = true
                    for (j in 1..n) {
                        if (adj[node][j] == 1) {
                            queue.offer(j)
                            edgeCnt++
                        }
                    }
                }
                if (edgeCnt / 2 == vertexSet.size - 1) {
                    count++
                    bw.write("count: $count\n")
                }
            }
            if (count == 1) {
                bw.write("Case $testCnt: There is one tree.\n")
            } else if (count > 1) {
                bw.write("Case $testCnt: A forest of $count trees.\n")
            } else bw.write("Case $testCnt: No trees.\n")
            testCnt++
        }
        br.close()
        bw.close()
    }
}

fun main() {
    val c = BOJ_트리()
    c.main()
}
