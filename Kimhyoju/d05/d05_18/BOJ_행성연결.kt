package d05.d05_18

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

// MST. Kruskal Algorithm

class BOJ_행성연결 {
    lateinit var root: IntArray
    lateinit var costs: Array<IntArray>
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val n = br.readLine().toInt()
        root = IntArray(n + 1) { it }
        costs = Array(n) { IntArray(n) }
        for (i in 0 until n) {
            costs[i] = br.readLine().split(" ").map { it.toInt() }.toIntArray()
        }
        val pq = PriorityQueue<List<Int>>(compareBy { it.last() })
        for (i in 1..n) {
            for (j in i + 1..n) {
                pq.offer(listOf(i, j, costs[i-1][j-1]))
            }
        }
        var answer = 0L
        while (pq.isNotEmpty()) {
            val (a, b, cost) = pq.poll()
            if (isUnion(a, b)) continue
            union(a, b)
            answer += cost.toLong()
        }
        bw.write("$answer")
        br.close()
        bw.close()
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
    val c = BOJ_행성연결()
    c.main()
}

