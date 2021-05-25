package d05.d05_24


import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class BOJ_세_수 {
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val list = br.readLine().split(" ").map { it.toInt() }
        bw.write("${list.sorted()[1]}")
        br.close()
        bw.close()
    }
}

fun main() {
    val c = BOJ_세_수()
    c.main()
}

