package d05.d05_09

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class BOJ_베르트랑_공준 {
    lateinit var primeArray: IntArray
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        var n = br.readLine().toInt()
        while (n != 0) {
            makePrimeArray(n)
            println("${primeArray.toList()}")
            val count = primeArray.filterIndexed { index, i -> index > n && i > 1 }.size
            bw.write("$count\n")
            n = br.readLine().toInt()
        }
        br.close()
        bw.close()
    }

    fun makePrimeArray(n: Int) {
        primeArray = IntArray(2 * n + 1) { it }
        for (i in 2..2 * n) {
            if (primeArray[i] == 0) continue
            for (j in 2 * i until primeArray.size step i) {
                if (primeArray[j] % i == 0) primeArray[j] = 0
            }
        }
    }
}

fun main() {
    val c = BOJ_베르트랑_공준()
    c.main()
}
