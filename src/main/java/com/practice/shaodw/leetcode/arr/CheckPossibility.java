package com.practice.shaodw.leetcode.arr;


import com.practice.shaodw.anno.Passed;

/**
 * @author shaodw
 * @date 2021/2/7 17:23
 * @description leet 655
 * 给你一个长度为n的整数数组，请你判断在 最多 改变1 个元素的情况下，该数组能否变成一个非递减数列。
 *
 * 我们是这样定义一个非递减数列的：对于数组中所有的i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。

 */
public class CheckPossibility {

    @Passed(complex = "N", note = "记录调整次数, 从1位置开始遍历  如果当前数比前一个数字小 我们有两个选择, 改变当前数字或者是前一个数字 " +
            "因为我们走到i位置 说明i位置之前的是升序, 我们改变前一个数字将它变大到和i为位置值相等即可 不得以不要动i位置的数 因为后面会以i位数字" +
            "作为基准来比较 但是如果 当前值比前一个数小 也比前一个数的前一个数小  那我们只能改变当前数了 把当前数变成和前一个数一样")
    public static boolean checkPossibility(int[] nums) {
        int changes = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                if (i == 1 || nums[i] >= nums[i - 2]) {
                    nums[i - 1] = nums[i];
                } else {
                    nums[i] = nums[i - 1];
                }
                if (++changes > 1){
                    return false;
                }
            }
        }
        return true;
    }

    @Passed(complex = "N", note = "上面的代码更优雅")
    public static boolean checkPossibility1(int[] nums) {
        int changes = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]){
                //决断改变当前值还是前一个值
                if (i == 1){
                    nums[i - 1] = nums[i];
                }else if (nums[i] < nums[i - 2]){
                    nums[i] = nums[i - 1];
                }else {
                    // nums[i - 2]  <= nums[i] < nums[i - 1]
                    nums[i - 1] = nums[i];
                }
                changes++;
                if (changes > 1){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,3};
        int[] arr1 = {1,2,3,4,3,2};
        System.out.println(checkPossibility(arr));
        System.out.println(checkPossibility(arr1));
    }
}
