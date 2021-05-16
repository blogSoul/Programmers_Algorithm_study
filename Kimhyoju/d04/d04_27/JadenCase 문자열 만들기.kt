package d04.d04_27

// 한방에 풀릴 줄 알았는데, 공백을 기준으로 나누면 안되는 걸 알고나서는 한방에는 안풀리는 문제구나 라고 깨달았다.
// 더군다나 코틀린은 스트링이 한번 정해지면 부분문자는 고칠 수 없기에..
// 스트링 빌더를 이용해 푸니 바로 풀림.

fun main() {
    class Solution {
        fun solution(s: String): String {
            var answer = ""
            val sb = StringBuilder()
            var newWord = true
            for (strIdx in s.indices) {
                val ch = s[strIdx]
                if (ch == ' ') {
                    newWord = true
                    answer+=("$sb ")
                    sb.clear()
                } else {
                    if (newWord && Character.isLetter(ch)) {
                        sb.append(ch.toUpperCase())
                    } else {
                        sb.append(ch.toLowerCase())
                    }
                    newWord = false
                }
            }
            answer+=sb
            return answer
        }
    }

    var s = "3people unFollowed me"
    println("solution: ${Solution().solution(s)}")
}
