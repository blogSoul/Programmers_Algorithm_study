package `4_20`


fun main() {
    class Solution {
        fun solution(s: String): Int {
            var answer: Int = 0
            for (rot in s.indices) if (isValid(rotate(rot, s))) answer++
            return answer
        }

        fun rotate(rot: Int, str: String): String {
            var sb = StringBuilder(str)
            sb.append(str.substring(0, rot))
            val res = sb.removeRange(0, rot)
            println("rot: $rot, res: $res")
            return res.toString()
        }

        fun isValid(str: String): Boolean {
            var stack = mutableListOf<Char>()
            for (s in str) {
                if (stack.isEmpty()) stack.add(s)
                else {
                    when (s) {
                        '}' -> if (stack.last() == '{') stack.removeAt(stack.lastIndex) else return false
                        ')' -> if (stack.last() == '(') stack.removeAt(stack.lastIndex) else return false
                        ']' -> if (stack.last() == '[') stack.removeAt(stack.lastIndex) else return false
                        else -> stack.add(s)
                    }
                }
            }
            return stack.isEmpty()
        }
    }

    val sol = Solution()
    var s = ""
    s = "[](){}"
    println("solution: ${sol.solution(s)}")
}
