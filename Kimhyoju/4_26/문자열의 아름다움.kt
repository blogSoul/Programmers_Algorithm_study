package `4_26`

// 슬라이딩 윈도우 투포인터로 getBeauty를 구현했다.
// 그리고 부분문자열 (연속)을 찾는 건 슬라이딩 윈도우로.

// 통과는 반쯤까진 되고 그 아래로는 시간초과..

// 나름대로 getBeauty를 투포인터를 사용해 개량했으나, 더 발전시킬 수 있다.
// abcdaa 라면, 기존에는  start 포인터를 맨처음 a, end 포인터를 맨 뒤 a에 두고,
// start를 고정시켜놓고 end를 start까지 접근시키면서 되면 res에 저장하고 break
// 그후로 start를 1옮기고 고정시키고 또 end를 움직이고.. 이렇게 했는데,
// 이러면 시간이 오래걸릴 수도 있다. max (그 자리에서 가능한 최대값)에 따라 로직을 수행하도록 해야한다.
// max가 5인건 맨첫a와 맨끝a 이게 안되면
// max가 4인건 맨첫 a와 끝에서 한칸 왼쪽의 a와, 두번째b와 맨 끝의 a.
// 이렇게 하면 편한 이유가, 일단 BFS처럼 최단시간에 찾는 것이 보장되고, 두개의 포인터를 동시에 오른쪽으로 옮기면서 검사가 가능하기 때문.
// 해보자. 노트로 쓰다보니 발견한 방법이다. 이렇게 하려면 max에 해당하는 end 로 기준을 잡아야 한다.
// 뭐 엄밀하게 말하면 투포인터랑은 좀 다르지만.

// 내 코드에서는 max는 곧 end이기에, max 변수는 따로 쓰지않았다.
// 이렇게해도 8번부터 모두 시간초과. 다른곳에 문제가 있다.
// getBeauty가 아니라, str 전체에서 슬라이딩 윈도우하는 solution 부분에서 문제가 있는건가...

// 생각해보니, abcdaba 에서, abcdaba의 아름다움은 양끝이 같으면 maxOf(abcdab, bcdaba) 이다.
// DP 로 하는건가? 한번 연산한 값은 다시 연산하지 않는다.
// Recursion 을 이용한 분할정복으로 가능할 것 같긴한데...
// 근데 s길이가 300,000 인데, 스택이 터질것같은데.. 게다가 DP도 써야한다.
// abcdab -> abcda, bcdab 인데,
// bcdaba -> bcdab, cdaba 이렇게 겹친다.
// Map 에 300,000 개의 데이터를 저장해도 되겠지..?
// 그냥 Recursion 없이 getBeauty만 이용하되, 여기서 Map을 써보자.

// .. 시간이 더 늘어났다. 7번이 원래 1000ms 안에 끝났는데 이번엔 1400~1500 정도 된다.
// 결국 구글링함.
// 이건 구간 합으로, 세그먼트 트리를 활용하는 문제였다....
// 세그먼트 트리를 구현해보자.

fun main() {
    class Solution {
        val strMap = mutableMapOf<String,Long>()
        fun solution(s: String): Long {
            if (s.toSet().size == 1) return 0L
            var answer: Long = 0
            for (wSize in 2..s.length) {
                for (idx in s.indices) {
                    if (idx + wSize > s.length) break
                    val subStr = s.substring(idx, idx + wSize)
                    println("subStr: $subStr")
                    answer += getBeauty(subStr)
                }
            }
            return answer
        }
        fun dfs(str: String) {

        }

        fun getBeauty(str: String): Long {
            if (str.toSet().size == 1) return 0L
            if (str.length == 2) return 1L
            if (strMap.containsKey(str)) return strMap[str]!!
            var res = 0L
            for (end in str.lastIndex downTo 1) {
                var j = end
                for (start in str.indices) {
                    if (j > str.lastIndex) break // max = end
                    if (str[start] != str[j]) {
//                        res = (j - start).toLong()
//                        break
                        strMap[str] = end.toLong()
                        return strMap[str]!!
                    }
                    j++
                }
                if (res >= end) break
            }
            println("getBeauty of $str: $res")
            return res
        }
    }

    var s = "baby"
//    s = "abbca"
    println("solution: ${Solution().solution(s)}")
}
