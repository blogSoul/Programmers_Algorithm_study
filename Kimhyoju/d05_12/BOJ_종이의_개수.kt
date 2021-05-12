package d05_12

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class BOJ_종이의_개수 {
    lateinit var paper: Array<IntArray>
    var minusPapers = 0
    var zeroPapers = 0
    var plusPapers = 0
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val n = br.readLine().toInt()
        paper = Array(n) { IntArray(n) }
        for (i in 0 until n) paper[i] = br.readLine().split(" ").map { it.toInt() }.toIntArray()
        dfs(0, 0, n)
        bw.write("$minusPapers\n")
        bw.write("$zeroPapers\n")
        bw.write("$plusPapers\n")
        br.close()
        bw.close()
    }

    fun dfs(x: Int, y: Int, size: Int) {
        var res = 9
        var allFlag = true
        firstLoop@ for (i in x until x + size) {
            for (j in y until y + size) {
                if (res == 9) res = paper[i][j]
                else if (res != paper[i][j]) {
                    allFlag = false
                    break@firstLoop
                }
            }
        }
        if (allFlag) {
            when (res) {
                -1 -> minusPapers++
                0 -> zeroPapers++
                1 -> plusPapers++
            }
        } else {
            val xList = listOf(0, 0, 0, size / 3 * 1, size / 3 * 1, size / 3 * 1, size / 3 * 2, size / 3 * 2, size / 3 * 2)
            val yList = listOf(0, size / 3 * 1, size / 3 * 2, 0, size / 3 * 1, size / 3 * 2, 0, size / 3 * 1, size / 3 * 2)
            for (i in xList.indices) {
                val nx = x + xList[i]
                val ny = y + yList[i]
                dfs(nx, ny, size / 3)
            }
        }
    }
}

fun main() {
    val c = BOJ_종이의_개수()
    c.main()
}
