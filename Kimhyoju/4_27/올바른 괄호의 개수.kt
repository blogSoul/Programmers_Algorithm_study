package `4_27`

import java.util.*

// 원리를 찾으려다가 완탐하니까 걍 풀림.
// 근데 마지막 테스트 시간초과... 마지막에 두번째인 13번 테스트는 메모리를 1.24기가 썼다;
// 열리는 (할때 +1이 되는데, 이게 n보다 커지면 무효로 하기로 했다.
// 이렇게 개량했는데도 똑같네... 최대가 14쌍인데 왜 시간초과인지..
// 4개의 쌍이 있는 경우, ()(((( 는, value 가 4이하지만, (와 )는 각각 4번밖에 못 쓰므로, 이건 안되는 걸로 쳐야한다.
// 하나의 value가 아닌 2개의 open, close를 써야겠다.
// 이렇게 하니까 바로 통과. 1.24GB -> 333MB로 대폭 줄었다.

// 정석대로 푸는 건, 카탈랑 수. 괄호문제를 풀때 중요한 개념이 될 것 같다. 공부해놓도록 하자.
// https://bb-dochi.tistory.com/51
// 우리는 n쌍의 괄호가 만든 모든 가능한 모든 괄호 형태의 개수를 Cn 이라 쓰고 이 를 카타란수(Catalan Numbers)라 부른다. 우리는 C0 = 0으로 정의하였다. 따라서 처음 4개의 카타란수는 다음과 같다
//C0 = 1, C1 = 1, C2 = 2, C3 = 5
//
//[C4를 구하는 방법]
//
//전제 : 왼쪽 끝에 한 쌍의 () 괄호를 두고, 이 괄호의 안과 밖을 생각해서 계산
//
//1단계. 왼쪽 끝 한 쌍의 괄호 () 안에는 없고, 오른쪽에 괄호 3쌍 두기
//() {3쌍의 괄호}   = 경우의 수 C0*C3
//
//2단계. 왼쪽 끝 한 쌍의 괄호 () 안에 1쌍의 괄호를 넣고, 오른쪽에 2쌍 두기
//({1쌍의 괄호}) {2쌍의 괄호}   = 경우의 수 C1*C2
//
//3단계. 왼쪽 끝 한 쌍의 괄호 () 안에 2쌍의 괄호를 넣고, 오른쪽에 1쌍 두기
//({2쌍의 괄호}) {1쌍의 괄호} = 경우의 수 C2*C1
//
//4단계. 왼쪽 끝 한 쌍의 괄호 () 안에 3쌍의 괄호를 넣고, 오른쪽에 0쌍 두기
//({3쌍의 괄호})  = 경우의 수 C3*C0
//
//
//결론적으로 C4 = C0*C3 + C1*C2 + C2*C1 + C3*C0 = 5+2+2+5 = 14
//
// class Solution {
//    public int solution(int n) {
//        int[] dp = new int[n+1];
//
//        dp[0] = 1;
//        dp[1] = 1;
//
//        for(int i = 2; i<=n; i++){
//            for(int j=1; j<=i; j++){
//                dp[i] += dp[i-j] * dp[j-1];
//            }
//        }
//
//        return dp[n];
//    }
//}

// 카탈란 공식을 적용시키니 완전히 빨리 된다.. 333MB -> 55MB, 1.14ms...

fun main() {
    class Solution {
        fun catalanSolution(n: Int): Int {
            val dp = IntArray(n+1)
            dp[0] = 1
            dp[1] = 1
            for (i in 2..n) {
                for (j in 0 until i) {
                    dp[i] += dp[j]*dp[i-1-j]
                }
            }
            return dp[n]
        }
        fun solution(n: Int): Int {
            data class Node(val index: Int, val open: Int, val close: Int)
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

    var n = 4
    println("solution: ${Solution().solution(n)}")
    println("catalan solution: ${Solution().catalanSolution(n)}")
}
