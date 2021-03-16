// failed. 답지 봤음.
// 조건 다 따지는 빡구현 문제인줄 알아서 하다가 뇌정지와서 포기함.
// 찾아보니, 간단히 푸는 방법이 있었다.
// 구조물 하나씩 추가하고 모든 구조물에 대해 조건을 만족하는지 찾아보는 방법
// 가장 간단하고 먼저 떠오른 생각이었지만 "비효율적"이라 생각해서 바로 패스했던 방법이다.
// 하지만 이 문제와 같은 경우 길이가 1000으로, 빨리 돌아간다.

// 92 / 100
// 뭐가 틀린지 찾아야함.


fun main() {
    class Solution {
        private lateinit var frames: MutableList<List<Int>>
        fun solution(n: Int, build_frame: Array<IntArray>): Array<IntArray> {
            frames = mutableListOf()
            var answer = arrayOf<IntArray>()
            for (order in build_frame) {
                var x = order[0]
                var y = order[1]
                var frame = order[2]
                var action = order[3]
                if (action == 0) frames.remove(order.slice(0..2))
                else frames.add(order.slice(0..2))
                if (!checkIntegrity()) {
                    if (action == 0) frames.add(order.slice(0..2))
                    else frames.removeAt(frames.lastIndex)
                }
            }

            frames.sortWith(compareBy({ it[0] }, { it[1] }, { it[2] }))
            println("sorted frames: $frames")
            answer = frames.map { it.toIntArray() }.toTypedArray()
            return answer
        }

        fun checkIntegrity(): Boolean {
            var result = false
            for (task in frames) {
                result = false
                var x = task[0]
                var y = task[1]
                var frame = task[2]
                if (frame == 0) { // pillar
                    if (y == 0 || frames.any { it[2] == 1 && it[0] == x && it[1] == y } ||
                        frames.any { it[2] == 1 && it[0] + 1 == x && it[1] == y } ||
                        frames.any { it[2] == 0 && it[0] == x && it[1] == y - 1 }) {
                        result = true
                    }
                    else return false
                } else { // beam
                    if (frames.any { it[2] == 0 && it[0] == x && it[1] == y - 1 } ||
                        frames.any { it[2] == 0 && it[0] == x + 1 && it[1] == y - 1 } ||
                        (frames.any { it[2] == 1 && it[0] == x - 1 && it[1] == y } &&
                                (frames.any { it[2] == 1 && it[0] == x + 1 && it[1] == y }))) {
                        result = true
                    }
                    else return false
                }
            }
            return result
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



    n = 5
    build_frame = arrayOf(
        intArrayOf(0, 0, 0, 1),
        intArrayOf(2, 0, 0, 1),
        intArrayOf(4, 0, 0, 1),
        intArrayOf(0, 1, 1, 1),
        intArrayOf(1, 1, 1, 1),
        intArrayOf(2, 1, 1, 1),
        intArrayOf(3, 1, 1, 1),
        intArrayOf(2, 0, 0, 0),
        intArrayOf(1, 1, 1, 0),
        intArrayOf(2, 2, 0, 1)
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
