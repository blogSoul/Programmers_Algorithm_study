package d04.d04_25

// 이거 백준에서도 풀었는데, 뭔가 법칙이 알듯 말듯한데 난이도는 쉽다고 써 있으니 묘한 문제였다.
// 생각 더 해봐도 생각이 안나서 그냥 구글링해봄. 아 이거 모의고사때 풀었던 것 같은데..
// 삼각형의 원소 배열 법칙을 잘 생각해보면 된다. layer에 따라서 내려갈 땐 앞에서 layer번째,
// 올라갈 땐 뒤에서 layer번째에 값을 채운다던지..

fun main() {
    class Solution {
        var k = 0
        var layer = 0
        var direction = 0
        var level = -1
        var count = 1
        lateinit var blocks: MutableList<MutableList<Int>>
        fun solution(n: Int): IntArray {
            var answer: IntArray = intArrayOf()
            blocks = MutableList(n) { mutableListOf<Int>() }
            k = n
            while (k > 0) {
                when (direction) {
                    0 -> goDown()
                    1 -> goRight()
                    else -> goUp()
                }
                k--
                direction = (direction + 1) % 3
                if (direction == 0) layer++
                println("blocks: $blocks")
            }
            val flat = blocks.flatten()
            println(flat)
            answer = flat.toIntArray()
            return answer
        }

        fun goDown() {
            for (i in k downTo 1) {
                level++
                if (layer == 0) blocks[level].add(count++)
                else blocks[level].add(layer, count++)
            }
        }

        fun goRight() {
            println("goRight, layer: $layer")
            val temp = mutableListOf<Int>()
            for (i in k downTo 1) {
                if (layer == 0) blocks[level].add(count++)
                else {
                    temp.add(count++)
                }
            }
            if (layer > 0) blocks[level].addAll(blocks[level].size/2+1, temp)
        }

        fun goUp() {
            for (i in k downTo 1) {
                level--
                if (layer == 0) {
                    blocks[level].add(count++)
                } else {
                    println("layer: $layer")
                    blocks[level].add(blocks[level].lastIndex - layer+1, count++)
                }
            }
        }
    }

    var n = 4
    n = 5
    n = 6
    println("solution: ${Solution().solution(n)}")
}
