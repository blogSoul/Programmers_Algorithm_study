package d04.d04_29

import java.util.*

// 하노이의 탑은 여러번 들어봤으나, 직접 짜본적은 하도 옛날이라 기억도 안난다.
// 그저 최소의 경우의 수만 따지라면 점화식을 세워서 구하는 거겠거니 해도
// 이건 아예 순서 자체를 물어보니까 완탐같다.

// 일단 최소 운반횟수는, N이 각각 1,2,3,4 일때
// 1, 3, 7, 15 이며 각 항의 차이는 2의 제곱수이다. 이로서, 점화식을 세울 수 있었다.
// = 2-1, 4-1, 8-1, 16-1, ... 2^n -1
// 최소 운반횟수를 구하라고 했으면 이미 푼거고, 이제 본 문제를 풀어보자.
// 최소 거리를 구하는 것이므로, BFS가 적절할 것 같다.
// 근데, 이동 경로랑 각각의 스택 상태를 큐에 그때마다 저장하면 로드가 너무 큰데...

// 풀긴 풀었는데 푸는데 엄청 걸렸다. 이런 구현에서 더 신속하게 할수있도록 실력을 기르자.
// 문제는, visited[pillar] clone을 해주지 않아서..
// 근데 마지막 5개가 시간초과..
// 지금은 pillar 변수를 고정시켜놓고,
// 큐를 꺼낼때마다 저 경로대로 옮기고, 다시 복구한다.
// 메모리 초과날까봐 저렇게 했는데, 흠... 스택 자체를 복사해서 큐에 담아볼까..?
// 메모리 초과날것같다.
// 더 쉽게 풀 수 있을 것 같아서 구글링해봄.
// 역시나.... 재귀로 풀면 엄청 쉽다. 분할 정복같다. 일반화시켜서..
// 아까 위에서, 최소 운반횟수는 점화식으로 풀 수 있다고 했는데,
// 이것도 또한 일반화 시켜서 경로도 구할 수 있다....
// https://shoark7.github.io/programming/algorithm/tower-of-hanoi

// 하노이의 탑은 재귀문제에서 거의 정석과도 같은 문제다. 확실히 짚고 넘어가야할 필요가 있다.
// solution 과 hanoi 함수로 구현했다... 알고보니 너무 쉬웠다.

fun main() {
    class Solution {
        lateinit var pillar: List<Stack<Int>>
        lateinit var visited: MutableMap<List<Stack<Int>>, Boolean>
        val answer = mutableListOf<List<Int>>()
        fun solution(n: Int): Array<IntArray> {
            hanoi(n,1,3,2)
            println("answer: $answer")
            return answer.map{it.toIntArray()}.toTypedArray()
        }
        fun hanoi(n: Int, start: Int, to: Int, via: Int) {
            if (n == 1) {
                move(start,to)
                return
            }
            hanoi(n-1,start,via,to)
            move(start,to)
            hanoi(n-1,via,to,start)
        }
        fun move(start: Int, to: Int) {
            answer.add(listOf(start,to))
        }

        fun mySolution(n: Int): Array<IntArray> {
            pillar = List<Stack<Int>>(3) { Stack() }
            visited = mutableMapOf()
            for (i in n downTo 1) pillar[0].push(i)
            var answer = mutableListOf<List<Int>>()

            val queue: Queue<MutableList<List<Int>>> = LinkedList()
            val initialList = mutableListOf<List<Int>>()
//            visited[pillar] = true
            queue.offer(initialList)
            while (queue.isNotEmpty()) {
                val list = queue.poll()
                println("list: $list")
//                println("initial pillar: $pillar")
                for (l in list) {
                    pillar[l[1] - 1].push(pillar[l[0] - 1].pop())
                }
                println("moved pillar: $pillar")
                if (pillar[2].size == n) {
                    println("result pillar: $pillar")
                    answer = list
                    break
                }
                if (visited[pillar] == true) {
                    println("visited pillar: $visited")
                    val reversedList = list.reversed()
                    for (l in reversedList) pillar[l[0] - 1].push(pillar[l[1] - 1].pop())
                    continue
                } else {
                    val newPillar = pillar.map{it.clone() as Stack<Int>}
                    visited[newPillar] = true
                    println("new visited : $pillar")
                }
                var newList = mutableListOf<List<Int>>()
                newList.addAll(list)

                // 1 -> 2
                movePlate(newList, queue, 1, 2)
                // 2 -> 1
                movePlate(newList, queue, 2, 1)
                // 1 -> 3
                movePlate(newList, queue, 1, 3)
                // 3 -> 1
                movePlate(newList, queue, 3, 1)
                // 2 -> 3
                movePlate(newList, queue, 2, 3)
                // 3 -> 2
                movePlate(newList, queue, 3, 2)
                // restoration
                val reversedList = list.reversed()
                for (l in reversedList) {
                    val plate = pillar[l[1] - 1].pop()
                    pillar[l[0] - 1].push(plate)
                }
//                println("restored pillar: $pillar")
            }
            println("answer: $answer")
            return answer.map { it.toIntArray() }.toTypedArray()
        }
        fun movePlate(newList: MutableList<List<Int>>, queue: Queue<MutableList<List<Int>>>, a: Int, b: Int) {
            if (pillar[a - 1].isEmpty()) {
                println("pillar[${a-1}] is empty. return.")
                return
            }
            if (pillar[b - 1].isEmpty() || pillar[b - 1].peek() > pillar[a - 1].peek()) {
                newList.add(listOf(a, b))
                println("offer $newList")
                queue.offer(newList.toMutableList())
                newList.removeAt(newList.lastIndex)
            }
        }
    }

    var n = 4
    println("solution: ${Solution().solution(n)}")
}
