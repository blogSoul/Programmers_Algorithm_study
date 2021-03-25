// 나름 BFS 로 정확하게 푼 것 같은데 한개만 맞고 한개는 실패고 나머지는 시간초과..
// 노드의 개수가 많으니 set을 복사하고 이러면서 더 부하가 커지나보다.

// 내가 푼 방법은 각 Link를 트리로 만들고, 이 트리에서 팀장들만을 하나의 배열로 만든다. (팀장배열)
// BFS 를 시작한다. 처음에는 A팀에 있는 팀장들과 팀원들을 하나씩 큐에 add.
// 그리고 그 다음 팀에 대해서 반복한다. 이렇게 하면 A x B x C ... Z (각 대문자는 팀 인원 수)
// 만약 팀이 2개고 각각이 10 만이라고 쳐도 10만 x 10만 으로 돼서 엄청나게 불어난다... DP를 사용할 수 있나..?
// 어떻게 줄일 것인가. 아니면 접근 자체가 틀린건가. 트리에 대한 공부가 더 필요한 것 같다.
// 트리에 대한 알고리즘.... 이 따로 필요한가 여기서?

// 결국 포기하고 Googling 함. 예상대로 트리 DP 를 사용하여 해결하는 문제.
// 트리 DP 를 몰랐으면 절대 풀 수 없는 문제라고 한다. 트리 DP 를 알았으면 구현은 쉬운편이라고...
// DP[노드번호][노드포함여부] = 최적해

// 대략, DP[i][j] = i를 투르로 하는 서브트리에서 i의 상태가 j 일 때..
// DP[i][0]: i번 노드가 루트인 서브트리에서 조건을 만족했을 때 매출합의 최소, i번은 워크샵에 불참석.
// DP[i][1]: i번 노드가 루트인 서브트리에서 조건을 만족했을 때 매출합의 최소, i번은 워크샵에 참석.

// i 의 children 이 각각 c0, c1, c2 일 때,
// D[i][1] = min(D[c0][0],D[c0][1]) + min(D[c1][0],D[c1][1]) + min(D[c2][0],D[c2][1]) + i.profit
// 즉, 참석 했을 시에는, 자식들이 참석했든 참석안했든 그 최솟값들을 구하면 된다.

// 하지만 루트 i 가 불참석 시에는, 자식 중 1명은 무조건 참석해야한다.
// 자식 c 가 참석했을 때 "손해" 가 가장 작은 사람이 참석하면 됨.
// D[i][0] = max( 0, min (D[c0][0]-D[c0][1], D[c1][0]-D[c1][1], D[c2][0]-D[c2][1])) + D[i][1] - i.profit
// 0을 넣은 이유는, -가 되면 안되기 때문
// 근데, 자식중 한명은 무조건 참석해야 되는거면,
// D[i][0] = min (D[c0][1], D[c1][1], D[c2][1]) 이렇게 하면 되는거 아닌가?
// 아 아니네. D[c0][1]를 골랐다고 치면, 나머지 c1과 c2에 대해서도 더해줘야 하는데,
// 얘네들이 참석했을 때 최소인지 참석안했을 때 최소인지 모르니까, 모두 다 고려를 해줘야되네
// 나라면 여기서 permutation 돌렸을 텐ㄷ... 100 010 001 이렇게.... 어떻게 똑똑하게 하지?

// 워크숍에 참가하지 않았을 때의 매출액과 워크숍에 참가했을 때의 매출액의 차이가 가장 작은 팀원을 보내는 것이 이득이라고 한다.
// 왜냐하면 우리는 워크숍에 참가하는 직원들의 매출액의 합이 최소가 되는 금액을 찾아야 하는 상황이고,
// 이 상황속에서 팀원 한명을 억지로 보내야 한다면, 워크숍에 참가하더라도,
// 참가하지 않았을 때와의 금액이 가장 차이가 작게 나는 팀원을 보내는 것이
// 워크숍에 참가하는 직원들의 매출액의 합을 최소가 되도록 만들 수 있기 때문이다.
// https://yabmoons.tistory.com/625
import java.util.*

fun main() {
    class Node(var id: Int, var profit: Int) {
        val children = mutableListOf<Node>()
        var leaf: Boolean = true
    }

    class Solution {
        lateinit var members: Array<Node>
        lateinit var memberIds: MutableSet<Int>
        lateinit var dp: Array<IntArray>
        lateinit var visited: IntArray
        fun solution(sales: IntArray, links: Array<IntArray>): Int {
            members = Array(sales.size) { Node(0, 0) }
            memberIds = mutableSetOf()
            dp = Array(sales.size) { IntArray(2) }
            visited = IntArray(sales.size)
            var answer: Int = 0
            for (link in links) {
                if (link[0] !in memberIds) {
                    memberIds.add(link[0])
                    members[link[0] - 1] = Node(link[0], sales[link[0] - 1])
                }
                if (link[1] !in memberIds) {
                    memberIds.add(link[1])
                    members[link[1] - 1] = Node(link[1], sales[link[1] - 1])
                }
                if (members[link[0] - 1].children.none { it.id == link[1] }) {
                    members[link[0] - 1].children.add(members[link[1] - 1])
                    members[link[0] - 1].leaf = false
                }
            }
            dfs(members[0])
            println(dp.map { it.toList() }.toList())

            answer = dp[0].minOrNull()?:Int.MAX_VALUE
            return answer
        }

        fun dfs(node: Node) {
            println("in dfs of node ${node.id}")
            visited[node.id - 1] = 1
            // 리프노드면, 참가하면 profit, 참가안하면 0이다. 여기서부터 위로 올라가서 합산.= min1
            if (node.leaf) {
                println("this is leaf node ${node.id}")
                dp[node.id - 1][0] = 0
                dp[node.id - 1][1] = node.profit
                return
            }
            var min0 = Int.MAX_VALUE // 해당 노드가 참가하지 않는 경우
            var min1 = 0 // 해당 노드가 참가하는 경우
            var attend = true
            for (n in node.children) {
                if (visited[n.id - 1] == 1) continue
                dfs(n)
                min1 += minOf(dp[n.id - 1][0], dp[n.id - 1][1]) // 참가 하는 경우, 팀원이 참가하던 말던 min 고르면 됨.
                if (dp[n.id - 1][0] >= dp[n.id - 1][1]) { // 어떤 부하가 참석하는게 참석안하는 것보다 더 도움이 될 때!
                    // 그럼 얘 보내고 리더는 안가면 되지
                    // = dp에 안더해도 된다.
                    attend = false
                } else min0 = minOf(min0, dp[n.id - 1][1] - dp[n.id - 1][0]) // 근데 왜 꼭 1 에서 0 을 빼야할까? 음수면 어떡하려고
                // 아 그 이유는 이미 위에서 0이 1보다 크거나 같을 때를 상정해주고 있기 때문에, 이것은 그 예외인 것.
                // 어차피 한명 보내야하면, 즉, dp에 더해야한다면?
                // 그나마 보내나 안보내나 비슷한 애를 보내서 dp가 최소가 되도록, 손해를 줄이자.
            }
            dp[node.id - 1][1] = min1 + node.profit
            println("dp[${node.id - 1}][1] = $min1 + ${node.profit}")
            if (!attend) dp[node.id - 1][0] = min1 // 참석하는 게 더 도움이 되는 애 보내고 리더는 참석안한다.
            else dp[node.id - 1][0] = min0 + min1
            // 왜 여기서 min0 + min1 이냐.
            // 지금까지 attend가 true 라면, 자식들은 모두 dp[i][1] > dp[i][0], 즉 참석안하는 게 모두 이득이다.
            // 즉 현재 노드가 가지 않을거니까 한명은 무조건 보내야한다.
            // 그러므로 위 for loop를 돌면서 min1은 dp[a][0]+dp[b][0] 이다.
            // 여기서 만약 a의 참석했을 때와 참석 안했을 때의 차이가 작다면 a를 보내야하므로, 구해야 하는 값은
            // dp[a][1] + dp[b][0] 이다.
            // min1 은 dp[a][0] + dp[b][0] 인데, 여기서 a의 차이, 즉 min0 = dp[a][1] - dp[a][0] 를 더하면
            // min0 + min1 = dp[a][1] - dp[a][0] + dp[a][0] + dp[b][0]
            // = dp[a][1] + dp[b][0] 이 된다. 허허.. 실전에서 이걸 생각해낼 수 있을까..
            println("dp[${node.id - 1}][0] = $min0")
        }
    }

    val sol = Solution()

    var sales: IntArray
    var links: Array<IntArray>

    sales = intArrayOf(14, 17, 15, 18, 19, 14, 13, 16, 28, 17)
    links =
        arrayOf(
            intArrayOf(10, 8), intArrayOf(1, 9), intArrayOf(9, 7),
            intArrayOf(5, 4), intArrayOf(1, 5), intArrayOf(5, 10),
            intArrayOf(10, 6), intArrayOf(1, 3), intArrayOf(10, 2)
        )
//    sales = intArrayOf(2,1)
//    links =
//        arrayOf(
//            intArrayOf(1, 2)
//        )
//    sales = intArrayOf(5, 6, 5, 3, 4)
//    links =
//        arrayOf(
//            intArrayOf(2, 3), intArrayOf(1, 4), intArrayOf(2, 5),
//            intArrayOf(1, 2)
//        )
//
//    sales = intArrayOf(5, 6, 5, 1, 4)
//    links =
//        arrayOf(
//            intArrayOf(2, 3), intArrayOf(1, 4), intArrayOf(2, 5),
//            intArrayOf(1, 2)
//        )
//
//    sales = intArrayOf(10,10,1,1)
//    links =
//        arrayOf(
//            intArrayOf(3,2), intArrayOf(4,3), intArrayOf(1,4)
//        )

    println("\n\n Answer: ${sol.solution(sales, links)}")
}
