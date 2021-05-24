package d05.d05_19

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class BOJ_찾기 {
    lateinit var pi: IntArray // failure function
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val t = br.readLine()
        val p = br.readLine()
        pi = IntArray(p.length)
        makeFailure(p, pi)

        br.close()
        bw.close()
    }

    fun makeFailure(p: String, pi: IntArray) {
        for (i in p.indices) {
            for (j in p.indices) {
            }
        }
    }
}

fun main() {
    val c = BOJ_찾기()
    c.main()
}
