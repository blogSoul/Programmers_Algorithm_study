package d05_14

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// 일반적으로 root 만 하는게 아니라, 여기서는 이름을 인덱스로 변환하는 게 필요.
// 시간초과뜬다. 카운트 쪽의 문제인듯 하다.
// 일일이 그때마다 카운트 하는게 아니라,  맵으로 유니온 개수를 계속 갱신해주자

class BOJ_친구_네트워크 {
    lateinit var root: IntArray
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val t = br.readLine().toInt()
        repeat(t) {
            val f = br.readLine().toInt()
            var count = 0
            root = IntArray(f * 2) { it }
            val friend = mutableMapOf<String, Int>()
            val networkMap = mutableMapOf<Int, Int>()
            for (i in 0 until f) {
                val (a, b) = br.readLine().split(" ")
                if (!friend.containsKey(a)) friend[a] = count++
                if (!friend.containsKey(b)) friend[b] = count++
                val networkA = findUnion(root[friend[a]!!])
                val networkB = findUnion(root[friend[b]!!])
                union(friend[a]!!, friend[b]!!)
                val networkC = findUnion(root[friend[a]!!])
                if (networkA != networkB) {
                    networkMap[networkC] = networkMap.getOrDefault(networkA, 1) + networkMap.getOrDefault(networkB, 1)
                }
                bw.write("${networkMap[networkC]}\n")
            }
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
        if (root[a] == a) return a
        root[a] = findUnion(root[a])
        return root[a]
    }
}

fun main() {
    val c = BOJ_친구_네트워크()
    c.main()
}
