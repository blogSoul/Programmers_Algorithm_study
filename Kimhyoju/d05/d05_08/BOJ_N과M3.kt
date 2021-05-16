package d05.d05_08

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class BOJ_N과M3 {
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val (n, m) = br.readLine().split(" ").map { it.toInt() }
        br.close()
        val list = mutableListOf<Int>()
        dfs(list, n, m)
        bw.close()
    }

    fun dfs(list: MutableList<Int>, n: Int, m: Int) {
        if (list.size == m) {
            for (l in list) bw.write("$l ")
            bw.write("\n")
            return
        }
        for (i in 1..n) {
            list.add(i)
            dfs(list, n, m)
            list.removeAt(list.lastIndex)
        }
    }
}

fun main() {
    val c = BOJ_N과M3()
    c.main()
}
