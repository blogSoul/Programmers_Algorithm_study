package d04_24

fun main() {
    class Solution { fun solution(n: Long) = n.toString().toList().sortedDescending().joinToString("").toLong() }

    var n = 118372L
    println("solution: ${Solution().solution(n)}")
}
