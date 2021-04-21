package com.shaodw.swordoffer;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: shaodw
 * @Date: 2020-01-16 23:18
 * @Description: 斐波那契数列
 * f(n) = {            0            n = 0
 *                     1            n = 1
 *            f(n - 1) + f(n - 2)   n > 1
 *         }
 */
public class Fibonacci {
    //递归
    public static long fib_recur(int n){
        return n <= 0 ? 0 : n == 1 ? 1 : fib_recur(n - 1) + fib_recur(n - 2);
    }

    //循环
    public static long fib_iter(int n){
        if (n <= 0){
            return 0;
        }else if (n == 1){
            return 1;
        }
        long prepre = 0;
        long pre = 1;
        long fibn = 0;
        for (int i = 2; i <= n ; i++) {
            fibn = prepre + pre;
            prepre = pre;
            pre = fibn;
        }
        return fibn;
    }

    //使用dp数组
    public static long fib_dp(int n){
        if (n <= 1)
            return n;
        long[] dp = new long[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    //矩阵乘法  O(logN)
    // Fib(n) =     1 1    ^  (n - 1)
    //              1 0
    public static long[][] fib_matrix(int n){
        //关联矩阵
        long[][] UNIT = {
                {1,1},
                {1,0}
                        };
        //全0矩阵
        long[][] ZERO = {
                {0,0},
                {0,0}
                        };
        if (n == 0)
            return ZERO;
        if (n == 1)
            return UNIT;
        //n是偶数
        if ((n & 1) == 0){
            long[][] matrix = fib_matrix(n >> 1);
            return matrixMultiply(matrix, matrix);
        }
        long[][] matrix = fib_matrix((n - 1) >> 1);
        return matrixMultiply(matrixMultiply(matrix, matrix), UNIT);
    }

    //矩阵相乘
    public static long[][] matrixMultiply(long[][] m, long[][] n) {
        int rows = m.length;
        int cols = n[0].length;
        long[][] r = new long[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                r[i][j] = 0;
                for (int k = 0; k < m[i].length; k++) {
                    r[i][j] += m[i][k] * n[k][j];
                }
            }
        }
        return r;
    }

    //提供一种函数式风格的写法
    private final Map<Integer, Long> cache;

    public Fibonacci(){
        cache = new HashMap<>();
        cache.put(0, 0L);
        cache.put(1, 1L);
    }
    public long fibonacci(int x){
        return cache.computeIfAbsent(x, n->fibonacci(n - 1) + fibonacci(n - 2));
    }

    public static void main(String[] args) {
        int k = 20;
        long res = 0;
        long start = System.nanoTime();
        for (int i = 0; i <= k; i++) {
            res = fib_iter(i);
        }
        long end = System.nanoTime();
        System.out.println("迭代求Fib(" + k + ") : " + res + " 花费时间：" + (end - start) /1000000000.0 + " s");
        //动态规划
        start = System.nanoTime();
        for (int i = 0; i <= k; i++) {
            res = fib_dp(i);
        }
        end = System.nanoTime();
        System.out.println("动态规划求Fib(" + k + ") : " + res +" 花费时间：" + (end - start) /1000000000.0 + " s");
        start = System.nanoTime();
        for (int i = 0; i <= k; i++) {
            res= fib_matrix(i)[0][1];
        }
        end = System.nanoTime();
        System.out.println("矩阵乘法求Fib(" + k + ") : " + res +" 花费时间：" + (end - start) /1000000000.0 + " s");
        //矩阵乘法 O(logN)

        //递归 复杂度O(2^n)
        start = System.nanoTime();
        for (int i = 0; i <= k; i++) {
            res = fib_recur(i);
        }
        end = System.nanoTime();
        System.out.println("递归求Fib(" + k + ") : " + res +" 花费时间：" + (end - start) /1000000000.0 + " s");
    }
}
