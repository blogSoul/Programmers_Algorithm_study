package d04_23

// 입력의 양이 많고 DFS인걸로 보아 DP를 이용하는 거 같다.
// Map의 키로 MutableList를 넣었는데, 같은 걸 인식하지 못하고 계속해서 map 에 추가한다.
// 뭐지? equals, hashcode 모두 잘 작동하는데..
// 내 착각. map 에 넣을 때, clone 해줘야 한다. (shallow copy)

// 근데 이상하게 10개의 리스트 예시를 넣으면 답이 6이어야 하는데
// 계속 10개가 나온다.
// 바보같이 문제를 잘 안읽음. index로 처리하는게 아니라, "값"이 더 작은 풍선임 ㅋㅋㅋㅋ
// 고치니까 답은 맞는데, 메모리초과와 시간초과..

// 결국 구글링함.
// 맙소사... 완전탐색 + DP가 아니라, 특정한 접근방식을 요하는 문제였다...
// 일단, 맨 왼쪽과 맨 오른쪽은 터뜨릴 수 있다.
// 가운데 풍선들을 이제 [left, right]의 시점에서 해결해야한다.
// 가운데 풍선의 경우, left와 right 중 하나가 되기 위해서는 left, right 보다 숫자가 작아야한다.
// <숫자가 작아야 마지막까지 남는다.>

//... 로직이 이해가 갈듯 말듯 한다. 존나 쉽게 풀었네 진짜 천재들인가...
fun main() {
    class Solution {
        fun solution(a: IntArray): Int {
            var answer = 0
            val blownSet = mutableSetOf<Int>()
            val balloons = a.toMutableList()
            var min = a.first()
            for (i in 1 until balloons.size) {
                blownSet.add(min) // 맨 왼쪽은 무조건 남을 수 있음
                min = minOf(balloons[i], min)
            }
            min = a.last()
            for (i in balloons.lastIndex - 1 downTo 0) {
                blownSet.add(min) // 맨 오른쪽은 무조건 남을 수 있음
                min = minOf(balloons[i], min)
            }
            println(blownSet)
            answer = blownSet.size
            return answer
        }
    }

    class MySolution {
        val blownSet: MutableSet<Int> by lazy { mutableSetOf<Int>() }
        lateinit var memo: MutableList<MutableMap<MutableList<Int>, Boolean>>
        fun solution(a: IntArray): Int {
            memo = mutableListOf(mutableMapOf(), mutableMapOf())
            var answer: Int = 0
            val balloons = a.toMutableList()
            dfs(balloons, 0)
            println(blownSet)
            answer = blownSet.size
            return answer
        }

        fun dfs(balloons: MutableList<Int>, blowSmall: Int): Boolean {
            if (memo[blowSmall].containsKey(balloons)) {
//                println("memo[$blowSmall].containsKey($balloons)")
                return true
            }
            if (balloons.size == 1) {
                blownSet.add(balloons.first())
                memo[blowSmall][balloons.toMutableList()] = true
                return true
            }
            if (blowSmall == 0) {
                for (i in 1..balloons.lastIndex) {
                    // blow Small
//                    val blown = balloons.removeAt(i - 1)
                    val blown = minOf(balloons[i - 1], balloons[i])
                    val blownIdx = balloons.indexOf(blown)
                    balloons.remove(blown)
                    memo[1][balloons.toMutableList()] = dfs(balloons, 1)
                    balloons.add(blownIdx, blown)
                }
            }
            for (i in 1..balloons.lastIndex) {
                // blow Bigger
                val blown = maxOf(balloons[i - 1], balloons[i])
                val blownIdx = balloons.indexOf(blown)
                balloons.remove(blown)
                memo[blowSmall][balloons.toMutableList()] = dfs(balloons, blowSmall)
                balloons.add(blownIdx, blown)
            }
            return true
        }
    }

    val sol = Solution()
    var a = intArrayOf(9, -1, -5)
    a = intArrayOf(-16, 27, 65, -2, 58, -92, -71, -68, -61)
    a = intArrayOf(-16, 27, 65, -2, 58, -92, -71, -68, -61, -33)
    println("solution: ${sol.solution(a)}")
}
