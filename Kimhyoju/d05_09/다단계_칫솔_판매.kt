package d05_09

import kotlin.math.floor

// 반올림 처리를 잘해줘야한다.
fun main() {
    data class Node(val name: String) {
        var parent: Node? = null
        var revenue = 0
    }

    class Solution {
        fun solution(enroll: Array<String>, referral: Array<String>, seller: Array<String>, amount: IntArray): IntArray {
            val node = mutableMapOf<String, Node>()
            for (i in enroll.indices) {
                node[enroll[i]] = Node(enroll[i])
                if (referral[i] != "-") node[enroll[i]]!!.parent = node[referral[i]]
            }
            for (i in seller.indices) {
                var profit = amount[i] * 100
                var person = node[seller[i]]
                while (person != null) {
                    println("profit: $profit")
                    val up = floor(profit.toDouble() * 0.1).toInt()
                    println("up: $up")
                    profit -= up
                    person.revenue += profit
                    println("${person.name} revenue: ${person.revenue}")
                    profit = up
                    person = person.parent
                }
            }
            val answer = IntArray(enroll.size)
            for (i in enroll.indices) {
                answer[i] = node[enroll[i]]!!.revenue
            }
            println(answer.toList())
            return answer
        }
    }

    var enroll = arrayOf("john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young")
    var referral = arrayOf("-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward")
    var seller = arrayOf("young", "john", "tod", "emily", "mary")
    var amount = intArrayOf(12, 4, 2, 5, 10)
    println("solution: ${Solution().solution(enroll, referral, seller, amount)}")
}
