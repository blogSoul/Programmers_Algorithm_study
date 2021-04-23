package `4_23`

import Solution

fun main() {
    class Solution {
        fun solution(n: Long): IntArray {
            var answer = mutableListOf<Int>()
            var temp = n
            answer.add((temp%10).toInt())
            while (temp >= 10) {
                temp /= 10
                answer.add((temp%10).toInt())
            }
            println("answer: $answer")
            return answer.toIntArray()
        }
    }

    val sol = Solution()
    var n = 12345L
    println("solution: ${sol.solution(n)}")
}
