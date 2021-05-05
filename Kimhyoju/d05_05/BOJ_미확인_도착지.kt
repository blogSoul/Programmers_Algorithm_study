package d05_05

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

// start 지점에서, 최단거리로 candidate의 노드로 이동할 때, g-h egde를 지나간다면
// 그 candidate 가 답이다.

// 그렇다면, g,h를 경유한다면, s->g->h->c 혹은 s->h->g->c 가 s->c 와 같다면, 그것은 그곳으로 향하고 있다고 봐도 된다.
// 플로이드를 쓰는 게 좋을듯.

// 접근법은 틀리지 않은 것 같은데 시간초과... 음... 다익스트라를 쓰라는 건가.

class BOJ_미확인_도착지 {
    private lateinit var adj: Array<MutableList<Pair<Int, Int>>>
    var n = 0
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val test = br.readLine().toInt()
        for (testCnt in 1..test) {
            val answer = PriorityQueue<Int>()
            val (_n, m, t) = br.readLine().split(" ").map { it.toInt() }
            n = _n
            val (s, g, h) = br.readLine().split(" ").map { it.toInt() }
            adj = Array(n + 1) { mutableListOf<Pair<Int, Int>>() }
            for (mCnt in 1..m) {
                val (a, b, d) = br.readLine().split(" ").map { it.toInt() }
                adj[a].add(Pair(b, d))
                adj[b].add(Pair(a, d))
            }
            val candidates = mutableListOf<Int>()
            for (tCnt in 1..t) {
                candidates.add(br.readLine().toInt())
            }
            for (c in candidates) {
                val destDist = dijkstra(s, c)
                val gViaH = dijkstra(g, h)
                if (dijkstra(s, g) + gViaH + dijkstra(h, c) == destDist) {
                    answer.offer(c)
                } else if (dijkstra(s, h) + gViaH + dijkstra(g, c) == destDist) {
                    answer.offer(c)
                }
            }
            while (answer.isNotEmpty()) bw.write("${answer.poll()} ")
            bw.write("\n")
        }
        br.close()
        bw.close()
    }

    fun dijkstra(start: Int, end: Int): Int {
        val dist = IntArray(n + 1) { Int.MAX_VALUE }
        dist[start] = 0
        val visited = BooleanArray(n + 1)
        visited[0] = true
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
        pq.offer(Pair(start, 0))
        while (pq.isNotEmpty()) {
            val (vertex, weight) = pq.poll()
            visited[vertex] = true
            for ((v, w) in adj[vertex]) {
                if (visited[v]) continue
                if (dist[v] > dist[vertex] + w) {
                    dist[v] = dist[vertex] + w
                    pq.offer(Pair(v, dist[v]))
                }
            }
        }
        return dist[end]
    }
}

fun main() {
    val c = BOJ_미확인_도착지()
    c.main()
}
