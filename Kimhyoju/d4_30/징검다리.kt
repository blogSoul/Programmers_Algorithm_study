package d4_30

// 딱봐도 이분탐색같긴 한데... 어떻게 이분탐색으로 풀지 감이 안온다...
// 바위간의 거리 차를 새로 구해서 이걸로 만지작 하는것 같은데..
// 결국 구글링함.
// https://velog.io/@hyeon930/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%A7%95%EA%B2%80%EB%8B%A4%EB%A6%AC-Java
// 문제에서 묻는 바를 다르게 생각할 수 있어야하는 것 같다.
// 문제에서는 돌을 n개 만큼 없앴을 때 시작점, 끝점, 돌 사이에 거리 중 최솟값 중에 최댓값을 구하라고 한다.
// 묻는 바를 반대로 생각하여 n개의 돌을 없애서 돌 사이 거리의 최솟값이 x로 만들 수 있는가? 로 바꾼다면 이분탐색을 생각해볼 수 있다.

// n개의 바위를 제거 한 뒤 구하게 되는 바위 간 간격의 최솟값 중 가장 큰 값이 무엇인지 찾아야 합니다.
// 이분 탐색을 적용하기 위해서 질문을 바꿔보면, 바위 간 간격의 최솟값이 x보다 크거나 같게 되는 집합이 존재하는가로 바꿀 수 있습니다.
// 만약 존재한다면 x 보다 더 큰 값을 찾아보면 되고, 그렇지 않다면 x보다 더 작은 값을 찾아보면 됩니다.
//출처: https://privatedevelopnote.tistory.com/76 [개인노트]
fun main() {
    class Solution {
        var answer = 0
        fun solution(distance: Int, rocks: IntArray, n: Int): Int {
            rocks.sort()
            return binarySearch(distance, rocks, n)
        }

        fun binarySearch(distance: Int, rocks: IntArray, n: Int): Int {
            var answer = 0
            var left = 0
            var right = distance
            var mid = 0
            while (left <= right) {
                var cnt = 0 // 제거한 돌 개수
                var prev = 0 // 이전 돌
                var min = Int.MAX_VALUE
                mid = (left + right) / 2 // 바위 사이의 최소거리
                for (i in rocks.indices) {
                    if (rocks[i] - prev < mid) {
                        // mid보다 작은 값이 존재한다는 뜻으로 해당 돌을 제거한다.
                        cnt++
                    } else {
                        // mid보다 크거나 같은 값이 존재하므로, prev를 현재 돌로 초기화.
                        min = minOf(min,rocks[i] - prev)
                        prev = rocks[i]
                    }
                }

                // 제거한 돌 개수 cnt 가 n 보다 많다면 줄여야한다.
                // 바위사이 최소거리의 기준을 낮춰야한다.
                if (cnt > n) right = mid - 1

                // 제거한 돌 개수가 기준과 같거나 적다면 더 많은 바위를 제거해야한다.
                // 바위사이 최소거리의 기준을 높여야한다.
                else {
                    answer = min
                    left = mid + 1
                }
            }
            return answer
        }
    }

    var distance = 25
    var rocks = intArrayOf(2, 14, 11, 21, 17)
    var n = 2
    println("solution: ${Solution().solution(distance, rocks, n)}")
}
