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

// 메모리 초과 이유 2가지
// 1. 스택 터짐
// 2. 인접행렬 X 인접 리스트 O

// 와 역시 대박. 인접리스트로 바꾸니까 메모리초과가 해결됐다...
// 근데 실패가 많다.... 런타임 에러도 있고...
// 그래도 대박...
// 그럼 나 리턴값 써도 되는거야? >.<
// 된다!

// 일단, Int를 안쓰고 Long을 쓰니까 통과는 하는데... 2개가 런타임 에러가 뜬다. 뭐지..?
// 설마 여기서 DFS Depth 문제가 나는 건가?

// 그렇다. StackOverflowError는 프로그래머스에서는 런타임 에러 라고만 뜬다... 메모리 초과는 그대로 뜨고..
// 그럼 재귀를 사용할 수 없으니 생각해야하는 방안은 2개.
// 1. tailrec : 꼬리재귀는, 피보나치처럼 일직선으로 쭈욱 이어진것만 된다. 즉, 트리처럼 부모의 여러 자손을 각자 다 방문해야하는
// 경우는 안된다는 것....
// 2. Stack 으로 재귀 구현
// 재귀함수를 Stack으로 바꾸는 연습도 해봐야지.
// DFS를 스택으로 대체할 때는, 판단이 중요하다. 단순한 트리처럼, 루트에서 Leaf로 가면 끝나는 DFS는 스택에서 pop, push
// 만 더해주면 DFS에서 그다지 수정할 게 없으나, 루트에서 Leaf로 갔다가, 다시 돌아오는 이런 문제같은 경우는
// 추가적인 작업이 더 필요하다. 나는 그래서 스택을 2개를 썼다. 루트에서 Leaf로 가는 스택과, pop할때마다 다른 스택에 push해서
// Leaf노드를 우선으로 하는 스택.
// 이게 mysolution 이다. 근데 8,11,17? 틀림 ㅠ

// 그런데, JS를 주력으로 쓰는 한 사람의 블로그를 보니, 나처럼 안해도 된다.
// https://www.cckn.dev/problem-solve/(JS)%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4%20-%20%EB%AA%A8%EB%91%90-0%EC%9C%BC%EB%A1%9C-%EB%A7%8C%EB%93%A4%EA%B8%B0/
// 아무래도, DFS를 스택으로 쓰는 게 오랜만인 내가 배울점이 많을 것 같다.
// visit이 아니면 stack에 push하고, visit한 노드면 이는 LeafNode부터 시작되고, 연산을 한다.
// 놀라운 건, push를 현재 노드의 이웃만 하는게 아니라, visit표시를 하고 난 후에 다시 넣는 것이다.
// 이 다시 넣는 걸 생각 못해서 나는 parent 스택을 반대로 한번 더 만든 것이었다.
// 무한루프가 되지 않을까 했으나 visit한 경우에는 조건문에서 continue로 넘어가므로 괜찮음.
// 또한, visit을 하지 않았으면, push를 한번만 하는게 아니라, visit 표시를 하고 난 후, 그 이웃중에 visit하지 않은 것들을 push한다.

// 완성하고보니 정답 코드랑 똑같다. 근데!! 8,11,17이 또 틀린다!!!! 이건 문제가 있다..
// 분명히 Long을 써서 정확도도 맞을텐데..
// 일단 이걸로 mySolution도 정답이라는 보장이 생겼다. 근데 왜지? 질답을 찾아보니 나말고도 여럿 있다.


// 채점에 문제가 있다는 의견이 많다. 일단 배워갔으니 만족.

import java.util.*
import kotlin.math.abs

fun main() {
    data class Node(val curNode: Int, val parent: Int)
    class Solution {
        lateinit var adj: MutableList<MutableList<Int>>
        lateinit var weight: MutableList<Int>
        fun mySolution(a: IntArray, edges: Array<IntArray>): Long {
            var answer = 0L
            weight = a.toMutableList()
            adj = MutableList(a.size) { mutableListOf<Int>() }
            for (e in edges) {
                adj[e[0]].add(e[1])
                adj[e[1]].add(e[0])
            }
            if (a.all { it == 0 }) return 0
            else if (a.sum() != 0) return -1

//            val (res, count) = dfs(0, 0)
            var res = 0L
            var count = 0L
            val stack = Stack<Node>()
            val parentStack = Stack<Node>()
            stack.push(Node(0, 0))
            while (stack.isNotEmpty()) {
                val node = stack.pop()
                val (curNode, parent) = node
                parentStack.push(node)
                for (i in adj[curNode]) {
                    if (i != parent) {
                        stack.push(Node(i, curNode))
                    }
                }
            }
            while (parentStack.isNotEmpty()) {
                val node = parentStack.pop()
                val (curNode, parent) = node
                count += abs(weight[curNode])
                weight[parent] += weight[curNode]
            }
            println("weight: $weight")
            return count
        }

        fun solution(a: IntArray, edges: Array<IntArray>): Long {
            weight = a.toMutableList()
            adj = MutableList(a.size) { mutableListOf<Int>() }
            for (e in edges) {
                adj[e[0]].add(e[1])
                adj[e[1]].add(e[0])
            }
            if (a.all { it == 0 }) return 0
            else if (a.sum() != 0) return -1

            val visited = BooleanArray(a.size)
            var count = 0L
            val stack = Stack<Node>()
            stack.push(Node(0, 0))
            while (stack.isNotEmpty()) {
                val (curNode, parent) = stack.pop()

                if (visited[curNode]) {
                    count += abs(weight[curNode])
                    weight[parent] += weight[curNode]
                    weight[curNode] = 0
                    continue
                }

                visited[curNode] = true
                stack.push(Node(curNode, parent))

                for (i in adj[curNode]) {
                    if (!visited[i]) stack.push(Node(i, curNode))
                }
            }
            println("weight: $weight")
            return count
        }

        fun dfs(curNode: Int, parent: Int): Pair<Long, Long> {
            var res = weight[curNode].toLong()
            var count = 0L
            for (i in adj[curNode]) {
                if (i != parent) {
                    val pair = dfs(i, curNode)
                    res += pair.first
                    count += pair.second
                    count += abs(pair.first)
                }
            }
            println("$curNode return Pair: ${Pair(res, count)}")
            return Pair(res, count)
        }
    }

    var a = intArrayOf(-5, 0, 2, 1, 2)
    var edges = arrayOf(intArrayOf(0, 1), intArrayOf(3, 4), intArrayOf(2, 3), intArrayOf(0, 3))
//    a = intArrayOf(0, 1, 0)
//    edges = arrayOf(intArrayOf(0, 1), intArrayOf(1, 2))
//    a = intArrayOf(4, 4, -3, -5)
//    edges = arrayOf(intArrayOf(0, 1), intArrayOf(1, 2), intArrayOf(2, 3))
//    a = intArrayOf(-3, -7, -3, 7, -1, -1, -3, 4, 1, 2, -3, 5, -1, 3, 0)
//    edges = arrayOf(
//        intArrayOf(0, 1), intArrayOf(0, 2), intArrayOf(0, 3), intArrayOf(1, 4), intArrayOf(1, 5), intArrayOf(2, 6),
//        intArrayOf(3, 10), intArrayOf(3, 12), intArrayOf(5, 14), intArrayOf(5, 13), intArrayOf(6, 7), intArrayOf(10, 11),
//        intArrayOf(7, 8), intArrayOf(7, 9)
//    )

    println("solution: ${Solution().mySolution(a, edges)}")
    println("solution: ${Solution().solution(a, edges)}")
}