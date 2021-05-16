// 가장 긴 팰린드롬
fun main() {
    val sol = Solution5()
    var s: String
    s = "abacde"
    s = "abcdcba"
    s = "abcdefggfedcba"
    s = "abcdefgfedcba"
    s = "abcdedcba"
    s = "a"
    sol.solution(s)
}

class Solution5 {
    fun solution(s: String): Int {
        var answer = 1
        // 1. 일단 완전탐색으로 해본다. 생각해봐도 뾰족한 수가 보이지 않아서..
        // 효율성도 통과함.
        for (idx1 in 0 until s.lastIndex) {
            for (idx in idx1 + 1 until s.length) {
//                println("idx1: $idx1 idx: $idx")
                if (idx - idx1 + 1 > answer) {
                    if (isPalindrome(s, idx1, idx)) {
                        answer = idx - idx1 + 1
//                        println("answer: $answer")
                    }
                }
            }
        }
        println("Final Answer: $answer")
        return answer
    }

    fun isPalindrome(s: String, start: Int, end: Int): Boolean {
        var count = 0
        var successFlag = false
        while (s[start + count] == s[end - count]) {
            if (start + count >= end - count) {
                successFlag = true
                println("result: ${s.substring(start..end)}")
                break
            }
            count++
        }
        return successFlag
    }
}
