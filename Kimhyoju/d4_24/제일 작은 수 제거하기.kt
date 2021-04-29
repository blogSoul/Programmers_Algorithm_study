package d4_24

fun main() {
    class Solution {
        fun solution(arr: IntArray): IntArray {
            var answer = arr.toMutableList()
            val min = arr.min()
            answer.removeAt(answer.indexOf(min))
            return if (answer.isEmpty()) intArrayOf(-1) else answer.toIntArray()
        }
    }

    var arr = intArrayOf(4,3,2,1)
    println("solution: ${Solution().solution(arr)}")
}
