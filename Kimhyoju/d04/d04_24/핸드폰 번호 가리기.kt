package d04.d04_24

fun main() {
    class Solution {
        fun solution(phone_number: String) = phone_number.mapIndexed { index, c ->  if (index <= phone_number.lastIndex-4) "*" else c}.joinToString("")
    }

    var phone_number = "01033334444"
    println("solution: ${Solution().solution(phone_number)}")
}
