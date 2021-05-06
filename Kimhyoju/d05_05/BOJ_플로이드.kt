package d05_05

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class BOJ_플로이드 {
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val n = br.readLine().toInt()
        val m = br.readLine().toInt()
        val dist = Array(n + 1) { IntArray(n + 1) { Int.MAX_VALUE } }
        dist.forEachIndexed { idx, arr -> arr[idx] = 0 }
        // 윗줄은 생략하면, 사이클을 찾을 수 있다. (음의 사이클은 안됨.)
        for (mCnt in 0 until m) {
            val (a, b, c) = br.readLine().split(" ").map { it.toInt() }
            dist[a][b] = minOf(dist[a][b],c)
        }
        br.close()

        for (mid in 1..n) {
            for (start in 1..n) {
                for (end in 1..n) {
                    if (dist[start][mid] != Int.MAX_VALUE && dist[mid][end] != Int.MAX_VALUE) {
                        if (dist[start][end] > dist[start][mid] + dist[mid][end]) {
                            dist[start][end] = dist[start][mid] + dist[mid][end]
                        }
                    }
                }
            }
        }
        for (i in 1..n) {
            for (j in 1..n) {
                if (dist[i][j] == Int.MAX_VALUE) bw.write("0 ")
                else bw.write("${dist[i][j]} ")
            }
            bw.write("\n")
        }
        bw.close()
    }
}

fun main() {
    val c = BOJ_플로이드()
    c.main()
}
