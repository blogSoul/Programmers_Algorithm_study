package d05.d05_16

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.abs

// 항상 그랬듯이 쉬워보였는데, pq에 담는 2중 for문에서 메모리 초과가 뜨는 것 같다.
// planet은 최대 100,000 인데 2중이므로, pq 에는 약 100,000 x 100,000 개가 들어간다. 메모리 초과날만함.
// 지금까지는 N이 보통 100, 많아봐야 10,000 이었다. 1억까진 괜찮은데 100억은 좀 힘든가보다.


// https://gmlwjd9405.github.io/2018/08/29/algorithm-kruskal-mst.html

// Kruskal 뿐만 아니라, Prim 알고리즘도 배우는 계기가 됨.

// 아무리 프림이라고 해도, n^2니까 어차피 100억. 메모리 초과가 뜸.

// https://chanhuiseok.github.io/posts/baek-34/
// 이래서 골드 1이었구나...
// x,y,z 를 한꺼번에 담는 것이 아니라,
// xyz 좌표를 별도로 담아두고 오름차순 정렬해놓는다.

// 모든 행성을 후보로 연결할 필요 없이 x,y,z 좌표 중 행성 가까이에 있는 행성들만 후보터널로 사용하면 된다.
//https://dev-jk.tistory.com/29

class BOJ_행성터널 {
    lateinit var planet: Array<List<Long>>
    lateinit var root: IntArray
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val n = br.readLine().toInt()
        planet = Array(n) { emptyList<Long>() }
        root = IntArray(n) { it }
        val xCoord = mutableListOf<Pair<Int, Long>>()
        val yCoord = mutableListOf<Pair<Int, Long>>()
        val zCoord = mutableListOf<Pair<Int, Long>>()
        for (i in 0 until n) {
            planet[i] = br.readLine().split(" ").map { it.toLong() }
            xCoord.add(Pair(i, planet[i][0]))
            yCoord.add(Pair(i, planet[i][1]))
            zCoord.add(Pair(i, planet[i][2]))
        }
        xCoord.sortBy { it.second }
        yCoord.sortBy { it.second }
        zCoord.sortBy { it.second }
//        println("$xCoord")
//        println("$yCoord")
//        println("$zCoord")
        val pq = PriorityQueue<Pair<Pair<Int, Int>, Long>>(compareBy { it.second })
        for (i in 0 until xCoord.lastIndex) {
            pq.offer(Pair(Pair(xCoord[i].first, xCoord[i + 1].first), xCoord[i + 1].second - xCoord[i].second))
            pq.offer(Pair(Pair(yCoord[i].first, yCoord[i + 1].first), yCoord[i + 1].second - yCoord[i].second))
            pq.offer(Pair(Pair(zCoord[i].first, zCoord[i + 1].first), zCoord[i + 1].second - zCoord[i].second))
        }

        var answer = 0L
        while (pq.isNotEmpty()) {
            val (pair, dist) = pq.poll()
            val (a, b) = pair
            if (isUnion(a, b)) continue
            union(a, b)
            answer += dist
        }
        bw.write("$answer")
        br.close()
        bw.close()
    }

    fun getDistance(planetA: Int, planetB: Int): Long {
        val a = planet[planetA]
        val b = planet[planetB]
        return minOf(abs(a[0] - b[0]), abs(a[1] - b[1]), abs(a[2] - b[2]))
    }

    fun union(a: Int, b: Int) {
        val (min, max) = listOf(findUnion(a), findUnion(b)).sorted()
        root[max] = min
    }

    fun isUnion(a: Int, b: Int): Boolean {
        return findUnion(a) == findUnion(b)
    }

    fun findUnion(a: Int): Int {
        if (a == root[a]) return a
        root[a] = findUnion(root[a])
        return root[a]
    }
}

fun main() {
    val c = BOJ_행성터널()
    c.main()
}
