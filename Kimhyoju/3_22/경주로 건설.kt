// 나름 코드를 짜보았지만, 하면서 visit을 이렇게 써도 되나..? 하는 의구심이 들었다.
// 일반적으로 한칸마다의 거리를 재는 BFS랑은 다르게 이건 경로의 형태로 계산을 하는 것이기에
// 어느 한 점을 두고 큐 안의 두 원소가 경합을 벌이고 있다면, 선입선출의 결과로만 하나만 가져가고 하나는
// 이미 다른 원소가 visit 했으므로 폐기되는 것이다. 두번째 에에서 1,6에서 그 현상이 벌어진다.
// 같은 count를 가진 두 원소가 0,6 과 1,5에서 대기중이고 내 코드에서는 1,5가 먼저 1,6을 visit하게 되므로
// 1,5 원소는 폐기된다. 하지만 1,5에 있는 원소가 "나중에" 가질 sum 값이 더 낮으므로 이게 선택되어야 한다는 게 문제다.
// 게다가 설상가상으로 0,6에서의 점은 이제 코너를 한번 꺾어야하는거고 1,5의 점은 이미 한번 꺾어서 sum값이 똑같다..
// 0,6의 점이 쭉 내려가므로 sum 값이 앞으로 더 낮아질거란 걸 모르는 상태이기 때문.
// 그럼 queue 안에서 같은 count 의 원소들 중에서 가장 낮은 sum값을 가진 원소들을 다시 보관하는 리스트를 만들어야 하는가?
// 이렇게 하면 풀 수는 있겠지만 이게 정답은 아닌 것 같다.
// 카드 짝 맞추기는 겉으로 보기엔 그냥 BFS 문제지만, 사실은 미리 카드의 위치를 파악해놓고 그 점들을 방문하는 순서를 미리 짜놓고
// 그 안에서 최단거리를 찾는 문제였다. 이건 그냥 BFS 문제처럼 보이지만... 최단"거리" 가 아닌 최단 "sum" 값이다.

// 결론적으로 지금 내 코드의 문제는 기존 BFS 알고리즘으로 풀이했기때문에 한번 방문한 칸은 다시 방문하지 못한다.
// 이 문제로, 탐색시 건설 최소비용인 경주로를 방문하지 않고 방문하지 않았던 칸만 방문하여 비용을 계산하는 문제가 발생한다.

// Googling 결과, 이를 해결하기 위해 방문여부를 저장하는 check 배열에 현재까지 계산한 최소 건설비용을 저장하여 메모이제이션을 한다.
// 하... visit 이 아니라 내가 백트래킹할때 오지게 했던 DP로 메모이제이션이었다니..
// 이렇게하면 방문했는지 안했는지가 아니라, 여기에 가면 최소비용이 되는지 안되는지로 합리적으로 판단할 수 있게된다.
// 아직도 공부 한참 더 해야할듯..

// 또 지금까지는 대충 큐를 여러개 했지만 data class로 구현해서 여러개의 큐를 한꺼번에 돌려보자.
// 또 routeList는 그냥 편의상 넣었다. 시간초과 뜬다면 이거 빼면 그냥 통과.
import java.util.*
import kotlin.math.abs

fun main() {
    class Solution {
        fun solution(board: Array<IntArray>): Int {
            var answer = 0

            var curY = 0
            var curX = 0
            val N = board.size

            val nextY = intArrayOf(-1, 1, 0, 0)
            val nextX = intArrayOf(0, 0, -1, 1) // 상 하 좌 우
            val memo = Array(board.size) { IntArray(board.size) { Int.MAX_VALUE } }
            memo[curY][curX] = 0

            val queueCursor: Queue<Pair<Int, Int>> = LinkedList()
            val queueRoute: Queue<MutableList<Pair<Int, Int>>> = LinkedList()
            val queueCost: Queue<Int> = LinkedList()
            val queueState: Queue<Int> = LinkedList()

            queueCursor.add(Pair(curY, curX))
            queueRoute.add(mutableListOf(Pair(curY, curX)))
            queueCost.add(0)
            queueState.add(-1)

            var minSum = Int.MAX_VALUE
            while (queueCursor.isNotEmpty()) {
                var cursor = queueCursor.poll()
                var routeList = queueRoute.poll()
                var cost = queueCost.poll()
                var state = queueState.poll()

                println("X: ${cursor.first}, Y: ${cursor.second}")
                println("cost: $cost")
                println("routeList: $routeList")

                if (cursor.first == N - 1 && cursor.second == N - 1) {
                    if (minSum > cost) minSum = cost
                }

                for (k in 0 until 4) {
                    val newY = cursor.first + nextY[k]
                    val newX = cursor.second + nextX[k]
                    if (newX >= 0 && newX < board.size
                        && newY >= 0 && newY < board.size &&
                        board[newY][newX] == 0
                    ) {
                        // cost 계산
                        var newState: Int
                        val horizon = abs(cursor.second - newX)
                        val vertical = abs(cursor.first - newY)
                        if (horizon > vertical) newState = 0
                        else newState = 1

                        var newCost = cost
                        if (state != -1) {
                            if (state != newState) newCost += 500
                        }
                        newCost += 100

                        println("${memo[newY][newX]} >? $newCost")
                        if (state == -1 || memo[newY][newX] >= newCost) {
                            memo[newY][newX] = newCost
                            queueCursor.add(Pair(newY, newX))
                            val tempRouteList = routeList.toMutableList()
                            tempRouteList.add(Pair(newY, newX))
                            queueRoute.add(tempRouteList)
                            queueCost.add(newCost)
                            queueState.add(newState)
                        }
                    }
                }
            }
            println("minSum: $minSum")
            answer = minSum
            return answer
        }
    }

    val sol = Solution()
    var board: Array<IntArray>
    board = arrayOf(
        intArrayOf(0, 0, 0),
        intArrayOf(0, 0, 0),
        intArrayOf(0, 0, 0)
    )
    board = arrayOf(
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 1),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 1, 0, 0),
        intArrayOf(0, 0, 0, 0, 1, 0, 0, 0),
        intArrayOf(0, 0, 0, 1, 0, 0, 0, 1),
        intArrayOf(0, 0, 1, 0, 0, 0, 1, 0),
        intArrayOf(0, 1, 0, 0, 0, 1, 0, 0),
        intArrayOf(1, 0, 0, 0, 0, 0, 0, 0)
    )
    println(sol.solution(board))
}
