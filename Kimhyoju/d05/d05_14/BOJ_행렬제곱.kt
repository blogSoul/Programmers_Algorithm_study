package d05.d05_14

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class BOJ_행렬제곱 {
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val list = br.readLine().split(" ")
        val n = list[0].toInt()
        val b = list[1].toLong()
        val matrix = Array(n) { LongArray(n) }
        for (i in 0 until n) {
            matrix[i] = br.readLine().split(" ").map { it.toLong() }.toLongArray()
        }
//        println("${matrix.map { it.toList() }}")
        val resMat = dfs(matrix, b)
        moduloOperation(resMat)
        for (i in resMat.indices) {
            for (j in resMat.indices) {
                bw.write("${resMat[i][j]} ")
            }
            bw.write("\n")
        }
        br.close()
        bw.close()
    }

    fun dfs(matrix: Array<LongArray>, b: Long): Array<LongArray> {
//        println("in dfs, b: $b")
        if (b == 1L) {
//            println("b == 1, matrix[0][0]: ${matrix[0][0]}")
            return matrix.map { it.clone() }.toTypedArray()
        } else if (b % 2 == 1L) {
            val n = (b - 1)
            val tempMat2 = dfs(matrix, n / 2)
            val mat2 = multiply(tempMat2, tempMat2)
//            println("end of dfs, b: $b")
            return multiply(matrix, mat2)
        }
        val tempMat2 = dfs(matrix, b / 2)
        val mat2 = multiply(tempMat2, tempMat2)
//        println("end of dfs, b: $b")
        return mat2
    }

    fun multiply(m1: Array<LongArray>, m2: Array<LongArray>): Array<LongArray> {
        val newMat = Array(m1.size) { LongArray(m1.size) }
        for (i in m1.indices) {
            for (j in m1.indices) {
                var sum = 0L
                for (k in m1.indices) {
                    sum += m1[i][k] * m2[k][j]
                }
                newMat[i][j] = sum
//                println("newMat[$i][$j]: ${newMat[i][j]}")
            }
        }
        moduloOperation(newMat)
        return newMat
    }

    fun moduloOperation(matrix: Array<LongArray>) {
        for (i in matrix.indices) {
            for (j in matrix.indices) {
                matrix[i][j] %= 1000L
            }
        }
    }
}

fun main() {
    val c = BOJ_행렬제곱()
    c.main()
}
