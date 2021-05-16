package boilerplate

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class Boiler {
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val n = br.readLine().toInt()

        bw.write("$n")
        br.close()
        bw.close()
    }
}

fun main() {
    val c = Boiler()
    c.main()
}