package d4_23

fun main() {
    class Solution {
        fun solution(s: String): String {
            var sList = mutableListOf<StringBuilder>()
            var words = s.split(" ")
            for (word in words) {
                var sb = StringBuilder()
                for ((idx,ch) in word.withIndex()) {
                    if (idx % 2 ==0) sb.append(ch.toUpperCase())
                    else sb.append(ch.toLowerCase())
                }
                sList.add(sb)
            }
            return sList.joinToString(" ")
        }
    }
    var s = "try hello world"
//    s = "try"
    println("solution: ${Solution().solution(s)}")
}
