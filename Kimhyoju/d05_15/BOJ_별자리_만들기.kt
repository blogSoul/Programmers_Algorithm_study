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

class BOJ_별자리_만들기 {
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val n = br.readLine().toInt()
        br.close()
        bw.close()
    }
}

fun main() {
    val c = BOJ_별자리_만들기()
    c.main()
}
