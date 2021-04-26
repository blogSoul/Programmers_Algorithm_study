package `4_26`

// 슬라이딩 윈도우 투포인터로 getBeauty를 구현했다.
// 그리고 부분문자열 (연속)을 찾는 건 슬라이딩 윈도우로.
fun main() {
    class Solution {
        fun solution(s: String): Long {
            if (s.toSet().size == 1) return 0L
            var answer: Long = 0
            for (wSize in 2..s.length) {
                for (idx in s.indices) {
                    if (idx+wSize > s.length) break
                    val subStr = s.substring(idx,idx+wSize)
                    answer += getBeauty(subStr)
                }
            }
            return answer
        }

        fun getBeauty(str: String): Long {
            if (str.toSet().size == 1) return 0L
            if (str.length == 2) return 1L
            var res = 0L
            var max = str.length.toLong() - 1
            for (start in str.indices) {
                for (end in str.lastIndex downTo start) {
                    if (str[start] != str[end]) {
                        val temp = end - start
                        if (res < temp) res = temp.toLong()
                        break
                    }
                }
                max--
                if (res >= max) break
            }
            println("getBeauty of $str: $res")
            return res
        }
    }
    var s = "baby"
    println("solution: ${Solution().solution(s)}")
}
