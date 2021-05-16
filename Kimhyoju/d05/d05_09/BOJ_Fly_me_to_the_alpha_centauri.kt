package d05.d05_09

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// 한방에 정답을 알 수있는 방법을 찾다가 포기.
// 이건 그게 아니라, 몇번의 시도가 필요할 것 같다.

// 생각해본 결과,
// 121 3 번안에 이동하는 최대거리
// 1221 4번안에 이동하는 최대거리
// 12321 5번안에 이동하는 최대거리
// 123321 6번안에 이동하는 최대거리
// 1234321 7번안에 이동하는 최대거리
// 이렇게, y-x 인 diff 인 사이거리가 주어졌을 때,
// n과 n+1 사이의 거리 범위에 있다면 n+1 번안에 갈 수 있다.
// 시간초과가 떠서 느려서 그런줄 알았는데, int 범위를 추가해서 while이 무한루프 돌아서 그런것.


class BOJ_Fly_me_to_the_alpha_centauri {
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val t = br.readLine().toInt()
        repeat(t) {
            val (x, y) = br.readLine().split(" ").map { it.toInt() }
            val diff = y - x
            var answer = 0L
            var n = 0L
            var before = 0L
            while (true) {
                val k = n / 2
                if (n % 2 == 1L) {
                    val res = (k + 1) * (k + 1)
                    if (diff in (before + 1)..res) {
                        answer = n
                        break
                    }
                    before = res
                } else {
                    val res = k * (k + 1)
                    if (diff in (before + 1)..res) {
                        answer = n
                        break
                    }
                    before = res
                }
                n++
            }
            bw.write("$answer\n")
        }
        bw.close()
    }
}

fun main() {
    val c = BOJ_Fly_me_to_the_alpha_centauri()
    c.main()
}
