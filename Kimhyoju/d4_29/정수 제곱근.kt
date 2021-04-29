package d4_29

import java.lang.Math.pow
import kotlin.math.sqrt

fun main() {
    class Solution {
        fun solution(n: Long): Long {
            val sq = sqrt(n.toDouble())
            return if (sq.toLong() - sq != 0.0) -1 else pow(sq+1, 2.0).toLong()
        }
    }
    var n = 121L
    println("solution: ${Solution().solution(n)}")
}
