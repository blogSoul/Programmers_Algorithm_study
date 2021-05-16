package d05.d05_15

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.sqrt

// Kruskal Algorithm

class BOJ_별자리_만들기 {
    lateinit var root: IntArray
    lateinit var edges: MutableList<Pair<Double, Double>>
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val n = br.readLine().toInt()
        edges = mutableListOf<Pair<Double, Double>>()
        root = IntArray(n + 1) { it }
        for (i in 0 until n) {
            val (x, y) = br.readLine().split(" ").map { it.toDouble() }
            edges.add(Pair(x, y))
        }
        val pq = PriorityQueue<List<Double>>(compareBy({ it.last() }))
        for (i in 0 until n) {
            for (j in i + 1 until n) {
                pq.offer(listOf(i.toDouble(), j.toDouble(), getDistance(i, j)))
            }
        }

        var answer = 0.0
        while (pq.isNotEmpty()) {
            val (s, e, cost) = pq.poll()
            val start = s.toInt()
            val end = e.toInt()
            if (isUnion(start, end)) continue
            union(start, end)
            answer += cost
        }
        bw.write("$answer")

        br.close()
        bw.close()
    }

    fun getDistance(a: Int, b: Int): Double {
        val (x1, y1) = edges[a]
        val (x2, y2) = edges[b]
        val sumX = (x2 - x1) * (x2 - x1)
        val sumY = (y2 - y1) * (y2 - y1)
        return sqrt(sumX + sumY)
    }

    fun union(a: Int, b: Int) {
        val (min, max) = listOf(findUnion(a), findUnion(b)).sorted()
        root[max] = min
    }

    fun isUnion(a: Int, b: Int): Boolean {
        return findUnion(a) == findUnion(b)

    }

    fun findUnion(a: Int): Int {
        if (a == root[a]) return a
        root[a] = findUnion(root[a])
        return root[a]
    }
}

fun main() {
    val c = BOJ_별자리_만들기()
    c.main()
}
