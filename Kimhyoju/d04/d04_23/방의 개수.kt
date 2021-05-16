package d04.d04_23

// 그래프 문제인 방의 개수.
// 점들로 이루어진 공간에서 선을 여러개 그으면
// 방이 몇개가 생기는지 묻는 문제.
// 아무리 생각해도 제대로 된 해결책이 떠오르지 않아
// 구글링을 해봤다.
// 일단 방문한 점을 다시 방문하면 방이 생기는 건 알겠으나..

//  찾아보니 어떤 사람은, 표시될 수 없는 정점, 즉, Z 모양의 간선에서
// 양 대각선 점을 이었을 때 2개의 방이 생기는 케이스를 중앙에
// 미세한 정점이 2번 방문하는 걸로 계산하면 2개가 생기는 걸 알 수 있다며
// 모든 방향의 움직임들에 대해 모두 2번씩 실행하면 표시되지 않는 정점들
// 또한 모두 정수로 표시되는 정점들만으로도 계싼이 가능하다고 한다.ㅓ
// 틀린말은 아니다. 좀 더 스마트한 방법은 없을까?

// 다 이렇게 풀었넹... 나도 이렇게 풀어볼란다.

fun main() {
    class Solution {
        val visitedEdge: MutableMap<Pair<Pair<Int, Int>, Pair<Int, Int>>, Boolean> by lazy { mutableMapOf<Pair<Pair<Int, Int>, Pair<Int, Int>>, Boolean>() }
        val visitedVertex: MutableMap<Pair<Int,Int>, Boolean> by lazy { mutableMapOf<Pair<Int,Int>,Boolean>() }
        fun solution(arrows: IntArray): Int {
            val doubleArrows = mutableListOf<Int>()
            arrows.forEach { doubleArrows.addAll(listOf(it,it)) }
            var answer = 0
            var curPos = Pair(0, 0)
            visitedVertex[curPos] = true
            for (dir in doubleArrows) {
                val newPos = when (dir) {
                    0 -> Pair(curPos.first, curPos.second + 1)
                    1 -> Pair(curPos.first + 1, curPos.second + 1)
                    2 -> Pair(curPos.first + 1, curPos.second)
                    3 -> Pair(curPos.first + 1, curPos.second - 1)
                    4 -> Pair(curPos.first, curPos.second - 1)
                    5 -> Pair(curPos.first - 1, curPos.second - 1)
                    6 -> Pair(curPos.first - 1, curPos.second)
                    else -> Pair(curPos.first - 1, curPos.second + 1)
                }
                val newEdge1 = Pair(curPos,newPos)
                val newEdge2 = Pair(newPos,curPos)
                if (visitedVertex.containsKey(newPos) && !visitedEdge.containsKey(newEdge1) && !visitedEdge.containsKey(newEdge2)) {
                    println("newPos: $newPos, newEdge: $newEdge1")
                    answer++
                }
                visitedEdge[newEdge1] = true
                visitedEdge[newEdge2] = true
                visitedVertex[newPos] = true
                curPos = newPos
            }
            return answer
        }
    }

    val sol = Solution()
    var arrows = intArrayOf()
    arrows = intArrayOf(6, 6, 6, 4, 4, 4, 2, 2, 2, 0, 0, 0, 1, 6, 5, 5, 3, 6, 0)
    arrows = intArrayOf(2,5,2,7)
    println("solution: ${sol.solution(arrows)}")
}
