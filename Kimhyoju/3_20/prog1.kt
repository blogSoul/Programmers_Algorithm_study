fun main() {
    class Solution {
        fun solution(table: Array<String>, languages: Array<String>, preference: IntArray): String {
            var answer: String = "SI"
            var topic = ""
            var maxSum = 0
            for (tableString in table) {
                val t = tableString.split(" ")
                topic = t[0]
                var sum = 0
                for ((idx,lang) in languages.withIndex()) {
                    if (t.indexOfFirst { it == lang } != -1) sum += (t.size - t.indexOfFirst { it == lang }) * preference[idx]
                }
                println("sum: $sum")
                if (sum > maxSum) {
                    answer = topic
                    maxSum = sum
                }
                else if (sum == maxSum) {
                    answer = minOf(answer,topic)
                }
            }
            return answer
        }
    }


    val sol = Solution()
    var stones: IntArray
    var k: Int
    var table = arrayOf(
        "SI JAVA JAVASCRIPT SQL PYTHON C#",
        "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++",
        "HARDWARE C C++ PYTHON JAVA JAVASCRIPT",
        "PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP",
        "GAME C++ C# JAVASCRIPT C JAVA"
    )
    var languages = arrayOf("PYTHON", "C++", "SQL")
    var preference = intArrayOf(7, 5, 5)
    table = arrayOf("SI JAVA JAVASCRIPT SQL PYTHON C#", "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++", "HARDWARE C C++ PYTHON JAVA JAVASCRIPT", "PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP", "GAME C++ C# JAVASCRIPT C JAVA")
    languages = arrayOf("JAVA", "JAVASCRIPT")
    preference = intArrayOf(7,5)

    println(sol.solution(table, languages, preference))
}
