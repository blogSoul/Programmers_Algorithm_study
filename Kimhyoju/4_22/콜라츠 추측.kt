package `4_22`

import Solution

fun main() {
    class Solution {
        fun solution(num: Int): Int {
            if (num == 1) return 0
            var temp = num.toLong()
            for (i in 1..500) {
                if (temp % 2 == 0L) temp /= 2
                else temp = temp * 3 + 1
                if (temp == 1L) return i
            }
            return -1
        }
    }

    val sol = Solution()
    var num = 6
    num = 626331
    println("solution: ${sol.solution(num)}")
}
