package d04.d04_24
fun main() {
    class Solution { fun solution(num: Int) = if (num%2 == 0) "Even" else "Odd" }

    var num = 3
    println("solution: ${Solution().solution(num)}")
}

