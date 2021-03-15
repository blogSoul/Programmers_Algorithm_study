// 합승택시요금
// 다익스트라는 적어도 정확성은 다 통과했느데
// 플로이드-와샬로 하니까 정확성 테스트가 4개정도 불합..
// 게다가 시간초과도 뜬다... 다익스트라보단 훨씬 낫지만..
// 45/100
fun main() {
    class Solution {
        private lateinit var distanceMatrix: List<MutableList<Int>>
        fun solution(n: Int, s: Int, a: Int, b: Int, fares: Array<IntArray>): Int {
            distanceMatrix = makeDistanceMatrix(n, fares)
            floydWarshall()
            distanceMatrix.forEach { println(it) }
            var answer = distanceMatrix[s][a] + distanceMatrix[s][b]
            println("answer: $answer")
            var finalMidNode = s
            for (midNode in 1 until distanceMatrix.size) {
                if (answer > distanceMatrix[s][midNode]
                    + distanceMatrix[midNode][a] + distanceMatrix[midNode][b]
                ) {
                    answer = distanceMatrix[s][midNode] + distanceMatrix[midNode][a] + distanceMatrix[midNode][b]
                    finalMidNode = midNode
                }
            }
            println("finalMidNode: $finalMidNode")
            println("answer: $answer")
            return answer
        }

        fun floydWarshall() {
            for (startNode in 1 until distanceMatrix.size) {
                for (midNode in 1 until distanceMatrix.size) {
                    // going through midNode, calculate the shortest distance
                    for (endNode in 1 until distanceMatrix.size) {
                        if (distanceMatrix[startNode][endNode] >
                            distanceMatrix[startNode][midNode] + distanceMatrix[midNode][endNode]
                        ) {
                            distanceMatrix[startNode][endNode] =
                                distanceMatrix[startNode][midNode] + distanceMatrix[midNode][endNode]
                        }
                    }
                }
            }
        }

        fun makeDistanceMatrix(n: Int, fares: Array<IntArray>): List<MutableList<Int>> {
            var distanceMatrix = List(n + 1) { MutableList(n + 1) { 100000 } }
            distanceMatrix.forEachIndexed { i, v -> v[i] = 0 }
            for (fare in fares) {
                distanceMatrix[fare[0]][fare[1]] = fare[2]
                distanceMatrix[fare[1]][fare[0]] = fare[2]
            }
            return distanceMatrix
        }

    }

    val sol = Solution()
    var n: Int
    var s: Int
    var a: Int
    var b: Int
    var fares: Array<IntArray>
    var varList = listOf(
        6,
        4,
        6,
        2
    )
    varList = listOf(
        7,
        3,
        4,
        1
    )
    varList = listOf(
        6,
        4,
        5,
        6
    )
    varList = listOf(
        4,
        1,
        2,
        4
    )
    varList = listOf(
        3,
        1,
        2,
        3
    )
    varList = listOf(
        4,
        1,
        3,
        4
    )
    fares = arrayOf(
        intArrayOf(4, 1, 10),
        intArrayOf(3, 5, 24),
        intArrayOf(5, 6, 2),
        intArrayOf(3, 1, 41),
        intArrayOf(5, 1, 24),
        intArrayOf(4, 6, 50),
        intArrayOf(2, 4, 66),
        intArrayOf(2, 3, 22),
        intArrayOf(1, 6, 25)
    )
//    fares = arrayOf(
//        intArrayOf(5,7,9),
//        intArrayOf(4,6,4),
//        intArrayOf(3,6,1),
//        intArrayOf(3,2,3),
//        intArrayOf(2,1,6)
//    )
//    fares = arrayOf(
//        intArrayOf(2,6,6),
//        intArrayOf(6,3,7),
//        intArrayOf(4,6,7),
//        intArrayOf(6,5,11),
//        intArrayOf(2,5,12),
//        intArrayOf(5,3,20),
//        intArrayOf(2,4,8),
//        intArrayOf(4,3,9)
//    )
    fares = arrayOf(
        intArrayOf(1,2,1),
        intArrayOf(2,3,2),
        intArrayOf(3,4,3),
        intArrayOf(1,4,50)
    )
    fares = arrayOf(
        intArrayOf(1,2,1),
        intArrayOf(1,3,50)
    )
    fares = arrayOf(
        intArrayOf(1,2,3),
        intArrayOf(1,3,100),
        intArrayOf(2,3,10),
        intArrayOf(1,4,50)
    )
    n = varList[0]
    s = varList[1]
    a = varList[2]
    b = varList[3]

    sol.solution(n, s, a, b, fares)
}
