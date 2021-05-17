package d05.d05_17

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// 위상정렬 문제
class BOJ_줄_세우기 {
    lateinit var incoming: Array<MutableList<Int>>
    lateinit var outgoing: Array<MutableList<Int>>
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val (n, m) = br.readLine().split(" ").map { it.toInt() }
        incoming = Array(n + 1) { mutableListOf<Int>() }
        outgoing = Array(n + 1) { mutableListOf<Int>() }
        for (i in 0 until m) {
            val (a, b) = br.readLine().split(" ").map { it.toInt() }
            incoming[b].add(a)
            outgoing[a].add(b)
        }
        var count = 0
        val visited = BooleanArray(n + 1)
        while (count < n) {
            for (i in 1..n) {
                if (visited[i]) continue
                if (incoming[i].isEmpty()) {
                    visited[i] = true
                    for (node in outgoing[i]) {
                        incoming[node].remove(i)
                    }
                    count++
                    bw.write("$i ")
                }
            }
        }
//        bw.write("$n")
        br.close()
        bw.close()
    }
}

fun main() {
    val c = BOJ_줄_세우기()
    c.main()
}

