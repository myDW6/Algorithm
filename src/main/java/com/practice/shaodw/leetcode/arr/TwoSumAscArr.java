package com.practice.shaodw.leetcode.arr;

import com.shaodw.anno.Passed;

import java.util.Arrays;

/**
 * @author shaodw
 * @date 2021/5/9 19:05
 * @description 升序数组求两数之和等于给定值 返回这两个值
 */
public class TwoSumAscArr {

    @Passed(complex = "N*logN")
    //因为是个升序数组 我们先默认一个数就是解 二分去找另一个解
    public static int[] twoSum(int[] arr, int target){
        for (int i = 0; i < arr.length; i++) {
            int anotherIndex = inArr(arr, target - arr[i]);
            if (anotherIndex != -1){
                return new int[]{arr[i], arr[anotherIndex]};
            }
        }
        return new int[0];
    }


    //判断target是否在目标数组中 如是 返回下标 否则返回-1
    public static int inArr(int[] arr, int target){
        int left = 0, right = arr.length - 1;
        while (left <= right){
            int mid = left + ((right - left) >> 1);
            if (arr[mid] == target){
                return mid;
            }else if (arr[mid] < target){
                left = mid + 1;
            }else if (arr[mid] > target){
                right = mid - 1;
            }
        }
        return -1;
    }

    //但其实本题最好使用双指针
    @Passed(complex = "N")
    public static int[] twoSumByDoublePoint(int[] arr, int target){
        int l = 0, r = arr.length - 1;
        while (l < r){
            int s = arr[l] + arr[r];
            if (s < target){
                l++;
            }else if (s > target){
                r--;
            }else return new int[]{arr[l], arr[r]};
        }
        return new int[0];
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6};
        int target = 4;
        System.out.println(Arrays.toString(twoSum(arr, target)));
        System.out.println(Arrays.toString(twoSumByDoublePoint(arr, target)));
    }
}
