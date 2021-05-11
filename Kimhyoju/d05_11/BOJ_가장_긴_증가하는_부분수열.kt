package d05_11

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// 이것도 점화식인가? 잘 모르겠다. 실버2인데도 어렵게 느껴지네.. 구글링
// https://wootool.tistory.com/96
// 생각했던 거이기도 한데.. 아니겠지 하고 넘어간 아이디어. 이렇게 풀어도 시간안에 되는구나..
// https://wootool.tistory.com/96
// dp[i] = i~i-1 까지의 원소들에서 i번째 원소보다 값이 작은 것들 중 가장 큰 dp값+1
class BOJ_가장_긴_증가하는_부분수열 {
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val n = br.readLine().toInt()
        val numbers = br.readLine().split(" ").map { it.toInt() }
        val dp = IntArray(n)
        var answer = 0
        for (i in numbers.indices) {
            var max = 0
            for (j in 0 until i) {
                if (numbers[i] > numbers[j]) max = maxOf(max, dp[j])
            }
            dp[i] = max + 1
            answer = maxOf(dp[i] , answer)
        }
        bw.write("$answer")
        br.close()
        bw.close()
    }
}

fun main() {
    val c = BOJ_가장_긴_증가하는_부분수열()
    c.main()
}
