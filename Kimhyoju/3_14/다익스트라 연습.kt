// 여행경로 다익스트라 연습.
// Dijkstra 알고리즘은 한 점에서 모든 점까지의 최소비용을 구하는 알고리즘이다.
// 단, 거리가 음수인 edge 가 있으면 안된다.
// Greedy 알고리즘이기 때문에.
// 효율성 반은 통과 반은 불합격.
// 100에 71.7점.
fun main() {
    fun dijkstra(distanceMatrix: List<MutableList<Int>>, start: Int) {
        var shortestDistance: Int
        var shortestV = start
        var visited = MutableList(distanceMatrix.size) { 0 }
        visited[0] = 1
        visited[start] = 1
        while (visited.any { it == 0 }) {
            shortestDistance = Int.MAX_VALUE
            for (i in 1 until distanceMatrix.size) {
                if (visited[i] == 0) {
                    // visited 하지 않은 점 중 가장 가까운 점
//                    println("unvisited: $i")
                    if (shortestDistance > distanceMatrix[start][i]) {
                        shortestDistance = distanceMatrix[start][i]
                        shortestV = i
                    }
                }
            }
            visited[shortestV] = 1
            // 이제 distanceMatrix 갱신.
            for (i in 1 until distanceMatrix.size) {
                for (j in 1 until distanceMatrix.size) {
                    if (distanceMatrix[i][j] > distanceMatrix[i][shortestV] + distanceMatrix[shortestV][j]) {
                        distanceMatrix[i][j] = distanceMatrix[i][shortestV] + distanceMatrix[shortestV][j]
                    }
                }
            }
        }
        // return 할 필요가 없다. dijkstra 를 한번 하고나면, distanceMatrix 가 완성되어 있으니.
//    return distanceMatrix[start][end]
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
    dijkstra(distanceMatrix, a)
    var answer = Int.MAX_VALUE
    for (i in 1..n) {
        var distance = distanceMatrix[s][i] + distanceMatrix[i][a] + distanceMatrix[i][b]
        if (answer > distance) answer = distance

    }
    distanceMatrix.forEach { println(it) }
    println("answer: $answer")
}
