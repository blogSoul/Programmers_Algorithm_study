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

// 다익스트라를 쓰라고 한다. 근데 문제가 또 있다. 또 시간초과. 질문을 해보니,

// 다익스트라의 시간복잡도는 O(V log E +E)이고
//현재 질문자님께서는 후보지마다 2번씩돌리고 계시니
//후보지의 갯수를 c 테스트케이스의 갯수를 T라하면
//이 코드의 시간복잡도는
//O(Tc(Vlog E+E))입니다.
//최대 인풋인 T = 100, V = 2000, E = 50000, c = 100를 대입하면 약 6억이라는 숫자가 나옵니다.
//알고리즘 테스트에서는 O notation에 숫자를 넣었을때 1억을 대략 1초로 잡는데 6억이면 6초 정도걸리니 시간초과가 뜹니다.
//로직을 조금 다듬으시면 다익스트라를 여러번 돌릴 필요없이 한번만 돌리고도 답을 알 수 있습니다.
//로직을 다듬어보시기를 추천드립니다.

// 음... 고민좀 해봐야겠다.

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
