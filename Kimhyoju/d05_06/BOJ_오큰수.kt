package d05_06

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

// 음.. 이분탐색으로도 풀 수 있을 것 같은데? 인덱스와 값 pair 로 저장해서, 값 우선, 그후 인덱스 순으로 정렬.
// 스택이라고 분류에 쓰여있어서, 스택에 집중해서 생각했다. 선형탐색으로는, O(N*N) 정도가 되겠다. N은 하나씩 줄어들지만..
// 이걸 O(N) 에 할 수 있을 것 같다.
// 1 5 3 2 6 8 4 로 생각해본 결과, waiting Stack을 선언하고 시작한다.
// stack이 비어있으므로 그냥 1 넣는다.
// 스택의 top은 1고 들어온건 5이므로 stack을 pop한다. 마지막에 들어온 5는 push한다.
// stack에 5가 있고 들어온건 3이다. top5 보다 작으므로 무시. 마지막에 들어온 3은 push.
// stack에 5,3이 있고 들어온건 2이다. top3 보다 작으므로 무시. 마지막에 들어온 2은 push.
// stack에 5,3,2가 있고 들어온건 6이다. top2 보다 크므로 pop한다. 그다음 top 3보다 크므로 pop, 그다음 top 5보다 크므로 pop.
// 마지막에 들어온 6은 push.
// 이렇듯. waiting STack을 유지하면서, 수가 하나 들어오면 top보다 큰지 작은지를 비교하고 작으면 바로 넘어가고 크면 stack을 pop하고
// 그 다음 top도 비교하고 이런식으로 한다.
// 솔직히 Pair로 스택에 저장할 필요는 없음. 인덱스만 있으면 됨. 그래도 뭔가 가독성을 위해서.

class BOJ_오큰수 {
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val n = br.readLine().toInt()
        val list = br.readLine().split(" ").map { it.toInt() }
        val answer = IntArray(n) { -1 }
        val stack = Stack<Pair<Int, Int>>() // Pair(index,value)
        for (i in list.indices) {
            while (stack.isNotEmpty()) {
                if (stack.peek().second >= list[i]) break
                else {
                    val (index, value) = stack.pop()
                    answer[index] = list[i]
                }
            }
            stack.push(Pair(i, list[i]))
        }
        for (num in answer) bw.write("$num ")
        br.close()
        bw.close()
    }
}

fun main() {
    val c = BOJ_오큰수()
    c.main()
}
