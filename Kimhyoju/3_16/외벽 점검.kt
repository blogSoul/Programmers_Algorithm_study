// 해결방법이 잘 떠오르지 않는다.
// 내가 생각한 방식은, 처음엔 광고삽입처럼 원을 배열로 flatten 시켜서
// 생각하는 것이었는데, 인덱스 경계 처리가 좀 귀찮을 것 같았다.

// 그 다음엔 원에서 weakPoint을 체크하고, start와 end 를 각각 투포인터로 지정하고,
// 두개를 하나씩 늘려가면서 start에서부터 end로부터의 길이가 가장 작은 구간을 가지고
// 시작을 수월히 하는 것을 생각했다. 근데 그 후로는 생각이 안난다...
// dist 가 7,4,3,2 라면 7로는 다 커버가 안되는 구간이면, 7과 4도 써봐야하고...
// 또 7을 먼저 쓰느냐 4를 먼저 쓰느냐는 그 구간의 weakpoint 위치에 따라 달라지니..
// 그럼 또 for loop를 돌리자니 dist 만큼 for loop를 돌려야하기때문에 힘들어진다.
// ... 힘든가? 그냥 완전탐색으로 모든 순열에 대해 하면 됨.

// 일단 데이터 수가 적으니 완전탐색으로도 해결가능할듯 한데..


// 결국 구글링함.
// 일단 배열로 표현하는 건 맞았는데, 단순한 1차원 배열은 번거로우므로
// 배열의 크기를 2배로 늘려주는 것이다.
// n = 12, weak = [1,5,6,10] 이면, weak = [1,5,6,10,13,17,18,22] 으로..
// 이러면 배열의 연속성까지 표현할 수 있다
// 이렇게하면, 모든 K 개의 취약점을 점검하는 것 = 2배로 늘린 weak 배열에서 연속된 k개의 정점을 방문하는 것
// 임을 알 수 있다.

// 그 다음으로 중요한 것은, (어쩌면 가장) 어떤 친구에게 어느 벽의 청소를 맡길 것인가?
// 친구들의 투입순서를 고려해줘야하므로 이것도 그저 완전탐색으로 모든 순열에 대해서 하면 된다.


// 이렇게 보니, 더 편하진 않지만 내 방법도 꽤나 괜찮다는 생각이 든다. ㅇㅇ
// 가장 짧은 구간을 구해서 그걸 index 0 부터 시작하게 세팅해놓고 친구들 순서만 고려하면 되니...
// 이렇게 세팅하는 동안 O(n^2)의 시간이 든다. 물론 이 문제에서는 n은 최대 200이니 나쁘지않음.

fun main() {
    class Solution {
        private lateinit var wall: IntArray
        fun solution(n: Int, weak: IntArray, dist: IntArray): Int {
            var answer = 0

            wall = IntArray(n) { 0 }
            for (i in weak) wall[i] = 1
            var totalWeak = weak.size
            var minPeople = dist.size

            val vel = dist.sortedDescending()
            for (v in vel) {
                for (i in weak) {
                    var rest = -1
                    if (i + v >= n) rest = n - i - v




                    if (rest >= 0) { // 돌아서 index 0부터 더 가야함.

                    }
                }
            }



            return answer
        }
    }

    val sol = Solution()
    var n: Int
    var weak: IntArray
    var dist: IntArray
    n = 5
    weak = intArrayOf(1, 5, 6, 10)
    dist = intArrayOf(1, 2, 3, 4)
    println(sol.solution(n, weak, dist))
}