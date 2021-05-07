package d05_07

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

class BOJ_토마토 {
    data class Tomato(val xy: Pair<Int, Int>, val count: Int)

    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val (a, b) = br.readLine().split(" ").map { it.toInt() }
        val box = Array(b) { IntArray(a) }
        val visited = Array(b) { IntArray(a) }
        for (i in 0 until b) {
            box[i] = br.readLine().split(" ").map { it.toInt() }.toIntArray()
        }
        val queue: Queue<Tomato> = LinkedList()
        for (i in 0 until b) {
            for (j in 0 until a) {
                if (box[i][j] == 1) queue.offer(Tomato(Pair(i, j), 0))
            }
        }
        var answer = 0
        while (queue.isNotEmpty()) {
            val (pair, count) = queue.poll()
            val (x, y) = pair
            if (visited[x][y] == 1) continue
            visited[x][y] = 1
            if (box[x][y] == -1) continue
            box[x][y] = 1
            println("x: $x, y: $y")
            // left
            if (y - 1 >= 0 && visited[x][y - 1] != 1) {
                queue.offer(Tomato(Pair(x, y - 1), count + 1))
            }
            // right
            if (y + 1 < a && visited[x][y + 1] != 1) {
                queue.offer(Tomato(Pair(x, y + 1), count + 1))
            }
            // up
            if (x - 1 >= 0 && visited[x - 1][y] != 1) {
                queue.offer(Tomato(Pair(x - 1, y), count + 1))
            }
            // down
            if (x + 1 < b && visited[x + 1][y] != 1) {
                queue.offer(Tomato(Pair(x + 1, y), count + 1))
            }
            answer = count
        }
//        println("visited: ${visited.map { it.toList() }}")
        if (box.any { arr -> arr.any { it == 0 } }) bw.write("-1")
        else bw.write("$answer")
        bw.close()
    }
}

fun main() {
    val c = BOJ_토마토()
    c.main()
}
