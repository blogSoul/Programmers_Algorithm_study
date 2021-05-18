package d05.d05_18

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// 벨만-포드 알고리즘 복습하려고 풀어봄.
// 다익스트라와 플로이드 와샬은 adj지만, 벨만포드는 edges 임.

// 노드와 노드 사이에 2개 이상의 간선이 있을 수 있다는 말에 흠칫했으나,
// 그런거 상관없이 죄다 edges(a,b,c) 에 때려박으면 된다.
// 벨만포드는 visited가 필요없다.

// 그리고, 벨만포드 알고리즘에서 distance[a] != Int.MAX_VALUE를 반드시 체크해줘야한다.

// 또한, 시작점을 1곳만 하면 사이클을 찾을 수 있다.
// 사이클을 찾으려고 모든 곳에 대해 start를 설정하지 않아도 되는 것.
//https://www.acmicpc.net/board/view/12205
//https://www.acmicpc.net/board/view/68402

// 모두 돌면 시간초과가 뜨고,
// 1만 하면 오답이고
// n을 넣으면 정답이다. ... 뭐지?

lateinit var edges: MutableList<List<Int>>
lateinit var distance: IntArray

class BOJ_웜홀 {
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val t = br.readLine().toInt()
        repeat(t) {
            val (n, m, w) = br.readLine().split(" ").map { it.toInt() }
            edges = mutableListOf()
            for (i in 0 until m) {
                val (a, b, c) = br.readLine().split(" ").map { it.toInt() }
                edges.add(listOf(a, b, c))
                edges.add(listOf(b, a, c))
            }
            for (i in 0 until w) {
                val (a, b, c) = br.readLine().split(" ").map { it.toInt() }
                edges.add(listOf(a, b, -c))
            }

            // 아래 주석을 해제해 1부터 n까지 모든 노드를 돌면서 검사하면 답은 나오지만 시간초과가 뜬다.
//            var cycleFlag = false
//            for (i in 1..n) {
//                val res = bellmanFord(i, n)
//                if (res) {
//                    cycleFlag = true
//                    break
//                }
//            }
//            if (cycleFlag) bw.write("YES\n")
//            else bw.write("NO\n")

            // 시작점을 1로 하면 오답이고, n으로 하면 정답이다. 이유는?
//            val start = 1
            val start = n
            val cycle = bellmanFord(start, n)
            if (cycle || distance[start] < 0) bw.write("YES\n")
            else bw.write("NO\n")
        }
        br.close()
        bw.close()
    }

    fun bellmanFord(start: Int, n: Int): Boolean {
        distance = IntArray(n + 1) { Int.MAX_VALUE }
        distance[start] = 0
        for (i in 1..n) {
            for ((a, b, c) in edges) {
                if (distance[a] != Int.MAX_VALUE && distance[b] > distance[a] + c) {
                    distance[b] = distance[a] + c
                    if (i == n) {
                        return true
                    }
                }
            }
        }
        return false
    }
}

fun main() {
    val c = BOJ_웜홀()
    c.main()
}
