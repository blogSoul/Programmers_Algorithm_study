fun main() {
    class Solution {
        fun solution(inp_str: String): IntArray {
            var answer: IntArray = intArrayOf()
            var resultSet = mutableSetOf<Int>()

            // 1
            if (inp_str.length !in 8..15) resultSet.add(1)

            // 2 and 4
            var set = mutableSetOf<Int>()
            var lastCh = ' '
            var count = 1
            for (ch in inp_str) {
                if (ch.toInt() in 65..90) {
                    set.add(1)
                } else if (ch.toInt() in 97..122) {
                    set.add(2)
                } else if (Character.getNumericValue(ch) in 0..9) {
                    set.add(3)
                } else if (ch == '~' || ch == '!' || ch == '@' || ch == '#'
                    || ch == '$' || ch == '%' || ch == '^' || ch == '&' || ch == '*'
                ) {
                    set.add(4)
                } else resultSet.add(2)

                if (lastCh == ch) count++
                else {
                    count = 1
                    lastCh = ch
                }
                if (count >= 4) {
                    resultSet.add(4)
                }
            }

            // 3
            if (set.size < 3) resultSet.add(3)

            // 5
            val charArr = inp_str.toCharArray().sorted()
            lastCh = ' '
            count = 1
            for (char in charArr) {
                if (char == lastCh) count++
                else {
                    count = 1
                    lastCh = char
                }

                if (count >= 5) {
                    resultSet.add(5)
                    break
                }
            }
            if (resultSet.size == 0) resultSet.add(0)
            answer = resultSet.sorted().toIntArray()
            return answer
        }
    }


    val sol = Solution()
    var inp_str = "AaTa+!12-3"
    println(sol.solution(inp_str))
    println('2'.toInt())
    println('A'.toInt())
    println('a'.toInt())
}

