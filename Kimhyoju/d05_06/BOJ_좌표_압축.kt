package d05_06

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

class BOJ_좌표_압축 {
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val n = br.readLine().toInt()
        val list = br.readLine().split(" ").map { it.toInt() }
        val set = list.toSet()
        val pq = PriorityQueue<Int>()
        for (i in set) pq.offer(i)
        val map = mutableMapOf<Int, Int>()
        var count = 0
        while (pq.isNotEmpty()) {
            map[pq.poll()] = count
            count++
        }
        for (num in list) {
            bw.write("${map[num]} ")
        }
        bw.close()
    }
}

fun main() {
    val c = BOJ_좌표_압축()
    c.main()
}
