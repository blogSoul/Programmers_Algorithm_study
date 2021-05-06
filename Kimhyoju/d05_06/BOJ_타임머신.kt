package d05_06

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// 다익스트라는 그리디 알고리즘이라 음수 가중치 처리도 가능하다고 많이 들었다.
// 단, 사이클이 없는 경우. 이 문제처럼 음수 가중치 중첩으로 인한 사이클이 존재하는 경우엔 불가능하다.
// 불가능한건가..? 내가 한것처럼 최소 거리가 음수라면, 사이클이 그냥 있구나 라고만 생각하면 되는거 아닌가? 답은 No!
// 여기 잘 나와있다. https://hy38.github.io/floyd-warshall-algorithm
// 그래서 사이클이 존재하는 경우, 즉, dist[1] 에 음수 거리가 존재하면  첫째줄에 -1을 출력하도록 했는데 틀렸다.

// 다익스트라는 음수 간선이 있으면 안된다. 다익스트라는 한번 경로를 확정한 정점에 대해서는 다시는 갱신이 일어나지 않는다.
// 플로이드 와샬은 음수 간선이 있어도 괜찮은데, 음수 간선 순환이 발생하면 안된다.
// 벨먼 포드는 음수 간선 순환이 있어도 되고, 음수 간선 순환을 탐지할 수있다.
// 다만 벨먼포드의 시간 복잡도는 O(VE)로, 다익스트라에 비해 느리다.
// 벨먼포드는 다익스트라와 유사하다.
// 1. 출발 노드 설정
// 2. 최단 거리 테이블 초기화 (dist)
// 3. 다음 과정을 N-1번 반복
//   1) 전체 간선 E개를 하나씩 확인
//   2) 각 간선을 거쳐 다른 노드로 가는 비용을 계산하여 최단 거리 테이블 갱신

// 만약 음수 간선 순환이 발생하는지 체크하고 싶다면, 3번의 과정을 한번 더 수행
// 이때, 최단거리 테이블이 갱신된다면 음수 간선 순환이 존재하는 것.

// 음수 간선에 관하여 최단경로 문제는 다음과 같이 분류 가능.
// 1) 모든 간선이 양수인 경우 => 다익스트라
// 2) 음수 간선이 있는 경우
// 2-1) 음수 간선 순환은 없는 경우 => 플로이드-와샬
// 2-2) 음수 간선 순환이 있는 경우 => 벨만-포드

// 다익스트라 알고리즘: 매번 방문하지 않은 노드 중에서 최단거리가 짧은 노드를 선택.
// 음수 간선이 없다면 최적해 찾을 수 있음.

// 벨만 포드 알고리즘: 매번 모든 간선을 전부 확인 (visited X)  = 다익스트라, 플로이드에서는 adj로 했으나 벨먼포드는 edge로..
// -> 따라서 다익스트라 알고리즘에서의 최적해를 항상 포함
// 다익스트라 알고리즘에 비해서 시간이 오래걸리지만 음수 간선 순환 탐지 가능

// 근데 그전에 일단, 한 노드에서 전체 노드의 거리를 구하는 거니까, 플로이드 말고 다른 거 쓰는게 훨씬 낫다.
// 다익스트라: O(V^2 + E) PQ다익스트라: O(ElogV) (벨먼-포드: O(V*E), 플로이드 O(V^3)

// 결국 플로이드 와샬로 풀었다가 실패하고 벨먼포드 알고리즘을 공부했다.

class BOJ_타임머신 {
    private lateinit var edges: MutableList<List<Int>>
    private lateinit var dist: IntArray
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val (n, m) = br.readLine().split(" ").map { it.toInt() }
        edges = mutableListOf()
        dist = IntArray(n + 1) { Int.MAX_VALUE }
        for (i in dist.indices) dist[0] = 0
        repeat(m) {
            val (a, b, c) = br.readLine().split(" ").map { it.toInt() }
            edges.add(listOf(a, b, c))
        }
        val negativeCycle = bellmanFord(1, n, m)
        if (negativeCycle) bw.write("-1")
        else {
            for (i in 1..n) {
                if (i == 1) continue
                if (dist[i] == Int.MAX_VALUE) bw.write("-1\n")
                else bw.write("${dist[i]}\n")
            }
        }
        bw.close()
    }

    fun bellmanFord(start: Int, n: Int, m: Int): Boolean {
        dist[start] = 0
        // 전체 정점 개수만큼 n-1번 라운드를 반복 (원래는 n-1번이지만, 마지막에 음수 순환을 탐지하기 위해 1번 더 돌리므로, n번)
        // 벨만포드 알고리즘에서는 n-1번 edge들을 거치고 나면 최단거리가 완성된다.
        // 근데 한번 더 돌리게 되면 음의 사이클을 가진 경우에는 최단거리가 갱신되므로, 이것으로 음의 사이클을 탐지하는 것.
        for (i in 1..n) {
            // 매 반복마다 "모든 간선" 확인 = 모든 edges 확인
            // 다익스트라는 정점을 기준으로 최단경로를 찾았지만, 벨만포드는 간선을 기준으로 찾는다.
            for ((a, b, c) in edges) {
                // 현재 간선을 거쳐서 다른 노드로 이동하는 거리가 더 짧은 경우
                if (dist[a] != Int.MAX_VALUE && dist[b] > dist[a] + c) {
                    dist[b] = dist[a] + c
                    if (i == n) return true
                }
            }
        }
        return false
    }
}

fun main() {
    val c = BOJ_타임머신()
    c.main()
}
