package d05.d05_18

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.concurrent.fixedRateTimer

// 동적계획법을 적용하는 위상정렬

// 반례:
// 1
// 10 11
// 10 1 1 100 10 10 100 1 1 1
// 1 2
// 2 3
// 3 6
// 4 3
// 4 7
// 4 9
// 5 4
// 6 9
// 7 8
// 8 9
// 10 7
// 9
// 오답: 221
// 정답: 212

// DFS 로 푸는게 좋을듯 하다..

// 아니. BFS로 풀면 해당 노드 전의 node 들 중 dp[node]의 최댓값에 + cost 하면 됨.
// DFS로 풀면 다음번 노드를 dfs 콜하기 전에 dp 최댓값 계속 갱신하면 됨.

class BOJ_ACM_Craft {
    lateinit var incoming: Array<MutableList<Int>>
    lateinit var outgoing: Array<MutableList<Int>>
    lateinit var visited: BooleanArray
    lateinit var costs: MutableList<Long>
    lateinit var dp: LongArray
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val t = br.readLine().toInt()
        repeat(t) {
            val (n, k) = br.readLine().split(" ").map { it.toInt() }
            incoming = Array(n + 1) { mutableListOf<Int>() }
            outgoing = Array(n + 1) { mutableListOf<Int>() }
            visited = BooleanArray(n + 1)
            costs = br.readLine().split(" ").map { it.toLong() }.toMutableList()
            dp = LongArray(n + 1)
            costs.add(0, 0)
            for (i in 0 until k) {
                val (x, y) = br.readLine().split(" ").map { it.toInt() }
                incoming[y].add(x)
                outgoing[x].add(y)
            }
            val finish = br.readLine().toInt()
            for (i in 1..n) {
                if (visited[i]) continue
                if (incoming[i].isEmpty()) {
                    dp[i] = costs[i]
                    dfs(i)
                }
            }
//            println("final dp: ${dp.toList()}")
            bw.write("${dp[finish]}\n")
        }
        br.close()
        bw.close()
    }

    fun dfs(n: Int) {
        if (visited[n]) return
        visited[n] = true
        for (node in outgoing[n]) {
            if (visited[node]) continue
            incoming[node].remove(n)
            dp[node] = maxOf(dp[node], costs[node] + dp[n])
            if (incoming[node].isEmpty()) {
                dfs(node)
            }
        }
    }
}

fun main() {
    val c = BOJ_ACM_Craft()
    c.main()
}

