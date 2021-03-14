// 입국심사
fun main() {
    val sol = Solution6()
    var n = 10
    n = 6
//    n = 14
    var times = intArrayOf(7, 10)
    sol.solution(n, times)
}

class Solution6 {
    fun solution(n: Int, times: IntArray): Long {

        // 문제 자체는 간단하다.
        // times = [7, 10] 이면, 각각의 배수를 정렬해놓은 것에서
        // n번째를 찾는 것.
        // 문제는 그 수가 너무 크다는 것.

        // 계속 고민하다가, 아! 최소공배수! 하고 깨달았다.
        // 최소공배수를 하나의 사이클로 정하고
        // n이 78이고 최소공배수가 30이라면,
        // 60~90 안에 78번째  element가 존재할 것이다.
        // 여기서 18번째 element를 찾으면 된다.
        // times를 정렬하고, 거기서 18번째 element를 찾자.
        // 하지만 어떻게?

        //즉, 최소공배수를 하나의 사이클로 이것으로 rough하게 bound를 찾고
        // (최소공배수의 lower bound와 upper bound 를 찾고)
        // 그 사이에서 이진탐색을 하자.


        // 생각해보니, 굳이 최소공배수를 구하지 않아도 될 것 같다.
        // 만약 n이 78, times가 [2, 5, 7, 9, 13, 16, 19] 라면,
        // 78번째는 저 최소공배수인 1244880 가 upper bound, 2가 lowerbound이다.

        // 최소공배수는 비효율적인듯... 예를들면 n이 10억이고
        // times = [2,3] 이면, 최소공배수 6으로 얼마나 해야하는거야...
        // 차라리 upperbound를 2 x n 으로, lowerbound를 2 x n/2 로 하는게 나을듯 하다.

        // 생각해보니 이렇게 해도 반례가 있다.
        // n=5이고, times가 [9,10,11,12,13,14,15] 면
        // upper bound가 45, lower bound가 18인데, 정답은 13이다. 너무 크다....
        // 어떡하지...


        // 추정되는 시간 값 / 각 입국심사관들의 소요값 = n (심사받는 인원 수)

        var answer: Long = 0
        var people: Long
        var lb: Long = 1
        var ub: Long = (times.last().toLong() * n.toLong())
        var mid: Long = ((lb + ub) / 2)
        while (ub > lb) {
            people = 0
            mid = ((lb + ub) / 2)
            println("upper bound: $ub")
            println("lower bound: $lb")
            println("mid_time: $mid")
            for (time in times) {
                people += mid / time.toLong()
            }
            if (people >= n) {
                println("people: $people >= n: $n")
                ub = mid
            } else {
                println("people: $people < n: $n")
                lb = mid + 1
            }
        }
        // while을 빠져나올 땐 ub와 lb가 같아진다.
        answer = ub.toLong()
        println("Final Answer: $answer")
        return answer
    }
}