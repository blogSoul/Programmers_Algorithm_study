import java.nio.file.FileVisitOption

fun main() {
    class Solution {
        fun solution(needs: Array<IntArray>, r: Int): Int {
            var answer: Int = 0
            val N = needs[0].size
            val combinations = combinationZeros(N, r)
            val map = mutableMapOf<Int, Int>()
            for (need in needs) {
                    println(need.joinToString(""))
                val a = need.joinToString("").toInt(2)
                for (comb in combinations) {
                    val b = comb.joinToString("").toInt(2)
                    if (a or b == b) {
                        map[b] = map.getOrDefault(b,0)+1
                    }
                }
            }
            println("map: $map")
            var max = 0
            for (key in map.keys) {
                if (map[key]!! > max) {
                    answer = key
                    max = map[key]!!
                }
            }
            println("answer: ${answer.toString(2)}b")
            println("max: $max")
            return answer
        }

        fun combinationZeros(n: Int, r: Int): MutableList<List<Int>> {
            val res = mutableListOf<List<Int>>()
            val temp = mutableListOf<Int>()
            temp.add(0)
            dfsCombZeros(n, r, temp, res)
            temp.removeAt(0)
            temp.add(1)
            dfsCombZeros(n, r, temp, res)
            temp.removeAt(0)
            return res
        }

        fun dfsCombZeros(n: Int, r: Int, list: MutableList<Int>, res: MutableList<List<Int>>) {
            if (list.size == n) {
                if (list.count { it == 1 } == r) {
                    res.add(list.toList())
                    return
                } else return
            }
            list.add(0)
            dfsCombZeros(n, r, list, res)
            list.removeAt(list.lastIndex)
            if (list.count { it == 1 } < r) {
                list.add(1)
                dfsCombZeros(n, r, list, res)
                list.removeAt(list.lastIndex)
            }
        }
    }

    val sol = Solution()
    val needs = arrayOf(
        intArrayOf(1, 0, 0), intArrayOf(1, 1, 0), intArrayOf(1, 1, 0), intArrayOf(1, 0, 1),
        intArrayOf(1, 1, 0), intArrayOf(0, 1, 1)
    )
    var r = 2
    println(sol.solution(needs, r))
}