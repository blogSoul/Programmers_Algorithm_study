package d05.d05_01

// 문제에 쓰여있는, 원래의 순서가 지켜진다는 글귀를 지나쳐서 Permutation을 만드는 삽질을 했다.
// 문제를 잘 읽자. 실전에서도 이런 실수 많이 함.

// 곳곳에서 생기는 문제를 해결하려다보니 코드가 지저분해지고, 시간초과는 또 엄청 뜬다...

// Map을 써도 시간초과.. 어떤 점이 문제지?
// 제한사항에서 길이가 500,000 이하인데, 난 49개만 써도 거의 그냥 멈춘다...
// 최소거리니깐, BFS로 해야하나?
// BFS로 해도 좀 나아지기만하지 해결은 안됨. 시간초과가 19개는 뜸....

// 구글링 함.

// https://yabmoons.tistory.com/610
// 나같은 완탐이 아니라, 특정한 접근방식이 필요한 문제다.
// 사실, 제한사항만 봐도 완탐으론 안된다고 판단을 할 수 있어야 한다.
// 핵심은, 스타수열을 만들 때, 공통된 원소 X에 대해서 만들 수 있는 길이 중 최대를 고르는 것.

fun main() {
    class Solution {
        fun solution(a: IntArray): Int {
            if (a.size == 1) return 0
            var answer = 0
            val numMap = mutableMapOf<Int, Int>() // 등장하는 숫자와 그 횟수를 기록해놓는다.
            for (i in a.indices) {
                numMap[a[i]] = numMap.getOrDefault(a[i], 0) + 1
            }
            println("numMap: $numMap")
            for (num in numMap.keys) {
                if (answer >= numMap[num] as Int) continue // 이미 얻은 최댓값이 새로운 num의 경우 나올 수 있는최대보다 크면 스킵.
                var res = 0
                var jumpFlag = false
                for (i in 0 until a.lastIndex) {
                    if (jumpFlag) {
                        jumpFlag = false
                        continue
                    }
                    if (a[i] != num && a[i + 1] != num) continue
                    if (a[i] == a[i + 1]) continue
                    res++
                    // 이미 확정된 집합이므로, 다음 계산에선 고려해선 안된다. 다음다음으로 넘어가야함.
                    jumpFlag = true // i++ 를 하고싶은데 안되니깐..
                }
                answer = maxOf(answer, res)
            }
            return answer * 2
        }

        var answer = 0
        val listMap = mutableMapOf<MutableList<Int>, Boolean>()
        fun dfs(list: MutableList<Int>) {
            if (answer >= list.size) return
            if (list.size == 1) return
            if (list.size % 2 == 0) {
                val listSet = mutableSetOf<List<Int>>()
                var validFlag = checkNotSame(list, listSet)
                if (validFlag) {
                    validFlag = checkIntersect(listSet)
                }
                if (validFlag) {
                    answer = maxOf(answer, list.size)
//                    println("answer: $answer")
                }
                for (i in list.indices) {
                    for (j in i + 1 until list.size) {
                        val newList = list.toMutableList()
                        newList.removeAt(i)
                        newList.removeAt(j - 1)
                        if (!listMap.containsKey(newList)) {
                            listMap[newList] = true
                            dfs(newList)
                        }
                    }
                }
            } else {
                for (i in list.indices) {
                    val newList = list.toMutableList()
                    newList.removeAt(i)
                    if (!listMap.containsKey(newList)) {
                        listMap[newList] = true
                        dfs(newList)
                    }
                }
            }
        }

        fun checkNotSame(list: MutableList<Int>, listSet: MutableSet<List<Int>>): Boolean {
            for (i in 0 until list.size / 2) {
                if (list[2 * i] == list[2 * i + 1]) return false
                listSet.add(listOf(list[2 * i], list[2 * i + 1]))
            }
            if (listSet.size != list.size / 2) return false
            return true
        }

        fun checkIntersect(listSet: MutableSet<List<Int>>): Boolean {
            var intersect = listSet.first().toSet()
            for (l in listSet) {
                intersect = intersect.intersect(l)
                if (intersect.isEmpty()) return false
            }
            return intersect.size == 1
        }
    }

    var a = intArrayOf(5, 2, 3, 3, 5, 3)
    a = intArrayOf(0, 3, 3, 0, 7, 2, 0, 2, 2, 0)
//    a = intArrayOf(1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 66, 6, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 9, 10)
    println("solution: ${Solution().solution(a)}")
}
