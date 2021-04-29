import java.util.*

fun main() {
    class Solution {
        lateinit var adjMatrix: Array<IntArray>
        fun solution(n: Int, passenger: IntArray, train: Array<IntArray>): IntArray {
            data class Train(val curStation: Int, val curPassenger: Int)
            adjMatrix = Array(n+1){ IntArray(n+1)}
            var answer = IntArray(n)
            for (t in train) {
                adjMatrix[t[0]][t[1]] = 1
                adjMatrix[t[1]][t[0]] = 1
            }
            var maxPassenger = passenger[0] // 1번 역
            var maxDest = 1
            val q: Queue<Train> = LinkedList()
            for (dest in 2..n) {
                println("dest: $dest")
                q.clear()
                q.add(Train(1,passenger[0]))
                val visited = IntArray(n+1){0}
                visited[1] = 1
                while (!q.isEmpty()) {
                    val t = q.poll()
                    val curStation = t.curStation
                    val curPassenger = t.curPassenger
                    for ((idx,node) in adjMatrix[curStation].withIndex()) {
                        if (node == 1 && visited[idx] == 0) {
                            if (idx == dest) {
                                val res = curPassenger+passenger[idx-1]
                                println("res: $res")
                                if (res >= maxPassenger) {
                                    println("$res >= $maxPassenger")
                                    maxPassenger = res
                                    maxDest = dest
                                }
                            }
                            else {
                                visited[idx] = 1
                                q.add(Train(idx,curPassenger+passenger[idx-1]))
                                println("q.add $idx, ${curPassenger+passenger[idx-1]}")
                            }
                        }
                    }
                }
            }
            println("maxPassenger: $maxPassenger")
            println("maxDest: $maxDest")
            answer[0] = maxDest
            answer[1] = maxPassenger
            return answer
        }
    }

    val sol = Solution()
    val n = 6
    val passenger = intArrayOf(1,1,1,1,1,1)
    val train = arrayOf(
        intArrayOf(1,2), intArrayOf(1,3), intArrayOf(1,4), intArrayOf(3,5), intArrayOf(3,6)
    )
    println(sol.solution(n,passenger,train))
}
