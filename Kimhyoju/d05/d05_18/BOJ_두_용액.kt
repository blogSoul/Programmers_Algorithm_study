package d05.d05_18

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.abs

// 왜 틀리지..? 맞는데?

// sum으로 두개를 합치고, 음수면 좌측을 올리고 양수면 우측을 내려야한다...
// 너무 복잡하게 생각했음.

class BOJ_두_용액 {
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val n = br.readLine().toInt()
        val solution = br.readLine().split(" ").map { it.toInt() }.toMutableList()
        solution.sort()
        var left = 0
        var right = n - 1
        var answer = intArrayOf(0,0)
        var diff = Int.MAX_VALUE
        while (left < right) {
            if (diff == 0) break
            var sum = solution[left] + solution[right]
            if (abs(sum) < diff) {
                diff = abs(sum)
                answer[0] = solution[left]
                answer[1] = solution[right]
            }
            if (sum < 0) left++
            else right--
        }
        answer.sort()
        bw.write("${answer.first()} ")
        bw.write("${answer.last()}")
        br.close()
        bw.close()
    }
}

fun main() {
    val c = BOJ_두_용액()
    c.main()
}
