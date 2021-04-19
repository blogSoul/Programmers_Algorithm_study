fun main() {
    class Solution {
        fun solution(s: String): Int {
            var answer = s.length
            for (unit in 1 until s.length) {
                println("unit: $unit")
                var sb = StringBuilder()
                var prep = s.substring(0,unit)
                var prepCount = 0
                for (idx in 0 until s.length step unit) {
                    if (idx+unit > s.length) {
                        if (prepCount > 1) {
                            sb.append(prepCount)
                            sb.append(prep)
                        }
                        else {
                            sb.append(prep)
                        }
                        sb.append(s.substring(idx,s.length))
                        break
                    }
                    else if (s.substring(idx,idx+unit) == prep) {
                        prepCount++
                    }
                    else{
                        if (prepCount > 1) {
                            sb.append(prepCount)
                            sb.append(prep)
                        }
                        else {
                            sb.append(prep)
                        }
                        prep = s.substring(idx,idx+unit)
                        prepCount = 1
                    }
                    if (idx+unit == s.length) {
                        if (prepCount > 1) {
                            sb.append(prepCount)
                            sb.append(prep)
                        }
                        else {
                            sb.append(prep)
                        }
                    }
                }
                println("sb: $sb length: ${sb.length}")
                if (answer > sb.length) answer = sb.length
            }
            return answer
        }
    }
    val sol = Solution()
    var s = "aabbaccc"
    s = "xababcdcdababcdcd"
    s = "abcabcabcabcdededededede"
    println(sol.solution(s))
}