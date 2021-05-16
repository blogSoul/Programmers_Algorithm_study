package d05.d05_08

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class BOJ_N_Queen {
    var answer = 0
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val n = br.readLine().toInt()
        val list = mutableListOf<Int>()
        for (i in 0 until n) {
            list.add(i)
            dfs(list, n)
            list.removeAt(list.lastIndex)
        }

        bw.write("$answer")
        br.close()
        bw.close()
    }

    fun dfs(list: MutableList<Int>, n: Int) {
        if (list.size == n) {
            answer++
            return
        }
        for (i in 0 until n) {
            var valid = true
            for (j in list.indices) {
                if (list[j] == i || i == list[j] + (list.size - j) || i == list[j] - (list.size - j)) {
                    valid = false
                    break
                }
            }
            if (valid) {
                list.add(i)
                dfs(list, n)
                list.removeAt(list.lastIndex)
            }
        }
    }
}

fun main() {
    val c = BOJ_N_Queen()
    c.main()
}
