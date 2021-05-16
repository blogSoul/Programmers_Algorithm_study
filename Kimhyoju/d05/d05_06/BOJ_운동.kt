package d05.d05_06

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// 최소 사이클 찾기
// 쓸데없지만 위상정렬은 사이클이 있으면 불가능. 즉, 위상정렬을 하면서 incoming edge가 0인 노드가 없다면
// 그건 사이클이 있는 거다.
// 이거랑은 관계없지만... 최소 사이클 찾기 이런건 처음 해본다. 그래프를 많이 해보질 않아서..
// 이게 왜 최단경로 섹션에 있는거지..
// 모르겠다 구글링.

// 플로이드와샬을 활용하는 문제라고 한다..
// https://wlstyql.tistory.com/108
// 1. 2차원 graph를 INF(1e9)로 초기화한 후, 노드에 거리들을 저장해준다.
// 2. 플로이드 와샬 알고리즘 (모든 경로를 거쳐가는 경우를 고려한 알고리즘)
//   - 거쳐가는 중간 노드 i, 시작 노드 v, 도착 노드 nv의 시간 복잡도 O(V^3)
//   - i와 v가 같은 경우(시작=중간) 또는 i와 nv가 같은 경우(중간=도착)은 continue로 생략
//   - 중간 노드를 거쳐가는 경우가 더 거리가 짧다면 갱신
// 3. graph에서 시작 노드와 도착 노드가 같은 idx의 값의 min값을 출력해준다.
//   - min 값이 INF라면 -1 출력

// 아... 플로이드 와샬을 자주 쓰면서도 본질을 까먹고 있었다.
// 물론, 사이클을 필요로 하지 않는 곳에서는 dist[i][i] = 0 을 해주겠지만, (BOJ_플로이드)
// 지금같은 경우에는 dist[i][i] = 0을 해주지말자. 그래야 시작지점과 도착지점이 같은, 즉
// 제자리로 돌아올 때의 최소값을 알 수 있다!

class BOJ_운동 {
    private lateinit var dist: Array<IntArray>
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val (vNum, eNum) = br.readLine().split(" ").map { it.toInt() }
        dist = Array(vNum + 1) { IntArray(vNum + 1) { Int.MAX_VALUE } }
        repeat(eNum) {
            val (a, b, c) = br.readLine().split(" ").map { it.toInt() }
            dist[a][b] = c
        }
        for (m in 1..vNum) {
            for (s in 1..vNum) {
                for (e in 1..vNum) {
                    if (dist[s][m] != Int.MAX_VALUE && dist[m][e] != Int.MAX_VALUE) {
                        if (dist[s][e] > dist[s][m] + dist[m][e]) {
                            dist[s][e] = dist[s][m] + dist[m][e]
                        }
                    }
                }
            }
        }
        var min = Int.MAX_VALUE
        for (i in dist.indices) {
//            println("dist[$i][$i]: ${dist[i][i]}")
            if (min > dist[i][i]) min = dist[i][i]
        }
        if (min == Int.MAX_VALUE) bw.write("-1")
        else bw.write("$min")
        bw.close()
    }
}

fun main() {
    val c = BOJ_운동()
    c.main()
}
