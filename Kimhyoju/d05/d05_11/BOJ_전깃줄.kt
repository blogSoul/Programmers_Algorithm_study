package d05.d05_11

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// 완탐을 메모이제이션으로 바꿔야하는데 아이디어가 생각이 안나네... 구글링..
// 아... first 순으로 정렬을 하면
//1 8
//2 2
//3 9
//4 1
//6 4
//7 6
//9 7
//10 10
// 이렇게 되는데, second가 오름차순으로 연속이되어야한다.
// 즉, 이 문제는 수열에서 LIS를 찾는 것과 같은 문제가 되는 것이다...
// 마지막 답은 전체 전깃줄 개수에서 LIS 최대길이를 빼주기만 하면 되고..
// DP의 대표적인 연습 문제.

// dp[i] = i번째 값을 마지막으로 하는 LIS 길이.
class BOJ_전깃줄 {
    var answer = 0
    lateinit var dp: IntArray
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val n = br.readLine().toInt()
        val lines = mutableListOf<Pair<Int, Int>>()
        dp = IntArray(n) { 1 } // LIS 길이를 1로 초기화.
        repeat(n) {
            val (a, b) = br.readLine().split(" ").map { it.toInt() }
            lines.add(Pair(a, b))
        }
        val sequence = lines.sortedBy { it.first }.map { it.second } // BOJ 가장 긴 증가하는 수열과 똑같은 문제가 된다.
        for (i in sequence.indices) {
            var max = 0
            for (j in 0 until i) {
                if (sequence[i] > sequence[j]) max = maxOf(max,dp[j])
            }
            dp[i] = max+1
            answer = maxOf(answer,dp[i])
        }
        bw.write("${n - answer}")
        br.close()
        bw.close()
    }
}

fun main() {
    val c = BOJ_전깃줄()
    c.main()
}
