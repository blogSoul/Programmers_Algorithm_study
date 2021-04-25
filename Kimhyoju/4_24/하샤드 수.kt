package `4_24`

fun main() {
    class Solution {
        fun solution(x: Int): Boolean {
            val sum = x.toString().toList().sumBy { Character.getNumericValue(it) }
            return x % sum == 0
        }
    }

    var x = 10
    println("solution: ${Solution().solution(x)}")
}
