package d4_28

// 비트마스킹을 연습해보자
// shl 은 1할때마다 2배 증가. 이건 비트연산뿐만 아니라, 일반 연산에서도 유용하게 쓰일 것 같다.
// 2의 제곱씩 늘어나니, value를 2배, 4배, 8배 해야한다면, value shl 1, shl 2, shl 3 하면 된다.
// shr도 마찬가지. 1/2
// ushr 은 부호없음 오른쪽 이동
// and, or, xor, inv()

fun main() {
    class Solution {
        fun solution(a: Array<IntArray>): Int {
            var answer: Int = -1
            val horLen = a[0].size
            val verLen = a.size
            var row = 0b0011
            println("row: $row")
            row = row shl 2
            println("row: $row")
            row = row shr 3
            println("row: $row")
            var r = 63
            println(r shl 2)
            return answer
        }
    }

    var a = arrayOf(
            intArrayOf(0, 1, 0),
            intArrayOf(1, 1, 1),
            intArrayOf(1, 1, 0),
            intArrayOf(0, 1, 1)
    )
    println("solution: ${Solution().solution(a)}")
}
