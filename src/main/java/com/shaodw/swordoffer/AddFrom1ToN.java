package com.shaodw.swordoffer;

/**
 * @Auther: shaodw
 * @Date: 2020-01-16 23:12
 * @Description: 递归和循环 演示1 + 2 + 3 + 。。。+ n
 */
public class AddFrom1ToN {
    public static int addFrom1ToN_Recur(int N){
        return N <= 0 ? 0 : N + addFrom1ToN_Recur(N - 1);
    }

    public static int addFrom1ToN_Iter(int N){
        int res = 0;
        for (int i = 1; i <= N; i++) {
            res += i;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(addFrom1ToN_Iter(20000));
        //stack over flow
        System.out.println(addFrom1ToN_Recur(20000));
    }
}
