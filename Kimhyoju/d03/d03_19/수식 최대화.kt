import java.lang.Math.abs
import java.lang.StringBuilder
import java.util.*

// operatorList: [-, *, -, +]
// operandList: [100, 200, 300, 500, 20]
// 일 때, 이제 계산만 하면 되는데...
// 이걸 쉽게 할 방법을 찾다가 너무 오래걸린 것 같다.

// 주어진 중위표현식을 후위표현식으로. 근데 이건 까먹었고 배보다 배꼽이 더 큰것같아서 안함.
// 또 그냥 for 문을 돌면서 operand 2개를 하나로 합칠때 index도 줄이는 방식. 근데 조잡해보여서 안함.
// 쉽게 푸는 방법을 찾으려 구글링해봄

// 찾아보니 결론은 내가 생각한 저 두가지 방법정도밖에 없는 것 같다.

// 제대로 푼 것 같은데, 정확도에서 몇개 틀린다... 뭐지?

// abs는 Int만 받아서 안된대.. 개빡치네 ㅎㅎ
// abs를 없애고 음수면 +로 바꿔주게 하니까 정답.

fun main() {
    class Solution {
        fun solution(expression: String): Long {
            var answer: Long = 0
            var sb = StringBuilder("")
            var operandList = mutableListOf<Long>()
            var operatorList = mutableListOf<Char>()
            for (exp in expression) {
                if (exp == '+' || exp == '*' || exp == '-') {
                    operandList.add(sb.toString().toLong())
                    sb.clear()
                    operatorList.add(exp)
                } else {
                    sb.append(exp)
                }
            }
            operandList.add(sb.toString().toLong())
            println("operatorList: $operatorList")
            println("operandList: $operandList")
            val preList = mutableListOf<Int>()
            val preMap = mutableMapOf(0 to '+', 1 to '*', 2 to '-')
            // 0: +
            // 1: *
            // 2: -
            for (i in 0 until 3) {
                preList.add(i)
                for (j in 0 until 3) {
                    if (j != i) {
                        preList.add(j)
                        for (k in 0 until 3) {
                            if (k != i && k != j) {
                                preList.add(k)
                                var sum = 0
                                var tempOperatorList = operatorList.toMutableList()
                                var tempOperandList = operandList.toMutableList()
                                for (pre in preList) {
                                    var op = preMap.getOrDefault(pre, ' ')
                                    // idx를 바꿀 수 없는 건 정말 짜증난다.
                                    // 바꾸고 싶다면 while문을 쓰란다.
                                    var idx = 0
                                    while (idx < tempOperatorList.size) {
                                        if (tempOperatorList[idx] == op) {
                                            if (op == '+') {
                                                println("met +")
                                                tempOperandList[idx+1] = tempOperandList[idx] + tempOperandList[idx + 1]
                                            } else if (op == '*') {
                                                println("met *")
                                                tempOperandList[idx+1] = tempOperandList[idx] * tempOperandList[idx + 1]
                                            } else { // -
                                                println("met -")
                                                tempOperandList[idx+1] = tempOperandList[idx] - tempOperandList[idx + 1]
                                            }
                                            tempOperatorList.removeAt(idx)
                                            tempOperandList.removeAt(idx)
                                            idx--
                                            println("tempOperatorList: $tempOperatorList")
                                            println("tempOperandList: $tempOperandList")
                                        }
                                        idx++
                                    }
                                }
                                var result = tempOperandList[0]
                                if (result < 0) result = -result
                                println("result: $result")
                                if (answer < result) answer = result
                                preList.removeAt(preList.lastIndex)
                            }
                        }
                        preList.removeAt(preList.lastIndex)
                    }
                }
                preList.removeAt(preList.lastIndex)
            }
            println("answer: $answer")
            return answer
        }
    }

    val sol = Solution()
    var expression = "100-200*300-500+20"
    expression = "2-990-5+2"
    expression = "2-990-5+2+32"
    expression = "25+1-1-1+1" // -> 26-1-1+1 -> 26-1-2 -> 25-2 -> 23
    expression = "2*4"
    expression = "1-2"
    expression = "1-2 * 4"
    expression = "1-2*4"
    expression = "1+4*2-3-2+5"
    expression = "50*6-3*2"
    println(sol.solution(expression))
}