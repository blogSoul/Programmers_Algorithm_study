// 처음에는 orders 만 보면 된다는 생각을 했다.
// 중요한 건, orders에서 필요한 key를 갖고 있느냐가 중요.
// Permutation을 돌려서, 먹는 순서를 order 그대로 가져가서 되면 ok. key가 없어서 못가면 false.
// 최솟값이 아니라 그냥 갈 수 있냐만 믇고있기 때문에..
// Order가 만약 [6,7] [4,1] [8,5] 면, 이렇게 Permutation 돌려서 3!, 즉 6가지 경우가 나오고
// 그 한 예가 위 그대로의 순서라고 하면,
// 0 -> ? -> 6 -> ? -> 7 -> ? -> 4 -> ? -> 1 -> ? -> 8 -> ? -> 5 이렇게 된다.
// 이렇게 경로를 0과 order로 고정시켜놓고, 그 사이 물음표에서 양 끝단으로 갈 수 있는지를 판별하는 식으로 했다.
// 하지만 시간초과와 메모리 초과..
// n 의 크기가 20만이기때문에 그런가보다. 나름 memo로 DP까지 했지만 안되는듯..

// 더 해봤지만 똑같아서 Googling.

// BFS 로 풀 수도 있고 DFS 로 풀 수도 있지만 나랑 접근방식이 다르다.
// 어떤 사람은 사이클이 있는지 아닌지를 판명하지만
// 그냥 BFS DFS 로 돌면서 key 가 필요한 곳은 잠시 저장해놓고 키를 얻으면
// pending 해놓은 노드를 큐에 넣음으로써 다시 탐색을 진행하는 방식이다.
// 역시 내 방법은 overhead 가 너무 많다. 나름 DP 를 해봤지만 저렇게 하는 게 맞는 거 같다.
// 구글링한대로 풀면 O(N)의 시간에 모두 해결가능하다.

// 엥 근데 효율성에서 시간초과가 뜬다?
// 아마도, keySet 때문에 그런 것 같다. adjNode 에 대해서 검사를 할때,
// 일단 이게 keySet의 어떤 원소의 첫번째에 있냐를 검색하기 때문에 계~속 검사를 하는 것.
// keySet이 길면 시간이 엄청 길어질 것이다.
// 개선하면 intArray로 만들면 될 것이다. O(1)의 시간으로 찾을 수 있게.
// 개선한 결과, 100점.

import java.util.*

fun main() {
    class Solution {
        lateinit var edge: List<MutableSet<Int>>
        lateinit var visited: MutableList<Int>
        lateinit var keyOf: IntArray
        lateinit var destOf: IntArray
        fun solution(n: Int, path: Array<IntArray>, order: Array<IntArray>): Boolean {
            var answer = false

            edge = List(n) { mutableSetOf<Int>() }
            for (p in path) {
                edge[p[0]].add(p[1])
                edge[p[1]].add(p[0])
            }
            visited = MutableList(n) { 0 }
            visited[0] = 1
            keyOf = IntArray(n)
            destOf = IntArray(n)
            for (ord in order) {
                if (ord[1] == 0) return false // 0 출발점으로 가는데에 키가 필요하면 아예 못 들어감.
                else {
                    keyOf[ord[1]] = ord[0]
                    destOf[ord[0]] = ord[1]
                }
            }

            var queue: Queue<Int> = LinkedList()
            queue.add(0)

            var pendingSet = mutableSetOf<Int>()
            while (queue.isNotEmpty()) {
                var node = queue.poll()
                for (adjNode in edge[node]) {
                    if (keyOf[adjNode] != 0 && visited[keyOf[adjNode]] == 0) {
                        // pending
                        pendingSet.add(adjNode)
                    } else {
                        if (visited[adjNode] == 0) {
                            if (destOf[adjNode] != 0 && destOf[adjNode] in pendingSet) {
                                pendingSet.remove(destOf[adjNode])
                                visited[destOf[adjNode]] = 1
                                queue.add(destOf[adjNode])
                            }
                            visited[adjNode] = 1
                            queue.add(adjNode)
                        }
                    }
                }
            }
            println("visited: $visited")
            answer = !visited.any { it == 0 }
            return answer
        }
    }

    val sol = Solution()

    var n: Int
    var path: Array<IntArray>
    var order: Array<IntArray>
    n = 9
    path =
        arrayOf(
            intArrayOf(0, 1),
            intArrayOf(0, 3),
            intArrayOf(0, 7),
            intArrayOf(8, 1),
            intArrayOf(3, 6),
            intArrayOf(1, 2),
            intArrayOf(4, 7),
            intArrayOf(7, 5)
        )
    order = arrayOf(intArrayOf(8, 5), intArrayOf(6, 7), intArrayOf(4, 1))
//
//    path =
//        arrayOf(
//            intArrayOf(8, 1),
//            intArrayOf(0, 1),
//            intArrayOf(1, 2),
//            intArrayOf(0, 7),
//            intArrayOf(4, 7),
//            intArrayOf(0, 3),
//            intArrayOf(7, 5),
//            intArrayOf(3, 6)
//        )
//    order = arrayOf(intArrayOf(4, 1), intArrayOf(5, 2))
//
    path =
        arrayOf(
            intArrayOf(0, 1),
            intArrayOf(0, 3),
            intArrayOf(0, 7),
            intArrayOf(8, 1),
            intArrayOf(3, 6),
            intArrayOf(1, 2),
            intArrayOf(4, 7),
            intArrayOf(7, 5)
        )
    order = arrayOf(intArrayOf(4, 1), intArrayOf(8, 7), intArrayOf(6, 5))

    println(sol.solution(n, path, order))
}
