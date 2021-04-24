package `4_23`

// 최소 신장 트리 문제.(MST)
// Kruskal Algorithm

// 기본 구현은 쉽게 했으나, Union-Find 부분에서, findUnion, union 등의 함수 구현없이
// 귀찮아서 대충 하려다보니, 더 오래걸린 것 같다.
// for문 돌때마다 모든 cycle, 즉, union 배열을 관리해줄 수 없으니, union 함수로 한번에 확실하게 해결해야한다.
// 틀린 이유는 아래 cycle 코드에 주석을 달아놓았다.
fun main() {
    class Solution {
        lateinit var visitedEdges: MutableMap<Pair<Int, Int>, Int>
        lateinit var cycle: IntArray
        fun solution(n: Int, costs: Array<IntArray>): Int {
            var answer = 0
            visitedEdges = mutableMapOf<Pair<Int, Int>, Int>()
            cycle = IntArray(n) { it }
            if (n == 1) return 0
            val cost = costs.map { it.toList() }.sortedBy { it[2] }
            println(cost)
            visitedEdges[Pair(cost[0][0], cost[0][1])] = cost[0][2]
            visitedEdges[Pair(cost[0][1], cost[0][0])] = cost[0][2]
            println("initial cycle: ${cycle.toList()}")
            cycle[maxOf(cost[0][0], cost[0][1])] = cycle[minOf(cost[0][0], cost[0][1])]
            println("cycle: ${cycle.toList()}")
            answer += cost[0][2]
            for (idx in 1..cost.lastIndex) {
                if (!isUnion(cost[idx][0],cost[idx][1])) {
                    visitedEdges[Pair(cost[idx][0], cost[idx][1])] = cost[idx][2]
                    visitedEdges[Pair(cost[idx][1], cost[idx][0])] = cost[idx][2]
                    answer += cost[idx][2]
//                    cycle[maxOf(cost[idx][0], cost[idx][1])] = findUnion(minOf(cost[idx][0], cost[idx][1]))
                    // 여기서 union 함수를 쓰지않고, 직접 cycle을 조작해버리면 문제가 생긴다.
                    // root parent를 직접 참조하지 않고 단순히 parent를 가리키는 상태에서 바꿔버리면
                    // 영영 다른 union이 되어버린다.
                    union(maxOf(cost[idx][0], cost[idx][1]),findUnion(minOf(cost[idx][0], cost[idx][1])))
                    println("cycle: ${cycle.toList()}")
                } else {
                    println(cost[idx][0].toString()+" is Union with "+cost[idx][1])
                }
                if (visitedEdges.size == 2 * (n - 1)) break
            }
            return answer
        }

        fun findUnion(n: Int): Int {
            if (cycle[n] == n) return n
            else {
                cycle[n] = findUnion(cycle[n])
                return cycle[n]
            }
        }
        fun isUnion(a: Int, b: Int) = findUnion(a) == findUnion(b)
        fun union(a: Int, b:Int) {
            println("findUnion($a): ${findUnion(a)}")
            println("findUnion($b): ${findUnion(b)}")
            cycle[findUnion(a)] = findUnion(b)
        }
    }

    val sol = Solution()
    var n = 4
    var costs = arrayOf(
        intArrayOf(0, 1, 1), intArrayOf(0, 2, 2),
        intArrayOf(1, 2, 5), intArrayOf(1, 3, 1), intArrayOf(2, 3, 8)
    )
    n = 5
    costs = arrayOf(intArrayOf(0,1,5), intArrayOf(1,2,3), intArrayOf(2,3,3), intArrayOf(3,1,2),
        intArrayOf(3,0,4), intArrayOf(2,4,6), intArrayOf(4,0,7))
//    costs = arrayOf(intArrayOf(0, 1, 3), intArrayOf(2, 3, 2), intArrayOf(1, 2, 1))
    println("solution: ${sol.solution(n, costs)}")
}
