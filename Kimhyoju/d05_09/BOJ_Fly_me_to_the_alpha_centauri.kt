package d05_09

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// 한방에 정답을 알 수있는 방법을 찾다가 포기.
// 이건 그게 아니라, 몇번의 시도가 필요할 것 같다.

class BOJ_Fly_me_to_the_alpha_centauri {
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val t = br.readLine().toInt()
        repeat(t) {
            val (x, y) = br.readLine().split(" ").map { it.toInt() }
            var k = 1
            var max = 0
            while (true) {
                if (y-x >= k*k) {
                    max = k
                }
            }
            bw.write("$t")
        }
        bw.close()
    }
}

fun main() {
    val c = BOJ_Fly_me_to_the_alpha_centauri()
    c.main()
}
