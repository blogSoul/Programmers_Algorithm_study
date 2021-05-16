package d05.d05_10

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class BOJ_신나는_함수_실행 {
    lateinit var dp: Array<Array<LongArray>>
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        dp = Array(21) { Array(21) { LongArray(21) } }
        for (i in 0..20) { // a
            for (j in 0..20) { // b
                dp[i][j][0] = 1
            }
            for (j in 0..20) { // c
                dp[i][0][j] = 1
            }
        }
        for (i in 0..20) { // b
            for (j in 0..20) { // c
                dp[0][i][j] = 1
            }
        }
        while (true) {
            val (a, b, c) = br.readLine().split(" ").map { it.toInt() }
//            println("in while a: $a, b: $b, c: $b\n\n")
            if (a == -1 && b == -1 && c == -1) break
            bw.write("w($a, $b, $c) = ${w(a, b, c)}\n")
        }
        br.close()
        bw.close()
    }

    fun w(a: Int, b: Int, c: Int): Long {
//        println("a: $a, b: $b, c: $c")
        if (a <= 0 || b <= 0 || c <= 0) {
            return 1
        } else if (a > 20 || b > 20 || c > 20) {
            if (dp[20][20][20] == 0L) dp[20][20][20] = w(20, 20, 20)
            return dp[20][20][20]
        } else if (b in (a + 1) until c) {
            if (dp[a][b][c] != 0L) return dp[a][b][c]
            if (dp[a][b][c - 1] == 0L) dp[a][b][c - 1] = w(a, b, c - 1)
            if (dp[a][b - 1][c - 1] == 0L) dp[a][b - 1][c - 1] = w(a, b - 1, c - 1)
            if (dp[a][b - 1][c] == 0L) dp[a][b - 1][c] = w(a, b - 1, c)
            dp[a][b][c] = dp[a][b][c - 1] + dp[a][b - 1][c - 1] - dp[a][b - 1][c]
        } else {
            if (dp[a][b][c] != 0L) return dp[a][b][c]
            if (dp[a - 1][b][c] == 0L) dp[a - 1][b][c] = w(a - 1, b, c)
            if (dp[a - 1][b - 1][c] == 0L) dp[a - 1][b - 1][c] = w(a - 1, b - 1, c)
            if (dp[a - 1][b][c - 1] == 0L) dp[a - 1][b][c - 1] = w(a - 1, b, c - 1)
            if (dp[a - 1][b - 1][c - 1] == 0L) dp[a - 1][b - 1][c - 1] = w(a - 1, b - 1, c - 1)
            dp[a][b][c] = dp[a - 1][b][c] + dp[a - 1][b - 1][c] + dp[a - 1][b][c - 1] - dp[a - 1][b - 1][c - 1]
        }
//        println("dp[$a][$b][$c]: ${dp[a][b][c]}")
        return dp[a][b][c]
    }
}

fun main() {
    val c = BOJ_신나는_함수_실행()
    c.main()
}
