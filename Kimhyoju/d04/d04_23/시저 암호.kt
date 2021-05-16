package d04.d04_23

fun main() {
    class Solution {
        fun solution(s: String, n: Int): String {
            var answer = StringBuilder()
            for (ch in s) {
                if (ch == ' ') {
                    answer.append(" ")
                    continue
                }
                var changedCh = ch + n
                if (Character.isUpperCase(ch)) {
                    if (!Character.isUpperCase(changedCh)) {
                        changedCh -=26
                    }

                } else {
                    if (!Character.isLowerCase(changedCh)) {
                        changedCh -=26
                    }
                }
//                if (!Character.isLetter(changedCh)) {
//                    changedCh -= 26
//                }
                println("$ch -> $changedCh")
                answer.append(changedCh)
            }
            return answer.toString()
        }
    }

    var s = "AB"
//    s = "z"
    s = "aBcDeFg C"
    s = "Z"
    s = "AaZz"
    var n = 25
//    n = 2
    println("solution: ${Solution().solution(s, n)}")
}
