package d05.d05_28

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class BOJ_다리놓기 {
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val t = br.readLine().toInt()
        repeat(t) {
            val (n, m) = br.readLine().split(" ").map { it.toInt() }
            bw.write("${combination(n, m)}\n")
        }
        br.close()
        bw.close()
    }

    fun combination(n: Int, m: Int): Long {
        if (n == m) return 1
        var (min, max) = listOf(n, m).sorted()
        min = minOf(min,max-min)
        println("max: $max, min: $min")
        var sum = 1L
        var temp = max
        for (i in 0 until min) {
            sum *= temp
            temp--
        }
        return sum / factorial(min)
    }

    fun factorial(n: Int): Long {
        var sum = 1L
        for (i in 2..n) sum *= i
        return sum
    }
}

fun main() {
    val c = BOJ_다리놓기()
    c.main()
}
