package d04.d04_23

// 작년쯤? 에 파이썬으로 풀다가 포기한 문제인 것 같더라. 그땐 뭐 알고리즘이고 뭐고 없이 놀기 바빴으니
// 이 문제도 당연히 못풀었을 것. 감회가 새롭다.

// 음.. 같은 소속에 속하는지를 묻는 것. Union-Find를 쓰면 되지않나? 라는 생각이 먼저 든다.
// 플로이드-와샬도 가능할 것 같다. (= 복서문제. A가 B를 이기고 B가 C를 이기면, A는 C를 이긴다.)

// 일단 유니온파인드로 한번 했는데 틀렸다.
// 또! val min = minOf(findUnion(idx1), findUnion(idx2)) 로 해야하는데,
// 또! val min = minOf(idx1, idx2) 로 해서 틀렸던 거. 나름 쉽게 풀었다.
fun main() {
    class Solution {
        lateinit var root: IntArray
        fun solution(n: Int, computers: Array<IntArray>): Int {
            var answer = 0
            root = IntArray(n) { it }
            for ((idx1, computer) in computers.withIndex()) {
                for ((idx2, com) in computer.withIndex()) {
                    if (idx1 == idx2) continue
                    if (com == 1 && !isUnion(idx1, idx2)) {
                        val min = minOf(findUnion(idx1), findUnion(idx2))
                        val max = maxOf(findUnion(idx1), findUnion(idx2))
                        union(min, max)
                        println("union $min, $max")
                        println("root: ${root.toList()}")
                    }
                }
            }
            for ((idx, r) in root.withIndex()) {
                root[idx] = findUnion(r)
            }
            println("root: ${root.toList()}")
            return root.toSet().size
        }

        fun findUnion(n: Int): Int {
            if (n == root[n]) return n
            else {
                root[n] = findUnion(root[n])
                return root[n]
            }
        }

        fun union(a: Int, b: Int) {
            // a < b
            root[b] = findUnion(a)
        }
        fun isUnion(a: Int, b: Int) = findUnion(a) == findUnion(b)
    }

    var n = 3
    var computers = arrayOf(intArrayOf(1, 1, 0), intArrayOf(1, 1, 0), intArrayOf(0, 0, 1))
    computers = arrayOf(intArrayOf(1, 1, 0), intArrayOf(1, 1, 1), intArrayOf(0, 0, 1))
    n = 7
    computers = arrayOf(
        intArrayOf(1, 0, 0, 0, 0, 1, 0),
        intArrayOf(0, 1, 1, 0, 0, 0, 0),
        intArrayOf(0, 1, 1, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 1, 0, 0, 1),
        intArrayOf(0, 0, 0, 0, 1, 1, 0),
        intArrayOf(1, 0, 0, 0, 1, 1, 0),
        intArrayOf(0, 0, 0, 1, 0, 0, 1)
    )
    println("solution: ${Solution().solution(n, computers)}")
}
