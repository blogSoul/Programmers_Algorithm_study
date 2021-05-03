// 그냥 했다가 시간초과로 한번 불합떴었음.
// 함정은, 쿼리가 중복되는 것이 있다는 것.
// 쿼리를 처리하고 나서 map에 저장해두면, 중복쿼리는 O(1)로 빠르게 처리가능.

// 그런데 이렇게 해도 다 통과해도 효율성 2번은 시간초과..
// 다른사람들도 효율성 2번에서 고통받고 있다. 모르겠어서 구글링함.

// 여기서 Trie 자료구조를 알게 되었다. 그냥 말로만 들었는데 여기서 쓰이는 구나.
// Trie 트라이 자료구조는 문자열을 담는 자료구조로 자동완성 기능에 자주 사용되는 자료구조.
//
fun main() {
    class Solution1 { // 내 방법 Linear하게 다 비교하지만 중복 키워드에 대해서는 map으로 개선.
        fun solution(words: Array<String>, queries: Array<String>): IntArray {
            val queryMap = mutableMapOf<String, Int>()
            var answer = IntArray(queries.size) { 0 }
            for ((idx, query) in queries.withIndex()) {
                if (queryMap.containsKey(query)) {
                    println("queryMap[$query]: ${queryMap[query]}")
                    answer[idx] = queryMap[query]!!.toInt()
                    continue
                } else {
                    queryMap[query] = 0
                }
                var state = 0
                if (query.first() == '?' && query.last() == '?') {
                    state = 0
                } else if (query.first() == '?') {
                    state = -1
                } else if (query.last() == '?') {
                    state = 1
                }
                for (word in words) {
                    if (word.length == query.length) {
                        if (state == 0) {
                            answer[idx]++
                        } else if (state == -1) {
                            val wordStartIdx = query.indexOfFirst { it != '?' }
                            if (word.endsWith(query.substring(wordStartIdx))
                            ) {
                                answer[idx]++
                            }
                        } else if (state == 1) {
                            val questionStartIdx = query.indexOfFirst { it == '?' }
                            if (word.startsWith(query.substring(0 until questionStartIdx))) {
                                answer[idx]++
                            }
                        }
                    }
                }
                queryMap[query] = answer[idx]
            }
            println("queryMap: $queryMap")
            println("answer: ${answer.toList()}")
            return answer
        }
    }

    // Trie를 이용한 처음에는, 다른 사람들의 풀이와는 다르게
    // insert와 find를 재귀로 작성했기에, ?가 접두사로 오던 접미사로 오던 모두 커버 가능했다.
    // 하지만, 런타임 에러가 뜨는 걸 보고 words의 길이는 100,000 이하인 것을 발견하고
    // 아 스택 터졌구나를 깨달았다. 또한 시간초과도 map을 사용했을땐 1개 뜨고
    // 그냥 naive linear도 시간초과가 3개 떴는데 이건 죄다 시간초과가 뜨더라.
    // 결국 다른 사람의 코드 보면서 재귀 말고 cur Node를 실시간으로 재정의 하는 방식으로 하기로 함.

    // 이제 해야할 것은 Trie 에서 아래로 남은 ?의 개수만큼 아래로 내려가 자식의 리스트를 구하는 건데
    // 로드가 굉장히 클 것 같다.. 아니나 다를까 시간초과..

    // DFS를 해야하나..?

    // 미친 그냥 Trie 자료구조를 길이별로 여러개 선언하고 count 변수를 노드마다 쓰면 되는 거였다.
    // 길이 5짜리 Trie에서는 cnt만 봐도 그 아래로 5개짜리가 몇개인지 파악가능하다..
    // 하... 길이별로 Trie를 쓰다니...
    // 공간이 존나 남아도나보네 이게 백준의 후유증인가

    class Solution2 { // Trie를 이용한 방법.
        inner class Trie (val trieLen: Int) {
            var root = Node('R')
            fun insertString(str: String) {
                var cur = root
                for (idx in str.indices) {
                    cur.count++
                    var firstIdx = cur.children.indexOfFirst { it.c == str[idx] }
                    if (firstIdx != -1) {
                        cur = cur.children[firstIdx]
                    }
                    else { // new node added
                        cur.children.add(Node(str[idx]))
                        cur = cur.children.last()
                    }
                }
                cur.terminal = 1
            }

            fun find(str: String): Int {
                var cur = root
                for (idx in str.indices) {
                    if (str[idx] == '?') {
                        println("cur.count: ${cur.count}")
                        return cur.count
                    }
                    else {
                        var firstIdx = cur.children.indexOfFirst { it.c == str[idx] }
                        if (firstIdx != -1) {
                            cur = cur.children[firstIdx]
                        }
                        else {
                            return 0
                        }
                    }
                }
                return cur.count
            }
            inner class Node (val c: Char) {
                var terminal = 0
                val children = mutableListOf<Node>()
                var count = 0
            }
        }

        fun solution(words: Array<String>, queries: Array<String>): IntArray {
            var answer = IntArray(queries.size) { 0 }
            val tries = mutableListOf<Trie>()
            val reverseTries = mutableListOf<Trie>()
            for (word in words) {
                var firstIdx = tries.indexOfFirst { it.trieLen == word.length }
                if (firstIdx == -1) { //not exist
                    tries.add(Trie(word.length))
                    reverseTries.add(Trie(word.length))
                    firstIdx = tries.lastIndex
                }
                tries[firstIdx].insertString(word)
                reverseTries[firstIdx].insertString(word.reversed())
            }

            for (idx in queries.indices) {
                val qLen = queries[idx].length
                val firstIndex = tries.indexOfFirst { it.trieLen == qLen }
                if (firstIndex != -1) {
                    if (queries[idx].last() == '?') answer[idx] = tries[firstIndex].find(queries[idx])
                    else answer[idx] = reverseTries[firstIndex].find(queries[idx].reversed())
                }
                else { // invalid query == no answer
                    answer[idx] = 0
                }
                println(answer[idx])
            }
            return answer
        }
    }

    var words: Array<String>
    var queries: Array<String>
    words = arrayOf("frodo", "front", "frost", "frozen", "frame", "kakao")
    queries = arrayOf("fro??", "????o", "fr???", "fro???", "pro?", "fro??", "pro?")


//    val sol = Solution()
//    Solution1().solution(words, queries)
    Solution2().solution(words, queries)
    val wordStartIdx = "????".indexOfFirst { it != '?' }
    val dmap = mutableMapOf<String, Int>()
    for (i in mutableListOf<Int>()) {
        println(i)
    }
}

