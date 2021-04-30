package d4_30

// 최소공배수의 원리를 파악
// 4,6의 최대공약수는 2. 최소공배수는, 2 x 4를 2로나눈 2, 6을 2로나눈 3 = 12
// 6,8의 최대공약수는 2. 최소공배수는, 2 x 6를 2로나눈 3, 8을 2로나눈 4 = 24
// -> 두 수를 곱한 후 거기서 최대공약수로 나눠주면 최소공배수가 나온다.
// 문제는, 최대공약수 구하는 공식을 까먹음.
// 찾아보니, 유클리드 알고리즘(유클리드 호제법)을 이용하면 쉽게 구할 수 있다고 한다.
// 최대공약수: Greatest Common Divisor
// 최소공배수: Least Common Multiple
fun main() {
    class Solution {
        fun solution(arr: IntArray): Int {
            if (arr.size == 1) return arr.first()
            var answer = 0
            println(lcmList(arr.toList()))
            return answer
        }
        fun gcd(a: Int, b: Int): Int {
            var big = a
            var little = b
            if (a < b) {
                big = b
                little = a
            }
            while (little != 0) {
                var temp = little
                little = big % little
                big = temp
            }
            return big
        }

        fun gcdList(list: List<Int>): Int {
            var res = list.first()
            for (i in 1 until list.size) {
                res = gcd(res, list[i])
            }
            return res
        }

        fun lcm(a: Int, b: Int) = a * b / gcd(a, b)

        fun lcmList(list: List<Int>): Int {
            var res = list.first()
            for (i in 1 until list.size) {
                res = lcm(res, list[i])
            }
            return res
        }

    }

    var arr = intArrayOf(2, 6, 8, 14)
    println("solution: ${Solution().solution(arr)}")
}
