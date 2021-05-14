package d05_14

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// 대표적인 DP문제인 냅색문제라고 한다.
// 점화식 찾기가 너무 어려운 것 같다..
class BOJ_평범한_배낭 {
    lateinit var dp: Array<MutableList<Pair<Int, Int>>>
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val (n, k) = br.readLine().split(" ").map { it.toInt() }
        dp = Array(n) { mutableListOf<Pair<Int, Int>>() }
        var answer = 0
        for (i in 0 until n) {
            val (w, v) = br.readLine().split(" ").map { it.toInt() }
            if (i > 0) {
                for (j in 0..i) dp[i].add(dp[i - 1][j])
                for ((weight, value) in dp[i - 1]) {
                    val newWeight = weight + w
                    if (newWeight > k) continue
                    val newValue = value + v
                    dp[i].add(Pair(newWeight, newValue))
                    answer = maxOf(answer, newValue)
                }
            } else {
                dp[i].add(Pair(0, 0))
                dp[i].add(Pair(w, v))
                if (w <= k) answer = v
            }
            println("dp: ${dp.toList()}")
        }
        println("final dp: ${dp.toList()}")

        bw.write("$answer")
        br.close()
        bw.close()
    }
}

fun main() {
    val c = BOJ_평범한_배낭()
    c.main()
}
