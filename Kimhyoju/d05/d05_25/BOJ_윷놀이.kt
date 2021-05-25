package d05.d05_25

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class BOJ_윷놀이 {
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        repeat(3) {
            val list = br.readLine().split(" ").map { it.toInt() }
            bw.write(when (list.count { it == 0 }) {
                1 -> "A"
                2 -> "B"
                3 -> "C"
                4 -> "D"
                else -> "E"
            })
            bw.write("\n")
        }
        br.close()
        bw.close()
    }
}

fun main() {
    val c = BOJ_윷놀이()
    c.main()
}

