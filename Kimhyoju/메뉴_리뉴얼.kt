fun main() {
    val sol = Solution3()
    var orders = arrayOf("ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH")
    orders = arrayOf("XYZ", "XWY", "WXA")
    var course = intArrayOf(2, 3, 4)
    sol.solution(orders, course)
}

class Solution3 {
    private lateinit var orderMap: MutableMap<String, Int>
    fun solution(orders: Array<String>, course: IntArray): Array<String> {
        var answer = mutableListOf<String>()
        orderMap = mutableMapOf<String, Int>()

        // 1. 조합을 알아낸다.
        for (crs in course) {
//            println("crs: $crs")
            orderMap.clear()
            for (orderString in orders) {
//                println("orderString: $orderString")
                val orderList = orderString.split("").filter { it.isNotEmpty() }
                    .sorted().toMutableList()
                println("orderList: $orderList")
                val tempOrder = mutableListOf<String>()
                for (idx in orderList.indices) {
                    tempOrder.add(orderList[idx])
                    dfs(tempOrder, orderList, idx, crs)
                    tempOrder.removeAt(tempOrder.lastIndex)
                }
            }
            println("orderMap: $orderMap")
            val maxVal = orderMap.maxByOrNull { it.value }?.value
            if (maxVal != null) {
                if (maxVal >= 2) {
                    val maxList = orderMap.filter {
                        it.value == maxVal
                    }.keys.toList()
                    answer.addAll(maxList)
                }
            }
        }
        println("answer: $answer")

        // 2. 알아낸 조합 목록을 정렬한다.
        answer.sort()
        println("Sorted answer: $answer")

//        val a = "XW"
//        val b = "WX"
//        if (a.toList().containsAll(b.toList())) {
//            println("a contains b")
//        }
//        if (b.toList().containsAll(a.toList())) {
//            println("b contains a")
//        }



        return answer.toTypedArray()
    }

    private fun dfs(
        tempOrder: MutableList<String>, orderList: MutableList<String>,
        lastIndex: Int, limit: Int
    ) {
//        println("lastIndex: $lastIndex")
        if (orderList.lastIndex - lastIndex + tempOrder.size < limit) {
            // 이대로 계속 불려도 limit까지 수를 못채울때, 즉 ABCDE에서 2개를 먹어야하는데 E부터 시작할때
            return
        } else if (tempOrder.size == limit) {
            // 다 먹었을때
            val order = tempOrder.joinToString("")
//            println("order: $order")
            orderMap[order] = orderMap.getOrDefault(order, 0) + 1
            // or
            // val value = map["Example"] ?: 0
            // map["Example"] = value + 3
            return
        } else {
            // 더 먹어야할때
            for (idx in lastIndex + 1 until orderList.size) {
                tempOrder.add(orderList[idx])
                dfs(tempOrder, orderList, idx, limit)
                tempOrder.removeAt(tempOrder.lastIndex)
            }
        }
    }
}