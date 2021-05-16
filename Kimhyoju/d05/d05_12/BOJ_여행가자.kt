package d05.d05_12

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class BOJ_여행가자 {
    lateinit var adj: Array<IntArray>
    lateinit var root: IntArray
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val n = br.readLine().toInt()
        val m = br.readLine().toInt()
        adj = Array(n + 1) { IntArray(n + 1) }
        root = IntArray(n + 1) { it }
        for (i in 1..n) {
            val list = br.readLine().split(" ").map { it.toInt() }
            for (j in list.indices) {
                if (j == 1) union(i, j + 1)
            }
        }
        val tripOrder = br.readLine().split(" ").map { it.toInt() }
        var validFlag = true
        if (tripOrder.size == 1) bw.write("YES")
        else {
            val place = tripOrder.first()
            for (i in tripOrder.indices) {
                if (!isUnion(place, tripOrder[i])) {
                    bw.write("NO")
                    validFlag = false
                    break
                }
            }
        }
        if (validFlag) bw.write("YES")
        br.close()
        bw.close()
    }

    fun union(a: Int, b: Int) {
        val (min, max) = listOf(findUnion(a), findUnion(b)).sorted()
        root[max] = root[min]
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
    val c = BOJ_여행가자()
    c.main()
}

