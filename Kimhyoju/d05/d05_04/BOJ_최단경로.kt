package d05.d05_04

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

// Dijkstra Algorithm With Priority Queue
// 그냥 다익스트라로 했더니 틀림. 알고보니 서로다른 두 정점 사이에는 여러개의 정점이 있다고 해서 수정.
// 56퍼 넘어가다가 시간초과. 질문검색을 보니 시간초과가 엄청 많다. 우선순위 큐로 풀어야하나보다.
// 언제나 생각하지만 백준은 확실히 시간에 대해 엄청 깐깐하다. 대회용이니까 어쩔 수 없지.
// 다익스트라를 우선순위큐로 풀일은 딱히 없었기에 구글링해봄.
//https://www.crocus.co.kr/546
// 우선순위 큐를 이용하면 시간복잡도가 O(V^2) -> O(E+V) x logV 로 현저히 줄어든다.
// 여기서 logV는, 선형탐색으로 최단거리를 가진 정점을 찾는 대신, MinHeap을 이용하여 최소값이 위로 올라오도록 정렬되기에..

//https://devlog-wjdrbs96.tistory.com/102

class BOJ_최단경로 {
    private lateinit var adj: Array<MutableList<Pair<Int, Int>>>
    private lateinit var distance: IntArray
    private lateinit var visited: BooleanArray
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val (v, e) = br.readLine().split(" ").map { it.toInt() }
        adj = Array(v + 1) { mutableListOf<Pair<Int, Int>>() }
        distance = IntArray(v + 1) { Int.MAX_VALUE }
        visited = BooleanArray(v + 1)
        val start = br.readLine().toInt()
        visited[0] = true
        for (i in 0 until e) {
            val (a, b, w) = br.readLine().split(" ").map { it.toInt() }
            adj[a].add(Pair(b, w))
        }
        br.close()

        distance[start] = 0
//        for ((vertex, weight) in adj[start]) {
//            distance[vertex] = weight
//        }
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
        pq.offer(Pair(start, 0))
        dijkstra(pq)

        for (i in 1..v) {
            if (distance[i] == Int.MAX_VALUE) bw.write("INF\n")
            else bw.write("${distance[i]}\n")
        }
        bw.close()
    }

    private fun dijkstra(pq: PriorityQueue<Pair<Int, Int>>) {
        while (pq.isNotEmpty()) {
            val (vertex, weight) = pq.poll()
            if (visited[vertex]) continue
            else visited[vertex] = true // visit 처리는 pq에서 뺄때만 해줘야함. 넣을때는 모름.
            // dist배열 업데이트 된 것은 PQ에 offer.
            for ((v, w) in adj[vertex]) {
                if (visited[v]) continue
                if (distance[v] > distance[vertex] + w) {
                    distance[v] = distance[vertex] + w
                    pq.offer(Pair(v, distance[v]))
                }
            }
            // 이렇게 pq를 이용함으로써, 기존의 다익스트라에서 start에서 가장 짧은 노드를 선형탐색으로 찾는 과정은 스킵.
        }
    }
}

fun main() {
    val c = BOJ_최단경로()
    c.main()
}
