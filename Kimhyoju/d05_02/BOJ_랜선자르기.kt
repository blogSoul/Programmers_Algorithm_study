package d05_02

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// Parametric Search

lateinit var cords: MutableList<Long>

class BOJ_랜선자르기 {
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val (k, n) = br.readLine().split(" ").map { it.toInt() }
        cords = mutableListOf()
        repeat(k) {
            cords.add(br.readLine().toLong())
        }
        br.close()
        cords.sort()

        var answer = 0L
        var left = 1L
        var right = cords.last()
        while (left <= right) {
            val mid = (left + right + 1) / 2L
            val max = maxNumber(mid)
            if (max >= n) {
                answer = mid
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        bw.write("$answer")
        bw.close()
    }

    fun maxNumber(length: Long): Long {
        var res = 0L
        for (cord in cords) {
            res += cord / length
        }
        return res
    }
}
