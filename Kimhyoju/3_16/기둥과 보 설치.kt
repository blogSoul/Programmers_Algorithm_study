// failed. 답지 봤음.
// 조건 다 따지는 빡구현 문제인줄 알아서 하다가 뇌정지와서 포기함.
// 찾아보니, 간단히 푸는 방법이 있었다.
// 구조물 하나씩 추가하고 모든 구조물에 대해 조건을 만족하는지 찾아보는 방법
// 가장 간단하고 먼저 떠오른 생각이었지만 비효율적이라 생각해서 바로 패스했던 방법이다.
// 하지만 이 문제와 같은 경우 길이가 1000으로, 빨리 돌아간다.


fun main() {
    class Solution {
        fun solution(n: Int, build_frame: Array<IntArray>): Array<IntArray> {
            var answer = arrayOf<IntArray>()



            return answer
        }
    }

    val sol = Solution()
    var n: Int
    var build_frame: Array<IntArray>
    var result: Array<IntArray>
    n = 5
    build_frame =
        arrayOf(
            intArrayOf(1, 0, 0, 1),
            intArrayOf(1, 1, 1, 1),
            intArrayOf(2, 1, 0, 1),
            intArrayOf(2, 2, 1, 1),
            intArrayOf(5, 0, 0, 1),
            intArrayOf(5, 1, 0, 1),
            intArrayOf(4, 2, 1, 1),
            intArrayOf(3, 2, 1, 1)
        )



    result =
        arrayOf(
            intArrayOf(1, 0, 0),
            intArrayOf(1, 1, 1),
            intArrayOf(2, 1, 0),
            intArrayOf(2, 2, 1),
            intArrayOf(3, 2, 1),
            intArrayOf(4, 2, 1),
            intArrayOf(5, 0, 0),
            intArrayOf(5, 1, 0)
        )
    println(sol.solution(n, build_frame))
}
