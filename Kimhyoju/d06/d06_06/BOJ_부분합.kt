package d06.d06_06

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class BOJ_부분합 {
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val (n, s) = br.readLine().split(" ").map { it.toInt() }
        val numbers = br.readLine().split(" ").map { it.toInt() }
        var minLength = Int.MAX_VALUE
        if (numbers[0] >= s) {
            bw.write("1")
            bw.close()
            return
        }
        var left = 0
        var right = 1
        var sum = 0
        for (i in left..right) sum += numbers[i]
        while (left <= numbers.lastIndex) {
            if (sum >= s) {
                sum -= numbers[left]
                minLength = minOf(minLength, right - left + 1)
                left++
            } else {
                right++
                if (right >= numbers.size) break
                sum += numbers[right]
            }
        }
        if (minLength == Int.MAX_VALUE) minLength = 0
        bw.write("$minLength\n")
        br.close()
        bw.close()
    }
}

fun main() {
    val c = BOJ_부분합()
    c.main()
}
