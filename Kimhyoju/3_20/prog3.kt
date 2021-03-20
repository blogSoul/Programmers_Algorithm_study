import java.util.*

fun main() {
    class Solution {
        fun solution(enter: IntArray, leave: IntArray): IntArray {
            var answer: IntArray = intArrayOf(enter.size)
            for ((idx1, ent) in enter.withIndex()) {
                var queue: Queue<Int> = LinkedList()
                for ((idx2, lev) in leave.withIndex()) {
                    if (ent == lev) {
//                        val inst1 = enter.slice(idx1+1..enter.lastIndex).intersect(leave.slice(0 until idx2))
//                        val inst2 = enter.slice(0 until idx1).intersect(leave.slice(idx2+1 until leave.size))

                        break
                    }
                    else {
                        queue.add(lev-1)
                    }
//                    else if (enter.indexOf(lev) > idx1) {
//                        meet[ent - 1].add(lev)
//                        meet[lev - 1].add(ent)
//                        println("meet $ent - $lev")
//                    }
                    println("idx2: $idx2 lev: $lev")
                    println("indexOf(lev): ${enter.indexOf(lev)}")
                    println("idx1: $idx1")
                }
            }
//            for (midNode in enter.indices) {
//                for (startNode in enter.indices) {
//                    for (endNode in enter.indices) {
//                        if (meet[startNode][midNode] == 1 && meet[midNode][endNode] == 1) {
//                            meet[startNode][endNode] = 1
//                        }
//                    }
//                }
//            }
//            meet.forEach { println(it.toList()) }
            println("")
            for (i in enter.indices) {
                for (v in enter.indices) {
                    if (i != v) {
//                        if ((meet[i].union(meet[v])).size < meet[i].size + meet[v].size) {
//                            meet[i].addAll(meet[v])
//                            meet[v].addAll(meet[i])
//                        }
                    }
                }
            }

//            meet.forEach { println(it.toList()) }
//            for (m in meet) {
//                answer.plus(m.count { it == 1 })
//            }
            return answer
        }
    }

    val sol = Solution()
    var enter: IntArray
    var leave: IntArray
    enter = intArrayOf(1, 3, 2)
    enter = intArrayOf(1, 4, 2, 3)
    leave = intArrayOf(1, 2, 3)
    leave = intArrayOf(2, 1, 3, 4)
    println(sol.solution(enter, leave))
}

