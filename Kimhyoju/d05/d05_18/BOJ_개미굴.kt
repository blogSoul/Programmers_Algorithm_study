package d05.d05_18

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// 트라이 자료구조 첫번째 연습.

class BOJ_개미굴 {
    class Trie() {
        val roots = mutableListOf<Node>()
    }

    class Node(val key: String) {
        var parent: Node? = null
        var children = mutableListOf<Node>()
    }

    val bw = BufferedWriter(OutputStreamWriter(System.`out`))
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val n = br.readLine().toInt()
        val trie = Trie()
        for (i in 0 until n) {
            val list = br.readLine().split(" ")
            var idx = trie.roots.indexOfFirst { it.key == list[1] }
            var temp = Node("0")
            if (idx == -1) {
                val root = Node(list[1])
                trie.roots.add(root)
                temp = root
            } else temp = trie.roots[idx]
            for (node in 2 until list.size) {
                idx = temp.children.indexOfFirst { it.key == list[node] }
                if (idx == -1) {
                    val newNode = Node(list[node])
                    temp.children.add(newNode)
                    newNode.parent = temp
                    temp = newNode
                } else temp = temp.children[idx]
            }
        }
        trie.roots.sortedBy { it.key }.forEach { dfs(it, 0) }

        br.close()
        bw.close()
    }

    fun dfs(node: Node, level: Int) {
        repeat(level) {
            bw.write("--")
//            print("--")
        }
        bw.write("${node.key}\n")
//        print("${node.key}\n")
        if (node.children.isNotEmpty()) node.children.sortedBy { it.key }.forEach { dfs(it, level + 1) }
    }
}

fun main() {
    val c = BOJ_개미굴()
    c.main()
}
