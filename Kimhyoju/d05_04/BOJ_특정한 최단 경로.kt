package d05_04

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

class BOJ_특정한_최단_경로 {
    lateinit var adj: Array<MutableList<Pair<Int, Int>>>
    lateinit var visited: BooleanArray
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val (v, e) = br.readLine().map { it.toInt() }
        adj = Array(v) { mutableListOf<Pair<Int, Int>>() }
        visited = BooleanArray(v)
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
        for (i in 0 until e) {
            val (a, b, w) = br.readLine().map { it.toInt() }
            adj[a].add(Pair(b, w))
            adj[b].add(Pair(a, w))
        }
        val (x,y) = br.readLine().map { it.toInt() }
        br.close()

        bw.write("$v")
        bw.close()
    }
}

fun main() {
    val c = BOJ_특정한_최단_경로()
    c.main()
}
