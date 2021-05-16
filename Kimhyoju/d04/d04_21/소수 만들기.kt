package d04.d04_21

import kotlin.math.sqrt

fun main() {
    class Solution {
        val nonPrimeMap: MutableMap<Int, Boolean> by lazy { mutableMapOf(1 to false) }
        val primeMap: MutableMap<Int, Boolean> by lazy { mutableMapOf(2 to true, 3 to true) }
        fun solution(nums: IntArray): Int {
            var answer = 0
            for (num1 in 0..nums.lastIndex - 2) {
                for (num2 in num1 + 1..nums.lastIndex - 1) {
                    for (num3 in num2 + 1..nums.lastIndex) {
                        val sum = nums[num1] + nums[num2] + nums[num3]
                        if (isPrimeNumber(sum)) answer++
                    }
                }
            }
            return answer
        }

        fun isPrimeNumber(n: Int): Boolean {
            if (nonPrimeMap.containsKey(n)) return false
            else if (primeMap.containsKey(n)) return true
            for (i in 2..sqrt(n.toDouble()).toInt()) {
                if (n % i == 0) {
                    nonPrimeMap[n] = false
                    return false
                }
            }
            primeMap[n] = true
            return true
        }
    }

    val sol = Solution()
    var nums = intArrayOf(1, 2, 3, 4)
    println("solution: ${sol.solution(nums)}")
}
