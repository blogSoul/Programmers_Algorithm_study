package d05_07

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter


// 이거 원리가 뭐지..? 1자리일때는 2씩 올라가다가 그다음부턴 11같은데 아니네...
// 와.. 그냥 돌리면 된다.. 성능걱정안하고... 보이는대로 막 짜면 됨. 원리같은거 없음.
class BOJ_셀프_넘버 {
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val self = BooleanArray(10001)
        self[0] = true
        dn(self)
        for (i in 1..10_000) {
            if (!self[i]) bw.write("$i\n")
        }
        br.close()
        bw.close()
    }

    fun dn(self: BooleanArray) {
        for (i in 1..10_000) {
            var temp = i
            var res = temp
            while (temp <= 10_000) {
                if (temp >= 10000) {
                    res += temp / 10000
                    temp %= 10000
                }
                if (temp >= 1000) {
                    res += temp / 1000
                    temp %= 1000
                }
                if (temp >= 100) {
                    res += temp / 100
                    temp %= 100
                }
                if (temp >= 10) {
                    res += temp / 10
                    temp %= 10
                }
                if (temp < 10) {
                    res += temp
                }
                temp = res
                if (temp <= 10_000) {
                    if (self[temp]) break
                    self[temp] = true
                }
            }
        }
    }
}

fun main() {
    val c = BOJ_셀프_넘버()
    c.main()
}
