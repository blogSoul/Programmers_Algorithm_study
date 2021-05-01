package d5_01

// 비트연산의 힘
fun main() {
    class Solution {
        fun solution(n: Int, A: Int, B: Int): Int {
            var answer = 0
            var a = A
            var b = B
            while(a != b) {
                answer++
                a = (a+1) shr 1
                b = (b+1) shr 1
                println("answer: $answer a: $a, b: $b")
            }
            return answer
        }
    }

    var n = 8
    var a = 4
    var b = 7
    println("solution: ${Solution().solution(n, a, b)}")
}
