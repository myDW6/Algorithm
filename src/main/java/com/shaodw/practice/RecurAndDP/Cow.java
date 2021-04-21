package com.shaodw.practice.RecurAndDP;

/**
 * @Auther: shaodw
 * @Date: 2020/2/7 13:09
 * @Description: 有一头母牛，它每年年初生一头小母牛。每头小母牛从第四个年头开始，每年年初也生一头小母牛。
 *  * 请编程实现在第n年的时候，共有多少头母牛？
 *
 * fn = f(n - 1) + f(n - 3)
 */
public class Cow {
    public static int cowNum1(int n){
        return n < 0 ? 0 : n == 1 || n == 2 || n == 3 ? n : cowNum1(n - 1) + cowNum1(n - 3);
    }

    public static void main(String[] args) {
        int k = 20;
        for (int i = 1; i <= k; i++) {
            System.out.println("第" + i + "年有" + cowNum1(i) + " 头奶牛");
        }
        for (int i = 1; i <= k; i++) {
            System.out.println("第" + i + "年有" + cowNum2(i) + " 头奶牛");
        }
        for (int i = 1; i <= k; i++) {
            System.out.println("第" + i + "年有" + cowNum3(i) + " 头奶牛");
        }
    }

    public static int cowNum2(int n){
        if (n < 0)
            return 0;
        if (n == 1 || n == 2 || n == 3)
            return n;
        int[] cowArr = new int[n];
        cowArr[0] = 1;
        cowArr[1] = 2;
        cowArr[2] = 3;
        for (int i = 3; i < n; i++) {
            cowArr[i] = cowArr[i - 1] + cowArr[i - 3];
        }
        return cowArr[n - 1];
    }

    public static int cowNum3(int n){
        if (n < 0)
            return 0;
        if (n == 1 || n == 2 || n == 3)
            return n;
        int res = 3;
        int pre = 2;
        int prepre = 1;
        int tmp1 = 0;
        int tmp2 = 0;
        for (int i = 4; i <= n; i++) {
           tmp1 = res;
           tmp2 = pre;
           res += prepre;//res = res + prepre 前一年为res 前三年为prepre
           prepre = tmp2;
           pre = tmp1;
        }
        return res;
    }
}
