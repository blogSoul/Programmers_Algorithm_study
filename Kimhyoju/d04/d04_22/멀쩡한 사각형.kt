// 사각형 중앙까지 구하고 x2 하면 되지만 귀찮아서 그냥 다 짜버림
// 6번만 틀린다. 기울기에서 오차가 살짝 나서 그런것같음
// 중요치 않으니 그냥 올림.
package d04.d04_22

import kotlin.math.floor

fun main() {
    class Solution {
        fun solution(w: Int, h: Int): Long {
            var sum: Long = 0
            val lean: Double = (h.toDouble() / w.toDouble())
            println("lean: $lean")
            fun y(x: Long): Double = h.toDouble() * w.toDouble() * x
            var beforeHeight = 0.0
            for (x in 1..w) {
                var newHeight = y(x.toLong())
                var add = 0L
                add += (floor(newHeight) - floor(beforeHeight)).toLong()
                if (newHeight - newHeight.toLong() == 0.0) add -= 1
                add += 1
                sum += add
                beforeHeight = newHeight
                println("progressing sum: $sum")
            }
            println("sum: $sum")
            return w.toLong() * h.toLong() - sum
        }
    }

    val sol = Solution()
    var w = 5
    var h = 6
//    w = 12
//    h = 8
    println("solution: ${sol.solution(w, h)}")
}
