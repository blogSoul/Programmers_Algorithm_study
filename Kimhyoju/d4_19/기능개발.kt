package d4_19

fun main() {
    class Solution {
        fun solution(progresses: IntArray, speeds: IntArray): IntArray {
            var answer = mutableListOf<Int>()
            var deployCount = 0
            var dayCount = 0
            var doneFlag = false
            for ((idx, progress) in progresses.withIndex()) {
                if(progress + dayCount * speeds[idx] < 100) {
                    if (doneFlag) {
                        answer.add(deployCount)
                        deployCount = 0
                    }
                    while (progress + dayCount * speeds[idx] < 100) {
                        dayCount++
                    }
                    doneFlag = true
                }
                deployCount++
            }
            answer.add(deployCount)




            return answer.toIntArray()
        }
    }

    val sol = Solution()
    var progresses = intArrayOf()
    var speeds = intArrayOf()
    progresses = intArrayOf(93,30,55)
    speeds = intArrayOf(1,30,5)
    println("solution: ${sol.solution(progresses, speeds).toList()}")
}
