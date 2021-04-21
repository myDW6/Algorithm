package com.shaodw.practice.binarysearch;

/**
 * @Auther: shaodw
 * @Date: 2020/3/7 14:04
 * @Description: 珂珂喜欢吃香蕉。这里有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 H 小时后回来。
 * 珂珂可以决定她吃香蕉的速度 K （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。
 * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 * 返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。
 */

public class EatBanana {
    public static int getK(int H, int[] piles){

        int L = 1;
        int R = Integer.MAX_VALUE;
        while (L < R){
            int K = L + ((R - L ) >> 1);
            if (eatAll(H, piles, K)){
                R = K;
            }else {
                L = K + 1;
            }
        }
        return L;
    }

    public static boolean eatAll(int H, int[] piles, int K){
        int allEatHours = 0;
        for (int i = 0; i < piles.length; i++) {
            allEatHours += (piles[i] / K) + (piles[i] % K == 0 ? 0 : 1);
        }
        return allEatHours <= H;
    }

    public static void main(String[] args) {
        int[] piles = {30,11,23,4,20};
        int H = 5;
        System.out.println(getK(H, piles));

        H = 6;
        System.out.println(getK(H, piles));

        int[] piles2 = {3,6,7,11};
        H = 8;
        System.out.println(getK(H, piles2));
    }
}


