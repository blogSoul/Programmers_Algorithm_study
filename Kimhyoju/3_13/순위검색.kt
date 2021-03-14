import javax.xml.stream.events.Characters

// 순위 검색
fun main() {
    val sol = Solution4()
    var info = arrayOf(
        "java backend junior pizza 150",
        "python frontend senior chicken 210",
        "python frontend senior chicken 150",
        "cpp backend senior pizza 260",
        "java backend junior chicken 80",
        "python backend senior chicken 50"
    )
    var query = arrayOf(
        "java and backend and junior and pizza 100",
        "python and frontend and senior and chicken 200",
        "cpp and - and senior and pizza 250",
        "- and backend and senior and - 150",
        "- and - and - and chicken 100",
        "- and - and - and - 150"
    )
    sol.solution(info, query)
}

// 정답은 맞는데 계속해서 효율성 모두 실패.
// 리스트로만 할게 아니라, hashmap 이용
class Solution4 {
    fun solution(info: Array<String>, query: Array<String>): IntArray {
        val answer = mutableListOf<Int>()
        val infoTempList = info.map { it.split(" ") }
        val infoList = List(3) { List(2) { List(2) { List(2) { mutableListOf<Int>() } } } }

        // infoList[i][j][k][l] i: 언어 j: 분야, k: 경력, l: 음식
        val dict = mapOf(
            "cpp" to 0, "java" to 1, "python" to 2, "backend" to 0, "frontend" to 1,
            "junior" to 0, "senior" to 1, "chicken" to 0, "pizza" to 1, "-" to 9
        )
        // info DB 저장
        for (inf in infoTempList) {
            val i = dict.getOrDefault(inf[0], 9)
            val j = dict.getOrDefault(inf[1], 9)
            val k = dict.getOrDefault(inf[2], 9)
            val l = dict.getOrDefault(inf[3], 9)
            infoList[i][j][k][l].add(inf.last().toInt())
        }

        // 추후 이분탐색을 위한 모든 배열 정렬
        for (i in 0 until 3) {
            for (j in 0 until 2) {
                for (k in 0 until 2) {
                    for (l in 0 until 2) {
                        infoList[i][j][k][l].sort()
                    }
                }
            }
        }

        // Query 수행
        val qList = query.map {
            it.split(" ").filterNot { it == "and" }
        }
        for (q in qList) {
            var count = 0
            for (a in 0 until 3) {
                if (q[0] == "-" || a == dict.getOrDefault(q[0], 9)) {
                    for (b in 0 until 2) {
                        if (q[1] == "-" || b == dict.getOrDefault(q[1], 9)) {
                            for (c in 0 until 2) {
                                if (q[2] == "-" || c == dict.getOrDefault(q[2], 9)) {
                                    for (d in 0 until 2) {
                                        if (q[3] == "-" || d == dict.getOrDefault(q[3], 9)) {
                                            if (infoList[a][b][c][d].isNotEmpty() &&
                                                infoList[a][b][c][d].last() >= q.last().toInt()
                                            ) {
                                                var lb = 0
                                                var ub = infoList[a][b][c][d].lastIndex
                                                while (ub - lb > 1) {
                                                    var mid = (lb + ub) / 2
                                                    if (infoList[a][b][c][d][mid] >= q.last().toInt()) ub = mid
                                                    else lb = mid
                                                    // 그냥 탐색이 아닌, 이진 탐색을 해야한다.
                                                    // 이진탐색을 하니까 효율성 문제가 풀리네...
                                                }
                                                count += if (infoList[a][b][c][d][lb] >= q.last().toInt()) {
                                                    infoList[a][b][c][d].size - lb
                                                } else infoList[a][b][c][d].size - ub
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            answer.add(count)
            println("answer: $answer")
        }
        println(answer)
        return answer.toIntArray()
    }
}