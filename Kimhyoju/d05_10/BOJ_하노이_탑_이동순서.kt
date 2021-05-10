package d05_10

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Math.pow

val bw = BufferedWriter(OutputStreamWriter(System.`out`))

class BOJ_하노이_탑_이동순서 {
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val n = br.readLine().toInt()
        bw.write("${(pow(2.0,n.toDouble())-1).toInt()}\n")
        hanoi(n, 1, 2, 3)
        br.close()
        bw.close()
    }

    fun hanoi(n: Int, start: Int, mid: Int, end: Int) {
        if (n == 0) return
        hanoi(n - 1, start, end, mid)
        move(start, end)
        hanoi(n - 1, mid, start, end)
    }

    fun move(start: Int, end: Int) {
        bw.write("$start $end\n")
    }
}

fun main() {
    val c = BOJ_하노이_탑_이동순서()
    c.main()
}
