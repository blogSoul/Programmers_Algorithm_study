package `4_21`

// 로직을 고민하다가 그리디로 푸니까 한방에 풀렸다. 기도메타 그리디..
import java.lang.Math.abs

fun main() {
    class Solution {
        lateinit var visited: BooleanArray
        fun solution(name: String): Int {
            visited = BooleanArray(name.length)
            var answer = 0
            var count = 0
            var goal = name.count { it != 'A' }
            var curIdx = 0
            while (count < goal) {
                val (minDist,minIdx) = getNearestNotAPos(name, curIdx)
                println("getNearestNotAPos(curIdx: $curIdx): $minDist, $minIdx")
                if (minIdx == curIdx) {
                    count++
                    answer+=getNearestLetter(name[curIdx])
                    visited[curIdx] = true
                } else {
                    curIdx = minIdx
                    answer+=minDist
                }
                println("answer: $answer")
            }
            return answer
        }

        fun getNearestLetter(target: Char): Int {
            // true -> go up, false -> go down
            val up = abs(target.toInt() - 'A'.toInt())
            val down = abs(target.toInt() - 'Z'.toInt()) + 1
            println("target: $target, up: $up, down: $down")
            println("min: ${minOf(up, down)}")
            return minOf(up, down)
        }

        fun getNearestNotAPos(name: String, curPos: Int): Pair<Int,Int> {
            var minDist = Int.MAX_VALUE
            var resIdx = 0
            for ((idx, ch) in name.withIndex()) {
                if (ch != 'A' && !visited[idx]) {
                    val temp = minOf(abs(curPos - idx), curPos + 1 + (name.lastIndex - idx))
                    if (temp < minDist) {
                        minDist = temp
                        resIdx = idx
                    }
                }
            }
            println("minDist: $minDist, resIdx: $resIdx")
            return Pair(minDist,resIdx)
        }
    }

    val sol = Solution()
    var name = ""
    name = "JAN"
    name = "JEROEN"
    name = "BCAAAAAD"
    println("solution: ${sol.solution(name)}")
}