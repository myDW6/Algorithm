package com.shaodw.leetcode;

/**
 * @author shaodw
 * @date 2021/1/24 21:43
 * @description 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。如果不存在符合条件的子数组，返回 0。
 */
public class _209_Minimum_Size_Subarray_Sum {

    //暴力 N^3
    public int minSubArrayLen_violence_bad(int s, int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (sum(nums, i, j) >= s){
                    min = Math.min(min, j - i + 1);
                }
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    private static int sum(int[] arr, int l, int r){
        int sum = 0;
        for (int i = l; i <= r; i++) {
            sum += arr[i];
        }
        return sum;
    }

    //暴力写法优化下 不要辅助函数  N^2
    public int minSubArrayLen_violence_better(int s, int[] nums) {
        int min = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= s){
                    min = Math.min(min, j - i + 1);
                    //此处 一旦找到就可以break
                    break;
                }
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    //滑动窗口  N
    //滑动窗口的精妙之处在于根据当前子序列和大小的情况，不断调节子序列的起始位置
    public int minSubArrayLen_window(int s, int[] nums) {
        int min = Integer.MAX_VALUE;
        int start = 0; //窗口起始位置
        int sum = 0;//sum收集信息
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            //注意这里使用while，每次更新 i（起始位置），并不断比较子序列是否符合条件
            while (sum >= s){
                min = Math.min(min, i - start + 1);
                sum -= nums[start++];    //这里体现出滑动窗口的精髓之处，不断变更i（子序列的起始位置
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    public static void main(String[] args) {
        _209_Minimum_Size_Subarray_Sum minimum_size_subarray_sum = new _209_Minimum_Size_Subarray_Sum();
        int s = 11;
        int[] arr = {1,10,3,9,5};
        System.out.println(minimum_size_subarray_sum.minSubArrayLen_violence_bad(s, arr));
        System.out.println(minimum_size_subarray_sum.minSubArrayLen_violence_better(s, arr));
        System.out.println(minimum_size_subarray_sum.minSubArrayLen_window(s, arr));
    }


}
