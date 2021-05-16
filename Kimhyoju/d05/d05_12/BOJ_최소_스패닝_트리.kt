package d05.d05_12

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class BOJ_최소_스패닝_트리 {
    lateinit var root: IntArray
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val (v, e) = br.readLine().split(" ").map { it.toInt() }
        root = IntArray(v + 1){it}
        val edges = mutableListOf<List<Int>>()
        var answer = 0
        for (i in 0 until e) {
            val (a, b, c) = br.readLine().split(" ").map { it.toInt() }
            edges.add(listOf(a, b, c))
        }
        edges.sortBy { it.last() }
        for ((a, b, cost) in edges) {
            if (isUnion(a, b)) {
                continue
            }
            union(a, b)
            answer += cost
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
    val c = BOJ_최소_스패닝_트리()
    c.main()
}
