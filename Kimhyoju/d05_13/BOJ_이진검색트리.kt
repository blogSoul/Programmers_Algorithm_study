package d05_13

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// 전위 순회를 역추적해가면서 찾으려고 했는데..
// 오래걸렸다. 그러다가 생각했는데, 굳이 그럴 필요있나?
// 전위순회 순서는 트리에 노드를 넣은 순서와 같은 것 같다.
// 그러니 트리를 만들고 그거 후위순회하면 될거같은데?

class BOJ_이진검색트리 {
    class Node(val key: Int) {
        var parent: Node? = null
        var left: Node? = null
        var right: Node? = null
        fun printInfo() {
            println("Node key: $key parent: ${parent?.key} left: ${left?.key} right: ${right?.key}")
        }
    }

    val node = mutableMapOf<Int, Node>()
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        var input = br.readLine()
        val preOrder = mutableListOf<Int>()
        var root = Node(Int.MAX_VALUE)
        while (input != null && input != "") {
            val value = input.toInt()
            if (preOrder.isEmpty()) {
                root = Node(value)
                preOrder.add(value)
            } else {
                var curNode = root
                while (true) {
                    if (value > curNode.key) {
                        if (curNode.right != null) {
                            curNode = curNode.right!!
                        } else {
                            curNode.right = Node(value)
                            break
                        }
                    } else {
                        if (curNode.left != null) {
                            curNode = curNode.left!!
                        } else {
                            curNode.left = Node(value)
                            break
                        }
                    }
                }
            }
            input = br.readLine()
        }
        postOrder(root)
        br.close()
        bw.close()
    }

    fun postOrder(node: Node?) {
        if (node == null) return
        postOrder(node.left)
        postOrder(node.right)
        visit(node)
    }

    fun visit(node: Node?) {
        if (node == null) return
        bw.write("${node.key}\n")
    }

}

fun main() {
    val c = BOJ_이진검색트리()
    c.main()
}

