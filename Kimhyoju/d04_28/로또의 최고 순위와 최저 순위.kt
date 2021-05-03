package d04_28

fun main() {
    class Solution {
        fun solution(lottos: IntArray, win_nums: IntArray): IntArray {
            val lotto = lottos.toMutableList()
            lotto.removeAll(lottos.intersect(win_nums.toList()))
            // lottos.subtract(win_nums.toList()) -> 이렇게 하면 안된다. set으로 변하기에, 0 2개가 1개로 줄어듦.
            val min = lottos.size - lotto.size
            val max = min + lotto.count{it == 0}
            return intArrayOf(getRank(max),getRank(min))
        }
        fun getRank(n: Int) = when(n) {
            2 -> 5
            3 -> 4
            4 -> 3
            5 -> 2
            6 -> 1
            else -> 6
        }
    }

    var lottos = intArrayOf(44, 1, 0, 0, 31, 25)
    var win_nums = intArrayOf(31, 10, 45, 1, 6, 19)
    println("solution: ${Solution().solution(lottos, win_nums)}")
}
