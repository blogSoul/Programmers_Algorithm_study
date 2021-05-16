package d05.d05_06

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

class BOJ_스택 {
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val n = br.readLine().toInt()
        val stack = Stack<Int>()
        repeat(n) {
            val order = br.readLine().split(" ")
            when (order[0]) {
                "push" -> {
                    stack.push(order[1].toInt())
                }
                "pop" -> {
                    if (stack.isNotEmpty()) bw.write("${stack.pop()}\n")
                    else bw.write("-1\n")
                }
                "size" -> {
                    bw.write("${stack.size}\n")
                }
                "empty" -> {
                    if (stack.isEmpty()) bw.write("1\n")
                    else bw.write("0\n")
                }
                "top" -> {
                    if (stack.isNotEmpty()) bw.write("${stack.peek()}\n")
                    else bw.write("-1\n")
                }
            }
        }
        bw.close()
    }
}

fun main() {
    val c = BOJ_스택()
    c.main()
}
