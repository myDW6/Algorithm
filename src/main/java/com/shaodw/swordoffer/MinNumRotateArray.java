package com.shaodw.swordoffer;

/**
 * @Auther: shaodw
 * @Date: 2020/1/27 14:08
 * @Description: 把一个数组开始的若干个元素 移到数组尾部 称为数组的旋转 输入一个递增数组的旋转 求旋转后数组的最小值
 */
public class MinNumRotateArray {
    public static int minRotateArray(int[] arr){
        if (arr == null || arr.length < 1)
            return 0;
        int left = 0;
        int right = arr.length - 1;
        while (left < right){
            int mid = left + ((right - left) >> 1);
            if (arr[mid] > arr[right]){
                left = mid + 1;////这种情况下，最小值一定在mid的右边
            }else if (arr[mid] == arr[right]){
                right = right - 1;//发现有相等元素，只能蛮力查找
            }else right = mid;//这种情况下最小值一定在mid位置或者其左边
        }
        return arr[left];
    }


    public static void main(String[] args) {
        int[] arr = {1,0,1,1,1};
        System.out.println(minRotateArray(arr));
        int[] arr2 = {1,1,1,0,1};
        System.out.println(minRotateArray(arr2));
    }
}
