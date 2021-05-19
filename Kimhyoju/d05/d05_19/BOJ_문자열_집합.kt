package d05.d05_19

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// Trie 자료구조를 맵으로!
class BOJ_문자열_집합 {
    class Node(val key: Char?) {
        var parent: Node? = null
        val children = mutableMapOf<Char, Node>()
        var end = false // 단어의 끝을 표현.
    }

    fun main2() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val (n, m) = br.readLine().split(" ").map { it.toInt() }
        val wordMap = mutableMapOf<String, Boolean>()
        for (i in 0 until n) {
            wordMap[br.readLine()] = true
        }
        var answer = 0
        for (i in 0 until m) {
            if (wordMap.getOrDefault(br.readLine(),false)) answer++
        }
        bw.write("$answer")
        br.close()
        bw.close()
    }

    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val (n, m) = br.readLine().split(" ").map { it.toInt() }
        val root = Node(null)
        for (i in 0 until n) {
            val str = br.readLine()
            var temp = root
            var newNode: Node
            for (ch in str) {
                if (temp.children.containsKey(ch)) {
                    newNode = temp.children[ch]!!
                } else {
                    newNode = Node(ch)
                    temp.children[ch] = newNode
                }
                newNode.parent = temp
                temp = newNode
            }
            temp.end = true
        }
        var answer = 0
        for (i in 0 until m) {
            val target = br.readLine()
            val res = findStr(target, root)
            if (res) answer++
        }
        bw.write("$answer")
        br.close()
        bw.close()
    }

    fun findStr(target: String, root: Node): Boolean {
        var temp = root
        for (ch in target) {
            if (temp.children.containsKey(ch)) {
                temp = temp.children[ch]!!
            } else return false
        }
//        println("target: $target")
        return temp.end
    }
}

fun main() {
    val c = BOJ_문자열_집합()
    c.main2()
}
