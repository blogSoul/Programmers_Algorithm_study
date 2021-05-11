package d05_11

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// https://mygumi.tistory.com/98
// 완탐으로만 풀려고 하다보니, 너무 오래걸렸다. 점화식을 세워야하는데..
// dp[n] = maxOf(dp[n-2], dp[n-3]+n-1) + n

// 얼핏 정답인 것 같지만, 실제로 돌려보면 틀린다. 2번 이상 와인을 안 먹을 경우를 위 점화식에서는 생각하지 않고
// 많아야 공백을 1개만 두기 때문.
// ex) 100 400 2 1 4 200. 정답은 704지만 위 점화식으로는 701이 나옴. 2,1을 스킵해야한다.
// 그래서 dp[n] = maxOf(dp[n-1],dp[n])이 필요하다고 한다.
class BOJ_포도주_시식 {
    lateinit var dp: IntArray
    lateinit var wines: IntArray
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val n = br.readLine().toInt()
        wines = IntArray(n + 1)
        dp = IntArray(n + 1)
        for (i in 1..n) wines[i] = br.readLine().toInt()
        dp[0] = 0
        dp[1] = wines[1]
        if (n > 1) dp[2] = dp[1] + wines[2]
        for (i in 3..n) {
            // 점화식. 2칸 이전것 vs 3칸이전 + 바로 이전것
            dp[i] = maxOf(dp[i - 2], dp[i - 3] + wines[i - 1]) + wines[i]
            // 이전것과 비교해서 더 큰거 선택.
            dp[i] = maxOf(dp[i], dp[i - 1])
        }

        bw.write("${dp[n]}")
        br.close()
        bw.close()
    }
}

fun main() {
    val c = BOJ_포도주_시식()
    c.main()
}
