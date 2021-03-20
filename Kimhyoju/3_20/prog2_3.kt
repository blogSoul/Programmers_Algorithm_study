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
                val aliasMap = mutableMapOf<String, String>()
                while (com.size > count) {
                    println("count: $count")
                    var flagFound = 0
                    for (flagR in flag_rules) {
                        println("flagR: $flagR")
                        var flagSplit = flagR.split(" ")
                        if (flagSplit.size > 2) { // ALIAS
                            if (!aliasMap.containsKey(flagSplit[0])) {
                                aliasMap[flagSplit[0]] = flagSplit[2]
//                                flagSplit = flag_rules.first {it.split(" ")[0] == " " }
                                flagSplit = flag_rules.first {it.split(" ")[0] == flagSplit[2] }.split(" ")
                            }
                        }
                        if (flagSplit[0] == com[count] || flagSplit[0] == aliasMap.getOrDefault(com[count], "")) {
                            if (flagSet.contains(flagSplit[0])) {
                                println("duplicate flag: ${com[count]}")
                                exitFlag = 1
                                break
                            }
                            flagSet.add(flagSplit[0])
                            println("flagSet.add(${flagSplit[0]})")
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

//    program = "line"
//    flag_rules = arrayOf("-s STRING", "-n NUMBER", "-e NULL")
//    commands = arrayOf("line -s 123 -n HI", "line fun")

//    flag_rules = arrayOf("-s STRING", "-n NUMBER", "-e NULL")
//    commands = arrayOf("line -s 123 -n HI", "line fun")

//    flag_rules = arrayOf("-s STRINGS", "-n NUMBERS", "-e NULL")
//    commands = arrayOf("line -n 100 102 100 200 -s dd -e", "line -n id pwd -n 100")

//
//    flag_rules = arrayOf("-s STRINGS", "-n NUMBERS", "-e NULL")
//    commands = arrayOf("line -n 100 102 -s hi -e", "line -n id pwd -n 100")

//    program = "trip"
//    flag_rules = arrayOf("-days NUMBERS", "-dest STRING")
//    commands = arrayOf("trip -days 15 10 -dest Seoul Paris", "trip -days 10 -dest Seoul")

//    program = "line"
//    flag_rules = arrayOf("-s STRING", "-num NUMBER", "-e NULL", "-n ALIAS -num")
//    commands = arrayOf("line -n 100 -s hi -e", "line -n 100 -e -num 150")
//
//    program = "bank"
//    flag_rules = arrayOf("-send STRING", "-a ALIAS -amount", "-amount NUMBERS")
//    commands = arrayOf("bank -send abc -amount 500 200 -a 400", "bank -send abc -a hey")

    program = "bank"
    flag_rules = arrayOf("-send STRING", "-a ALIAS -amount", "-amount NUMBERS","-e NULL")
    commands = arrayOf("bank -e -send dsga -amount 3")
    commands = arrayOf("bank -e -send dsga -a 3 -amount 3")
    commands = arrayOf("bank -send dsga -e -amount 3")
    flag_rules = arrayOf("-ss ALIAS -sends", "-send STRING","-sends STRINGS", "-a ALIAS -amount", "-amount NUMBERS","-e NULL")
    commands = arrayOf("bank -ss dsga dsfsd -e -amount 3")
    commands = arrayOf("bank -ss dsga dsfsd -send dfs -sends ds -e -amount 3")

    println(sol.solution(program, flag_rules, commands))
}
