package com.practice.shaodw.leetcode.arr;

import com.shaodw.anno.Passed;

import java.util.Arrays;

/**
 * @author shaodw
 * @date 2021/5/9 18:11
 * @description  三个数最大乘积 整数数组中 找出三个数组成的最大乘积 输出乘积 不考虑溢出
 */
public class ThreeNumMultiply {

    //violence 如果全是正数 获取三个最大的数  如果全是负数 一样找三个最大数的乘积
    //有正有负需要找到最小的两个负数和最大的正数 然后相乘  或者 三个最大正数的乘积
    @Passed(complex = "N*LogN主要开销在排序")
    public static int violence(int[] arr){
        Arrays.sort(arr);
        int len = arr.length;
        return Math.max(arr[len - 1] * arr[len - 2] * arr[len - 3], arr[0] * arr[1] * arr[len - 1]);
    }

    //线性扫描
    //思路在于如何一遍就找到上面的这五个值
    @Passed(complex = "N一次扫描代价",note = "注意中间千万不要用else if")
    public static int threeNumMultiply(int[] arr){
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        for (int i : arr) {
            if (i < min1){
                min2 = min1;
                min1 = i;
            }else if (i < min2){
                min2 = i;
            }
            //这里千万不要用else if
            if (i > max1){
                max3 = max2;
                max2 = max1;
                max1 = i;
            }else if (i > max2){
                max3 = max2;
                max2 = i;
            }else if (i > max3){
                max3 = i;
            }
        }
        return Math.max(
                min1 * min2 * max1,
                max1 * max2 * max3
        );
    }




    public static void main(String[] args) {
        int[] arr = { 1,2,3,4};
        System.out.println(violence(arr));
        System.out.println(threeNumMultiply(arr));
    }
}
