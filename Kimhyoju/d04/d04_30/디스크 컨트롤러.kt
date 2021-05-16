package d04.d04_30


// 다 된 것 같은데 틀리더라
// 혹시나 해서 질문을 봤는데, 처음에 정렬할 때, 시작순서로만 정렬하면 안된다.
// 시작순서로 정렬한 후, 시작순서가 같다면 수행시간이 짧은 것순으로 정렬해야하기 때문!!
// 배워간다.
fun main() {
    class Solution {
        lateinit var tasks: MutableList<List<Int>>
        fun solution(jobs: Array<IntArray>): Int {
            tasks = jobs.map { it.toList() }.sortedWith(compareBy<List<Int>> { it[0] }.thenBy { it[1] }).toMutableList()
            println("tasks: $tasks")
            var answer = 0
            var currentTime = 0
            answer = tasks[0][1]
            currentTime = tasks[0][1] + tasks[0][0]
            tasks.removeAt(0)
            while (tasks.isNotEmpty()) {
                val newTask = tasks.filter { it[0] <= currentTime }.minBy { it[1] }
                if (newTask.isNullOrEmpty()) {
                    val task = tasks.removeAt(0)
                    println("task: $task")
                    currentTime = task[1] + task[0]
                    answer += task[1]
                } else { // newTask is not null
                    tasks.remove(newTask)
                    println("newTask: $newTask")
                    currentTime += newTask[1]
                    answer += currentTime - newTask[0]
                }
                println("answer: $answer")
                println("currentTime: $currentTime")
                println("tasks: $tasks")
            }
            return answer / jobs.size
        }
    }

    var jobs = arrayOf(
            intArrayOf(0, 3),
            intArrayOf(1, 9),
            intArrayOf(2, 6),
            intArrayOf(25, 8)
    )
    jobs = arrayOf(
            intArrayOf(1, 9),
            intArrayOf(2, 6),
            intArrayOf(25, 8)
    )
    jobs = arrayOf(
            intArrayOf(24, 10),
            intArrayOf(28, 39),
            intArrayOf(43, 20),
            intArrayOf(37, 5),
            intArrayOf(47, 22),
            intArrayOf(20, 47),
            intArrayOf(15, 34),
            intArrayOf(15, 2),
            intArrayOf(35, 43),
            intArrayOf(26, 1)
    )
    println("solution: ${Solution().solution(jobs)}")
}
