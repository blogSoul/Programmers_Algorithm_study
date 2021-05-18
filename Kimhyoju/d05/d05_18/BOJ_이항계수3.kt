package d05.d05_18

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// https://shoark7.github.io/programming/algorithm/3-ways-to-get-binomial-coefficients
// privateLab 이항계수확장 참고

// 직접 뽑는 거로 했는데, 메모리 초과. 하긴, N이 4백만이니까, dp = 4백만 x 4백만 하면 메모리 초과날만함.

// 공식을 유도하면 이렇게 됨.
//[n] = [n-1] + [n-1]
//[k]   [ k ]   [k-1]

// 근데 n이 너무 크니까 적어도 사이즈를 2개로 나눠야 될것같은데... 어떤 공식을 써야하지?

// 모르겠다. 구글링...
// 특정한 공식인, 페르마의 소정리를 사용해야 풀 수있는 문제.
// 귀찮으니 걍 스킵.

class BOJ_이항계수3 {
    val MOD_MAX = 1_000_000_007L
    fun bino_coef(n: Int, k: Int): Long {
        if (k > n) return 0
        else if (k == n) return 1

        val dp = Array(n + 1) { LongArray(n + 1) { -1L } }

        fun choose(times: Int, got: Int): Long {
            if (times == n) {
                return if (got == k) 1 else 0
            }

            if (dp[times][got] != -1L) return dp[times][got]

            dp[times][got] = (choose(times + 1, got) + choose(times + 1, got + 1)) % MOD_MAX
            return dp[times][got]
        }
        return choose(0, 0)
    }

    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val (n, k) = br.readLine().split(" ").map { it.toInt() }
        val answer = bino_coef(n, k)
        bw.write("$answer")
        br.close()
        bw.close()
    }
}

fun main() {
    val c = BOJ_이항계수3()
    c.main()
}

