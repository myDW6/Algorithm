package com.practice.shaodw.leetcode.doublepoint;


import com.practice.shaodw.anno.Passed;

/**
 * @author shaodw
 * @date 2021/2/4 09:31
 * @description 盛最多水的容器
 */
public class MaxArea {


    @Passed(complex = "N", note = "双指针 最左和最右分别向中间收缩, 谁小收缩谁")
    public static int maxArea(int[] height) {
        int l = 0;
        int r = height.length - 1;
        int max = 0;
        while (l < r){
            int lower = height[l] <= height[r] ? l : r;
            max = Math.max((r - l) * height[lower], max);
            if (lower == l){
                l++;
            }else {
                r--;
            }
        }
        return max;
    }



    public static int maxArea_volice(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length ; j++) {
                int w = j - i ;
                int h = Math.min(height[i], height[j]);
                max = Math.max(w * h, max);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr1 = {1,8,6,2,5,4,8,3,7};
        int[] arr2 = {1,1};
        int[] arr3 = {4,3,2,1,4};
        int[] arr4 = {1,2,1};
        System.out.println(maxArea_volice(arr1)); //49
        System.out.println(maxArea_volice(arr2)); //1
        System.out.println(maxArea_volice(arr3)); //16
        System.out.println(maxArea_volice(arr4)); //2

        System.out.println(maxArea(arr1));
        System.out.println(maxArea(arr2));
        System.out.println(maxArea(arr3));
        System.out.println(maxArea(arr4));
    }
}
