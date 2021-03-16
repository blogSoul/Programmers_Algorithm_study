// 여행경로 플로이드-와샬 연습.
// Floyd-Warshall 알고리즘은 모든 점에서 모든 점까지의 최소비용을 구하는 알고리즘이다.
// Dijkstra 와 다르게, 음수여도 된다. 이는 Bellman-Ford 알고리즘도 마찬가지.
fun main() {
    // 플로이드-와샬 알고리즘은 시작점을 기준으로가 아닌,
    //모든 Pair에 대해서 연산하므로 start 가 필요없다.
    fun floydWarshall(distanceMatrix: List<MutableList<Int>>) {
        var shortestDistance = Int.MAX_VALUE
        var shortestV = 1
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
        var distanceMatrix = List(n + 1) { MutableList(n + 1) { 99999999 } }
        distanceMatrix.forEachIndexed { i, v -> v[i] = 0 }
        for (fare in fares) {
            distanceMatrix[fare[0]][fare[1]] = fare[2]
            distanceMatrix[fare[1]][fare[0]] = fare[2]
        }
        return distanceMatrix
    }

    var n = 6
    var s = 4
    var a = 6
    var b = 2
    var fares = arrayOf(
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
    var distanceMatrix = makeDistanceMatrix(n, fares)
    println("distanceMatrix: $distanceMatrix")
//    for (i in 1..n) {
////        distanceMatrix = makeDistanceMatrix(n, fares)
//        for (j in 1..n) {
//            println("dijkstra from $i to $j: ${dijkstra(distanceMatrix, i, j)}")
//        }
//    }
//    println("dijkstra from $a to $b: ${dijkstra(distanceMatrix, a, b)}")
    var answer = Int.MAX_VALUE
    floydWarshall(distanceMatrix)
    for (i in 1..n) {
//        var distance = dijkstra(distanceMatrix, s, i) + dijkstra(distanceMatrix, i, a) + dijkstra(distanceMatrix, i, b)
        var distance = distanceMatrix[s][i] + distanceMatrix[i][a] + distanceMatrix[i][b]
        if (answer > distance) answer = distance

    }
    println("answer: $answer")
}

