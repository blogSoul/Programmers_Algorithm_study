package d05.d05_04

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// 1에서 n 으로 가는데, x와 y를 거쳐야한다면,
// 1->x->y->n 또는, 1->y->x->n 이어야한다.
// 다익스트라로 할 경우에는 시간초과 걸릴것같으니, 플로이드-와샬 복습해보자.
// 아니다 다익스트라로 하는게 더 빠름. 다익스트라 활용문제라고 한다.
// 나처럼 플로이드 쓴사람도 많음.
//플로이드 워셜 알고리즘은 2차원 int 배열을 통해 관리한다는 것을 인지하고
// 양방향 간선이면 graph[a][b] = graph[b][a]를 동시에 초기화 해 주어야 한다는 점을 꼭 인지하자.

// 58퍼쯤에서 틀렸습니다가 뜬다... 뭐가 문제지?

// 아놔 플로이드에서, 마지막 점은 가장 마지막에 딱 한번 visit해야하는줄 알고 mid에서 v 뺐는데...
// 내가 너무 깐깐하게 봤음. 마지막 점도 여러번 방문 가능함 ㅡㅡ

class BOJ_특정한_최단_경로 {
    lateinit var dist: Array<IntArray>

    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val (v, e) = br.readLine().split(" ").map { it.toInt() }
        dist = Array(v + 1) { IntArray(v + 1) { Int.MAX_VALUE } }
        for (i in 1..v) dist[i][i] = 0
        for (i in 0 until e) {
            val (a, b, w) = br.readLine().split(" ").map { it.toInt() }
            dist[a][b] = w
            dist[b][a] = w
        }
        val (x, y) = br.readLine().split(" ").map { it.toInt() }
        br.close()

        for (mid in 1 until v) { // 중간에 v를 거쳐가서는 안된다. 마지막 목적지임.
            for (start in 1..v) { // v에서 start 할일은 없으므로.
                for (end in 1..v) {
                    if (dist[start][mid] != Int.MAX_VALUE && dist[mid][end] != Int.MAX_VALUE) {
                        dist[start][end] = minOf(dist[start][end], dist[start][mid] + dist[mid][end])
                    }
                }
            }
        }
        val route1 = listOf(dist[1][x], dist[x][y], dist[y][v])
        println("dist[1][$x] = ${dist[1][x]}")
        println("dist[$x][$y] = ${dist[x][y]}")
        println("dist[$y][$v] = ${dist[y][v]}")

        val route2 = listOf(dist[1][y], dist[y][x], dist[x][v])
        println("dist[1][$y] = ${dist[1][y]}")
        println("dist[$y][$x] = ${dist[y][x]}")
        println("dist[$x][$v] = ${dist[x][v]}")


        var minDist = -1
        if (route1.contains(Int.MAX_VALUE)) {
            minDist = if (route2.contains(Int.MAX_VALUE)) -1 else route2.sum()
        } else if (route2.contains(Int.MAX_VALUE)) {
            minDist = route1.sum()
        } else minDist = minOf(route1.sum(), route2.sum())
        bw.write("$minDist")
        bw.close()
    }
}

fun main() {
    val c = BOJ_특정한_최단_경로()
    c.main()
//    4 6
//    1 2 3
//    2 3 3
//    3 4 1
//    1 3 5
//    2 4 5
//    1 4 4
//    2 3
}
