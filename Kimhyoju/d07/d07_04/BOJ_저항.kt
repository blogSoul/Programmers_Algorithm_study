package d07.d07_04

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

enum class Resistance {
    BLACK, BROWN, RED, ORANGE, YELLOW, GREEN, BLUE, VIOLET, GREY, WHITE
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))
    val inputList = mutableListOf<String>()
    repeat(3){
        inputList.add(br.readLine().toUpperCase())
    }
    var result = "${Resistance.valueOf(inputList[0]).ordinal}${Resistance.valueOf(inputList[1]).ordinal}".toLong()
    println("result: $result")
    result *= getTenMultiply(Resistance.valueOf(inputList.last()).ordinal)
    bw.write("$result\n")
    br.close()
    bw.close()
}
fun getTenMultiply(n: Int): Long {
    var res = 1L
    repeat(n) {
        res *= 10L
    }
    return res
}