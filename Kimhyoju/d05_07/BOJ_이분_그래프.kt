package d05_07

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*


// 풀 수 있을듯 말듯 하다가 결국 포기 구글링함.
// 그래프 이론에서, 이분 그래프(bipartite graph)란 모든 꼭짓점을 빨강(색1)과 파랑(색2)으로 색칠하되,
// 모든 변이 빨강(색1)과 파랑(색2) 꼭짓점을 포함하도록 색칠할 수 있는 그래프이다.
// DFS든 BFS든, 1부터 시작해서 R 혹은 1로 칠한다면 , 그 인접노드들은 B 혹은 2로 칠하고, 각 인접노드마다 또 그것의 인접한 노드는 반대로 R로
// 칠한다. 모든 정점을 돌고나서, 이분그래프인지 판별 (노드 다 돌면서) 하면 끝이다. 의외로 간단.

// 예제는 거의 다 맞는데, 37퍼쯤에서 틀린다.
// https://www.acmicpc.net/board/view/28396
// 여기서 해답을 찾았다. BFS를 돌릴때, 1부터 시작해 연결된 노드들을 담는데, 연결되지 않은 그래프면
// BFS에 넣을 수가 없으니 나머지 정점들은 큐에 들어가지도 못하고 끝난다. 연결되어있는 그래프라도 1과 연결이 안되어있으면
// 둘다 visit이 0이라는 것. 그러므로 1에서만 탐색하면 안된다.
// 그럼 연결되지 않은 그래프를 찾아야 하는데... 유니온 파인드로 해야하나?
// 아니. while 루프를 하나 추가해서, visited에 0이 있으면 그 인덱스로 offer를 한다.
// 그 인덱스로부터 연결된 모든 것들 다 컬러링하고난 다음 또 while 진입, 또 visited에 0이 있으면 그 인덱스로 offer...

class BOJ_이분_그래프 {
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val t = br.readLine().toInt()
        repeat(t) {
            val (vNum, eNum) = br.readLine().split(" ").map { it.toInt() }
            val adj = Array(vNum + 1) { mutableListOf<Int>() }
            val visited = IntArray(vNum + 1)
            for (i in 0 until eNum) {
                val (a, b) = br.readLine().split(" ").map { it.toInt() }
                adj[b].add(a)
                adj[a].add(b)
            }
            var unvisited = visited.indexOfFirst { it == 0 }
            while (unvisited != -1) {
                val queue: Queue<Pair<Int, Int>> = LinkedList()
                queue.offer(Pair(unvisited, 1))
                while (queue.isNotEmpty()) {
                    val (vertex, color) = queue.poll()
                    if (visited[vertex] != 0) continue
                    visited[vertex] = color
                    for (node in adj[vertex]) {
                        when (color) {
                            1 -> queue.offer(Pair(node, 2))
                            else -> queue.offer(Pair(node, 1))
                        }
                    }
                }
                unvisited = visited.indexOfFirst { it == 0 }
            }
            var flag = true
            for (i in 1 until vNum) {
                for (node in adj[i]) {
                    if (visited[node] == visited[i]) {
                        flag = false
                        println("flag false node: $node, i: $i")
                        break
                    }
                }
                if (!flag) break
            }
            if (flag) bw.write("YES\n")
            else bw.write("NO\n")
        }
        br.close()
        bw.close()
    }
}

fun main() {
    val c = BOJ_이분_그래프()
    c.main()
}
