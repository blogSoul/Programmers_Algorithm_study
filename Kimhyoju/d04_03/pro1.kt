fun main() {
    class Solution {
        fun solution(gift_cards: IntArray, wants: IntArray): Int {
            var answer = 0
            val giftCards = gift_cards.toMutableList()
            val wantsCards = wants.toMutableList()

            var removingValue = mutableListOf<Int>()
            for ((idx,gift) in giftCards.withIndex()) {
                if (gift == wantsCards[idx]) {
                    removingValue.add(gift)
                }
            }
            for (v in removingValue) {
                giftCards.remove(v)
                wantsCards.remove(v)
            }
            giftCards.sort()
            wantsCards.sort()
            println("giftCards: $giftCards")
            println("wantsCards: $wantsCards")

            // Two-Pointer
            var giftIdx = 0
            var wantIdx = 0
            var count = 0
            while (maxOf(giftIdx,wantIdx) < giftCards.size) {
                if (giftCards[giftIdx] == wantsCards[wantIdx]) {
                    giftIdx++
                    wantIdx++
                    count++
                }
                else if (giftCards[giftIdx] > wantsCards[wantIdx]) {
                    wantIdx++
                    answer++
                }
                else {
                    giftIdx++
                    answer++
                }
            }
            println("giftIdx: $giftIdx")
            println("wantIdx: $wantIdx")
            println("count: $count")
            answer = giftCards.size - count
            return answer
        }
    }

    val sol = Solution()
    val gift_cards = intArrayOf(4,5,3,2,1)
    val wants = intArrayOf(2,4,4,5,1)
    println(sol.solution(gift_cards,wants))
}
