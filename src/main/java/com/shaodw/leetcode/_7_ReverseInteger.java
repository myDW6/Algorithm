package com.shaodw.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther: shaodw
 * @Date: 2020/7/4 22:37
 * @Description:给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 */
public class _7_ReverseInteger {
    /**
     * 思路一致 但写法不成熟 见reverse2
     * @param x
     * @return
     */
    public static int reverse1(int x) {
        Queue<Integer> queue =  new LinkedList<>();
        while (x != 0){
            queue.add(x % 10);
            x /= 10;
        }
        long res = 0 ;
        while (!queue.isEmpty()){
            res = 10 * res + queue.poll();
            if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE)
                return 0;
        }
        return (int)res;
    }

    public static int reverse2(int x) {
        long n  = 0;
        while (x != 0){
            n = n * 10 + x % 10;
            x /= 10;
        }
        return (int)n == n ? (int)n : 0;
    }

    public static void main(String[] args) {
        int x1 = 123;
        int x2 = -123;
        int x3 = 120;
        int x4 = -1534236469;
        int x6 = -1056389759;
        //int x5 = 9646324351;
        System.out.println(reverse1(x1));
        System.out.println(reverse1(x2));
        System.out.println(reverse1(x3));
        System.out.println(reverse1(x4));
        System.out.println(reverse2(x1));
        System.out.println(reverse2(x2));
        System.out.println(reverse2(x3));
        System.out.println(reverse2(x4));
    }





}
