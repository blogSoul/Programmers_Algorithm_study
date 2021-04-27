package `4_27`

import java.util.*

// 원리를 찾으려다가 완탐하니까 걍 풀림.
// 근데 마지막 테스트 시간초과... 마지막에 두번째인 13번 테스트는 메모리를 1.24기가 썼다;
// 열리는 (할때 +1이 되는데, 이게 n보다 커지면 무효로 하기로 했다.
// 이렇게 개량했는데도 똑같네... 최대가 14쌍인데 왜 시간초과인지..
// 4개의 쌍이 있는 경우, ()(((( 는, value 가 4이하지만, (와 )는 각각 4번밖에 못 쓰므로, 이건 안되는 걸로 쳐야한다.
// 하나의 value가 아닌 2개의 open, close를 써야겠다.
// 이렇게 하니까 바로 통과. 1.24GB -> 333MB로 대폭 줄었다.
fun main() {
    data class Node(val index: Int, val open: Int, val close: Int)
    class Solution {
        fun solution(n: Int): Int {
            if (n == 1) return 1
            if (n == 2) return 2
            var answer = 0
            val queue: Queue<Node> = LinkedList()
            queue.offer(Node(0, 1,0))
            while (queue.isNotEmpty()) {
                val (index, open, close) = queue.poll()
                if (index == n * 2 - 1) {
                    if (open-close == 0) {
                        println("open: $open, close: $close")
                        answer++
                    }
                    continue
                }
                if (open > n || close > n) continue
                if (open-close > 0) queue.offer(Node(index + 1, open,close+1))
                queue.offer(Node(index + 1, open + 1, close))
            }
            return answer
        }
    }

    var n = 3
    println("solution: ${Solution().solution(n)}")
}
