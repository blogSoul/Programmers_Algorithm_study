package d05.d05_02

fun main(args: Array<String>) {
    val (a, b) = readLine()!!.split(' ').map(String::toInt)
    repeat(b){
        repeat(a){
            print("*")
        }
        println()
    }
}