package d05_06

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

class BOJ_스택_수열 {
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val n = br.readLine().toInt()
        val stack = Stack<Int>()
        val list = mutableListOf<Int>()
        val numbers = IntArray(n) { it + 1 }
        val popped = BooleanArray(n)
        var last = 0
        repeat(n) {
            val num = br.readLine().toInt()
            list.add(num)
            popped[num - 1] = true
            if (list.size > 1 && last > num) {
                for (i in num - 1 until last) {
                    if (!popped[i]) {
                        bw.write("NO")
                        br.close()
                        bw.close()
                        return
                    }
                }
            }
            last = num
        }
        var cur = 0
        for (num in list) {
            var top = 0
            if (stack.isNotEmpty()) top = stack.peek()
            while (top != num) {
                stack.push(numbers[cur++])
                top = stack.peek()
                bw.write("+\n")
            }
            stack.pop()
            bw.write("-\n")
        }
        br.close()
        bw.close()
    }
}

fun main() {
    val c = BOJ_스택_수열()
    c.main()
}