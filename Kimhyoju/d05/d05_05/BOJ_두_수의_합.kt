package d05.d05_05

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// 실버 2인데도 틀렸다.
// https://velog.io/@kpg0518/%EB%B0%B1%EC%A4%80-3273%EB%B2%88-%EB%91%90%EC%88%98%EC%9D%98-%ED%95%A9
// 입력을 받아서 sort하기
//두 수의 조합들을 찾기
// 포인터를 양 끝에서부터 시작해서 만나면 멈추게. meet in the middle 비슷한건가.
// 중복이 없으므로, 양 끝에서부터 검사하고 넘어가고 이렇게 해도 됨.

class BOJ_두_수의_합 {
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val n = br.readLine().toInt()
        val numbers = br.readLine().split(" ").map { it.toLong() }.sorted()
        val x = br.readLine().toLong()
        br.close()
        println("numbers: $numbers")
        var answer = 0
        var left = 0
        var right = numbers.lastIndex
        while (left < right) {
            val sum = numbers[left] + numbers[right]
            if (sum > x) right--
            if (sum < x) left++
            else if (sum == x){
                println("right: ${numbers[right]}")
                println("left: ${numbers[left]}")
                right--
                left++
                answer++
            }
        }
        bw.write("$answer")
        bw.close()
    }
}

fun main() {
    val c = BOJ_두_수의_합()
    c.main()
}
