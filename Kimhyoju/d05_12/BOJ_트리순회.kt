package d05_12

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class BOJ_트리순회 {
    class Node(val id: String) {
        var parent: Node? = null
        var left: Node? = null
        var right: Node? = null
    }

    val node = mutableMapOf<String, Node>()
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val n = br.readLine().toInt()
        repeat(n) {
            val (parent, left, right) = br.readLine().split(" ")
            if (!node.containsKey(parent)) node[parent] = Node(parent)
            if (left != "." && !node.containsKey(left)) node[left] = Node(left)
            if (right != "." && !node.containsKey(right)) node[right] = Node(right)
            if (left != ".") {
                node[parent]!!.left = node[left]
                node[left]!!.parent = node[parent]
            }
            if (right != ".") {
                node[parent]!!.right = node[right]
                node[right]!!.parent = node[parent]
            }
        }
        val root = node["A"]
        preOrder(root)
        bw.write("\n")
        inOrder(root)
        bw.write("\n")
        postOrder(root)
        bw.write("\n")
        br.close()
        bw.close()
    }

    fun preOrder(curNode: Node?) { // 전위순회 : 부모 -> left -> right
        if (curNode == null) return
        visit(curNode)
        preOrder(curNode.left)
        preOrder(curNode.right)
    }

    fun inOrder(curNode: Node?) { // 중위순회 : left -> 부모 -> right
        if (curNode == null) return
        inOrder(curNode.left)
        visit(curNode)
        inOrder(curNode.right)
    }

    fun postOrder(curNode: Node?) { //  후위순회 : left -> right -> 부모
        if (curNode == null) return
        postOrder(curNode.left)
        postOrder(curNode.right)
        visit(curNode)
    }

    fun visit(node: Node?) {
        if (node == null) return
        bw.write(node.id)
    }
}

fun main() {
    val c = BOJ_트리순회()
    c.main()
}
