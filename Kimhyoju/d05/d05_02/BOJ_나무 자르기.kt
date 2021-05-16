package d05.d05_02

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// Parametric Search
class BOJ_나무자르기 {
    lateinit var trees: MutableList<Long>
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val (n, m) = br.readLine().split(" ").map { it.toLong() }
        trees = br.readLine().split(" ").map { it.toLong() }.sorted().toMutableList()
        br.close()
        var answer = 0L
        var left = 0L
        var right = trees.last()
        while (left <= right) {
            val mid = (left + right) / 2
            val max = getHeight(mid)
            println("left: $left, right: $right, mid: $mid")
            println("max: $max")
            if (max >= m) {
                answer = mid
                left = mid + 1
            }
            else if (max < m) right = mid - 1
        }
        bw.write("$answer")
        bw.close()
    }

    fun getHeight(n: Long): Long {
        var sum = 0L
        trees.forEach { if (it > n) sum += it - n }
        println("n: $n")
        println("sum: $sum")
        return sum
    }

}

fun main() {
    val c = BOJ_나무자르기()
    c.main()
}
