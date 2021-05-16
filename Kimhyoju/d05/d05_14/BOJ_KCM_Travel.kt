package d05.d05_14

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

// 간선을 사용하는 비용과 예산 제약이 있을 때 다이나믹 프로그래밍으로 최단거리를 찾는 문제
// 일반적인 PQ 다익스트라를 쓰니 역시나 37% 쯤에서 틀렸다.
// 왜냐하면, visit 해가면서 중간에 cost 가 넘을 시에는 아무것도 안하고 넘어가는데,
// 이 때 visit 된채로 남아있으니, 다른 노드가 이곳에 갈 수 없기 때문.
// visit를 bool 값이 아닌, int로 하고, 실패하면 지나온 visit을 --하는 건 어떨까?

// 그렇게 해도 안될듯. 먼 곳에 있는 노드의 distance는 시작점에서 가까운 노드보다 거리가 멀 가능성이 높다.
// 거리가 먼 노드에서 실패해서 visit을 -- 하더라도, 그 전에 시작점에서 가까운 노드가 먼저 poll 되고나면
// visit 이 0보다 크므로 그대로 탐색을 중단할 것이기 때문.

// 모르겠어서 구글링함.

// visit을 쓰는 게 아니라, DP를 쓰는거라고 한다.
// 근데 DP를 쓰는 사람들이 말하는게, cost도 고려해야하는 이유라고는 하는데..
// cost도 같이 PQ에 넣어주면 되는 거 아닌가? 그럼 해결되는데..
// 아무튼 visit으로 continue를 해주는 게 아니라, 다른 위상에 있는 값에서 탐색을 계속 해나갈 수 있도록 설계한 것이 포인트라고 한다.
//https://kyunstudio.tistory.com/160

// distance를 2차원으로 선언하자.
// distance[vertex][cost]
// 그리고 pq loop 를 돌면서 distance[start][cost] < dist 면, 볼 필요도 없이 continue 해주면 된다.

// 기존에 나는 distance를 각 노드로의 거리로만 했지만,
// 거기에 cost도 넣어서 고려를 해줘야하는 것이다.


class BOJ_KCM_Travel {
    data class Edge(val dest: Int, val cost: Int, val distance: Int)

    lateinit var adj: Array<MutableList<Edge>>
    lateinit var distance: Array<IntArray>
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val t = br.readLine().toInt()
        repeat(t) {
            val (n, m, k) = br.readLine().split(" ").map { it.toInt() }
            adj = Array(n + 1) { mutableListOf<Edge>() }
            distance = Array(n + 1) { IntArray(m + 1) { Int.MAX_VALUE } }
            distance[1][0] = 0
            for (i in 0 until k) {
                val (u, v, c, d) = br.readLine().split(" ").map { it.toInt() }
                adj[u].add(Edge(v, c, d))
            }
            dijkstra(m)
            var answer = Int.MAX_VALUE
            distance[n].forEach { answer = minOf(answer, it) }
            if (answer == Int.MAX_VALUE) bw.write("Poor KCM\n")
            else bw.write("$answer\n")
        }
        br.close()
        bw.close()
    }

    private fun dijkstra(costLimit: Int) {
        val pq = PriorityQueue<List<Int>>(compareBy({ it[2] }, { it[1] })) // 0: des, 1: cost, 2: distance. distance 먼저.
        pq.offer(listOf(1, 0, 0))
        while (pq.isNotEmpty()) {
            val (des, cost, dist) = pq.poll()
            if (distance[des][cost] < dist) continue
            for ((v, c, d) in adj[des]) {
                val nextDist = d + dist
                val nextCost = cost + c
                // 해당 공항에 해당 비용으로 갈 떄의 최소시간보다 크다면 pass
                if (nextCost > costLimit) continue
                if (distance[v][nextCost] <= nextDist) continue
                // 해당 공항에 최소시간으로 가는 비용보다 높은 비용으로 가는 방법들을 최소시간으로 업데이트
                // (무의미한 계산 막기 위함)
                for (i in nextCost..costLimit) {
                    if (distance[v][i] > nextDist) {
                        distance[v][i] = nextDist
                    }
                }
                pq.offer(listOf(v, nextCost, nextDist))
            }
        }
    }
}

fun main() {
    val c = BOJ_KCM_Travel()
    c.main()
}
