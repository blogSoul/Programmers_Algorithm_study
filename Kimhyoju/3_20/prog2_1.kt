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
                                count--
                                break
                            } else if (flagSplit[1] == "NUMBER") {
                                println("com[${count+1}]: ${com[count+1]}")
                                for (num in com[count+1]) {
                                    if (num.toInt() !in 48..57) {
                                        exitFlag = 1
                                        println("b")
                                        break
                                    }
                                }
                                break
                            } else if (flagSplit[1] == "STRING") {
                                for (ch in com[count+1]) {
                                    if (ch.toInt() !in 65..90 && ch.toInt() !in 97..122) {
                                        exitFlag = 1
                                        println("c")
                                        break
                                    }
                                }
                                break
                            }
                        }
                    }
                    count += 2
                    if (flagFound == 0) {
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
            answerList.forEach{println(it)}
            return answerList.toBooleanArray()
        }
    }


    val sol = Solution()
    var program: String
    program = "line"
    var flag_rules = arrayOf("-s STRING", "-n NUMBER", "-e NULL")
    var commands = arrayOf("line -n 100 -s hi -e", "lien -s Bye")
    commands = arrayOf("line")
    commands = arrayOf("line -e")
//    program = "line"
//    flag_rules = arrayOf("-s STRING", "-n NUMBER", "-e NULL")
//    commands = arrayOf("line -s 123 -n HI", "line fun")

    println(sol.solution(program, flag_rules, commands))
}
