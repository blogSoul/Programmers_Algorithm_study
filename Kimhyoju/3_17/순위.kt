//  위상정렬인지 한참 고민하다가 찾아봤는데 플로이드-와샬로 푸는 거라고 한다.
// 나는 위상정렬로 푸는게 더 쉬워보였다.

// 근데 그래프를 그려보고 예제를 만들어보다가 알았다.
// 위상정렬로는 안되네..?
// 사이트에 쓰인 예제인
// [4, 3], [4, 2], [3, 2], [1, 2], [2, 5]
// 에서는 무엇을 먼저 큐에 넣을 것인가에 따라 답이 달라지기 때문. 그게 위상정렬의 매력이긴 하지만...
// 1 -> 4 -> 3 -> 2 -> 5
// 4 -> 1 -> 3 -> 2 -> 5
// 4 -> 3 -> 1 -> 2 -> 5
// 4를 먼저 큐에 넣었다면 좋겠으나,
// 만약 1을 먼저 넣는다면, 1이 3보다 우선순위라고 잘못 판단하게 된다.
// 실제로는 1과 3의 승패는 모르는데도.
// 다른 사람도 그래프 문제라고 되어있어서 위상정렬문제인가 한참을 고민했다고 한다.

// 선수의 수는 100 이하, 결과도 4500 이하이기 때문에 완탐을 돌려도 될듯하다.
// 오히려 완탐을 돌리라고 만든 문제인듯.

// 생각보다 쉬움.
// win[i] : i가 이긴 사람 set
// lose[i] : i가 진 사람 set
// 인덱스 i에 대해서 이 두개의 합이 n-1이면 순위가 정해지는 것.
// 하지만 이걸 그래프로 풀고싶다.
// 이게 왜 플로이드-와샬인지 한참 고민했다.


// 결국 구글링해봄.
// 플로이드 와샬답게 3중 for loop는 동일했으나, graph[a][b]를 a->b (a가 b를 이겼으면) 1, a가 b에게 졌으면 -1 이렇게
// 자료구조를 준비하고,
// if (graph[a][mid] == 1 && graph[mid][b] == 1) graph[a][b] = 1 이렇게 풀었다...
// 대박. 난 플로이드 와샬이란 알고리즘을 그저 거리를 재는 용도로밖에 사용 안했는데, 이렇게 pair 간의
// 관계를 생각하는 것이 이 알고리즘의 근본이라고 할 수 있겠다.

fun main() {
    class Solution {
        fun solution(n: Int, results: Array<IntArray>): Int {
            var answer = 0
            var fight = List(n + 1) { MutableList(n + 1) { 0 } }
            fight.forEachIndexed { i, v -> v[i] = 2; v[0] = 2 } // same
            for (result in results) {
                fight[result[0]][result[1]] = 1
                fight[result[1]][result[0]] = -1
            }

            for (midNode in 1..n) {
                for (startNode in 1..n) {
                    for (endNode in 1..n) {
                        if (fight[startNode][midNode] == 1 && fight[midNode][endNode] == 1) {
                            fight[startNode][endNode] = 1
                        }
                        if (fight[startNode][midNode] == -1 && fight[midNode][endNode] == -1) {
                            fight[startNode][endNode] = -1
                        }
                    }
                }
            }

            println("fight: $fight")
            answer = fight.count { it.all { v -> v != 0 } }
            println("answer: $answer")
            return answer
        }
    }

    val sol = Solution()
    var n: Int
    var results: Array<IntArray>
    n = 5
    results = arrayOf(
        intArrayOf(4, 3), intArrayOf(4, 2), intArrayOf(3, 2), intArrayOf(1, 2), intArrayOf(2, 5)
    )
    println(sol.solution(n, results))
}