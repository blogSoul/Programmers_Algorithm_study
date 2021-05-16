package d05.d05_06

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class BOJ_그룹_단어_체커 {
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val n = br.readLine().toInt()
        var count = 0
        repeat(n) {
            val word = br.readLine().toString()
            val letterMap = mutableMapOf<Char, Int>()
            var before = '0'
            var validFlag = true
            for (i in word.indices) {
                if (!letterMap.containsKey(word[i])) letterMap[word[i]] = i
                else {
                    if (i != letterMap.getOrDefault(word[i], 0) + 1) {
                        validFlag = false
                        break
                    } else letterMap[word[i]] = i
                }
            }
            if (validFlag) count++
        }
        br.close()
        bw.write("$count")
        bw.close()
    }
}

fun main() {
    val c = BOJ_그룹_단어_체커()
    c.main()
}
