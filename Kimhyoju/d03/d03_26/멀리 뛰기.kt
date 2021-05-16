// 팩토리얼로 풀 수 있는 것처럼 해놨지만, 개떡같은 문제이기에 오버플로우때문에 팩토리얼로는 절대 못품.
//  DP로 풀어야 한다.
fun main() {
    class Solution {
        fun solution(n: Int): Long {
            var answer: Long = 0
            var count = 1L
            var N = n / 2
            // 2개수 = 0 ~ N 개
            // 1개수 = n ~ n-2N개
            for (i in 1..N) { // i = 2의 개수
                var sum = 1L
                var min = minOf(i,n-2*i)
                var max = maxOf(i,n-2*i)
                for (j in max+1..n-i) {
                    sum *= j
                    sum%=1234567
                }
                for (j in 1..min) {
                    sum /= j
                }
                count += sum % 1234567
                count %= 1234567
                println("sum: $sum")
            }
            answer = count % 1234567
            return answer
        }
    }


    val sol = Solution()
    var n: Int

    n = 2000

    println(sol.solution(n))
}
