package d05.d05_12

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class BOJ_집합의_표현 {
    lateinit var root: IntArray
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val (n, m) = br.readLine().split(" ").map { it.toInt() }
        root = IntArray(n + 1) { it }
        repeat(m) {
            val (op, a, b) = br.readLine().split(" ").map { it.toInt() }
            when (op) {
                0 -> {
                    // 주의! union(a,b) 가 아니라, findUnion 을 해줘야한다!
                    union(findUnion(a), findUnion(b))
                }
                1 -> {
                    bw.write(if (isUnion(a, b)) "YES\n" else "NO\n")
                }
            }
        }

        br.close()
        bw.close()
    }

    fun isUnion(a: Int, b: Int): Boolean {
        return findUnion(a) == findUnion(b)
    }

    fun union(a: Int, b: Int) {
        if (a > b) root[a] = findUnion(b)
        else root[b] = findUnion(a)
    }

    fun findUnion(a: Int): Int {
        if (a == root[a]) return a
        root[a] = findUnion(root[a])
        return root[a]
    }
}

fun main() {
    val c = BOJ_집합의_표현()
    c.main()
}