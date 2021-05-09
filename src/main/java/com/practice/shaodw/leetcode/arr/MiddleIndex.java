package com.practice.shaodw.leetcode.arr;

import com.shaodw.anno.Passed;

import java.util.Arrays;

/**
 * @author shaodw
 * @date 2021/5/9 15:02
 * @description 数组的中心下标 中心下标左边所有元素相加的和等于右边所有元素相加的和 不存在返回-1
 * 有多个返回最左边的一个(代表了应该从左往右遍历)  中心下标可能出现在数组两端
 */
public class MiddleIndex {

    @Passed(complex = "N", note = "其实也不够快")
    public static int middleIndex(int[] arr){
        int sum = Arrays.stream(arr).sum();
        int total = 0;
        for (int i = 0; i < arr.length; i++) {
            if (total + arr[i] == sum){
                return i;
            }
            total += arr[i]; //i左边的 + 当前的
            sum -= arr[i];
        }
        return -1;
    }

    @Passed(complex = "O(n)，其中 n 为数组的长度")
    public static int middleIndex1(int[] arr){
        int sum = Arrays.stream(arr).sum();
        int total = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((total << 1) + arr[i] == sum){
                return i;
            }
            total += arr[i];
        }
        return -1;
    }



    //暴力法判断
    @Passed(note = "慢得一匹", complex = "N*N")
    public static int violence(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            if (is(arr, i)){
                return i;
            }
        }
        return -1;
    }

    private static boolean is(int[] arr, int i){
        int sumLeft = 0;
        int sumRight = 0;
        for (int j = 0; j < i; j++) {
            sumLeft += arr[j];
        }
        for (int j = i+1; j < arr.length; j++) {
            sumRight += arr[j];
        }
        return sumLeft == sumRight;
    }


    public static void main(String[] args) {
        int[] arr = {1, 7, 3, 6, 5, 6};//3
        int[] arr1 = {1,2,3,4,6,4,3,2,1};//4
        int[] arr2 = {1,7,3,6,5,6};//3
        System.out.println(violence(arr));
        System.out.println(middleIndex(arr));
        System.out.println(middleIndex1(arr));
        System.out.println(violence(arr1));
        System.out.println(middleIndex(arr1));
        System.out.println(middleIndex1(arr1));
        System.out.println(violence(arr2));
        System.out.println(middleIndex(arr2));
        System.out.println(middleIndex1(arr2));
    }
}
