package d5_02

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// 프로그래머스의 징검다리와 같은 문제.
// 이분탐색의 응용이다.
// 징검다리가 이미 있는 집에서 n개를 빼면 그 거리의 최소값(가장 인접한 두 바위 사이의 거리)중 최대를 구하는 거라면,
// 이 문제는 n개의 집중 c개를 골라 인접한 거리의 최소값을 구하는 것.
// 그렇다는 건, n개의 집 중 n-c개를 빼 그 거리의 최소값(가장 인접한 두 집의 거리) 중 최대를 구하는 것이다.
// (이것은 모든 공유기들의 사이 간격이 공평하게 설치되기를 바라는 것을 의미한다고도 볼 수 있다.)
// 두 문제가 똑같다. 카운트를 세는 방법을 이용하더라.

// https://mygumi.tistory.com/301
//각 간격을 기준으로 일일이 확인하는 것이 아닌 이분 탐색의 방식을 이용하는 것이다.
// 1. 특정 간격을 기준으로 가능한 위치에 공유기를 설치한다.
// 2. 설치한 후에는 다음과 판단한다.
// 3. 공유기 수가 더 설치되어야 한다면, 간격을 줄인다.
// 4. 공유기 수를 줄여야한다면, 간격을 늘린다.

// 징검다리에서처럼, cnt 개의 공유기를 설치해서, 공유기 사이의 간격의 최소값을 mid로 만들 수 있는가?로 바꾼다면 이분탐색으로 생각 가능.
// 그리고, cnt가 주어진 c보다 많다 -> 이러면 안된다. c만큼만 설치해야하기에. -> 공유기 수를 줄여야한다. -> 사이 간격을 늘려야한다.
// cnt가 주어진 c보다 적다 -> 이 정도로도 되지만 더 줄일 수 있다. -> 공유기 수를  늘려야한다.-> 사이 간격을 좁혀야한다.

class Boiler {
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val (n, c) = br.readLine().split(" ").map { it.toInt() }
        val hubs = LongArray(n)
        for (i in 0 until n) hubs[i] = br.readLine().toLong()
        br.close()
        hubs.sort()
        var answer = 0L
        var left = 1L // 가능한 최소 거리
        var right = hubs.last() - hubs.first() // 가능한 최대거리
        var d = 0L
        while (left <= right) {
            var mid = (left + right) / 2 // 기준
            var start = hubs.first()
            var cnt = 1 // 가장 인접한 두 공유기 사이를 가능한 크게 해야하기에, 맨 끝은 당연히 차야한다.
            // 징검다리와 다른 부분. 징검다리는 cnt = 0 부터 시작해, for (i in rocks.indices) 이렇게 했다.
            // 징검다리는, 바위사이들 뿐만 아니라, 시작점과 끝부분도 고려해야했기에 이 문제와는 다르다.

            // 간격(d)를 기준으로 공유기 설치
            for (i in 1 until hubs.size) {
                d = hubs[i] - start
                if (mid <= d) {
                    cnt++
                    start = hubs[i]
                }
            }
            println("mid: $mid, cnt: $cnt")
            if (cnt >= c) { // 공유기가 너무 많다.
                // 공유기의 수를 줄이자 => 간격 넓히기
                answer = mid
                left = mid + 1
            } else {
                // 공유기가 너무 적다. 더 설치하자 => 간격 좁히기
                right = mid - 1
            }
        }
        bw.write("$answer")
        bw.close()
    }
}

fun main() {
    val c = Boiler()
    c.main()
}
