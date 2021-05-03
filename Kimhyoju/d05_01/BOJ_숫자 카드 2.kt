package d05_01

// 일단 target을 찾고, 양옆으로 for문으로 찾는 방식을 택했는데, 이렇게 하니까 시간초과가 난다...
// 그러면서 덤으로 Parametric Search도 찾아봤다.

// 음... 설마 target을 찾고, answer+1 한다음, 그 target의 양옆으로 또 binary search 하는건가? DFS 로.

// 하나씩 뻗어나가면 최악의 경우 똑같은 값이 10만 개 있고 그 값을 10만 개 찾게 되면 전체를 탐색하는 것과 다를 게 없어집니다.
// 음... 그럼 lowerbound와 upperbound를 설정을 해야하는 건가..?

// https://jackpot53.tistory.com/33
// 여기서 upper bound와 lower bound를 제대로 설명해주고있다. 같은 값일때 자주 쓰이는 알고리즘.
// 두 bound를 보아하니, lower bound는 같은 값일때 가장 먼저 나오는 값, upper bound는 같은 값일 때 가장 나중의 값.
// 즉, Parametric Search와 같아보인다.

// 이진탐색과 다른점은 크기가 9인  int test1[] = {1,2,2,3,3,3,4,6,7} 이 주어질때 이진탐색은 정확히 같은 값이 있는곳을 찾는거지만
// lower bound는 주어진 값보다 같거나 큰 값이 처음으로 나오는걸 리턴해야 하는데 만약 lower_bound(9) 면 주어진 배열크기 만큼
// 리턴되어야 하기 때문에 high = array.length -1 이 아니고 high = array.length로 지정 해야 한다.
// 그리고 탐색한 값이  주어진 값보다 크거나 같으면 바로 리턴하지않고 같거나 처음으로 나오는 값을 찾기위해 범위를 더 좁히면서 찾아간다.
// 크거나 같은경우 high = mid 로 지정해서 범위를 좀더 좁혀 나가면서 찾아가는 것이다.
// lowerbound는 x값이 처음 나오는 인덱스, upperbound는 x를 초과한 값이 처음 나오는 인덱스.


import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

lateinit var cards: List<Int>
fun mainWithNaiveBinarySearch() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))
    val n = br.readLine().toInt()
    cards = br.readLine().split(" ").map { it.toInt() }.sorted()
    val m = br.readLine().toInt()
    val targets = br.readLine().split(" ").map { it.toInt() }
    for (target in targets) {
        var left = 0
        var right = n - 1
        var mid = 0
        var res = binarySearchRecursive(left, right, target)
        bw.write("$res ")
    }
    bw.close()
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))
    val n = br.readLine().toInt()
    cards = br.readLine().split(" ").map { it.toInt() }.sorted()
    println("\ncards: $cards")
    val m = br.readLine().toInt()
    val targets = br.readLine().split(" ").map { it.toInt() }
    br.close()
    for (target in targets) {
        var left = 0
        var right = n - 1
        var mid = 0
        var res = upperBound(target) - lowerBound(target)
        bw.write("$res ")
    }
    bw.close()
}

// lower_bound를 이용하여 x라는 값을 찾으면 x보다 크거나 작은값중 최솟값의 위치를 return
fun lowerBound(target: Int): Int {
    var low = 0
    var high = cards.size // size -1 이 아닌, size
    while (low < high) {
        val mid = (low + high) / 2
        if (target <= cards[mid]) high = mid // mid-1 이면 안됨. mid-1이 target이 아닐 수 있기 때문.
        else low = mid + 1
    }
    println("lowerBound low: $low, high: $high")
    return low
}

// lower bound와 마찬가지로 upper bound는 주어진 값보다  큰 값이 처음으로 나오는걸 리턴해야 하는데
// 만약 upper_bound(9) 면 주어진 배열크기 만큼 리턴되어야 하기 때문에 high = array.length -1 이 아니고
// high = array.length로 지정 해야 한다.
// 그리고 탐색한 값이 주어진 값보다 크다면  바로 리턴하지 않고 최초로 큰 값이 있는 위치를  찾기 위해
// high = mid 지정하여 범위를 더 좁히면서 찾아가면 된다.
fun upperBound(target: Int): Int {
    var low = 0
    var high = cards.size // size -1 이 아닌, size
    while (low < high) {
        val mid = (low + high) / 2
        if (target < cards[mid]) high = mid // mid -1이면 안됨. 찾으려는건 target 보다 큰 바로 다음 수이므로, 현재를 포함해야함.
        else low = mid + 1
    }
    println("upperBound low: $low, high: $high")
    return low
}

fun binarySearchRecursive(left: Int, right: Int, target: Int): Int {
    if (left > right) return 0
    var res = 0
    var mid = (left + right) / 2
    if (cards[mid] > target) res = binarySearchRecursive(left, mid - 1, target)
    else if (cards[mid] < target) res = binarySearchRecursive(mid + 1, right, target)
    else {
        res++
        res += binarySearchRecursive(left, mid - 1, target)
        res += binarySearchRecursive(mid + 1, right, target)
    }
    return res
}

fun binarySearch(_left: Int, _right: Int, target: Int): Boolean { // 오직 한 값이 존재하는지 안하는지.
    var left = _left
    var right = _right
    while (left < right) {
        var mid = (left + right) / 2
        if (target > cards[mid]) left = mid + 1
        else if (target < cards[mid]) right = mid - 1
        else return true
    }
    return false
}
