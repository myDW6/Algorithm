package com.practice.shaodw.leetcode.arr;


import com.practice.shaodw.anno.Passed;

/**
 * @author shaodw
 * @date 2021/2/3 10:18
 * @description 寻找两个有序数组的中位数
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 你可以假设 nums1 和 nums2 不会同时为空。
 */
public class FindMiddleFromTwoSortedArr {

    @Passed(note = "虽然通过,但是很明显复杂度不满足要求",complex = "N + M", better = FindMiddleFromTwoSortedArr1.class)
    public static double findMedianSortedArrays(int[] nums1, int[] nums2){
        //先不考虑二分 和要求的复杂度
        //如果是一个数组 且有序
        // 如果是奇数 直接根据索引 num.size >> 1取值即可
        // 如果是偶数 直接根据索引 num.size >> 1 nums.size>>1  + 1的
        //问题转化为如何将两个有序数组 变为一个有序数组  很明显是归并排序的merge过程
        //需要注意int的取值范围  精度提升
        int[] merge = merge(nums1, nums2);
        int mid = merge.length >> 1;
        return (merge.length & 1) == 0 ? ((double) merge[mid - 1] + (double) merge[mid]) / 2:
                merge[mid];
    }

    private static int[] merge(int[] arr1, int[] arr2){
        int[] merge = new int[arr1.length + arr2.length];
        int p1 = 0, p2 = 0, i = 0;
        while (p1 < arr1.length && p2 < arr2.length){
            merge[i++] =arr1[p1] < arr2[p2] ? arr1[p1++] : arr2[p2++];
        }
        while (p1 < arr1.length){
            merge[i++] = arr1[p1++];
        }
        while (p2 < arr2.length){
            merge[i++] = arr2[p2++];
        }
        return merge;
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2,3,4,5};
        int[] nums2 = {1,2,2,3,4,5}; //1 1 2 2 2 3 3 4 4 5 5    /3
        System.out.println(findMedianSortedArrays(nums1, nums2));
        System.out.println(findMedianSortedArrays(nums2, nums2)); //2.5

    }
}
