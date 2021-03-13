// 단어 변환
fun main() {
    val sol = Solution()
    var begin = "hit"
    var target = "cog"
    var words = arrayOf("hot", "dot", "dog", "lot", "log", "cog")
    sol.solution(begin, target, words)
}

class Solution {
    fun solution(begin: String, target: String, words: Array<String>): Int {
        if (!words.contains(target)) return 0

        var answer = 0
        println("begin: $begin")
        println("target: $target")
        println("words: $words")
        var queue = mutableListOf<String>()
        var temp = mutableListOf<String>().also { it.add(begin) }
        val visited = MutableList(words.size) { 0 }

        var turn = 0
        var finished = 0
        while (temp.isNotEmpty() && answer == 0) {
            turn += 1
            queue.addAll(temp)
            temp.clear()
            while (queue.isNotEmpty() && answer == 0) {
                val newWord = queue.first().also { queue.removeFirst() }

                // 1. target 에 가까워지는 word 찾기.
                // words에서 target과 문자 1개라도 겹치는 것을 찾음으로써 target에 가까워지는 로직을 구현하려다가,
                // 반례) hit -> fit -> fog -> cog
                // 위와같은 반례가 너무 많아서, 완전탐색이라고 생각하고 풀었다.

                // 2. 문자 1개만 틀린 것 필터링해서 큐에 넣기.
                // 성능에 약간 의문점이 있어 보류.
                val filtered = words.filterIndexed { i, v ->
                    hasDifferenceOneLetter(newWord, v) and (visited[i] == 0) //? visited가 0이면 1로 동시에 어떻게 바꾸지
                }

                // 2. 문자 1개만 틀린 것 필터링해서 큐에 넣기.
                for (i in words.indices) {
                    if (hasDifferenceOneLetter(newWord, words[i]) and (visited[i] == 0)) {
                        if (words[i] == target) {
                            answer = turn
                            println("Target Found! $target")
                            break
                        }
                        visited[i] = 1
                        temp.add(words[i])
                    }
                }
            }
            println("turn: $turn")
            println("temp: $temp")
        }

        println("answer: $answer")
        return answer
    }

    fun hasDifferenceOneLetter(a: String, b: String): Boolean {
        var count = 0
        for (idx in a.indices) {
            if (a[idx] != b[idx]) count++
        }
        return count == 1
    }
}
