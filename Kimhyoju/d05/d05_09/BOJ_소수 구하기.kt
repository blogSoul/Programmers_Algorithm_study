package d05.d05_09

// 1. 2부터 x의 루트까지 범위에서 나눠지면 소수가 아님.
// 2. 에라토스테네스의 체로 푸는 방법
// 내가 아는 에라토스테네스의 체는 2부터 시작해서 하나씩 돌려가면서 기존의 체에 있는거로 나눠지지않으면 더하는 식으로 했는데.. 흠.
// 근데 아닌듯.

// https://velog.io/@max9106/Algorithm-%EC%97%90%EB%9D%BC%ED%86%A0%EC%8A%A4%ED%85%8C%EB%84%A4%EC%8A%A4%EC%9D%98-%EC%B2%B4
// 단일 숫자 소수 여부 확인
//어떤 수의 소수의 여부를 확인 할 때는, 특정한 숫자의 제곱근 까지만 약수의 여부를 검증하면 O(N^1/2)의 시간 복잡도로 빠르게 구할 수 있다.
//수가 수(N이라고 가정)를 나누면 몫이 생기는데, 몫과 나누는 수 둘 중 하나는 N 제곱근 이하이기 때문이다.
//만약, 대량의 소수를 한꺼번에 판별해야할 경우는 '에라토스테네스의 체'를 이용한다.

// 에라토스테네스의 체 원리
// 에라토스테네스의 체는 가장 먼저 소수를 판별할 범위만큼 배열을 할당하여, 해당하는 값을 넣어주고, 이후에 하나씩 지워나가는 방법을 이용한다.
// 배열을 생성하여 초기화한다.
// 2부터 시작해서 특정 수의 배수에 해당하는 수를 모두 지운다.(지울 때 자기자신은 지우지 않고, 이미 지워진 수는 건너뛴다.)
// 2부터 시작하여 남아있는 수를 모두 출력한다.

// 지우는 횟수가 많으니... Array를 이용하자.
// 또 MutableList로 하고싶어도, MutableList안에서 루프를 도는동안 remove를 하면 concurrentException이 뜨므로..

// primeList는, n과 m의 diff만큼만 만드는게 아니라, 0부터 n까지 만드는게 훨씬 편한 것 같다.

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class BOJ_소수구하기 {
    lateinit var primeList: IntArray
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val (m, n) = br.readLine().split(" ").map { it.toInt() }
        makeEratosthenes(m, n)
        for (num in primeList.filterIndexed {idx,v -> idx>=m && v > 1}) bw.write("$num\n")
        br.close()
        bw.close()
    }

    private fun makeEratosthenes(m: Int, n: Int) {
        primeList = IntArray(n + 1) { it }
        for (i in 2..n) {
            if (primeList[i] == 0) continue
            for (j in 2 * i until primeList.size step i) {
                if (primeList[j] % i == 0) primeList[j] = 0
            }
        }
    }
}

fun main() {
    val c = BOJ_소수구하기()
    c.main()
}
