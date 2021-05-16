package d05.d05_07

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

// 방향벡터
// int dx = [0, 0,  1, -1]
// int dy = [1, -1, 0,  0]
//
//# 현재 위치
//int x, y= 1;
//
//for (int i = 0; i < 4; i++) {
//    int nx = x + dx[i];
//    int ny = y + dy[i];
//
//    System.out.println(nx, ny);
//}

class BOJ_토마토3D {
    data class Tomato(val xyz: List<Int>, val count: Int)

    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val (a, b, c) = br.readLine().split(" ").map { it.toInt() }
        val box = Array(c) { Array(b) { IntArray(a) } }
        val visited = Array(c) { Array(b) { IntArray(a) } }
        for (i in 0 until c) {
            for (j in 0 until b) {
                box[i][j] = br.readLine().split(" ").map { it.toInt() }.toIntArray()
            }
        }
        val queue: Queue<Tomato> = LinkedList()
        for (i in 0 until c) {
            for (j in 0 until b) {
                for (k in 0 until a) {
                    if (box[i][j][k] == 1) queue.offer(Tomato(listOf(i, j, k), 0))
                }
            }
        }
        var answer = 0
        while (queue.isNotEmpty()) {
            val (xyzList, count) = queue.poll()
            val (z, y, x) = xyzList
            if (visited[z][y][x] == 1) continue
            visited[z][y][x] = 1
            if (box[z][y][x] == -1) continue
            box[z][y][x] = 1
            val zList = listOf(1, -1, 0, 0, 0, 0)
            val yList = listOf(0, 0, 1, -1, 0, 0)
            val xList = listOf(0, 0, 0, 0, 1, -1)
            for (i in zList.indices) {
                val nz = z + zList[i]
                val ny = y + yList[i]
                val nx = x + xList[i]
                if (nz in 0 until c && ny in 0 until b && nx in 0 until a && visited[nz][ny][nx] != 1) {
                    queue.offer(Tomato(listOf(nz, ny, nx), count + 1))
                }
            }
            if (answer < count) answer = count
        }
        println(visited.map{it.map{arr -> arr.toList()}})
        if (box.any { arr1 -> arr1.any { arr2 -> arr2.any { it == 0 } } }) bw.write("-1")
        else bw.write("$answer")
        bw.close()
    }
}

fun main() {
    val c = BOJ_토마토3D()
    c.main()
}
