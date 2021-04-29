// 여행 경로
fun main() {
    class Solution {
        private lateinit var candidate: List<String>
        fun solution(tickets: Array<Array<String>>): Array<String> {
            candidate = listOf()
            var answer = arrayOf<String>()
            var routes = mutableListOf<String>()
            val visited = MutableList(tickets.size) { 0 }
            for ((idx, ticket) in tickets.withIndex()) {
                if (ticket[0] == "ICN") {
                    routes.add(ticket[0])
                    routes.add(ticket[1])
                    visited[idx] = 1
                    dfs(tickets, routes, visited)
                    visited[idx] = 0
                    routes.clear()
                }
            }
            println("candidate: $candidate")
            // 원래는 여기서 모든 가능한 루트들을 구해놓고 sortedWith (compareBy {it[0], it[1]} )
            // 이런식으로 정렬하고 맨 첫번째를 구하려했으나, 총 길이를 알지못하므로, 실패.
            // 정답은 최선의 candidate를 매번 비교하면서 갱신하는 것이다.
            // String을 하나하나 대수비교 하면서.
            return candidate.toTypedArray()
        }

        fun dfs(tickets: Array<Array<String>>, routes: MutableList<String>, visited: MutableList<Int>) {
            if (routes.size == tickets.size + 1) {
                if (candidate.isEmpty()) {
                    candidate = routes.toList()
                    println("candidate created: $candidate")
                } else {
                    // 이미 candidate 가 존재한다면, String을 하나하나 비교하며 candidate를 유지하거나 갱신.
                    println("Existing candidate: $candidate")
                    println("New route : $routes")
                    for (idx in routes.indices) {
                        if (candidate[idx] < routes[idx]) {
                            println("existing candidate is more optimized")
                            break
                            // No changes
                        } else if (candidate[idx] > routes[idx]) {
                            println("idx: $idx")
                            println("new route is more optimized")
                            candidate = routes.toList()
                            break
                        }
                    }
                }
            } else {
                for ((idx, ticket) in tickets.withIndex()) {
                    if (visited[idx] == 0 && ticket[0] == routes.last()) {
                        routes.add(ticket[1])
                        visited[idx] = 1
//                    println("visited: $visited")
                        dfs(tickets, routes, visited)
                        visited[idx] = 0
                        routes.removeAt(routes.lastIndex)
                    }
                }
                return
            }
        }
    }
    val sol = Solution()
    var tickets =
        arrayOf(arrayOf("ICN", "JFK"), arrayOf("HND", "IAD"), arrayOf("JFK", "HND"))
    tickets = arrayOf(
        arrayOf("ICN", "SFO"),
        arrayOf("ICN", "ATL"),
        arrayOf("SFO", "ATL"),
        arrayOf("ATL", "ICN"),
        arrayOf("ATL", "SFO")
    )
    sol.solution(tickets)
}