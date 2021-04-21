package com.shaodw.practice;

/**
 * @Auther: shaodw
 * @Date: 2020/3/16 19:31
 * @Description:
 * 1:有一头母牛，它每年年初生一头小母牛。每头小母牛从第四个年头开始，每年年初也生一头小母牛。
 *  请编程实现在第n年的时候，共有多少头母牛？
 * 2:已知一对兔子每一个月可以生一对小兔子，而一对兔子出生后.第三个月开始生小兔子假如一年内没有发生死亡，则一对兔子开始，第N个月后会有多少对？
 */
public class Test {

    static int test(int n){
        int count = 0;
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            if (n % i == 0){
                count++;
                sum += i;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(test(256));
    }
}
