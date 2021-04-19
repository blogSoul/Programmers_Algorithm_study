import kotlin.text.StringBuilder

fun main() {
    class Solution {
        fun solution(numbers: String): Int {
            var answer = 0
            val numList = numbers.toList().map { it.toString() }
            val numStringList = numPermutation(numList)
            val numSet = numStringList.map{it.toInt()}.toSet()
            println(numSet)
            for (num in numSet) {
                if (isPrime(num)) answer++
            }
            return answer
        }

        fun numPermutation(list: List<String>): MutableList<String> {
            val res = mutableListOf<String>()
            var temp = StringBuilder()
            val visited = IntArray(list.size)
            for (i in list.indices) {
                res.add(list[i])
                visited[i] = 1
                temp.append(list[i])
                dfsNumPerm(list, temp, visited, res)
//                temp.deleteCharAt(0)
                temp.delete(0,1)
                visited[i] = 0
            }
            return res
        }

        fun dfsNumPerm(
            list: List<String>, temp: StringBuilder,
            visited: IntArray, res: MutableList<String>
        ) {
            if (list.size == temp.length) {
                res.add(temp.toString())
                return
            }
            for (i in list.indices) {
                if (visited[i] == 0) {
                    visited[i] = 1
                    temp.append(list[i])
                    res.add(temp.toString())
                    dfsNumPerm(list,temp,visited,res)
//                    temp.deleteAt(temp.lastIndex)
                    temp.delete(temp.lastIndex,temp.length)
                    visited[i] = 0
                }
            }

        }

        fun isPrime(n: Int): Boolean {
            if (n == 0) return false
            else if (n == 1) return false
            else if (n == 2) return true
            else if (n % 2 == 0) return false
            for (i in 2 until n) {
                if (n % i == 0) return false
            }
            return true
        }
    }

    val sol = Solution()
    var numbers = "1"
    numbers = "12013"
    println(sol.solution(numbers))
}