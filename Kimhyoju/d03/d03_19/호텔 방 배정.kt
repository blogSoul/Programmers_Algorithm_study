// 2019 카카오 개발자 겨울인턴십.
// 언뜻 유니온 파인드로 풀어야 한다는 걸 들었던 것 같다.
// 근데 시발 어떻게? 그래프도 아닌데. 그래프로 생각을 해야하나?

// k의 숫자가 Int형의 크기를 넘어선다. Int는 2x10^9인데 이건 최대 10^12
// 그래 리니어하게 하는 건 안되는 걸 알겠어. 근데 어떻게 유니온 파인드를?

// 아 알겠다. 이건 1부터 부모가 되는게 아니라, 각 노드가 처리되면서 자신의 뒤를 각각 부모로 삼는 것이다.
// 여기서 부모는, 고객이 room[1]를 갖고싶어한다면, 실질적으로 1의 부모인 parent 번호의 방을 넘겨줘야한다는 뜻.
// reverse Union Find 라고해야하나..? 이런 유니온이 여러개가 생기면서 합쳐지는 것이다.
// 1 2 3 4 5 6
// 초기화 상태이다. 여기서 각각의 부모는 자기 자신이다.
// 여기서 1이 선택되면 1을 넘겨주고 1은 그 다음 점유되지 않은 부모 2를 가리킨다.
// 1 -> 2 3 4 5 6
// 3이 선택되면 3을 넘겨주고 3은 그 다음 점유되지 않은 부모 4를 가리킨다.
// 1 -> 2 3 -> 4 5 6
// 여기까지만 봤을때, 이제 1을 요구하면 1의 부모인 2, 3을 요구했을 때 그 부모인 4를 넘겨줘야한다.
// 4이 선택되면 4를 넘겨주고 4은 그 다음 점유되지 않은 부모 5를 가리킨다.
// 1 -> 2 3 -> 4 -> 5 6
// 이렇게, 지금까진 set(1,2), set(3,4,5), set(6) 이렇게 3개의 셋이 있다.
// 1이 선택되면, 1의 부모인 2를 넘겨준다.
// 2는 이제 그 다음인 3을 가리키는데, 3의 부모인 5를 가리키게 된다.
// 1 -> 2 -> 3 -> 4 -> 5  6
// 3이 선택되면, 3의 부모인 5를 넘겨준다.
// 5는 이제 다음인 6을 가리킨다.
// 1 -> 2 -> 3 -> 4 -> 5 -> 6

// 90.9 / 100
// 메모리 초과와 런타임 에러가 뜬다.
// 둘다 어떤 문제인지 감은 온다.
// k에 최대값 가까이 넣으면 바로 parent의 인덱스값은 Int만 되므로, Negative가 되는 것..
// 하지만 배열의 size는 Int까지만 되는데.. 어떡하지?
// 처음 생각했던대로, StringBuilder (StringBuffer)를 이용해서 메모리를 절약해보자
// Like Bitmasking

// 이것도 Heap Space Memory 초과가 뜨는 것같다.
// 생각해보면, K가 2x10^11 일때, 정작 방문객이 2명이면 엄청난 공간 낭비다.

// 이를 어떻게 개선할까. 아, 맵으로 하면 될 것 같다.
// 바로 다음 노드의 부모를 자기 자신으로 초기화하는 과정을 없애고
// 애초에 부모가 자기 자신이고 바뀌지않았으면 map에 추가된적도 없는 것으로.
// 부모가 바뀌었을때만 map에 등록되도록 해보자.



fun main() {
    class Solution {
        fun solution(k: Long, room_number: LongArray): LongArray {
            val answer = LongArray(room_number.size) { 0 }
//            var parent = IntArray((k + 1).toInt()) { it }
            var parent = mutableMapOf<Long, Long>()
            for ((idx, rn) in room_number.withIndex()) {
                if (!parent.containsKey(rn)) parent[rn] = rn
                var tempRn = rn
                if (parent[rn] != rn) {
                    tempRn = parent.getOrDefault(rn,tempRn)
                    while (parent[tempRn] != tempRn) {
                        parent[tempRn] = parent.getOrDefault(parent.getOrDefault(tempRn,tempRn),tempRn)
                        tempRn = parent.getOrDefault(tempRn,tempRn)
                    }
                }
                answer[idx] = tempRn
                println("answer[$idx] = $tempRn")
                parent[tempRn] = parent.getOrDefault(tempRn + 1, tempRn + 1)
                tempRn+=1
                while (parent[tempRn] != tempRn) {
                    parent[tempRn] = parent.getOrDefault(parent.getOrDefault(tempRn,tempRn),tempRn)
                    tempRn = parent.getOrDefault(tempRn,tempRn)
                }
                println("parent: ${parent.toList()}")
            }
            println("answer: ${answer.toList()}")
            return answer
        }
    }

    val sol = Solution()
    var k: Long
    var room_number: LongArray
    k = 2_000_000_000_00
    room_number = longArrayOf(1, 3, 4, 1, 3, 1)
//    k= 2
//    room_number = longArrayOf(1,1)
    println(sol.solution(k, room_number))
}
