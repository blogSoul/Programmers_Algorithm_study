package d05_14

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
        val weights = mutableList<Int>()
        val values = mutableList<Int>()
        var MAX_WEIGHT = 0
        var MAX_VALUE = 0
        for (i in 0 until n) {
            val (w, v) = br.readLine().split(" ").map { it.toInt() }
            weights.add(w)
            values.add(v)
            MAX_VALUE = maxOf(v, MAX_VALUE)
            MAX_WEIGHT = maxOf(w, MAX_WEIGHT)
        }
        dp = Array(MAX_WEIGHT + 1) { IntArray(MAX_WEIGHT + 1) { 0 } } // dp[i] : 무게가 i 일때 최대 가치
        for (i in weights.indices) {
            val w = weights[i]
            val v = values[i]
            for (j in w until weights.size) {
                dp[j] = maxOf(dp[j])
            }
        }
        var answer = 0
        bw.write("$answer")
        br.close()
        bw.close()
    }
}

fun main() {
    val c = BOJ_평범한_배낭()
    c.main()
}
