package d05.d05_06

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// 실버 5인데 좀 걸림. 반례를 찾기 힘들어서..
// 반례: abcc-c=dd-dz=efghijklljmnnjoprss=tuvzz=dzempersljjlljnjjnnjjdzz=d-z
// 정답: 52
class BOJ_크로아티아_알파벳 {
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val word = br.readLine().toString()
        var cur = 0
        var count = 0
        var sb = StringBuilder()
        val wordSet = mutableSetOf("c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z=")
        while (cur < word.length) {
            sb.append(word[cur])
            if (wordSet.contains(sb.toString())) {
                sb.clear()
                count++
            } else if (sb.length == 2) {
                if (sb.toString() != "dz") {
                    sb = StringBuilder(sb.removeRange(0..0))
                    count++
                }
            } else if (sb.length == 3) {
                sb = StringBuilder(sb.removeRange(0..0))
                count++
                if (wordSet.contains(sb.toString())) {
                    sb.clear()
                    count++
                }
            }
            println("sb: $sb, count: $count")
            cur++
        }
        count += sb.length
        bw.write("$count")
        bw.close()
    }
}

fun main() {
    val c = BOJ_크로아티아_알파벳()
    c.main()
}
