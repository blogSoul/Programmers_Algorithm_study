fun main() {
    val sol = Solution2()
    var new_id = "...!@BaT#*..y.abcdefghijklm"
    sol.solution(new_id)
}

class Solution2 {
    fun solution(new_id: String): String {
        var answer: String = new_id
        println(answer)

        // 1. Captial to Small letter
        answer = answer.toLowerCase()
        println(answer)

        // 2. remove all except of letter, number, -, _, .
        answer = answer.filter {
            (it in 'a'..'z') or (it in '0'..'9') or (it == '-') or (it == '_') or (it == '.')
        }
        println(answer)

        // 3. continuous ... to single .
        var regex = "\\.+".toRegex()
        answer = answer.replace(regex, ".")
        println(answer)

        // 4. first and last . should be removed
        if (answer.isNotEmpty() && answer.first() == '.') answer = answer.removeRange(0..0)
        if (answer.isNotEmpty() && answer.last() == '.') answer = answer.removeRange(answer.lastIndex..answer.lastIndex)
        println(answer)

        // 5. if new_id is empty string, add a to new_id
        if (answer.isEmpty()) answer = "a"
        println(answer)

        // 6. if new_id is empty string, add a to new_id
        if (answer.length >= 16) answer = answer.take(15)
        if (answer.last() == '.') answer = answer.removeRange(answer.lastIndex..answer.lastIndex)
        println(answer)

        // 7. if new_id length <= 2, repeat new_id's last letter until new_id's length is 3
        if (answer.length <= 2) {
            val letter = answer.last()
            while (answer.length <= 2) {
                answer += letter
            }
        }
        println(answer)

        return answer
    }
}

