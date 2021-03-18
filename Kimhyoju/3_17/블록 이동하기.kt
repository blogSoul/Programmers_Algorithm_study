// 개빡구현인듯.... DP를 이용하는 것 같은데, 점이 1개일땐 쉽지만
// 이렇게 회전도 하는 2개의 점을 동시에 처리하는 아이디어가 몇개 떠오르긴 하지만
// 확신이 없다.
// 행동을 하는 함수 action으로 DFS를 하려다가
// 최단거리이므로 BFS가 편하리라고 생각하고 바꾸다가 시간 엄청 잡아먹었다.
// 애초에 DP만 잘 하면 큰 상관은 없을듯 한데..

// dp 배열에 2구간에 각각 1씩 더하도록 하고,
// count가 dp 배열의 좌표값보다 크거나 같을때만 통과하도록 했다.
// 정확도는 다 맞는 것 같지만 시간초과가 3개 있다.

// 79.3 / 100


// 일단 경계조건 처리를 완전히 쉽게 하기위해
// padding을 1씩 넣는다... 간단하지만 확실한 성능

// 또한 회전할 때, 나는 조건 그대로 넣었지만 생각해보면 더 간단히 할 수 있다.
// -> 드론이 현재 가로방향일때, 상단 2칸이 모두 비어있을때만
// 어느방향으로도 회전하고 갈 수 있다.
// 하지만 어느 1칸에라도 벽이 있으면 어느 방향으로도 회전하지 못하고 갈 수 없다.
// 즉, go up, rotate left up, rotate right up 이 3개의 조건을 모두 1개로 통합할 수 있다.
// 이는 하단도 마찬가지... go down, rotate left down, rotate right down 을 1개로 통합 가능..
// 자그마치 6개의 조건을 2개로 줄이는 것이다.

// 현재 세로방향일때도 마찬가지. 좌측 2칸과 우측 2칸이 모두 비어있는지를 각각 확인.
// 여기도 6개의 조건을 2개로 줄여서

// 나는 무식하게 12개의 조건을 봤지만 쉽게 하면 딱 4개의 조건만 보면 되는 것이다..
// 구현문제긴 하지만... 개빡친다. 빡구현으로 풀었는데 더 스마트하게 그냥 평?구현으로 풀 수 있으니
// 게다가 시간초과 줄이려고 DP 까지 썼는데... 이 문제는 DP는 필요가 없다.
// 진짜 그냥 단순하게 BFS로 돌리면 된다. 처음 나오는게 당연히 최단거리니까... 억울해 시발 내 시간
// 그리고 당연히 BFS와 visited도 포함.


// Solution1 이 이상하게 푼 내 방식 (시간초과 3개)
// Solution2 가 다른 블로그에서 참조해서 쓴 방식

fun main() {
    class Solution1 {
        private lateinit var dp: List<MutableList<Int>>
        private lateinit var boards: Array<IntArray>
        private lateinit var queue: MutableList<List<Int>>
        var N = 1
        fun solution(board: Array<IntArray>): Int { // 0,0 -> N-1,N-1
            var answer = board.size * board.size
            boards = board
            queue = mutableListOf()
            var count = 0
            N = board.size
            var x1 = 0
            var y1 = 0
            var x2 = 1
            var y2 = 0
//            dp = Array(N) { IntArray(N) { Int.MAX_VALUE } }
            dp = List(N) { MutableList(N) { 0 } }
            dp[y1][x1] = 0
            dp[y2][x2] = 0
            queue.add(listOf(x1, y1, x2, y2, count))

            while (queue.isNotEmpty()) {
                val q = queue.first()
                queue.removeAt(0)

                val x1 = q[0]
                val y1 = q[1]
                val x2 = q[2]
                val y2 = q[3]
                val count = q[4]
                println("x1: $x1, x2: $x2")
                println("y1: $y1, y2: $y2")
                println("count: $count")
                dp[y1][x1]++
                dp[y2][x2]++
                println("dp: ")
                dp.forEach { println(it) }
                if ((x1 == N - 1 && y1 == N - 1) || (x2 == N - 1 && y2 == N - 1)) {
                    answer = count
                    break
                }

                // action
                // go right
                if (x1 + 1 < N && x2 + 1 < N && board[y1][x1 + 1] == 0 && board[y2][x2 + 1] == 0) {
                    if (count >= dp[y1][x1 + 1] || count >= dp[y2][x2 + 1]) {
                        println("from $x1 $y1 $x2 $y2")
                        println("go right")
                        queue.add(listOf(x1 + 1, y1, x2 + 1, y2, count + 1))
                    }
                }
                // go left
                if (x1 - 1 >= 0 && x2 - 1 >= 0 && board[y1][x1 - 1] == 0 && board[y2][x2 - 1] == 0) {
                    if (count >= dp[y1][x1 - 1] || count >= dp[y2][x2 - 1]) {
                        println("from $x1 $y1 $x2 $y2")
                        println("go left")
                        queue.add(listOf(x1 - 1, y1, x2 - 1, y2, count + 1))
                    }
                }
                // go up
                if (y1 - 1 >= 0 && y2 - 1 >= 0 && board[y1 - 1][x1] == 0 && board[y2 - 1][x2] == 0) {
                    if (count >= dp[y1 - 1][x1] || count >= dp[y2 - 1][x2]) {
                        println("from $x1 $y1 $x2 $y2")
                        println("go up")
                        queue.add(listOf(x1, y1 - 1, x2, y2 - 1, count + 1))
                    }
                }
                // go down
                if (y1 + 1 < N && y2 + 1 < N && board[y1 + 1][x1] == 0 && board[y2 + 1][x2] == 0) {
                    if (count >= dp[y1 + 1][x1] || count >= dp[y2 + 1][x2]) {
                        println("from $x1 $y1 $x2 $y2")
                        println("go down")
                        queue.add(listOf(x1, y1 + 1, x2, y2 + 1, count + 1))
                    }
                }
                if (x1 == x2) { // vertical
                    var upY = minOf(y1, y2)
                    var downY = maxOf(y1, y2)
                    // rotate left up
                    if (x1 - 1 >= 0 && boards[downY][x1 - 1] != 1 && board[upY][x1 - 1] == 0) {
                        if (downY == y1) {
                            if (count >= dp[y1 - 1][x1 - 1] || count >= dp[y2][x2]) {
                                println("from $x1 $y1 $x2 $y2")
                                println("rotate left up")
                                queue.add(listOf(x1 - 1, y1 - 1, x2, y2, count + 1))
                            }
                        } else {
                            if (count >= dp[y1][x1] || count >= dp[y2 - 1][x2 - 1]) {
                                println("from $x1 $y1 $x2 $y2")
                                println("rotate left up")
                                queue.add(listOf(x1, y1, x2 - 1, y2 - 1, count + 1))
                            }
                        }
                    }
                    // rotate left down
                    if (x1 - 1 >= 0 && boards[upY][x1 - 1] != 1 && board[downY][x1 - 1] == 0) {
                        if (upY == y1) {
                            if (count >= dp[y1 + 1][x1 - 1] || count >= dp[y2][x2]) {
                                println("from $x1 $y1 $x2 $y2")
                                println("rotate left down")
                                queue.add(listOf(x1 - 1, y1 + 1, x2, y2, count + 1))
                            }
                        } else {
                            if (count >= dp[y1][x1] || count >= dp[y2 + 1][x2 - 1]) {
                                println("from $x1 $y1 $x2 $y2")
                                println("rotate left down")
                                queue.add(listOf(x1, y1, x2 - 1, y2 + 1, count + 1))
                            }
                        }
                    }
                    // rotate right up
                    if (x1 + 1 < N && boards[downY][x1 + 1] != 1 && board[upY][x1 + 1] == 0) {
                        if (downY == y1) {
                            if (count >= dp[y1 - 1][x1 + 1] || count >= dp[y2][x2]) {
                                println("from $x1 $y1 $x2 $y2")
                                println("rotate right up")
                                queue.add(listOf(x1 + 1, y1 - 1, x2, y2, count + 1))
                            }
                        } else {
                            if (count >= dp[y1][x1] || count >= dp[y2 - 1][x2 + 1]) {
                                println("from $x1 $y1 $x2 $y2")
                                println("rotate right up")
                                queue.add(listOf(x1, y1, x2 + 1, y2 - 1, count + 1))
                            }
                        }

                    }
                    // rotate right down
                    if (x1 + 1 < N && boards[upY][x1 + 1] != 1 && board[downY][x1 + 1] == 0) {
                        if (upY == y1) {
                            if (count >= dp[y1 + 1][x1 + 1] || count >= dp[y2][x2]) {
                                println("from $x1 $y1 $x2 $y2")
                                println("rotate right down")
                                queue.add(listOf(x1 + 1, y1 + 1, x2, y2, count + 1))
                            }
                        } else {
                            if (count >= dp[y1][x1] || count >= dp[y2 + 1][x2 + 1]) {
                                println("from $x1 $y1 $x2 $y2")
                                println("rotate right down")
                                queue.add(listOf(x1, y1, x2 + 1, y2 + 1, count + 1))
                            }
                        }

                    }
                } else if (y1 == y2) { // horizontal
                    var leftX = minOf(x1, x2)
                    var rightX = maxOf(x1, x2)
                    // rotate left up
                    if (y1 - 1 >= 0 && boards[y1 - 1][rightX] != 1 && board[y1 - 1][leftX] == 0) {
                        if (rightX == x1) {
                            if (count >= dp[y1 - 1][x1 - 1] || count >= dp[y2][x2]) {
                                println("from $x1 $y1 $x2 $y2")
                                println("rotate left up")
                                queue.add(listOf(x1 - 1, y1 - 1, x2, y2, count + 1))
                            }
                        } else {
                            if (count >= dp[y1][x1] || count >= dp[y2 - 1][x2 - 1]) {
                                println("from $x1 $y1 $x2 $y2")
                                println("rotate left up")
                                queue.add(listOf(x1, y1, x2 - 1, y2 - 1, count + 1))
                            }
                        }
                    }
                    // rotate left down
                    if (y1 + 1 < N && boards[y1 + 1][rightX] != 1 && board[y1 + 1][leftX] == 0) {
                        if (rightX == x1) {
                            if (count >= dp[y1 + 1][x1 - 1] || count >= dp[y2][x2]) {
                                println("from $x1 $y1 $x2 $y2")
                                println("rotate left down")
                                queue.add(listOf(x1 - 1, y1 + 1, x2, y2, count + 1))
                            }
                        } else {
                            if (count >= dp[y1][x1] || count >= dp[y2 + 1][x2 - 1]) {
                                println("from $x1 $y1 $x2 $y2")
                                println("rotate left down")
                                queue.add(listOf(x1, y1, x2 - 1, y2 + 1, count + 1))
                            }
                        }
                    }
                    // rotate right up
                    if (y1 - 1 >= 0 && boards[y1 - 1][leftX] != 1 && board[y1 - 1][rightX] == 0) {
                        if (leftX == x1) {
                            if (count >= dp[y1 - 1][x1 + 1] || count >= dp[y2][x2]) {
                                println("from $x1 $y1 $x2 $y2")
                                println("rotate right up")
                                queue.add(listOf(x1 + 1, y1 - 1, x2, y2, count + 1))
                            }
                        } else {
                            if (count >= dp[y1][x1] || count >= dp[y2 - 1][x2 + 1]) {
                                println("from $x1 $y1 $x2 $y2")
                                println("rotate right up")
                                queue.add(listOf(x1, y1, x2 + 1, y2 - 1, count + 1))
                            }
                        }
                    }
                    // rotate right down
                    if (y1 + 1 < N && boards[y1 + 1][leftX] != 1 && board[y1 + 1][rightX] == 0) {
                        if (leftX == x1) {
                            if (count >= dp[y1 + 1][x1 + 1] || count >= dp[y2][x2]) {
                                println("from $x1 $y1 $x2 $y2")
                                println("rotate right down")
                                queue.add(listOf(x1 + 1, y1 + 1, x2, y2, count + 1))
                            }
                        } else {
                            if (count >= dp[y1][x1] || count >= dp[y2 + 1][x2 + 1]) {
                                println("from $x1 $y1 $x2 $y2")
                                println("rotate right down")
                                queue.add(listOf(x1, y1, x2 + 1, y2 + 1, count + 1))
                            }
                        }
                    }
                }
            }
            return answer
        }
    }

    class Solution2 {
        private lateinit var boards: List<MutableList<Int>>
        private lateinit var queue: MutableList<List<Int>>
        private lateinit var visited: MutableSet<Set<Pair<Int, Int>>>
        var N = 1
        fun solution(board: Array<IntArray>): Int { // 0,0 -> N-1,N-1
            var answer = board.size * board.size
            boards = List(board.size + 2) { MutableList(board.size + 2) { 1 } }
            for (y in 1..board.size) {
                for (x in 1..board.size) {
                    boards[y][x] = board[y - 1][x - 1]
                }
            }
            println("boards: $boards")
            queue = mutableListOf()
            visited = mutableSetOf()
            N = board.size
            queue.add(listOf(1,1,2,1,0))
            visited.add(setOf(Pair(1,1), Pair(2,1)))

            while (queue.isNotEmpty()) {
                val q = queue.first()
                queue.removeAt(0)

                val x1 = q[0]
                val y1 = q[1]
                val x2 = q[2]
                val y2 = q[3]
                val count = q[4]
                println("x1: $x1, x2: $x2")
                println("y1: $y1, y2: $y2")
                println("count: $count")
                if ((x1 == N && y1 == N) || (x2 == N && y2 == N)) {
                    answer = count
                    break
                }

                // action

                // go right
                if (boards[y1][x1 + 1] == 0 && boards[y2][x2 + 1] == 0) {
                    if (!visited.contains(setOf(Pair(x1+1,y1), Pair(x2+1,y2)))) {
                        queue.add(listOf(x1 + 1, y1, x2 + 1, y2, count + 1))
                        visited.add(setOf(Pair(x1 + 1, y1), Pair(x2 + 1, y2)))
                    }
                }
                // go left
                if (boards[y1][x1 - 1] == 0 && boards[y2][x2 - 1] == 0) {
                    if (!visited.contains(setOf(Pair(x1-1,y1), Pair(x2-1,y2)))) {
                        queue.add(listOf(x1 - 1, y1, x2 - 1, y2, count + 1))
                        visited.add(setOf(Pair(x1 - 1, y1), Pair(x2 - 1, y2)))
                    }
                }
                // go up
                if (boards[y1 - 1][x1] == 0 && boards[y2 - 1][x2] == 0) {
                    if (!visited.contains(setOf(Pair(x1,y1-1), Pair(x2,y2-1)))) {
                        queue.add(listOf(x1, y1 - 1, x2, y2 - 1, count + 1))
                        visited.add(setOf(Pair(x1, y1 - 1), Pair(x2, y2 - 1)))
                    }
                }
                // go down
                if (boards[y1 + 1][x1] == 0 && boards[y2 + 1][x2] == 0) {
                    if (!visited.contains(setOf(Pair(x1,y1+1), Pair(x2,y2+1)))) {
                        queue.add(listOf(x1, y1 + 1, x2, y2 + 1, count + 1))
                        visited.add(setOf(Pair(x1, y1 + 1), Pair(x2, y2 + 1)))
                    }
                }

                if (x1 == x2) { // vertical
                    var upY = minOf(y1, y2)
                    var downY = maxOf(y1, y2)
                    // 좌측 2개 칸이 모두 비어있을 때
                    if (boards[y1][x1 - 1] == 0 && boards[y2][x2 - 1] == 0) {
                        // rotate left up
                        if (!visited.contains(setOf(Pair(x1,upY), Pair(x2-1,upY)))) {
                            if (downY == y1) {
                                queue.add(listOf(x1 - 1, y1 - 1, x2, y2, count + 1))
                                visited.add(setOf(Pair(x1 - 1, y1 - 1), Pair(x2, y2)))
                            } else {
                                queue.add(listOf(x1, y1, x2 - 1, y2 - 1, count + 1))
                                visited.add(setOf(Pair(x1, y1), Pair(x2 - 1, y2 - 1)))
                            }
                        }
                        // rotate left down
                        if (!visited.contains(setOf(Pair(x1,downY), Pair(x2-1,downY)))) {
                            if (upY == y1) {
                                queue.add(listOf(x1 - 1, y1 + 1, x2, y2, count + 1))
                                visited.add(setOf(Pair(x1 - 1, y1 + 1), Pair(x2, y2)))
                            } else {
                                queue.add(listOf(x1, y1, x2 - 1, y2 + 1, count + 1))
                                visited.add(setOf(Pair(x1, y1), Pair(x2 - 1, y2 + 1)))
                            }
                        }
                    }
                    // 우측 2개 칸이 모두 비어있을 때
                    if (boards[y1][x1 + 1] == 0 && boards[y2][x2 + 1] == 0) {
                        // rotate right up
                        if (!visited.contains(setOf(Pair(x1,upY), Pair(x2+1,upY)))) {
                            if (downY == y1) {
                                queue.add(listOf(x1 + 1, y1 - 1, x2, y2, count + 1))
                                visited.add(setOf(Pair(x1 + 1, y1 - 1), Pair(x2, y2)))
                            } else {
                                queue.add(listOf(x1, y1, x2 + 1, y2 - 1, count + 1))
                                visited.add(setOf(Pair(x1, y1), Pair(x2 + 1, y2 - 1)))
                            }
                        }
                        // rotate right down
                        if (!visited.contains(setOf(Pair(x1,downY), Pair(x2+1,downY)))) {
                            if (upY == y1) {
                                queue.add(listOf(x1 + 1, y1 + 1, x2, y2, count + 1))
                                visited.add(setOf(Pair(x1 + 1, y1 + 1), Pair(x2, y2)))
                            } else {
                                queue.add(listOf(x1, y1, x2 + 1, y2 + 1, count + 1))
                                visited.add(setOf(Pair(x1, y1), Pair(x2 + 1, y2 + 1)))
                            }
                        }
                    }
                } else if (y1 == y2) { // horizontal
                    var leftX = minOf(x1, x2)
                    var rightX = maxOf(x1, x2)

                    // 상단 2개 칸이 모두 비어있을 때
                    if (boards[y1 - 1][x1] == 0 && boards[y2 - 1][x2] == 0) {
                        // rotate left up
                        if (!visited.contains(setOf(Pair(leftX,y1), Pair(leftX,y2 - 1)))) {
                            if (rightX == x1) {
                                queue.add(listOf(x1 - 1, y1 - 1, x2, y2, count + 1))
                                visited.add(setOf(Pair(x1 - 1, y1 - 1), Pair(x2, y2)))
                            } else {
                                queue.add(listOf(x1, y1, x2 - 1, y2 - 1, count + 1))
                                visited.add(setOf(Pair(x1, y1), Pair(x2 - 1, y2 - 1)))
                            }
                        }
                        // rotate right up
                        if (!visited.contains(setOf(Pair(rightX,y1), Pair(rightX,y2 - 1)))) {
                            if (leftX == x1) {
                                queue.add(listOf(x1 + 1, y1 - 1, x2, y2, count + 1))
                                visited.add(setOf(Pair(x1 + 1, y1 - 1), Pair(x2, y2)))
                            } else {
                                queue.add(listOf(x1, y1, x2 + 1, y2 - 1, count + 1))
                                visited.add(setOf(Pair(x1, y1), Pair(x2 + 1, y2 - 1)))
                            }
                        }
                    }
                    // 하단 2개 칸이 모두 비어있을 때
                    if (boards[y1 + 1][x1] == 0 && boards[y2 + 1][x2] == 0) {
                        if (!visited.contains(setOf(Pair(leftX,y1), Pair(leftX,y2 + 1)))) {
                            // rotate left down
                            if (rightX == x1) {
                                queue.add(listOf(x1 - 1, y1 + 1, x2, y2, count + 1))
                                visited.add(setOf(Pair(x1 - 1, y1 + 1), Pair(x2, y2)))
                            } else {
                                queue.add(listOf(x1, y1, x2 - 1, y2 + 1, count + 1))
                                visited.add(setOf(Pair(x1, y1), Pair(x2 - 1, y2 + 1)))
                            }
                        }
                        // rotate right down
                        if (!visited.contains(setOf(Pair(rightX, y1), Pair(rightX, y2 + 1)))) {
                            if (leftX == x1) {
                                queue.add(listOf(x1 + 1, y1 + 1, x2, y2, count + 1))
                                visited.add(setOf(Pair(x1 + 1, y1 + 1), Pair(x2, y2)))
                            } else {
                                queue.add(listOf(x1, y1, x2 + 1, y2 + 1, count + 1))
                                visited.add(setOf(Pair(x1, y1), Pair(x2 + 1, y2 + 1)))
                            }
                        }
                    }
                }
            }
            return answer
        }
    }

    val sol1 = Solution1()
    val sol2 = Solution2()
    var board: Array<IntArray>
    board = arrayOf(
        intArrayOf(0, 0, 0, 1, 1),
        intArrayOf(0, 0, 0, 1, 1),
        intArrayOf(0, 1, 0, 1, 1),
        intArrayOf(1, 1, 0, 0, 1),
        intArrayOf(0, 0, 0, 0, 0)
    )
//    println(sol1.solution(board))
    println(sol2.solution(board))
}
