package d4_29

// 이건 어떻게 풀어야할지 감도 안온다...
// 단순 완탐은 아닌 것 같고, 원리가 있을 것 같은데...
// 구글링 해봄.

// https://gurumee92.tistory.com/164
// 5가 1개 있을 때는, 5
// 5가 2개 있을 때는, 55, 5+5, 5-5, 5/5, 5*5
// 이렇게 5가 N개 있을 때, N = 1 이면, 1 = 1
// 2 = 2, 1+1
// 3 = 3, 1+2, 2+1, 1+1+1 이렇게 늘려나갈 수 있다. 재귀를 써야할 것 같다.
//재귀안써도 가능. n의 차례에서, N을 n개 이은것과, 1+ (n-1번째 set의 모든 원소들) , (n-1번째 set의 모든 원소들) + 1
// 여기서 1 + 란, N 과의 사칙연산을 말한다.
// DP를 적용하면 더 빠르겠지만 오버킬.

// 계속 답이 틀려서 고민해봤는데, 이렇게 하면 안된다.
// 예를 들어 4의 경우, 4, 1+(3), (3)+1, 1+(2+1),(2+1)+1, (1+2)+1,1+(1+2),1+(1+1+1), (1+1+1)+1 이렇게만 했는데,
// 2+2의 경우는 고려하지 않았다. 2+2의 경우도 고려해주어야하므로,
// 결국, 3의 경우는 555 Union 1과2 Union 2와1
// 4의 경우는 5555 Union 1과3 Union 2와2 Union 3와1 인 것...

fun main() {
    class Solution {
        val setList = mutableListOf<MutableSet<Int>>()
        fun solution(N: Int, number: Int): Int {
            var answer = 0
            for (i in 1..8) {
                val set = mutableSetOf<Int>()
                set.add("$N".repeat(i).toInt())
                for (j in 0 until i - 1) set.addAll(getSetCalculation(setList[j], setList[i-2-j]))
                if (number in set) return i
                setList.add(set)
                println("setList.add($set)")
            }
            println("setList: $setList")
            return -1
        }

        fun getSetCalculation(set1: MutableSet<Int>, set2: MutableSet<Int>): MutableSet<Int> {
            val set = mutableSetOf<Int>()
            val list1 = set1.toList()
            val list2 = set2.toList()
            for (i in list1.indices) {
                for (j in list2.indices) {
                    set.addAll(getCalculation(list1[i],list2[j]))
                }
            }
            return set
        }

        fun getCalculation(N: Int, value: Int): MutableSet<Int> {
            val set = mutableSetOf<Int>()
            // plus
            set.add(N + value)
            // minus
            set.add(N - value)
            // multiply
            set.add(N * value)
            // divide
            set.add(N / value)

            set.remove(0) // 0은 필요없다. 낭비.
            return set
        }
    }

    var N = 5
    var number = 12
    println("solution: ${Solution().solution(N, number)}")
}
