package d05_07

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class BOJ_한수 {
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val n = br.readLine().toInt()
        val check = BooleanArray(n + 1)
        checkHanSu(n, check)
        for (i in 1..n) {
            if (check[i]) println(i)
        }
        bw.write("${check.count { it }}")
        bw.close()
    }

    fun checkHanSu(n: Int, check: BooleanArray) {
        for (i in 1..n) {
            var validFlag = true
            var diffInit = false
            var diff = 0
            var last = -1
            for (ch in i.toString()) {
                val v = Character.getNumericValue(ch)
                if (last != -1) {
                    if (diffInit) {
                        if (diff != v - last) {
                            validFlag = false
                            break
                        }
                    } else {
                        diffInit = true
                        diff = v - last
                    }
                }
                last = Character.getNumericValue(ch)
            }
            if (validFlag) check[i] = true
        }
    }
}

fun main() {
    val c = BOJ_한수()
    c.main()
}
