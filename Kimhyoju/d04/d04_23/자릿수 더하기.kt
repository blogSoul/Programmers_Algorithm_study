package d04.d04_23

fun main() {
    class Solution {
        fun solution(n: Int): Int {
            var answer = 0
            var temp = n
            answer += temp % 10
            while (temp >= 10) {
                temp /= 10
                answer += temp % 10
            }
            return answer
        }
    }

    val sol = Solution()
    var n = 123
    n = 1200000
    n = 987
    n = 10
    println("solution: ${sol.solution(n)}")
}
