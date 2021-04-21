package com.practice.shaodw.leetcode.window;


import com.practice.shaodw.anno.Passed;

/**
 * @author shaodw
 * @date 2021/2/4 10:55
 * @description 窗口内平均数最大值
 */
public class FindMaxAverage {
    @Passed(complex = "N", note = "滑动窗口")
    public static double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        int max;
        for (int i = 0; i < k ; i++) {
            sum += nums[i];
        }
        max = sum;
        for (int i = k; i < nums.length; i++) {
            sum += nums[i];
            sum -= nums[i - k];
            max = Math.max(sum, max);
        }
        return (double) max / k;
    }


    public static void main(String[] args) {
        int[] nums = {-1};
        int k = 1;
        System.out.println(findMaxAverage(nums, k));
    }
}
