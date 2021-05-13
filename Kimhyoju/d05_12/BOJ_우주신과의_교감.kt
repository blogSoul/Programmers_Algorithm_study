package d05_12

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.abs
import kotlin.math.sqrt

class BOJ_우주신과의_교감 {
    lateinit var root: IntArray
    val MAX_VALUE = Int.MAX_VALUE.toDouble()

    data class Edge(val a: Int, val b: Int, val distance: Double)

    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val (n, m) = br.readLine().split(" ").map { it.toInt() }
        root = IntArray(n + 1) { it }
        val gods = Array(n + 1) { Pair(0L, 0L) }
        for (i in 1..n) {
            val (x, y) = br.readLine().split(" ").map { it.toLong() }
            gods[i] = Pair(x, y)
        }
        var answer = 0.0
        val edges = mutableListOf<Edge>()
        for (i in 1..n) {
            for (j in 1..n) {
                val distance = getDistance(gods[i], gods[j])
                edges.add(Edge(i, j, distance))
            }
        }
        edges.sortBy { it.distance }
        for (i in 0 until m) {
            val (a, b) = br.readLine().split(" ").map { it.toInt() }
            union(a, b)
        }
        for ((a, b, distance) in edges) {
            if (isUnion(a, b)) continue
            answer += distance
            union(a, b)
        }
        bw.write(String.format("%.2f", answer))
        br.close()
        bw.close()
    }

    fun getDistance(a: Pair<Long, Long>, b: Pair<Long, Long>): Double {
        val dx = abs(a.first - b.first)
        val dy = abs(a.second - b.second)
        val res = dx * dx + dy * dy
        return sqrt(res.toDouble())
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
    val c = BOJ_우주신과의_교감()
    c.main()
}
