// Level 4 문제인데도, 시간은 역시나 꽤 걸렸으나 혼자 풀 수 있었다.
// 처음에는 일단 유니온 파인드를 쓰는것같긴 했지만, 어떤 블록이 되는 블록이고 안되는 블록인지 구별을 위해서,
// 각 블록 객체를 만들고 4개의 좌표들을 저장한 후, 위에서 떨궜을 때 완성될 수 있는 타입을 1, 안되는 걸 0으로 미리 정했다.
// 그리고 유니온 파인드랑 비슷한 원리로, 그 블록의 위에 어떤 블록이라도 타입이 0이면 그것도 0으로 변하게 했다.
// 마지막 answer에는 최종적 블록들 객체중 type이 여전히 1인 것의 개수를 리턴했다.
fun main() {
    class Solution {
        fun solution(board: Array<IntArray>): Int {
            var answer = 0

            data class Block(
                val number: Int,
                val coordinate: MutableList<Pair<Int, Int>>,
                var type: Int,
                var dependencies: MutableSet<Int>
            )

            val N = board.size
            val blocks = mutableListOf<Block>()
            for (i in board.indices) {
                for (j in board.indices) {
                    if (board[i][j] != 0) {
                        if (blocks.isEmpty() || !blocks.any { it.number == board[i][j] }) {
                            println("add number : ${board[i][j]}")
                            blocks.add(Block(board[i][j], mutableListOf(Pair(i, j)), 1, mutableSetOf()))
                        } else blocks.first { it.number == board[i][j] }.coordinate.add(Pair(i, j))
                    }
                }
            }
            blocks.sortBy { it.number }
            println("blocks: $blocks")
            blocks.filter { it.number == 1 }.forEach { println(it.coordinate) }
            for (block in blocks) {
                var maxY = block.coordinate.first().first
                var minY = maxY
                var maxX = block.coordinate.first().second
                var minX = maxX
                for (cord in block.coordinate) {
                    if (cord.first > maxY) maxY = cord.first
                    else if (cord.first < minY) minY = cord.first
                    if (cord.second > maxX) maxX = cord.second
                    else if (cord.second < minX) minX = cord.second
                }
                println("in block number: ${block.number}")
                println("x: $minX ~ $maxX")
                println("y: $minY ~ $maxY")
                for (j in minX..maxX) {
                    if (board[maxY][j] != block.number) {
                        block.type = 0
                        break
                    }
                }
                println("type: ${block.type}")
                if (block.type == 1) { // 채워질 수 있는 블록들
                    var emptyCells = mutableListOf<Pair<Int, Int>>()
                    for (i in minY..maxY) {
                        for (j in minX..maxX) {
                            var cell = board[i][j]
                            if (cell != block.number) {
                                emptyCells.add(Pair(i, j))
                            }
                        }
                    }
                    for (cell in emptyCells) {
                        var y = cell.first
                        var x = cell.second
                        for (i in 0..y) {
                            if (board[i][x] != 0 && board[i][x] != block.number) {
                                println("${block.number} add board[$i][$x]: ${board[i][x]}")
                                block.dependencies.add(board[i][x])
                            }
                        }
                    }
                }
            }
            println("blocks: $blocks")
            fun isValid(num: Int): Boolean {
                var dependencies = blocks.first { it.number == num }.dependencies
                if (dependencies.isEmpty()){
                    return blocks.first { it.number==num }.type != 0
                }
                for (dep in dependencies) {
                    if (blocks.first { it.number == dep }.type == 0) return false
                    if (!isValid(dep)) return false
                }
                return true
            }
            for (block in blocks.filter { it.type == 1 }) {
                for (dep in block.dependencies) {
                    println("block: ${block.number}, dep: $dep")
                    if (!isValid(dep)) block.type = 0
                }
            }

            println(blocks.filter { it.type == 1 })
            answer = blocks.count { it.type == 1 }
            return answer
        }
    }

    val sol = Solution()

    var board: Array<IntArray>
    board =
        arrayOf(
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 4, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 4, 4, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 3, 0, 4, 0, 0, 0),
            intArrayOf(0, 0, 0, 2, 3, 0, 0, 0, 5, 5),
            intArrayOf(1, 2, 2, 2, 3, 3, 0, 0, 0, 5),
            intArrayOf(1, 1, 1, 0, 0, 0, 0, 0, 0, 5)
        )
    println(sol.solution(board))
}

