package d05.d05_07

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

class BOJ_나이트의_이동 {
    data class Location(val xy: Pair<Int, Int>, val count: Int)

    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val t = br.readLine().toInt()
        repeat(t) {
            val l = br.readLine().toInt()
            val (curX, curY) = br.readLine().split(" ").map { it.toInt() }
            val (nextX, nextY) = br.readLine().split(" ").map { it.toInt() }
            val visited = Array(l) { IntArray(l) }
            val queue: Queue<Location> = LinkedList()
            queue.offer(Location(Pair(curX, curY), 0))
            var answer = 0
            while (queue.isNotEmpty()) {
                val (xy, count) = queue.poll()
                val (x, y) = xy
                if (x == nextX && y == nextY) {
                    answer = count
                    break
                }
                if (visited[y][x] == 1) continue
                visited[y][x] = 1
                val xList = listOf(-1, -1, -2, -2, 1, 1, 2, 2)
                val yList = listOf(2, -2, 1, -1, 2, -2, 1, -1)
                for (i in xList.indices) {
                    val nx = x + xList[i]
                    val ny = y + yList[i]
                    if (nx in 0 until l && ny in 0 until l && visited[ny][nx] != 1) {
                        queue.offer(Location(Pair(nx, ny), count + 1))
                    }
                }
            }
            println("$answer")
        }
        bw.close()
    }
}

fun main() {
    val c = BOJ_나이트의_이동()
    c.main()
}
