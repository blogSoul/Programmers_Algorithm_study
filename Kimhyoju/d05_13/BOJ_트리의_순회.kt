package d05_13

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// InOrder 와 PostOrder 를 알고있을 때, PreOrder를 구하는 문제.
// 이진트리에서 InOrder는 정렬과 같다.
// PreOrder는 트리에 넣은 순서와 같은거같고.

// 전위순회나 후위순회가 주어지면 단번에 루트 파악이 가능하다.

// PostOrder에서, 맨 마지막이 parent, 앞의 절반정도가  left, 뒤의 절반이 right.
// 분할정복으로 해봤다.

// 정답은 나오는데, 메모리 초과가 뜸.. subList 부분이 하도 많이 호출돼서 그런가 보다.
// 1<=n<=100,000

// 구글링함.
// 내가 푼것과 비슷.
// 후위순회는 left 나오고 right 나오고 parent.
// 중위순회는 left 나오고 parent 나오고 right.
// 후위순회만 있어도 충분할것같은데, 중위순회를 어떻게 사용해야할까.
// https://suhwanc.tistory.com/26
// 중위순회는 왜 주어졌을까? 후위순회에서 루트를 뽑을 때 어디부터 어디까지가
// 왼쪽 서브트리인지를 알고 뽑아야 전위순회에 차례대로 루트를 붙일 수 있기 때문.
// 난 계속해서 indexOfFirst 로 subList를 이용해서 뽑았는데,
// 여기서 중위순회의 배열을 참고하면 그러지않아도 된다. 중위순회의  루트 인덱스가 a고 후위순회의 루트 인덱스가 b라면,
// 왼쪽 서브트리는 후위순회 상에서, [0,a) 이고, 오른쪽 서브트리는 [a,b), 루트는 b가 된다.
// 이렇게 하면 나처럼 서브리스트로 인한 메모리 초과가 나오지 않게 될 수 있다!
// 생각하는데 오래걸릴것같은 문제.

// 하다보니까, postOrder의 left, right 값만으로는 제대로 안된다.
// inOrder의 left, right 값도 써줘야 제대로 작동한다.
// 내가 만든 TC
// 11
// 5 24 28 30 45 46 48 50 52 60 98
// 5 28 24 46 48 45 30 60 52 98 50
// answer: 50 30 24 5 28 45 48 46 98 62 50

class BOJ_트리의_순회 {
    lateinit var postOrder: List<Int>
    val poMap = mutableMapOf<Int, Int>()
    lateinit var inOrder: List<Int>
    val ioMap = mutableMapOf<Int, Int>()
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val n = br.readLine().toInt()
        inOrder = br.readLine().split(" ").map { it.toInt() }
        for (i in inOrder.indices) {
            ioMap[inOrder[i]] = i
        }
        postOrder = br.readLine().split(" ").map { it.toInt() }
        preOrder(0, n - 1, 0, n - 1)
        br.close()
        bw.close()
    }

    fun preOrder(inLeft: Int, inRight: Int, posLeft: Int, posRight: Int) {
        if (posRight > posRight || inLeft > inRight) return
        println("inLeft: $inLeft, inRight: $inRight, posLeft: $posLeft, posRight: $posRight")
        val root = postOrder[posRight]
        val ioRootIdx = ioMap[root]!!.toInt()
        //left
        val leftSize = ioRootIdx - inLeft // 7
        val rightSize = inRight - ioRootIdx // 3
        visit(posRight)
        // left
        preOrder(inLeft, ioRootIdx - 1, posLeft, posLeft + leftSize - 1)
        // right
        preOrder(ioRootIdx + 1, inRight, posLeft + leftSize, posRight - 1)
    }

    fun preOrder2(left: Int, right: Int) {
        visit(right)
        if (left == right) return
        val sublist = postOrder.subList(left, right)
        val leftRight = sublist.indexOfLast { it < postOrder[right] } // 서브리스트와 postOrder 인덱스가 달라서 문제가 생김.
        if (leftRight != -1) {
//            println("calling left preOrder $left, $leftRight in $left $right")
            preOrder2(left, left + leftRight)
        }
        val rightLeft = sublist.indexOfFirst { it > postOrder[right] }
        if (rightLeft == 7) {
//            println("rightLeft is 7. $left $right")
        }
        if (rightLeft != -1) {
//            println("calling right preOrder $rightLeft, ${right - 1} in $left $right")
            preOrder2(left + rightLeft, right - 1)
        }
    }

    fun visit(a: Int) {
        println("visit ${postOrder[a]}")
        bw.write("${postOrder[a]} ")
    }
}

fun main() {
    val c = BOJ_트리의_순회()
    c.main()
}
