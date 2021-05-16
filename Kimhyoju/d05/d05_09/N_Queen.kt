package d05.d05_09

fun main() {
    class Solution {
        lateinit var queens: MutableList<Int>
        var answer = 0
        fun solution(n: Int): Int {
            queens = mutableListOf()
            dfs(n)
            return answer
        }

        fun dfs(n: Int) {
            if (queens.size == n) {
                println("queens: $queens")
                answer++
                return
            }
            for (i in 0 until n) {
                var validFlag = true
                for (j in queens.indices) {
                    val diff = queens.size - j
                    if (i == queens[j] || queens[j] + diff == i || queens[j] - diff == i) {
                        validFlag = false
                        break
                    }
                }
                if (!validFlag) continue
                queens.add(i)
                dfs(n)
                queens.removeAt(queens.lastIndex)
            }
        }
    }

    var n = 4
    println("solution: ${Solution().solution(n)}")
}

