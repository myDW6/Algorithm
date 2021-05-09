package com.practice.shaodw.leetcode.arr;

import com.shaodw.anno.Passed;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author shaodw
 * @date 2021/5/9 14:42
 * @description 删除有序数组中的重复项 原地删除 返回删除后数组的长度
 */
public class DelSortedArrDulNum {

    //对数器
    public static int del_comp(int[] arr){
        Integer[] integers = new Integer[arr.length];
        for (int i = 0; i < integers.length; i++) {
            integers[i] = arr[i];
        }
        return Arrays.stream(integers).collect(Collectors.toSet()).size();
    }

    @Passed(complex = "O(n)，其中 n 是数组的长度。快指针和慢指针最多各移动 n 次")
    public static int del_with_double_point(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        int i = 0;
        for (int j = 1; j < arr.length; j++) {
            //i位置 和 j位置 值相等 j直接往后移 不相等的时候 把j位置的值赋给i+1位置
            if (arr[i] != arr[j]){
                arr[++i] = arr[j];
            }
        }
        return i + 1;
    }

    public static void main(String[] args) {
        int[] arr = {0,1,2,2,2,3,4,5,5,6,6,6,7};
        System.out.println(del_comp(arr));
        System.out.println(del_with_double_point(arr));
    }
}
