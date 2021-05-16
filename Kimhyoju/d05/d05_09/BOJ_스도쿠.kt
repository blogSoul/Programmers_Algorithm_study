package d05.d05_09

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class BOJ_스도쿠 {
    val board = Array(9) { IntArray(9) }
    val zeros = mutableListOf<Pair<Int, Int>>()
    var exitFlag = false
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))
    fun main() {
        for (i in 0 until 9) {
            val array = br.readLine().split(" ").map { it.toInt() }.toIntArray()
            for (j in 0 until 9) if (array[j] == 0) zeros.add(Pair(i, j))
            board[i] = array
        }
        dfs(0)
        br.close()
        bw.close()
    }

    fun dfs(n: Int) {
        if (exitFlag) return
        if (n == zeros.size) {
            // if check succeeds
            exitFlag = true
            for (i in 0 until 9) {
                for (j in 0 until 9) {
                    bw.write("${board[i][j]} ")
                }
                bw.write("\n")
            }
            return
        }
        val (x, y) = zeros[n]
        for (i in 1 .. 9) {
            if (!checkSquare(x, y, i) || !checkHorizontal(x, y, i) || !checkVertical(x, y, i)) continue
            board[x][y] = i
            dfs(n + 1)
            board[x][y] = 0
        }
    }

    fun checkSquare(x: Int, y: Int, value: Int): Boolean {
        for (i in 3 * (x / 3)..3 * (x / 3) + 2) {
            for (j in 3 * (y / 3)..3 * (y / 3) + 2) {
                if (board[i][j] == value) return false
            }
        }
        return true
    }

    fun checkHorizontal(x: Int, y: Int, value: Int): Boolean {
        for (i in 0 until 9) {
            if (board[x][i] == value) return false
        }
        return true
    }

    fun checkVertical(x: Int, y: Int, value: Int): Boolean {
        for (i in 0 until 9) {
            if (board[i][y] == value) return false
        }

        return true
    }
}

fun main() {
    val c = BOJ_스도쿠()
    c.main()
}
