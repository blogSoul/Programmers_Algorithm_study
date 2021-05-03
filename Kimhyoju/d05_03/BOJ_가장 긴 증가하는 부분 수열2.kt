package d05_03

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

// 수열을 만들어보고, 수열의 원소들간의 차도 확인해보고, 정렬도 해봤는데 모르겠다..
// 인덱스와 값을 Pair로해서 정렬하는 건가..?
// K번째수와 마찬가지로, 어떻게 접근해야할지를 모르겠다... 구글ㅐ링.

// LIS = Longest Increasing Subsequence.
// 이 시리즈의 1번은 DP로 푸는 거다. dp[x] 는 x번째 수를 마지막 원소로 가지는 lis의 길이.
// DP로 풀땐 데이터수가 적어서 이중 포문으로 푼다. 하지만 이건 N이 100만.
// 이걸 푸는 방법은 2개. 세그먼트 트리와 이분탐색.
// 이분탐색으로 푸는게 더 간단하다고 한다.
// 쉽게 말하면 이 문제는, 수열을 처음부터 끝까지 읽어가면서 x에 대해 stack의 top보다 크면 push, 작으면 lower bound(x)의 인덱스에
// 넣는 것이다.
// 이걸 어떻게 알아!!!


class 가장긴증가하는부분수열2 {
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val n = br.readLine().toInt()
        val sequence = br.readLine().split(" ").map { it.toInt() }
        val stack = Stack<Int>()
        for (num in sequence) {
            if (stack.isEmpty() || num > stack.peek()) stack.push(num)
            else stack[lowerBound(stack,num)] = num
        }
        bw.write("${stack.size}")
        bw.close()
    }
    fun lowerBound(stack: Stack<Int>, target: Int): Int {
        var low = 0
        var high = stack.lastIndex
        while (low < high) {
            val mid = (low+high)/2
            if (stack[mid] >= target) high = mid
            else low = mid+1
        }
        return low
    }
}

fun main() {
    val c = 가장긴증가하는부분수열2()
    c.main()
}
