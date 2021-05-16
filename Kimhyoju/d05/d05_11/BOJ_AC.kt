package d05.d05_11

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

// 일단 Dequeue로 구현했는데 시간초과가 뜨네 흠..
// R을 할때, 순서를 그대로 뒤집는게 아니라, 2개의 함수를 flip-flop으로.
// 즉, R을 하면 실제로 뭔가 작업을 하는게 아니라, D에 쓰이는 함수를 바꾼다.
// 원래 D는 deque.pollFirst() 인데, R이 눌리면 pollLast()로 스위치.

// 시간초과는 안나는데 틀리네 흠... 뭐가 문제지? 질문 검색해봄.

class BOJ_AC {
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val t = br.readLine().toInt()
        for (i in 0 until t) {
            val p = br.readLine()
            var n = br.readLine().toInt()
            val readline = br.readLine().drop(1).dropLast(1)
            if (readline == "") {
                if ('D' in p) bw.write("error\n")
                else bw.write("[]")
                continue
            }
            val array = readline.split(",").map { it.toInt() }
            println("array: $array")
            var deque: Deque<Int> = LinkedList<Int>()
            for (el in array) deque.offer(el)
            var errorFlag = false
            var reverseFlag = false
            init@ for (cmd in p) {
                when (cmd) {
                    'R' -> {
                        reverseFlag = !reverseFlag
                    }
                    'D' -> {
                        if (n == 0) {
                            errorFlag = true
                            break@init
                        }
                        val removed = if (reverseFlag) deque.pollLast() else deque.pollFirst()
                        n--
                    }
                }
            }
            if (errorFlag) bw.write("error\n")
            else if (n == 0) bw.write("[]\n")
            else {
                bw.write("[")
                while (deque.size > 1) {
                    bw.write("${if (reverseFlag) deque.pollLast() else deque.pollFirst()},")
                }
                bw.write("${if (reverseFlag) deque.pollLast() else deque.pollFirst()}]\n")
            }
        }
        br.close()
        bw.close()
    }
}

fun main() {
    val c = BOJ_AC()
    c.main()
}

