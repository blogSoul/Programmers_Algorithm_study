package d05_12

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// 최소 신장 트리 : MST
// Kruskal 알고리즘은 쉽게 말하면 Greedy + UnionFind 라고 보면 됨.

// 1. 그래프의 간선들을 가중치에 따라 오름차순으로 정렬
// 2. 정렬된 간선 리스트에서 순서대로 사이클을 형성하지 않는 간선을 선택
//  - 즉, 가장 낮은 가중치를 먼저 선택
//  - 사이클을 형성하는 간선을 제외한다. (이미 Union 에 포함되어 있으면 스킵)
// 3. 해당 간선을 현재 MST 의 집합(Union) 에 추가한다.

class BOJ_상근이의_여행 {
    lateinit var root: IntArray
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val t = br.readLine().toInt()
        repeat(t) {
            val (n, m) = br.readLine().split(" ").map { it.toInt() }
            root = IntArray(n + 1) { it }
            val edges = mutableListOf<List<Int>>() // a, b, cost
            var answer = 0
            for (i in 0 until m) {
                val (a, b) = br.readLine().split(" ").map { it.toInt() }
                edges.add(listOf(a, b, 1))
            }
            edges.sortBy { it.last() }
            for (edge in edges) {
                val (a, b, cost) = edge
                if (isUnion(a, b)) continue
                union(a, b)
                answer++
            }
            bw.write("$answer\n")
        }
        br.close()
        bw.close()
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
    val c = BOJ_상근이의_여행()
    c.main()
}
