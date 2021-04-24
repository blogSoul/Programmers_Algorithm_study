package `4_23`

// 같은 날 풀었던, DP인줄 알고 신나게 풀다가 문제 해결의 원리를 이해하는 것이 중요한 것에 비추어 보며
// 풍선 터뜨리기와 비슷한 문제같다.
// 고로 문제 해결의 원리를 먼저 파악해야한다.
// 일단, 겉으로 보기엔 모든 노드의 점수의 합이 애초에 0이어야 하는 문제같다..
// 만약 합이 0이라면, 그건 가능한 것.

// 음... 계속 봤지만 원리를 역시나 파악하기가 어렵다... 어떻게 노드를 순회해야 최소비용이 될까..?
// root 부터 순회하려고 했으나, 어떤 root를 택하느냐에 따라 비용이 또 달라지는 것 같다...

// 구글링해보니, 내 생각이 틀렸다. root를 택하고 leaf 까지 내려가면 그냥 된다..
// 이하는 타인의 Velog 글을 발췌한 것.
// 트리의 한 노드를 잡고 해당 노드로 부터 DFS 탐색을 진행한다.
// DFS탐색을 통해 리프노드에 도착했을경우 다시 위로 올라오면서 각 간선의 가중치 값을 더해서 올라온다
// (모든 노드의 가중치를 0으로 만드는 과정). 가중치값을 더함과 동시에 가중치값이 횟수가 됨으로 횟수를 함께 저장해서 올라온다.
// 루트노드와 더해온 가중치 값을 연산해서 0이 나온 경우 모든 정점의 가중치가 0이 되기에 횟수를 출력하고 그렇지 않은 경우 -1을 출력한다.

// 결론은 그냥 가중치 몇번 더해지냐를 리턴하면 되는건데.. 노드를 순회하는 과정 자체는 정답에 아무런 영향이 없는 걸 알수있구나
// 그냥 처음 가중치에서 0이 아닌 것들의 절대값 구해서 리턴하면 안되나?
// -> 안된다. 직접 해보니까 트리로 짠거랑 다르게 나옴. 루트로 올라가는 과정에서 값이 연산되는 과정도 생각해야한다.
// 직접 트리로 짜보는 수밖에 없다. (이참에 이 문제와는 관계없지만 트리 DP도 나중에 공부해보자)

// 똑같이 짰더니, 답은 맞는데 메모리 초과가 우수수 뜬다.
// 리턴값을 없애고 전역변수로 처리하면 되려나..? 간단한 문제긴 한데 좀 야매인것같은데..
// 리턴하는 게 깔끔한데..

// 리턴값을 아예 없애도 메모리 초과가 뜬다... 개빡치네..
// 답은 맞는 것 같으니 그냥 패쓰.. 답변 올라오면 봐야겠다.
import kotlin.math.abs

fun main() {
    class Solution {
        lateinit var adj: Array<IntArray>
        lateinit var weight: MutableList<Int>
        var wholeCount = 0
        fun solution(a: IntArray, edges: Array<IntArray>): Long {
            var answer = 0L
            weight = a.toMutableList()
            adj = Array(a.size) { IntArray(a.size) }
            for (e in edges) {
                adj[e[0]][e[1]] = 1
                adj[e[1]][e[0]] = 1
            }
            if (a.all { it == 0 }) return 0
            else if (a.sum() != 0) return -1
            val root = 0
            dfs(root, root)
            answer = wholeCount.toLong()
            return answer
        }

        fun dfs(curNode: Int, parent: Int) {
            weight[parent] += weight[curNode]
            for ((i, connect) in adj[curNode].withIndex()) {
                if (connect == 1 && i != parent) {
                    dfs(i, curNode)
                    weight[parent] += weight[i]
                    wholeCount += weight[i]
                }
            }
        }
    }

    var a = intArrayOf(-5, 0, 2, 1, 2)
    var edges = arrayOf(intArrayOf(0, 1), intArrayOf(3, 4), intArrayOf(2, 3), intArrayOf(0, 3))
    println("solution: ${Solution().solution(a, edges)}")
}