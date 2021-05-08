package d05_08

fun main() {
    class Solution {
        fun solution(enroll: Array<String>, referral: Array<String>, seller: Array<String>, amount: IntArray): IntArray {
            var answer: IntArray = intArrayOf()
            return answer
        }
    }

    var enroll = arrayOf("john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young")
    var referral = arrayOf("-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward")
    var seller = arrayOf("young", "john", "tod", "emily", "mary")
    var amount = intArrayOf(12, 4, 2, 5, 10)
    println("solution: ${Solution().solution(enroll, referral, seller, amount)}")
}
