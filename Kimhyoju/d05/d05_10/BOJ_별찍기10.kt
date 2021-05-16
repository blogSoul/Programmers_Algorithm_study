package d05.d05_10

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// 문제 이해하고, 파악한 법칙 적용하는 데에 오래걸림.

class BOJ_별찍기10 {
    lateinit var stars: Array<CharArray>
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val n = br.readLine().toInt()
        br.close()
        stars = Array(n) { CharArray(n) }
        dfs(n, 0, 0)
        for (i in stars.indices) {
            stars[i].forEach { bw.write("$it") }
            bw.write("\n")
        }
        bw.close()
    }

    fun dfs(n: Int, x: Int, y: Int) {
        println("dfs n: $n, x:$x y: $y")
        if (n == 3) {
            for (i in 0 until 3) {
                stars[x][y + i] = '*'
            }
            stars[x + 1][y] = '*'
            stars[x + 1][y + 1] = ' '
            stars[x + 1][y + 2] = '*'
            for (i in 0 until 3) {
                stars[x + 2][y + i] = '*'
            }
            return
        }
        for (i in 0 until 3) {
            dfs(n / 3, x, y + n / 3 * (i))
//            dfs(n / 3, n / 3 * x, y + n / 3 * (i))
        }
//        dfs(n / 3, x+1 + n / 3, n / 3 * (y + i))
        dfs(n / 3, x + n / 3, y)
        for (i in x + n / 3 until x + n / 3 * 2) {
            for (j in y + n / 3 until y + (n / 3) * 2) {
                stars[i][j] = ' '
            }
        }
        dfs(n / 3, x + n / 3, y + n / 3 * 2)
//        dfs(n / 3, x + 1 + n / 3, y + 2 + n / 3)
        for (i in 0 until 3) {
            dfs(n / 3, x + n / 3 * 2, y + (n / 3) * (i))
//            dfs(n / 3, n / 3 * (x + 2), n / 3 * (y + i))
        }
    }
}

fun main() {
    val c = BOJ_별찍기10()
    c.main()
}
