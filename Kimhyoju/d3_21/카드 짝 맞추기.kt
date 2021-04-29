import java.util.*

// 시간 엄청 잡아먹었다. 시뮬레이션 식으로
// 큐 자료구조를 존나게 만들어서 각각 bfs를 돌리는 식이다.
// 솔직히 속으론 메모리를 돼지같이 다 먹네 하면서 정답이 아닌걸 알지만
// 일단은 해보자 해서 완성은 했다. 정확하긴 한거 같은데 문제는
// 대부분이 시간초과다. 고민한 결과 카드를 먹기 전과 먹은 후
// visited 를 만들고 초기화하는 Queue를 넣을까 하다가 뇌절인 것 같아서
// 그냥 하지말고 Googling을 해봤다. 백트래킹이다..
// 그니까 그냥 처음부터 카드 쌍 먹는 순서를 permutation 해서 정해놓고
// 그에 맞춰서 최단 경로를 찾는 거였다.
// 결국 이 문제는 최단경로 + 백트래킹.
// 한 점에서 다른 점까지의 길이는 BFS 로 찾는 건 맞다.
// 하지만 난 여기서 BFS 로 모든 걸 해결하려 했으니... 틀린거지.

/*
일단 문제의 큰 틀을 잡아보면 4가지 과정으로 나눌 수 있습니다.

1. 어떤 짝 먼저 맞춰갈지에 대한 순서 정하기

2. 순서를 정했다면 한 쌍에서 어떤 카드부터 선택 할지 정하기

3. 2번 과정까지 완료 됐다면 각각 지점사이에 이동 횟수를 알아내어 전체 경로의 이동횟수를 구하기 (BFS)

4. 3번 과정을 반복하며 최소값 갱신
*/

// Permutation 작업에만 엄청 걸렸다. 또 Permutation 한거를 setList 와 비교하는데에도 걸리고..
// 여러번 해봐야겠다. 내 백트래킹 실력 다 없어졌다 몇달동안 쉬었다고...


// 아무리 해도 90 / 100
// 10, 22, 25 번만 실패다. 문제가 뭐지. 반례생각해서 여러개 돌려봐도 다 되는데..
// 다른 사람들이 4방향 처리하는 것과 내가 하는것과 코드 수가 너무 차이난다.
// 좀 줄이는 방법을 찾아야겠다


fun main() {
    class Solution {
        fun solution(board: Array<IntArray>, r: Int, c: Int): Int {
            var answer = Int.MAX_VALUE
            var distSum: Int

            var brd = board.map { it.clone() }.toTypedArray()

            val cardSet = mutableSetOf<Int>()
            for (i in board.indices) {
                for (j in board[0].indices) {
                    if (board[i][j] != 0) {
                        cardSet.add(board[i][j])
                    }
                }
            }
            println("cardSet: $cardSet")

            val cardPosition = List(cardSet.max()!!.toInt() + 1) { mutableListOf<Pair<Int, Int>>() }
            for (i in board.indices) {
                for (j in board[0].indices) {
                    if (board[i][j] != 0) {
                        val found = board[i][j]
                        cardPosition[found].add(Pair(i, j))
                    }
                }
            }
            println("cardPosition: $cardPosition")

            // BFS 를 이용한 최단거리 함수는 일단 만들었음.
            // distanceTo (0,0) -> (1,2): ${distanceTo(0, 0, 1, 2, board, cardSet)}

            // 이제 Permutation 으로 모든 쌍의 순서를 구하고, 각 쌍마다 둘중 어느 카드를 먼저 찾을 것인지 결정.
            val permSetList = permutationSet(cardSet)
//            println("permSetList: $permSetList")
            for (setList in permSetList) {
                val permList = permutationList(setList, cardPosition)
                println("setList: $setList")
                println("permList: $permList")
                println("permList.size: ${permList.size}")
                for (list in permList) {
                    println("list: $list")
                    distSum = 0
                    var startY = list[0].first
                    var startX = list[0].second
                    println("finding ${setList[0]}")
                    var tempBrd = brd.map { it.clone() }.toTypedArray()
                    distSum += distanceTo(r, c, startY, startX, tempBrd, cardSet)
                    tempBrd[startY][startX] = 0
                    tempBrd.forEach { println(it.toList()) }
                    var count = -2
                    val subtractSetList = mutableSetOf<Int>()
                    for ((idx, pair) in list.withIndex()) {
                        if (idx > 0) {
                            count++
                            if (count % 2 == 0) {
                                subtractSetList.add(setList[count / 2]) // 1, 3
                            }
                            val y = pair.first
                            val x = pair.second
                            println("finding ${setList[idx / 2]}")
                            distSum += distanceTo(startY, startX, y, x, tempBrd, cardSet.subtract(subtractSetList))
                            tempBrd[y][x] = 0
                            tempBrd.forEach { println(it.toList()) }
                            startY = y
                            startX = x
                        }
                    }
                    println("distSum: $distSum")
                    if (distSum < answer) {
                        answer = distSum
//                        if (distSum == 16) return 0
                    }
                }
            }
            answer += (cardSet.size * 2)
            println("answer: $answer")
            return answer
        }

        fun permutationSet(set: Set<Int>): List<List<Int>> {
            val result = mutableListOf<List<Int>>()
            val visited = IntArray(set.size)
            fun dfs(temp: MutableList<Int>) {
                if (temp.size == set.size) {
                    result.add(temp.toList())
                    return
                }
                for ((idx, el) in set.withIndex()) {
                    if (visited[idx] == 0) {
                        visited[idx] = 1
                        temp.add(el)
                        dfs(temp)
                        temp.removeAt(temp.lastIndex)
                        visited[idx] = 0
                    }
                }
            }
            for ((idx, el) in set.withIndex()) {
                val temp = mutableListOf<Int>()
                visited[idx] = 1
                temp.add(el)
                dfs(temp)
                temp.removeAt(0)
                visited[idx] = 0
            }
            return result
        }

        fun permutationList(
            list: List<Int>,
            cardPosition: List<MutableList<Pair<Int, Int>>>
        ): MutableList<List<Pair<Int, Int>>> {
            val result = mutableListOf<List<Pair<Int, Int>>>()
            val visited = IntArray(list.size)
            fun dfs(idx: Int, temp: MutableList<Pair<Int, Int>>) {
                if (temp.size == list.size * 2) {
//                    println("result: $result")
                    result.add(temp.toList())
                    return
                }
                if (visited[idx] == 0) {
                    val el = list[idx]
                    visited[idx] = 1
                    temp.add(cardPosition[el][0])
                    temp.add(cardPosition[el][1])
                    dfs(idx + 1, temp)
                    temp.removeAt(temp.lastIndex - 1)
                    temp.add(cardPosition[el][0])
                    dfs(idx + 1, temp)
                    temp.removeAt(temp.lastIndex)
                    temp.removeAt(temp.lastIndex)
                    visited[idx] = 0
                }
            }

            val idx = 0
            val el = list[idx]
            val temp = mutableListOf<Pair<Int, Int>>()
            visited[idx] = 1
            temp.add(cardPosition[el][0])
            temp.add(cardPosition[el][1])
            dfs(idx + 1, temp)
            temp.removeAt(0)
            temp.add(cardPosition[el][0])
            dfs(idx + 1, temp)
            temp.removeAt(temp.lastIndex)
            temp.removeAt(temp.lastIndex)
            visited[idx] = 0
            return result
        }

        // 이제 BFS 를 이용해서 최단거리 구하는 함수 작성. 이미 찾아서 없어진 카드는 빼고 계산.
        fun distanceTo(
            startY: Int,
            startX: Int,
            destY: Int,
            destX: Int,
            brd: Array<IntArray>,
            existingCard: Set<Int>
        ): Int {
            var cursor = Pair(startY, startX)
            var count = 0

            val visited = Array(brd.size) { IntArray(brd.size) }
            visited[cursor.first][cursor.second] = 1
            val queueCursor: Queue<Pair<Int, Int>> = LinkedList()
            val queueCount: Queue<Int> = LinkedList()

            queueCursor.add(cursor)
            queueCount.add(count)

            while (cursor.first != destY || cursor.second != destX) {
                cursor = queueCursor.poll()
                count = queueCount.poll()

                var index: Int

                // go right
                if (cursor.second != 3) {
                    index = cursor.second + 1
                    if (visited[cursor.first][index] == 0) {
                        queueCursor.add(Pair(cursor.first, index))
                        queueCount.add(count + 1)
                        visited[cursor.first][index] = 1
                    }
                }

                // go ctrl right
                index = -1
                if (cursor.second != 3) {
                    for (i in cursor.second + 1 until 4) {
                        if (brd[cursor.first][i] != 0) { // 오른쪽으로 갈때 맨 첫번째로 걸리는 카드 좌표로.
                            index = i
                            break
                        }
                    }
                    if (index == -1) index = 3 // 오른쪽으로 CTRL 할때 걸리는 카드 없으면 그냥 오른쪽 끝으로.

                    if (visited[cursor.first][index] == 0) {
                        queueCursor.add(Pair(cursor.first, index))
                        queueCount.add(count + 1)
                        visited[cursor.first][index] = 1
                    }
                }

                // go left
                if (cursor.second != 0) {
                    index = cursor.second - 1

                    if (visited[cursor.first][index] == 0) {
                        queueCursor.add(Pair(cursor.first, index))
                        queueCount.add(count + 1)
                        visited[cursor.first][index] = 1
                    }
                }

                // go ctrl left
                index = -1
                if (cursor.second != 0) {
                    for (i in 0 until cursor.second) {
                        if (brd[cursor.first][i] != 0) {
                            index = i
                        }
                    }
                    if (index == -1) index = 0

                    if (visited[cursor.first][index] == 0) {
                        queueCursor.add(Pair(cursor.first, index))
                        queueCount.add(count + 1)
                        visited[cursor.first][index] = 1
                    }
                }

                // go up
                if (cursor.first != 0) {
                    index = cursor.first - 1

                    if (visited[index][cursor.second] == 0) {
                        queueCursor.add(Pair(index, cursor.second))
                        queueCount.add(count + 1)
                        visited[index][cursor.second] = 1
                    }
                }

                // go ctrl up
                index = -1
                if (cursor.first != 0) {
                    for (i in 0 until cursor.first - 1) {
                        if (brd[i][cursor.second] != 0) {
                            index = i
                        }
                    }
                    if (index == -1) index = 0

                    if (visited[index][cursor.second] == 0) {
                        queueCursor.add(Pair(index, cursor.second))
                        queueCount.add(count + 1)
                        visited[index][cursor.second] = 1
                    }
                }

                // go down
                if (cursor.first != 3) {
                    index = cursor.first + 1

                    if (visited[index][cursor.second] == 0) {
                        queueCursor.add(Pair(index, cursor.second))
                        queueCount.add(count + 1)
                        visited[index][cursor.second] = 1
                    }
                }

                // go ctrl down
                index = -1
                if (cursor.first != 3) {
                    for (i in cursor.first + 1 until 4) {
                        if (brd[i][cursor.second] != 0) {
                            index = i
                            break
                        }
                    }
                    if (index == -1) index = 3
                    if (visited[index][cursor.second] == 0) {
                        queueCursor.add(Pair(index, cursor.second))
                        queueCount.add(count + 1)
                        visited[index][cursor.second] = 1
                    }
                }
            }
            println("($startY,$startX) -> ($destY,$destX): $count")
            return count
        }
    }

    val sol = Solution()
    var board: Array<IntArray>
    var r: Int
    var c: Int

    board = arrayOf(
        intArrayOf(1, 0, 0, 3),
        intArrayOf(2, 0, 0, 0),
        intArrayOf(0, 0, 0, 2),
        intArrayOf(3, 0, 1, 0)
    )
    r = 1
    c = 0
//
//    board = arrayOf(
//        intArrayOf(3, 0, 0, 2),
//        intArrayOf(0, 0, 1, 0),
//        intArrayOf(0, 1, 0, 0),
//        intArrayOf(2, 0, 0, 3)
//    )
//    r = 0
//    c = 1
//
//    board = arrayOf(
//        intArrayOf(1, 0, 0, 0),
//        intArrayOf(0, 0, 0, 0),
//        intArrayOf(0, 0, 0, 0),
//        intArrayOf(0, 0, 1, 0)
//    )
//    r = 1
//    c = 0
//
//
//    board = arrayOf(
//        intArrayOf(1, 2, 2, 3),
//        intArrayOf(0, 6, 3, 4),
//        intArrayOf(0, 5, 4, 5),
//        intArrayOf(6, 0, 1, 0)
//    )
//    r = 3
//    c = 1
//
//    board = arrayOf(
//        intArrayOf(1, 2, 2, 3),
//        intArrayOf(0, 0, 3, 0),
//        intArrayOf(0, 4, 0, 4),
//        intArrayOf(0, 0, 1, 0)
//    )
//    r = 0
//    c = 0
//
//    board = arrayOf(
//        intArrayOf(1, 0, 0, 3),
//        intArrayOf(0, 0, 3, 0),
//        intArrayOf(0, 2, 0, 2),
//        intArrayOf(0, 0, 1, 0)
//    )
//    r = 0
//    c = 0
//
//    board = arrayOf(
//        intArrayOf(6, 0, 0, 0),
//        intArrayOf(0, 0, 0, 0),
//        intArrayOf(0, 0, 0, 0),
//        intArrayOf(0, 0, 6, 0)
//    )
//    r = 2
//    c = 3
    println(sol.solution(board, r, c))

    // Mutable 객체를 큐에 넣으면, 변화가 적용됨. 주의.
//    var a = mutableListOf(1,2,3)
//    val q: Queue<List<Int>> = LinkedList()
//    q.add(a)
//    println("q.front: ${q.element()}")
//    a[0] = 9
//    println("q.front: ${q.element()}")
}
