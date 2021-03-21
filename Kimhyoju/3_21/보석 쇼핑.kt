import kotlin.math.min

// 처음엔 그저 gemSet을 만들고, map을 만들어
// 각각 가장 큰 인덱스값을 갖게하고난 다음 가장 짧은 거리일때 갱신하고 그 첫 인덱스와 마지막 인덱스를 갖게 하였다.
// 어떻게 보면 투포인터 알고리즘.
// 이렇게 O(NM) 이 되었는데 시간초과가 3개 정도 났다. 그래서 max와 min을 갱신하는 과정을
// for loop 돌떄마다가 아닌 gemMap[gem] < idx 일때, 즉 기존 인덱스보다 큰 인덱스를 만났을 때 갱신하도록 하니까
// 그냥 통과해버렸다... 무슨 아이디어를 쓰는 걸까 인건. 너무 쉽게 풀었네 큰 고민없이.
fun main() {
    class Solution {
        fun solution(gems: Array<String>): IntArray {
            var answer = intArrayOf()
            val gemSet = gems.distinct()
            val gemMap = mutableMapOf<String, Int>()
            for (gem in gemSet) {
                gemMap[gem] = gems.indexOfFirst { it == gem }
            }
            println(gemMap.values)
            var max = gemMap.values.maxOrNull()!!.toInt()
            var min = gemMap.values.minOrNull()!!.toInt()
            var minDist = max - min
            answer = intArrayOf(min + 1, max + 1)
            println("initial minDist: $minDist")
//            answer = gemMap.values.minByOrNull { it }
            for ((idx, gem) in gems.withIndex()) {
                if (gemMap[gem]!! < idx) {
//                    println("modify gemMap[$gem]")
//                    println("gemMap: $gemMap")
                    val before = gemMap[gem]
                    gemMap[gem] = idx
                    if (idx > max) max = idx
                    if (before == min) min = gemMap.values.minOrNull()!!.toInt()
                }
                val dist = max - min
                if (minDist > dist) {
                    minDist = dist
                    answer = intArrayOf(min + 1, max + 1)
                }
            }
            println("final minDIst: $minDist")
            println("gemMap: $gemMap")
            println("${gemMap.values.minOrNull()} ${gemMap.values.maxOrNull()}")
            println("answer: ${answer.toList()}")
            return answer
        }
    }


    val sol = Solution()
    var gems: Array<String>

    gems = arrayOf("DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA")
    println(sol.solution(gems))
}
