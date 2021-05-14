package d05_14

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// 플로이드 와샬을 돌렸더니 메모리 초과.
// 한 점에서 각 이웃노드로 DFS로 돌린 합을 찾으면 될거라 생각했는데, 아니었다.
// 은근히 걸리네.
// 한 노드에서, 각 n개의 이웃노드로 DFS를 돌리고, n개중에 가장 큰 2개를 고르면?
// 아니. 최장경로에 root가 포함되어있지 않을 가능성이 있다.
// 일단 leaf 노드를 기준으로 해야한다는 건 알겠는데..
// root를 다 해보면 O(n^2) 인데.. 당연히 시간초과.

// 구글링함.
// https://mygumi.tistory.com/226
// 트리의 지름은 DFS 탐색 2번으로 구할 수 있다고 한다.
//https://kyu9341.github.io/algorithm/2020/03/12/algorithm1967/
// 한 정점 s에서 가장 먼 거리에 있는 노드를 u라고 한다.
// u에서 가장 먼 거리에 있는 노드를 v라고 한다.
// u에서 v 까지의 거리가 트리의 지름이다.

// 본능적으로 이런식으로 접근을 했으나, 원의 지름과 비슷하다는 개념을 알아야
// 더 효율적으로 답을 찾을 수 있을 것 같다.
// root를 아무거나 잡고, 그것에서 가장 가까운 노드 u 를 저장.
// root를 u로 잡고, 그것에서 가장 가까운 노드 v를 저장.
// u~v 의 거리가 바로 트리의 지름.

class BOJ_트리의_지름 {
    lateinit var adj: Array<MutableList<Pair<Int, Int>>>

    lateinit var visited: BooleanArray
    var max = 0
    var maxNode = 0
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val n = br.readLine().toInt()
        adj = Array(n + 1) { mutableListOf<Pair<Int, Int>>() }
        visited = BooleanArray(n + 1)
        repeat(n) {
            val vList = br.readLine().split(" ").map { it.toInt() }
            for (i in 1 until vList.size step 2) {
                if (vList[i] == -1) break
                val start = vList.first()
                adj[start].add(Pair(vList[i], vList[i + 1]))
            }
        }
        var root = 1
        visited = BooleanArray(n + 1)
        visited[root] = true
        for ((node, count) in adj[root]) {
            dfs(node, count)
//            println("node: $node, max: $max, maxIdx: $maxNode")
        }

//        println("root = $maxNode")
        root = maxNode
        max = 0
        maxNode = 0
        visited = BooleanArray(n + 1)
        visited[root] = true
        for ((node, count) in adj[root]) {
            dfs(node, count)
//            println("node: $node, max: $max, maxNode: $maxNode")
        }
        var answer = max
        bw.write("$answer")
        br.close()
        bw.close()
    }

    fun dfs(cur: Int, count: Int) {
        if (visited[cur]) return
        visited[cur] = true
        var endFlag = true
        for ((n, c) in adj[cur]) {
            if (visited[n]) continue
            dfs(n, count + c)
            endFlag = false
        }
        if (endFlag) {
            if (max < count) {
                max = count
                maxNode = cur
            }
        }
    }
}

fun main() {
    val c = BOJ_트리의_지름()
    c.main()
}
