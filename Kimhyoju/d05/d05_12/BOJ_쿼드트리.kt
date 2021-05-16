package d05.d05_12

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class BOJ_쿼드트리 {
    lateinit var board: Array<IntArray>
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))
    var n = 0
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        n = br.readLine().toInt()
        board = Array(n) { IntArray(n) }
        for (i in 0 until n) {
            val str = br.readLine()
            for (j in str.indices) {
                board[i][j] = Character.getNumericValue(str[j])
            }
        }
        dfs(0, 0, n)
        br.close()
        bw.close()
    }

    fun dfs(x: Int, y: Int, size: Int) { // x until x+size
        var res = -1
        var allFlag = true
        first@ for (i in x until x + size) {
            for (j in y until y + size) {
                if (res == -1) {
                    res = board[i][j]
                } else if (res != board[i][j]) {
                    allFlag = false
                    break@first
                }
            }
        }
//        println("x: $x, y: $y, size: $size, allFlag: $allFlag")
        if (allFlag) {
            bw.write("$res")
            return
        }
        val xList = listOf(0, 0, size / 2, size / 2)
        val yList = listOf(0, size / 2, 0, size / 2)
        bw.write("(")
        for (i in xList.indices) {
            val nx = x + xList[i]
            val ny = y + yList[i]
            dfs(nx, ny, size / 2)
        }
        bw.write(")")
    }
}

fun main() {
    val c = BOJ_쿼드트리()
    c.main()
}
