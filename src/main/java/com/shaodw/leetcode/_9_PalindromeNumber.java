package com.shaodw.leetcode;

import java.util.HashMap;
import java.util.Stack;

/**
 * @Auther: shaodw
 * @Date: 2020/7/5 18:58
 * @Description: 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * 你能不将整数转为字符串来解决这个问题吗？
 */
public class _9_PalindromeNumber {
    /**
     * 利用字符串 可作为对数器检验
     * @param x
     * @return
     */
    public static boolean isPalindromeUseString(int x) {
        char[] chs = String.valueOf(x).toCharArray();
        for (int i = 0, j = chs.length - 1; i < j; i++, j--){
            if (chs[i] != chs[j]){
                return false;
            }
        }
        return true;
    }

    /**
     * 使用leetcode第七题的思路 反转数字然后比较
     * @param x
     * @return
     */
    public static boolean isPalindromeReverseNumber(int x){
        //return x < 0 ? false : x == 0 ? true : process(x)
        return x >= 0 && (x == 0 || process(x));
    }

    //leetcode 7 解法
    private static boolean process(int x){
        long compare = x;
        long n = 0;
        while (x > 0){
            n = 10 * n + x % 10;
            x /= 10;
        }
        return n == compare;
    }
    //为了不提升精度 这里采取反转一半数字的方法
    public static boolean isPalidrome(int x){
        if (x < 0 || (x % 10) == 0 && x != 0){
            return false;
        }
        int revertedNum = 0;
        //迭代过程x会越来越小 后部分反转的revertedNum会越来越大
        while (x > revertedNum){
            revertedNum = revertedNum * 10 + x % 10;
            x /= 10;
        }
        return x == revertedNum || x == revertedNum / 10;
    }

    public static boolean isPalindrome1(int x){
        if (x < 0)
            return false;
        int div = 1;
        while (x / div >= 10){
            div *= 10;
        }
        while (x != 0){
            int l = x / div;
            int r = x % 10;
            if (l != r)
                return false;
            x = (x % div) / 10;
            div /= 100;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindromeUseString(121));
        System.out.println(isPalindromeUseString(-121));
        System.out.println(isPalindromeUseString(10));
        System.out.println(isPalindromeReverseNumber(121));
        System.out.println(isPalindromeReverseNumber(-121));
        System.out.println(isPalindromeReverseNumber(10));
        System.out.println(isPalindrome1(121));
        System.out.println(isPalindrome1(-121));
        System.out.println(isPalindrome1(10));
        System.out.println(isPalidrome(121));
        System.out.println(isPalidrome(-121));
        System.out.println(isPalidrome(10));

    }
}
