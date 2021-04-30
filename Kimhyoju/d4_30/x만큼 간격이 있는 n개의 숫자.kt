package d4_30

fun main() {
    class Solution {
        fun solution(x: Int, n: Int): LongArray {
            var answer = mutableListOf<Long>()
            for (i in 1..n) answer.add(x.toLong()*i.toLong())
            return answer.toLongArray()
        }
    }

    var x = 2
    var n = 5
//    x = 4
//    n = 3
    println("solution: ${Solution().solution(x, n)}")
}
