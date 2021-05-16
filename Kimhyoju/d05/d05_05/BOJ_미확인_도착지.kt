package d05.d05_05

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

// 계속 거리의 비교의 틀 안에서 생각을 하느라 처음엔 이해가 안갔는데, 조언자는 거리의 비교를 이용하는 게 아니었다.
// 시작점부터 모든 점까지 다익스트라를 돌리고 만약 g-h를 거치면 그때부터 pq와 isVia를 이용해 꼬리표를 붙이는 것.
// 다익스트라를 돌린 결과, 후보지로의 최소거리를 구한 것중에 꼬리표가 붙어있으면, 즉 isVia 가 true인 것이면 g-h를 거쳤다는 게 된다는 것.

class BOJ_미확인_도착지 {
    private lateinit var adj: Array<MutableList<Pair<Int, Int>>>
    private lateinit var dist: IntArray
    private lateinit var isVia: BooleanArray
    private lateinit var visited: BooleanArray
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val test = br.readLine().toInt()
        for (testCnt in 1..test) {
            val (n, m, t) = br.readLine().split(" ").map { it.toInt() }
            val (s, g, h) = br.readLine().split(" ").map { it.toInt() }
            adj = Array(n + 1) { mutableListOf<Pair<Int, Int>>() }
            dist = IntArray(n + 1) { Int.MAX_VALUE }
            isVia = BooleanArray(n + 1)
            visited = BooleanArray(n + 1)
            for (mCnt in 1..m) {
                val (a, b, d) = br.readLine().split(" ").map { it.toInt() }
                adj[a].add(Pair(b, d))
                adj[b].add(Pair(a, d))
            }
            val candidates = PriorityQueue<Int>()
            for (tCnt in 1..t) {
                candidates.offer(br.readLine().toInt())
            }
            dijkstra(s, g, h)
            while (candidates.isNotEmpty()) {
                val c = candidates.poll()
                if (isVia[c]) bw.write("$c ")
            }
            bw.write("\n")
        }
        br.close()
        bw.close()
    }

    private fun dijkstra(start: Int, g: Int, h: Int) {
        visited[0] = true
        val pq = PriorityQueue<List<Int>>(compareBy<List<Int>> { it[1] }.thenByDescending { it[2] })
        pq.offer(listOf(start, 0, 0))
        while (pq.isNotEmpty()) {
            val (vertex, weight, via) = pq.poll()
            visited[vertex] = true
            dist[vertex] = weight
            when (via) {
                0 -> isVia[vertex] = false
                else -> isVia[vertex] = true
            }
            for ((v, w) in adj[vertex]) {
                if (visited[v]) continue
                if (dist[v] >= dist[vertex] + w) { // > 가 아니라 >=인 이유는 같은 cost가 이미 있어도 g-h 통과 노드는 넣어야함.
                    dist[v] = dist[vertex] + w
                    if (listOf(vertex, v) == listOf(g, h) || listOf(vertex, v) == listOf(h, g)) { // g-h 통과하면
                        pq.offer(listOf(v, weight + w, 1))
                    } else pq.offer(listOf(v, weight + w, via))
                }
            }
        }
    }
}

fun main() {
    val c = BOJ_미확인_도착지()
    c.main()
}
