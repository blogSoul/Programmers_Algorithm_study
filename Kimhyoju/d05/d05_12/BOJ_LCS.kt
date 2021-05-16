package d05.d05_12

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// A C A Y K P  에 대해
// C A P C A K 에서, 처음 문자열의 인덱스와 매칭해보면
// 1 0 5 1 0 4
//   2     2

// 이렇게 된다. 이렇게 되면, LIS와 같은 방법으로 풀 수 있으나, (0,1,2,3 => ACAK)
// 수열이 1개로 정해진 LIS와는 달리, 1개의 수의 인덱스 경우가 여러개이므로.. 어떻게 하지?
// dp 차원을 여러개로 해야하나?

// 시간초과나서, 그냥 구글링함.

// 풀이방법이... 어렵다.... 2차원 배열로 표 만들어서...
// http://melonicedlatte.com/algorithm/2018/03/15/181550.html
// 이건 그냥 나중에 하는걸로..

class BOJ_LCS {
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val strA = br.readLine()
        val strB = br.readLine()
        val info = Array(strB.length) { mutableListOf<Int>() }
        val dp = Array(strB.length) { mutableListOf<Int>() }
        for (i in strA.indices) {
            for (j in strB.indices) {
                if (strA[i] == strB[j]) {
                    info[j].add(i)
                    dp[j].add(0)
                }
            }
        }
        var answer = 0
        for (i in info.indices) {
            for (j in 0 until i) {
                for (k in info[i].indices) {
                    var max = 0
                    for (l in info[j].indices) {
                        if (info[i][k] > info[j][l]) {
                            max = maxOf(max, dp[j][l])
                        }
                    }
                    dp[i][k] = maxOf(max, dp[i][k])
                }
            }
            for (k in dp[i].indices) {
                dp[i][k]++
                answer = maxOf(answer,dp[i][k])
            }
        }
        println("info: ${info.toList()}")
        println("dp: ${dp.toList()}")
        bw.write("$answer")
        br.close()
        bw.close()
    }
}

fun main() {
    val c = BOJ_LCS()
    c.main()
}

