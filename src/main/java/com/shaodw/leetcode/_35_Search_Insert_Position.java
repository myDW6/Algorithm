package com.shaodw.leetcode;

/**
 * @author shaodw
 * @date 2021/1/24 19:09
 * @description 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置
 * 数组中无重复元素
 */

public class _35_Search_Insert_Position {

    /**
     * 思路:二分法
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right){
            int mid = left + ((right - left) >> 1);
            if (nums[mid] < target){
                left = mid + 1;
            }else if (nums[mid] > target){
                right = mid - 1;
            }else if (nums[mid] == target){
                return mid;
            }
        }
        //for循环结束后: left = right + 1
        //此时意味着数组中没有查找到: 那么就会有3种情况:
        //1  在整个数组中没有 最左边  那么[left, right] 就是  [0, -1]  我们应该返回0
        //2  在整个数组中没有 最右边  那么[left, right] 就是 [arr.length, arr.length - 1]  应该返回arr.length
        //3  在整个数组中没有 在中间 [1,2,4]  target = 3  当for结束时 [left, right] 就是[2, 1]
        System.out.println("left: " + left);
        System.out.println("right: " + right);
        return right + 1;
    }

    /**
     * 暴力
     */
    public int searchInsert_violence(int[] nums, int target) {
        //数组有序 从前往后找到第一个大于等于目标值的元素 该元素下标就是答案 找不到就是数组长度
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target){
                return i;
            }
        }
        return nums.length;
    }


    public static void main(String[] args) {
        int[] arr = {1,2,4,5};
        int target = 3;
        _35_Search_Insert_Position search_insert_position = new _35_Search_Insert_Position();
        System.out.println("暴力解法: " + search_insert_position.searchInsert_violence(arr, target));
        System.out.println("二分: " + search_insert_position.searchInsert(arr, target));
    }



}
