package d05.d05_18

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class BOJ_패션왕_신해빈 {
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val t = br.readLine().toInt()
        repeat(t) {
            val n = br.readLine().toInt()
            val clothesMap = mutableMapOf<String, Int>()
            for (i in 0 until n) {
                val clothes = br.readLine().split(" ")
                clothesMap[clothes.last()] = clothesMap.getOrDefault(clothes.last(), 0) + 1
            }
            var sum = 1
            for (value in clothesMap.values) sum *= (value + 1)
            bw.write("${sum - 1}\n")
        }
        br.close()
        bw.close()
    }
}

fun main() {
    val c = BOJ_패션왕_신해빈()
    c.main()
}
