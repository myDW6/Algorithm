package com.practice.shaodw.leetcode.doublepoint;



import com.practice.shaodw.anno.Error;
import com.practice.shaodw.anno.Passed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author shaodw
 * @date 2021/2/4 10:15
 * @description 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 *a，b，c ，*使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * 不可以包含重复的三元组。
 */
public class ThreeSum {
    //艹  思路错了  不是这样
    @Error(becauseOf = "暴力法都没写过 思路完全不对 难点在于如何去除重复解。")
    //本身是个组合问题 暴力思路  先用回溯  复习下回溯 找出所有的 然后找出需要的
    public static List<List<Integer>> threeSumVolince(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        List<Integer> track = new LinkedList<>();
        backTracking(result, track,  nums, 3, 0);
        return result;
    }

    static void backTracking(List<List<Integer>> result,
                             List<Integer> track,
                             int[] arr,
                             int k,
                             int index){
        if (k == track.size()){
            if (track.get(0) + track.get(1) + track.get(2) == 0){
                result.add(new LinkedList<>(track));
                return;
            }
        }
        for (int i = index; i < arr.length; i++)
        {
            track.add(arr[i]);
            backTracking(result, track, arr, k, i + 1);
            track.remove(track.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] arr = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> list = threeSumBySortAndDoublePointer(arr);
        System.out.println(list.toString());
    }

    /**
     * 算法流程：
     * 1特判，对于数组长度 nn，如果数组为 null 或者数组长度小于 3，返回 []。
     * 2对数组进行排序。
     * 3遍历排序后数组：
     *  若 nums[i]>0nums[i]>0：因为已经排序好，所以后面不可能有三个数加和等于 00，直接返回结果。
     *  对于重复元素：跳过，避免出现重复解
     *  令左指针 L=i+1，右指针 R=n-1，当 L<R 时，执行循环：
     *  当 nums[i]+nums[L]+nums[R]==0，
     *      执行循环，判断左界和右界是否和下一位置重复，去除重复解。并同时将 L,R 移到下一位置，寻找新的解
     *  若和大于 0，说明 nums[R] 太大，R 左移
     *  若和小于 0，说明 nums[L] 太小，L 右移
     * @param nums
     * @return
     */

    @Passed(note = "排序 + 双指针", complex = "N*N")
    public static List<List<Integer>> threeSumBySortAndDoublePointer(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length && nums[i] <= 0; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                //去重
                continue;
            }
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int sum = nums[l] + nums[r] + nums[i];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    //去重
                    while (l < r && nums[l] == nums[++l]) {
                    }
                    //去重
                    while (l < r && nums[r] == nums[--r]) {
                    }
                } else if (sum < 0) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return res;
    }
}
