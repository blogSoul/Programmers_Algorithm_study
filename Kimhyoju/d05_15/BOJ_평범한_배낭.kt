package d05_15

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// 대표적인 DP문제인 냅색문제라고 한다.
// 점화식 찾기가 너무 어려운 것 같다..
class BOJ_평범한_배낭 {
    lateinit var dp: Array<IntArray>
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val (n, k) = br.readLine().split(" ").map { it.toInt() }
        val weights = IntArray(n) { 0 }
        val values = IntArray(n) { 0 }
        for (i in 0 until n) {
            val (w, v) = br.readLine().split(" ").map { it.toInt() }
            weights[i] = w
            values[i] = v
        }
        dp = Array(n) { IntArray(k + 1) { 0 } } // dp[i] : 무게가 i 일때 최대 가치
        for (i in 0 until n) {
            val w = weights[i]
            val v = values[i]
            for (j in 0..k) {
                if (i == 0) { // i = 0
                    if (j >= w) dp[0][j] = v
                    continue
                }
                if (j < w) {
                    dp[i][j] = dp[i - 1][j]
                } else if (j >= w) {
                    dp[i][j] = maxOf(dp[i - 1][j - w] + v, dp[i - 1][j])
//                println("dp[$j]: ${dp[j]}")
                }
            }
            println("dp: ${dp[i].toList()}")
        }
        var answer = 0
        for (i in 0..k) answer = maxOf(answer, dp[n - 1][i])
        bw.write("$answer")
        br.close()
        bw.close()
    }
}

fun main() {
    val c = BOJ_평범한_배낭()
    c.main()
}
