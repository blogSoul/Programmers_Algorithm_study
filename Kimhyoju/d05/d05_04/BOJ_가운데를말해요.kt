package d05.d05_04

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*


// https://regularmember.tistory.com/142
//https://www.crocus.co.kr/625
// 이 문제는 최소힙과 최대힙을 동시에 사용하는 문제다.
//1. 최소 힙의 값들은 모두 최대 힙보다 크도록하고
//2. 최대 힙의 크기가 최소 힙의 크기보다 1 크거나 같도록 유지하며 값을 넣는다.
//3. 값을 넣어준 후 최대 힙과 최소 힙의 top 값을 비교해서 최소 힙의 top보다 최대 힙의 top이 더 크다면 값을 교환해준다.
//그러면 최대 힙의 top값이 중간값이 된다.

// 문제 조건을 다시 읽어보니, 반으로 자르면, 앞의 것을 선택하도록 되어있다.
// 사이즈가 홀수면 당연히 가운데지만, 짝수면 가운데중에서도 앞.
// 여기서, 앞을 담당하는 것이 바로 max heap. 뒤는 min heap이다.
// max heap의 top이 중간값이되는 것.


// 이걸 어떻게 알아!! 혼자서 고민하다가 2개 다 쓰는건가 라고 생각은 했지만..

class BOJ_가운데를말해요 {
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val n = br.readLine().toInt()

        val maxHeap = PriorityQueue<Int>(Collections.reverseOrder())
        val minHeap = PriorityQueue<Int>()

        for (i in 0 until n) {
            val num = br.readLine().toInt()
            if (minHeap.size < maxHeap.size) minHeap.offer(num)
            else maxHeap.offer(num)
            if (minHeap.isNotEmpty() && minHeap.peek() < maxHeap.peek()) {
                val max = maxHeap.poll()
                val min = minHeap.poll()
                maxHeap.offer(min)
                minHeap.offer(max)
            }
            bw.write("${maxHeap.peek()}")
        }
        br.close()
        bw.close()
    }
}

fun main() {
    val c = BOJ_가운데를말해요()
    c.main()
}