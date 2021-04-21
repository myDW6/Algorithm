package com.shaodw.swordoffer;

/**
 * @Auther: shaodw
 * @Date: 2019-12-09 19:47
 * @Description:跳格子 变态跳
 * （一只青蛙）一次可以跳上 1 级台阶，也可以跳上2 级。求该青蛙跳上一个n 级的台阶总共有多少种跳法
 * （一只青蛙）一次可以跳上1级台阶，也可以跳上2 级……它也可以跳上n 级，此时该青蛙跳上一个n级的台阶总共有多少种跳法？
 */
public class JumpFloor {
    //一次一格或两格 Fib(n) = Fib(n - 1) + Fib(n - 2) 斐波那契
    public static int jumpTimes1(int n){
        if (n < 0) return -1;
        else if (n == 0 || n == 1) return 1;
        return jumpTimes1(n - 1) + jumpTimes1(n - 2);
    }

    public static int jumpTimes1_DP(int n){
        if (n < 0)
            return -1;
        else if (n <= 1)
            return 1;
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }


    //一次任意格 Fib(n) = Fib(n - 1) + Fib(n - 2) + Fib(n - 3) + ... + Fib(1) + Fib(0)    Fib(0) = Fib(1) = 1;
    //          Fib(n - 1) = Fib(n - 12) + Fib(n - 3) + ... + Fib(1) + Fib(0)
    //          Fib(n) = 2Fib(n - 1);   Fib(n) = 2 ^ (n - 1)
    public static int jumpTime2(int n){
        return n < 0 ? -1 : n == 0 || n == 1 ? 1 : 1<<(n-1);    //2^(n-1)
    }
    public static void main(String[] args) {
        System.out.println(jumpTimes1(0));
        System.out.println(jumpTimes1_DP(0));
        System.out.println(jumpTimes1(1));
        System.out.println(jumpTimes1_DP(1));
        System.out.println(jumpTimes1(30));
        System.out.println(jumpTimes1_DP(30));
        System.out.println(jumpTime2(20));
        System.out.println(jumpTime2(0));
        System.out.println(jumpTime2(1));
    }
}
