package d5_03

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// 음.. 의외로 이분탐색으로 풀 수 있는 문제라고 골드3인데... 모르겠다.
// N x N 배열을 만들어보니, mid는 항상 중앙에 있는, KxK의 위치에 있고 사각형으로 low, high와
// 이뤄지는데... 이것 이상으론 모르겠다.
// Googling..

// https://kyu9341.github.io/algorithm/2020/03/13/algorithm1300/
// B의 현재 원소의 인덱스를 구해 k와 비교하며 이분 탐색을 수행한다.
//인덱스를 구하는 방법은 현재 원소보다 작거나 같은 원소의 개수를 구하면 된다.
//예를 들어, 5 * 5 행렬에서 8보다 작거나 같은 수의 개수를 구한다면
//*1 *2 *3 *4 *5
//*2 *4 *6 *8 10
//*3 *6 9 12 15
//*4 *8 12 16 20
//*5 10 15 20 25
// 이건 알고있었는데... 대각선으로 긋는거..
// 근데 이게 그렇다는 보장이 있나? 여러번 해봤는데 이렇기만 하지도 않은 것 같던데.
// 아.. 내가한방법은 틀렸다. 나는 mid를 구하고, 거기부터 대각선을 그었는데
// 이건 그냥 mid보다 작은 수의 개수를 구한 것.

// 즉, 8을 1부터 n까지 각 행으로 나누었을 때의 몫이 그 행에서 8보다 작거나 같은 수의 개수가 된다.
// ex) 2로 8을 나누면 몫이 4이므로, 2행에서는 8보다 작거나 같은 값의 개수는 4개. 3으로8을 나누면 2이므로 2개.
// 1로 8을 나누면 8. 하지만 1행은 최대개수가 N=5이므로, 5개다. 즉, p행에서의 q보다 작거나 같은 값의 개수는,
// f(q) = min(N,q/p) 가 되겠다.
//또한 8로 나누어 떨어지는 행에는 8이 존재한다.
//따라서 위와 같이 8보다 작거나 같은 수의 개수를 구하면 14가 되고, 이것이 8의 인덱스가 된다.(1부터 시작)
// 즉, 위와같이 구한 f(q)의 합이 바로 q의 인덱스가 된다는 것이다. 다시말하면, q의 인덱스들 중 마지막 인덱스.
// : q의 lastIndex 가 된다.


class Boiler {
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val n = br.readLine().toLong()
        val k = br.readLine().toLong()
        br.close()
        var left = 1L
        var right = (n * n)
        var answer = 0L
        while (left <= right) {
            val mid = (left + right) / 2
            var res = 0L
            for (i in 1..n) {
                res += minOf(n, mid / i)
            }
            if (res >= k) {
                answer = mid
                right = mid - 1
            } else if (res < k) left = mid + 1
        }
        bw.write("$answer")
        bw.close()
    }
}

fun main() {
    val c = Boiler()
    c.main()
}
