import java.lang.StringBuilder

// 1. 비디오를 모두 초로 변환하고,

// 2. 각 시청자들의 구간을 +=1 하여 하나의 비디오로 flatten 한다.
// -> 여기서 나는 나름 신경쓴다고 bitmasking 비슷한 원리로 StringBuilder를 이용해 "00001122333221110001122100"
// -> 이런식으로 했는데, 시간초과가 뜬다.. 그냥 얌전히 int 배열로 해야겠다..
// -> 공간은 싸지만, 시간은 비싸다. 백준때문에 공간 너무 신경쓰는듯..

// 3. 이후, 광고의 시간을 video에 처음부터 끝까지 하나하나 갖다대어 sliding window 해야하는데...
// -> 나는 모르겠어서 일단 무식하게 그냥 완전탐색으로 하나하나 비교하고 아니면 다음으로 넘어가서 다시 하나하나 다시 비교했다.
// -> 여기서 이 문제의 진가가 드러난다. 바로 투 포인터 알고리즘. 포인터 2개를 각각 광고의 시작과 끝으로 설정하고
// 광고의 길이는 정해져있으니, 다음으로 넘어갈 때, window의 첫 부분은 빼고 다음부분만 더하면 되는 것이다.
// 알고나면 간단하지만 생각하기는 어려운.... 다음에는 생각해내자!

// 시간초과 몇개는 그렇다 쳐도... 28번만 계속 틀린다.. 뭐지?
// -> sum을 갱신하는 과정에서 끝 인덱스가 잘못됐었음. +1 해줘야 끝까지 간다.

// 이제 시간초과를 해결해야하는데...


// 77.4 / 100

fun main() {
    class Solution {
        fun solution(play_time: String, adv_time: String, logs: Array<String>): String {
            var answer: String = ""
            val playSec = changeTimeToSec(play_time)
            // 모든 play_time을 초로 변환한 배열
            var video = Array(playSec) { 0 }
            for (log in logs) {
                val startSec = changeTimeToSec(log.substring(0..7))
                val endSec = changeTimeToSec(log.substring(9))
                for (sec in startSec until endSec) {
                    video[sec] += 1
                }
            }
//            println("flattened video: $video")
            // 이제 adv_time을 window로 하고, 이 큰 video에 sliding 하여 합이 가장 커지는 곳을 찾아낸다.
            var adv_start = 0
            var adv_sec = changeTimeToSec(adv_time)

            // 일단 처음 값 계산하고
            var sum = 0
            for (i in 0 until adv_sec) {
                sum += video[i]
            }
            var maxSum = sum

            // 투 포인터 알고리즘. 중복연산없이 처음만 빼고 끝만 추가.
            for (start in 1..playSec - adv_sec) {
                sum -= video[start - 1]
                sum += video[start + adv_sec - 1]
                if (sum > maxSum) {
                    adv_start = start
                    maxSum = sum
                }
            }
            answer = changeSecToTime(adv_start)
            return answer
        }

        fun changeTimeToSec(time: String): Int {
            var result = 0
            result += time.substring(0..1).toInt() * 60 * 60
            result += time.substring(3..4).toInt() * 60
            result += time.substring(6..7).toInt()
            return result
        }

        fun changeSecToTime(time: Int): String {
            var result = ""
            var hour = (time / (60 * 60)).toString()
            if (hour.length == 1) hour = "0$hour"
            var minute = (time % (60 * 60) / 60).toString()
            if (minute.length == 1) minute = "0${minute}"
            var second = (time % 60).toString()
            if (second.length == 1) second = "0${second}"
            result = "$hour:$minute:$second"
            return result
        }
    }

    val sol = Solution()
    var play_time: String
    var adv_time: String
    var logs: Array<String>
    play_time = "02:03:55"
    adv_time = "00:14:15"
    logs =
        arrayOf("01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30")

//    play_time = "99:59:59"
//    adv_time = "25:00:00"
//    logs = arrayOf(
//        "69:59:59-89:59:59",
//        "01:00:00-21:00:00",
//        "79:59:59-99:59:59",
//        "11:00:00-31:00:00",
//        "10:14:18-15:36:51",
//        "38:21:49-42:51:45"
//    )

    play_time = "50:00:00"
    adv_time = "50:00:00"
    logs = arrayOf("15:36:51-38:21:49", "10:14:18-15:36:51", "38:21:49-42:51:45")
    println(sol.solution(play_time, adv_time, logs))
}