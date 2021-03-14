fun main() {
    class Solution {
        fun solution(n: Int, s: Int, a: Int, b: Int, fares: Array<IntArray>): Int {
            var answer: Int = 0



            return answer
        }
    }

    val sol = Solution()
    var n: Int
    var s: Int
    var a: Int
    var b: Int
    var fares: Array<IntArray>
    var varList = listOf(
        6,
        4,
        6,
        2
    )
    fares = arrayOf(
        intArrayOf(4, 1, 10),
        intArrayOf(3, 5, 24),
        intArrayOf(5, 6, 2),
        intArrayOf(3, 1, 41),
        intArrayOf(5, 1, 24),
        intArrayOf(4, 6, 50),
        intArrayOf(2, 4, 66),
        intArrayOf(2, 3, 22),
        intArrayOf(1, 6, 25)
    )
    n = varList[0]
    s = varList[1]
    a = varList[2]
    b = varList[3]

    sol.solution(n, s, a, b, fares)
}
