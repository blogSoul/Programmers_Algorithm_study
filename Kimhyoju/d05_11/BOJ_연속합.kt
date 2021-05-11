package d05_11

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// Prefix Sum 으로 O(N^2) 로 풀었는데 시간초과났다. 구글링..
// https://sihyungyou.github.io/baekjoon-1912/
// 1) 직전까지의 부분합과 현재 가리키고 있는 수열의 수를 더한 수,
// 2) 현재 가리키고 있는 수열의 수
// 그리고 1과 2를 비교하고 더 큰 값을 부분합으로 지정한다.
// 즉, 지금까지 모두 더한 값과 현재 값을 비교했는데 현재 값이 더 크다면 그 이전 수들은 망설임 없이 버리고 새로 시작해도 된다는 뜻이다.

// 점화식을 세우면 dp[i] = maxOf(dp[i-1],0) + arr[i]

class BOJ_연속합 {
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val n = br.readLine().toInt()
        val numbers = br.readLine().split(" ").map { it.toInt() }
        val dp = IntArray(n)
        dp[0] = numbers[0]
        var answer = dp[0]
        for (i in 1 until numbers.size) {
            dp[i] = maxOf(dp[i - 1], 0) + numbers[i]
            answer = maxOf(answer, dp[i])
        }
        bw.write("$answer")
        br.close()
        bw.close()
    }
}

fun main() {
    val c = BOJ_연속합()
    c.main()
}
