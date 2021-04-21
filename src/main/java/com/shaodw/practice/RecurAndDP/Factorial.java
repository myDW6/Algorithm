package com.shaodw.practice.RecurAndDP;

/**
 * @Auther: shaodw
 * @Date: 2020/2/5 18:42
 * @Description: 求n!的值
 */
public class Factorial {
    public static long getFactorial1(int N){
        return N == 1 ? N : N * getFactorial1(N - 1);
    }

    public static long getFactorial2(int N){
        long res = 1L;
        for (int i = 1; i <= N; i++) {
            res *= i;
        }
        return res;
    }

    public static void main(String[] args) {
        int N = 5;
        System.out.println(getFactorial1(N));
        System.out.println(getFactorial2(N));
    }
}
