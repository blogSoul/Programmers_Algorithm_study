// 최대 힙, 최소 힙은 아는데, 이중 우선순위 큐는 직접 짜본적이 없다.
// 그냥 2개를 구현하면 되는 것 같다.

// Heap 클래스에서 data 인덱스의 2배면 left, 2배+1이면 right로 간단하게 구현하려 했는데,
// remove 함수는 cascading 후, 마지막 노드를 제거해줘야한다.
// 그냥 리니어한 배열로 관리하려면 level이 오를때마다 2의 n승만큼 더해주고 없어지면 삭제해야한느데
// 굉장히 귀찮다. 그냥 바꿔서 node클래스를 선언하고 left, right로 바꾸기로 했다.
// 실전에선 무조건 Node로 구현하도록 하자. 편하게 하려다가 피본다.

// 아니, 실전에선 그냥 PriorityQueue 를 쓰면 된다. 굳이 직접 구현할 필요 절대 없음.
// val pq = PriorityQueue<Int>()
// val reversePq = PriorityQueue<Int>(Comparator.reverseOrder())

package d4_26

import java.util.*

fun main() {
    class Solution {
        fun solution(operations: Array<String>): IntArray {
            var minHeap = PriorityQueue<Int>()
            var maxHeap = PriorityQueue<Int>(Comparator.reverseOrder())
            for (operation in operations) {
                val (op, t) = operation.split(" ")
                when (op) {
                    "I" -> { // Insert
                        minHeap.offer(t.toInt())
                        maxHeap.offer(t.toInt())
                    }
                    "D" -> { // Delete
                        if (minHeap.size > 0) {
                            when (t.toInt()) {
                                1 -> minHeap.remove(maxHeap.poll())
                                -1 -> maxHeap.remove(minHeap.poll())
                            }
                        }
                    }
                }
            }
            return if (minHeap.size == 0) intArrayOf(0, 0) else intArrayOf(maxHeap.poll(), minHeap.poll())
        }
    }

    var operations = arrayOf("I 16", "D 1")
    operations = arrayOf("I 7", "I 5", "I -5", "D -1")
    println("solution: ${Solution().solution(operations)}")
}
