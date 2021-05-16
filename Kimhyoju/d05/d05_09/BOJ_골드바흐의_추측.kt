package d05.d05_09

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class BOJ_골드바흐의_추측 {
    lateinit var primeArray: IntArray
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val t = br.readLine().toInt()
        repeat(t) {
            val n = br.readLine().toInt()
            makePrimeArray(n)
            var (a,b) = listOf(0,0)
            for (i in 2..n / 2) {
                if (primeArray[i] > 1 && primeArray[n-i] > 1) {
                    a = i
                    b = n-i
                }
            }
            bw.write("$a $b\n")
        }
        br.close()
        bw.close()
    }

    fun makePrimeArray(n: Int) {
        primeArray = IntArray(n + 1) { it }
        for (i in 2..n) {
            if (primeArray[i] == 0) continue
            for (j in 2 * i until primeArray.size step i) {
                if (primeArray[j] % i == 0) primeArray[j] = 0
            }
        }
    }
}

fun main() {
    val c = BOJ_골드바흐의_추측()
    c.main()
}
