package `4_19`

import kotlin.math.abs
import kotlin.math.sign

fun main() {
    class Solution {
        fun solution(absolutes: IntArray, signs: BooleanArray): Int {
            var answer: Int = 0
            for(i in absolutes.indices) {
                answer += when (signs[i]) {
                    true -> absolutes[i]
                    else -> -absolutes[i]
                }
            }
            return answer
        }
    }
    val sol = Solution()
    var absolutes = intArrayOf()
    absolutes = intArrayOf(4,7,12)
    var signs = booleanArrayOf(true,false,true)
    println("solution: ${sol.solution(absolutes, signs)}")
}
