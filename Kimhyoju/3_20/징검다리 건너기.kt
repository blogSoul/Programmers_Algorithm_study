// 유니온 파인드로 풀어봤는데, 정확성은 다 맞는데 효율성이 죄다 시간초과.
// 이상하넹... Googlong 해보니 다 이분탐색으로 풀었다고 한다.
// 지금보니까 stones의 배열값들은 최대 2억..... 허허 루프를 엄청 돌아야겠구나 유니온파인드를 해도.
// 이분탐색은 log(NlogM), N: 디딤돌의 개수, M: 디딤돌 값의 최댓값.

// 이분탐색이 빠르긴 빠르다. 미리 mid로 결과값을 정해놓고 그게 최대인지 아닌지를 검사하면 되니.

// 유니온 파인드의 시간복잡도는  O(α(N)). 안쪽은 아커만 함수인데, N이 2의 65536제곱일때 함수값이 5가 된다고하니
// 그냥 상수라고 봐도 무방하다.
// 하지만 이 문제에서는, N이 디딤돌의 개수. M을 디딤돌값의 최댓값으로 하면
//  O(α(N))을 찾는 과정을 최대 M번 해야하니,  O(Mα(N)) 이된다. 2억번을 상수값만큼 해야하는 것.
// N이 크냐 M이 크냐에 따라서 알고리즘을 따로 적용해야할듯싶다.

fun main() {
    class Solution {
        fun solution(stones: IntArray, k: Int): Int {
            var answer = 0
            var left = 1
            var right = 200_000_000
            var mid = (left + right) / 2
            var result = binarySearch(mid, k, stones)
            while (left <= right) {
                println("left: $left")
                println("mid: $mid")
                println("right: $right")
                if (result) left = mid+1
                else right = mid-1
                mid = (left + right) / 2
                result = binarySearch(mid, k, stones)
            }
            answer = left
            return answer
        }

        fun binarySearch(n: Int, k: Int, stones: IntArray): Boolean {
            var count = 0
            for (i in stones.indices) {
                if (stones[i] - n <= 0) count++
                else count = 0
                if (count >= k) return false
            }
            println("n: $n")
            return true
        }
    }


    val sol = Solution()
    var stones: IntArray
    var k: Int

    stones = intArrayOf(2, 4, 5, 3, 2, 1, 4, 2, 5, 1)
//    stones = intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
    k = 3

    println(sol.solution(stones, k))
}
