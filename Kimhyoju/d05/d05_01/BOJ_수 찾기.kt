package d05.d05_01

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))
    val n = br.readLine().toInt()
    val numbers = br.readLine().split(" ").map { it.toInt() }.sorted()
    val m = br.readLine().toInt()
    val targets = br.readLine().split(" ").map { it.toInt() }
    br.close()

    for (target in targets) {
        var left = 0
        var right = n-1
        var mid = 0
        var found = 0
        while (left <= right) {
            mid = (left+right)/2
            if (numbers[mid] > target) right = mid-1
            else if (numbers[mid] < target) left = mid+1
            else {
                found = 1
                break
            }
        }
        println(found)
        bw.write("$found\n")
    }
    bw.close()
}
