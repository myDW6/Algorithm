package com.practice.shaodw.leetcode.math;


/**
 * @author shaodw
 * @date 2021/5/9 13:47
 * @description 统计n以内的素数个数
 */
public class PrimeNumber {

    //violence
    public static int violence(int N){
        int count = 0;
        for (int i = 2; i < N; i++) {
            if (isPrime(i)) {
                count++;
            }
        }
        return count;
    }

    private static boolean isPrime(int x){
        for (int i = 2; i * i <= x; i++) {
            //i只需要到 根号x  注意这里必须取等
            //比如12 =>  1*12 2*6 3*4 4*3 6*2 12*1 可见后面直接反过来了 所以只需判断一半 这个一半就是 根号12
            if (x % i == 0){
                return false;
            }
        }
        return true;
    }

    //埃筛法 2是素数 那么2*2 2*3 2*4 2*...都是合数 标记
    //减少判断次数
    public static int eratosthenes(int n){
        boolean[] notPrime = new boolean[n];  //false为素数
        int count  = 0;
        for (int i = 2; i < n; i++) {
            if (!notPrime[i]){
                count++;
                for (int j = 2 * i; j < n; j+=i) {
                    notPrime[j] = true;
                }
            }
        }
        return count;
    }

    //埃筛法还存在重复计算  2*3 2*4   3*2 3*4   4*2 4*3
    public static int eratosthenes_better(int n){
        boolean[] notPrime = new boolean[n];
        int count  = 0;
        for (int i = 2; i < n; i++) {
            if (!notPrime[i]){
                count++;
                for (int j = i * i; j < n; j+=i) {
                    //这里需要放置j过大越界 且该算法此处数字过大时误差相当大 因为我们完全根据标记数组去取值

                    notPrime[j] = true;
                }
            }
        }
        return count;
    }



    public static void main(String[] args) {
        int N = 10000;
        long l1 = System.currentTimeMillis();
        System.out.println(eratosthenes_better(N));
        System.out.println("埃筛法优化后耗时: " + (System.currentTimeMillis() - l1));
        l1 = System.currentTimeMillis();
        System.out.println(eratosthenes(N));
        System.out.println("埃筛法耗时: " + (System.currentTimeMillis() - l1));
        l1 = System.currentTimeMillis();
        System.out.println(violence(N));
        System.out.println("暴力法耗时: " + (System.currentTimeMillis() - l1));
    }
}
