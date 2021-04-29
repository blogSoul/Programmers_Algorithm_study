package d4_24


// 흠.. 쉽게 BFS 로 풀었는데, 마지막 2개가 메모리 초과가 뜬다.
// 노드 2만 개에 간선 5만개... 음... 메모리 부족한가..?

// 질답글을 보니, 그래프를 인접행렬이 아닌 인접리스트로 만들면 된다고 한다!
// 기본적이긴 하나 까먹고 있었네 너무 당연해서... 인접 리스트! 기억하자!
// 코틀린에서 MutableList는 인터페이스고, 자세히 보면 반환값은 ArrayList 다.
// 즉, 미래에 코틀린이 바뀌지 않는 이상, 둘 모두 같다고 보면 된다. 즉 MutableList 쓰면 됨.

// 노드가 많으면! 인접행렬이 아닌 인접리스트를 쓰자!

import java.util.*
fun main() {
    data class Node(val curNode: Int, val count: Int)
    class Solution {
        var maxDist = 0
        var maxSum = 0
        lateinit var adj: Array<MutableList<Int>>
        lateinit var visited: IntArray
        fun solution(n: Int, edge: Array<IntArray>): Int {
            adj = Array(n+1){ mutableListOf<Int>()}
            visited = IntArray(n+1)
            for (e in edge) {
                adj[e[0]].add(e[1])
                adj[e[1]].add(e[0])
            }
            val queue: Queue<Node> = LinkedList()
            queue.offer(Node(1,0))
            visited[1] = 1
            while (queue.isNotEmpty()) {
                val node = queue.poll()
                var curNode = node.curNode
                var count = node.count
                if (count > maxDist) {
                    println("in $curNode new maxDist: $maxDist")
                    maxDist = count
                    maxSum = 1
                } else if (count == maxDist) {
                    maxSum++
                    println("in $curNode maxSum: $maxSum")
                }
                for (adNode in adj[curNode]) {
                    if (visited[adNode] == 0) {
                        visited[adNode] = 1
                        queue.offer(Node(adNode,count+1))
                    }
                }
            }
            println("maxDist: $maxDist")
            println("maxSum: $maxSum")
            return maxSum
        }
    }

    var n = 6
    var edge = arrayOf(
        intArrayOf(3, 6),
        intArrayOf(4, 3),
        intArrayOf(3, 2),
        intArrayOf(1, 3),
        intArrayOf(1, 2),
        intArrayOf(2, 4),
        intArrayOf(5, 2)
    )
    var speeds = intArrayOf()
    println("solution: ${Solution().solution(n,edge)}")
}
