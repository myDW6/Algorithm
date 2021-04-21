package com.shaodw.practice.subarray;

/**
 * @Auther: shaodw
 * @Date: 2020/2/28 20:11
 * @Description: 给定一个数组arr，值可正，可负，可0；一个整数k，求累加 和小于等于aim的，最长子数组，要求时间复杂度O(N)
 */

/**
 * 这个问题要比之前两个子数组的题难很多 直接看最优解
 */
public class LongestSumSubArray {

    public static int maxLength(int[] arr, int k){
        if (arr == null || arr.length == 0){
            return 0;
        }
        int[] minSums = new int[arr.length];//记录从当前位置到最右边的位置能形成的最小累加和
        int[] minSums_index = new int[arr.length];//记录能形成最小累加和的右边界

        //从后往前决策 先填最右边的值
        minSums[arr.length - 1] = arr[arr.length - 1];
        minSums_index[arr.length - 1] = arr.length - 1;

        for (int i = arr.length - 2; i >= 0; i--){
            if (minSums[i + 1] < 0){
                minSums[i] = arr[i] + minSums[i + 1];
                minSums_index[i] = minSums[i + 1];
            }else {
                minSums[i] = arr[i];
                minSums_index[i] = i;
            }
        }

        int right = 0;
        int sum = 0; //start到end累加和是多少
        int res = 0;
        for (int start = 0; start < arr.length; start++) {
            while (right < arr.length && sum + minSums[right] <= k){
                sum += minSums[right];
                right = minSums_index[right] + 1;
            }
            sum -= right > start ? arr[start] : 0;
            res = Math.max(res, right - start); //从start扩到r-1位置一共的数
            right = Math.max(res,  start + 1);//扩不动咋办
        }
        return res;
    }
}
