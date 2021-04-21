package com.shaodw.leetcode;

import java.util.HashMap;
import java.util.Map;
/**
 * LeetCode1：TwoSum
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 */
public class _1_TwoSum {

    /**
     * 暴力
     */
    public static int[] twoSum(int[] arr, int target){
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == target){
                    return new int[] {i,j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solotion");
    }

    /**
     * 两遍哈希表
     */
    public static int[] twoSumTwoMap(int[] nums, int target){
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])){
                return new int[] {i, map.get(target - nums[i])};
            }
        }
        throw new IllegalArgumentException("No two sum solotion");
    }

    /**
     * 一遍哈希表
     */
    public static int[] twoSumMap(int[] nums, int target){
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])){
                return new int[]{map.get(nums[i]), i};
            }else {
                map.put(target - nums[i], i);
            }
        }
        throw new IllegalArgumentException("No two sum solotion");
    }

    public static void main(String[] args) {
        int[] nums = {2,7,11,15};
        int target = 9;
        int[] res = twoSum(nums, target);
        System.out.println("[" + res[0] + " , " + res[1] + "]");
        res = twoSumTwoMap(nums, target);
        System.out.println("[" + res[0] + " , " + res[1] + "]");
        res = twoSumMap(nums, target);
        System.out.println("[" + res[0] + " , " + res[1] + "]");
    }
}