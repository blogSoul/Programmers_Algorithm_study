package d05_12

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// 쉬운데, 53퍼에서 계속 시간초과가 난다..
// 질문을 찾아보니 시간 제한 1초에 N이 10만개쯤 되면, O(N^2)인 알고리즘은 안 된다고 보시면 됩니다.
// 1을 루트로 하는 트리를 구성하는 데에 O(N) 시간으로 해결할 수 있습니다.

// 음... 큐로 하지말고 DFS로 해야되나보다.
class BOJ_트리의_부모_찾기 {
    lateinit var adj: Array<MutableList<Int>>
    lateinit var parent: IntArray
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val n = br.readLine().toInt()
        parent = IntArray(n + 1) { -1 }
        parent[1] = 1
        adj = Array(n + 1) { mutableListOf<Int>() }
        repeat(n - 1) {
            var (a, b) = br.readLine().split(" ").map { it.toInt() }.sorted()
            adj[a].add(b)
            adj[b].add(a)
        }
        for (c in adj[1]) {
            parent[c] = 1
            dfs(c)
        }
        for (i in 2..n) {
            bw.write("${parent[i]}\n")
        }
        br.close()
        bw.close()
    }

    fun dfs(par: Int) {
        for (c in adj[par]) {
            if (parent[c] != -1) continue
            parent[c] = par
            dfs(c)

        }
    }
}

fun main() {
    val c = BOJ_트리의_부모_찾기()
    c.main()
}
