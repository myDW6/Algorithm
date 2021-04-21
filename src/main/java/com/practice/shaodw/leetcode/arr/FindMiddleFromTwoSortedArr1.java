package com.practice.shaodw.leetcode.arr;

/**
 * @author shaodw
 * @date 2021/2/3 10:18
 * @description 寻找两个有序数组的中位数
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 你可以假设 nums1 和 nums2 不会同时为空。
 */
public class FindMiddleFromTwoSortedArr1 {


    //还是得2分 一次排除一半
    //l1: 1 2 3 4 5
    //l2: 1 2 3 4 5 6
    //始终保持l1.len < l2.len
    //第一轮  l1中点元素值为 3   l2为3
    //l1(mid) < l2(mid)  所以可以知道l1左边的mid - 1的元素 全部不符合   l2右边的mid - 1个元素全不符合
    //数组变成  l1: 3 4 5   l2: 1 2 3 4
    //第二轮: l1.mid = 4  >  l2.mid = 2
    //知道l1右边的元素 不可能符合  l2 左边的元素不可能符合
    //数组变成  l1: 3 4   l2: 2 3 4
    //l1.mid = 3  ==  l2.mid = 3
    //
    public static double findMedianSortedArrays(int[] nums1, int[] nums2){
        int l = nums1.length + nums2.length + 1 >> 1;
        System.out.println("l= " + l);
        int r = nums1.length + nums2.length + 2 >> 1;
        System.out.println("r= " + r);
        return (find(nums1, nums2, 0, 0, l) + find(nums1, nums2, 0, 0, r)) / 2.0;
    }

    // 1,2,3,4,5
    // 1,2,2,3,4,5
    //( 0 0 6    0 0 6 ) / 2
    //
    private static double find(int[] nums1, int[] nums2, int start1, int start2, int k) {
        if (start1 >= nums1.length) {
            return nums2[start2 + k - 1];
        }
        if (start2 >= nums2.length) {
            return nums1[start1 + k - 1];
        }

        if (k == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }

        int mid1 = start1 + k / 2 - 1 >= nums1.length ? Integer.MAX_VALUE : nums1[start1 + k / 2 - 1];
        System.out.println("mid1: " + mid1);
        int mid2 = start2 + k / 2 - 1 >= nums2.length ? Integer.MAX_VALUE : nums2[start2 + k / 2 - 1];
        System.out.println("mid2: " + mid2);

        return mid1 > mid2 ? find(nums1, nums2, start1, start2 + k / 2, k - k / 2) :
                find(nums1, nums2, start1 + k / 2, start2, k - k / 2);
    }



    public static void main(String[] args) {
        int[] nums1 = {1,2,3,4,5};
        int[] nums2 = {1,2,2,3,4,5}; //1 1 2 2 2 3 3 4 4 5 5    /3
        System.out.println(findMedianSortedArrays(nums1, nums2));
        System.out.println(findMedianSortedArrays(nums2, nums2)); //2.5

    }
}
