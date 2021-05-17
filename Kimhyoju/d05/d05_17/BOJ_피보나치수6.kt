package d05.d05_17

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// 피보나치를 분할정복으로...
// 10 ^ 18 은 너무 큰데... 이걸 어떻게 1초안에 구하지? 구글링함.

// 행렬로 푼다....
// https://jow1025.tistory.com/101
// 피보나치수를 푸는데 최적화 기법은, 1. DP 2. 행렬의 분할정복

class BOJ_피보나치수6 {
    val MOD_MAX = 1000000007L
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.`out`))
        val n = br.readLine().toLong()
        val normalArray = arrayOf(longArrayOf(1, 1), longArrayOf(1, 0))
        val res = matrixFibonacci(normalArray, n)
        bw.write("${res[0][1]}")
        br.close()
        bw.close()
    }

    fun naiveFibonacci(n: Long): Long {
        if (n == 0L) return 0
        if (n == 1L) return 1
        return naiveFibonacci(n - 1) + naiveFibonacci(n - 2)
    }

    tailrec fun tailFibonacci(a: Long, b: Long, n: Long): Long {
        if (n == 0L) return 0L
        else if (n == 1L) return 1L
        return if (n == 2L) (a + b) else tailFibonacci(b, a + b, n - 1)
    }

    fun matrixFibonacci(matrix: Array<LongArray>, n: Long): Array<LongArray> {
        if (n == 1L) return matrix
        if (n % 2 == 1L) {
            return multiplyMat(matrixFibonacci(matrix, n - 1), matrix)
        }
        val mat = matrixFibonacci(matrix, n / 2)
        return multiplyMat(mat, mat)
    }

    fun multiplyMat(mat1: Array<LongArray>, mat2: Array<LongArray>): Array<LongArray> {
        val res = Array(2) { LongArray(2) }
        for (i in 0 until 2) {
            for (j in 0 until 2) {
                for (k in 0 until 2) {
                    res[i][j] += mat1[i][k] * mat2[k][j]
                }
                res[i][j] %= MOD_MAX
            }
        }
        return res
    }
}

fun main() {
    val c = BOJ_피보나치수6()
    c.main()
}
