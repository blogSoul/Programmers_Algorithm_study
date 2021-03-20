fun main() {
    class Solution {
        fun solution(program: String, flag_rules: Array<String>, commands: Array<String>): BooleanArray {
            var answer: BooleanArray = booleanArrayOf()
            val answerList = mutableListOf<Boolean>()
            for (command in commands) {
                val com = command.split(" ")
                if (com[0] != program) {
                    //answer.plus(false)
                    answerList.add(false)
                    println("continue")
                    continue
                }
                var count = 1
                var exitFlag = 0
                val flagSet = mutableSetOf<String>()
                while (com.size > count) {
                    println("count: $count")
                    var flagFound = 0
                    for (flagR in flag_rules) {
                        val flagSplit = flagR.split(" ")
                        if (flagSplit[0] == com[count]) {
                            if (flagSet.contains(com[count])) {
                                println("a")
                                exitFlag = 1
                                break
                            }
                            flagSet.add(com[count])
                            flagFound = 1
                            if (flagSplit[1] == "NULL") {
                                println("NULL")
                                count--
                                break
                            } else if (flagSplit[1] == "NUMBER") {
                                println("NUMBER")
                                println("com[${count + 1}]: ${com[count + 1]}")
                                for (num in com[count + 1]) {
                                    if (num.toInt() !in 48..57) {
                                        exitFlag = 1
                                        println("b")
                                        break
                                    }
                                }
                                break
                            } else if (flagSplit[1] == "NUMBERS") {
                                println("NUMBERS")
                                println("com[${count + 1}]: ${com[count + 1]}")
                                for (num in com[count + 1]) {
                                    if (num.toInt() !in 48..57) {
                                        exitFlag = 1
                                        println("b")
                                        break
                                    }
                                }
                                count++
                                while (count + 1 < com.size && !com[count + 1].startsWith("-")) {
                                    println("com[${count + 1}]: ${com[count + 1]}")
                                    for (num in com[count + 1]) {
                                        if (num.toInt() !in 48..57) {
                                            exitFlag = 1
                                            println("b")
                                            break
                                        }
                                    }
                                    count++
                                }
                                count--
                                break
                            } else if (flagSplit[1] == "STRING") {
                                println("STRING")
                                println("com[${count + 1}]: ${com[count + 1]}")
                                for (ch in com[count + 1]) {
                                    println("ch: $ch")
                                    if (ch.toInt() !in 65..90 && ch.toInt() !in 97..122) {
                                        exitFlag = 1
                                        println("c")
                                        break
                                    }
                                }
                                break
                            } else if (flagSplit[1] == "STRINGS") {
                                println("STRINGS")
                                println("com[${count + 1}]: ${com[count + 1]}")
                                for (ch in com[count + 1]) {
                                    println("ch: $ch")
                                    if (ch.toInt() !in 65..90 && ch.toInt() !in 97..122) {
                                        exitFlag = 1
                                        println("c")
                                        break
                                    }
                                }
                                count++
                                while (count + 1 < com.size && !com[count + 1].startsWith("-")) {
                                    for (ch in com[count + 1]) {
                                        println("ch: $ch")
                                        if (ch.toInt() !in 65..90 && ch.toInt() !in 97..122) {
                                            exitFlag = 1
                                            println("c")
                                            break
                                        }
                                    }
                                    count++
                                }
                                count--
                                break
                            }
                        }
                    }
                    count += 2
                    if (flagFound == 0) {
                        println("flagFound: $flagFound")
                        exitFlag = 1
                    }
                    if (exitFlag == 1) break
                }
                println("command: $command")
                println("exitFlag: $exitFlag")
                if (exitFlag == 1) {
//                    answer.plus(false)
                    answerList.add(false)
                } else {
//                    answer.plus(true)
                    answerList.add(true)
                }
            }
            answerList.forEach { println(it) }
            return answerList.toBooleanArray()
        }
    }


    val sol = Solution()
    var program: String
    program = "line"
    var flag_rules = arrayOf("-s STRING", "-n NUMBER", "-e NULL")
    var commands = arrayOf("line -n 100 -s hi -e", "lien -s Bye")
    program = "line"
    flag_rules = arrayOf("-s STRING", "-n NUMBER", "-e NULL")
    commands = arrayOf("line -s 123 -n HI", "line fun")

    flag_rules = arrayOf("-s STRINGS", "-n NUMBERS", "-e NULL")
    commands = arrayOf("line -n 100 102 100 200 -s dd -e", "line -n id pwd -n 100")

    flag_rules = arrayOf("-s strings", "-n numbers", "-e null")
    commands = arrayOf("line -n 100 102 -s hi -e", "line -n id pwd -n 100")

    program = "trip"
    flag_rules = arrayOf("-days NUMBERS", "-dest STRING")
    commands = arrayOf("trip -days 15 10 -dest Seoul Paris", "trip -days 10 -dest Seoul")

    flag_rules = arrayOf("-dests STRINGS","-day NUMBER", "-days NUMBERS", "-dest STRING","-e NULL")
    commands = arrayOf("trip -days 15 10 -dest Seoul Paris", "trip -days 10 -dest Seoul")
    commands = arrayOf("trip -days 15 10 -dests Seoul Paris DSFS SDAG -e")

    println(sol.solution(program, flag_rules, commands))
}
