package com.practice.shaodw.leetcode.math;

/**
 * @author shaodw
 * @date 2021/5/9 19:48 剑指 Offer 10- I. 斐波那契数列
 * @description F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 */
public class Fib {
    public static int fib(int n) {
        if (n == 0 || n == 1) return n;
        int pre = 1;
        int prepre = 0;
        int res = 0;
        for (int i = 2; i <= n ; i++) {
            res = (pre + prepre) % 1000000007;
            prepre = pre;
            pre = res;
        }
        return res;
    }

    public static int fib2(int n){
        int a = 0, b = 1, sum;
        for(int i = 0; i < n; i++){
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println(fib(49));
      //  System.out.println(fib1(45));
        System.out.println(fib2(49));
    }

    public static int fib1(int n){
        return (n == 0 || n == 1 ? n : fib1(n - 1) + fib1(n - 2)) % 1000000007;
    }
}
