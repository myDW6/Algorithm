package com.shaodw.leetcode;

/**
 * @Auther: shaodw
 * @Date: 2019-12-31 16:57
 * 两个排序数组的中位数 要求时间复杂度为O(log(m + n))
 * @Description :第一种思路利用归并排序的merge过程排序所有数， 按照索引直接取值 复杂度O(m+n) 额外空间复杂度为辅助数组O（m+n）
 * 第二种思路利用在数组中遍历直到中位数的位置
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-2/
 */
public class _4_FindMedianSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null){
            return getMiddle(nums2);
        }
        if (nums2 == null){
            return getMiddle(nums1);
        }

        int p = 0, q = 0, i = 0;
        int[] tmp = new int[nums1.length + nums2.length];
        while (p < nums1.length && q < nums2.length){
            tmp[i++] = nums1[p] < nums2[q] ? nums1[p++] : nums2[q++];
        }

        while (p < nums1.length){
            tmp[i++] = nums1[p++];
        }

        while (q < nums2.length){
            tmp[i++] = nums2[q++];
        }
        return getMiddle(tmp);
    }

    private double getMiddle(int[] arr){
        int mid = (arr.length - 1) >> 1;
        return (arr.length & 1) == 0 ? (arr[mid] + arr[mid + 1]) / 2.0 : arr[mid];
    }

    public static void main(String[] args) {
        int[] arr1 = {1,2, 3,4,5,7,8,9,10};
        int[] arr2 = {2,3,4,5,6,7,8};
        System.out.println(new _4_FindMedianSortedArrays().findMedianSortedArrays(arr1, arr2));

    }
}

/**
 * 写个循环判断是否到了中位数位置 到了即返回结果  时间复杂度O(m + n)
 */
class Solution2{
    public double findMedianSortedArrays(int[] nums1, int[] nums2){
        int m = nums1.length;
        int n = nums2.length;
        int len = m + n;
        int left = -1;
        int right = -1;
        int astart = 0;//nums1数组当前遍历元素位置
        int bstart = 0;//nums2数组当前遍历元素位置

        for (int i = 0; i <= len / 2 ; i++) {
            left = right;
            //astart往前走的时机是atart未越界且值比bstart值小， 若bstart越界 也该往前走
            if (astart < m && (bstart >= n || nums1[astart] < nums2[bstart])){
                right = nums1[astart++];
            }else {
                right = nums2[bstart++];
            }
        }
        return (len & 1) == 0 ? (left + right) / 2.0 : right;
        //奇数的二进制表示法的最低位是1，偶数的最低位是0。那么用这个数去和1按位与，
        // 如果是奇数，那么结果就是1；偶数结果则是0。
    }

    public static void main(String[] args) {
        int[] arr1 = {1,2, 3,4,5,7,8,9,10};
        int[] arr2 = {2,3,4,5,6,7,8};

        System.out.println(new Solution2().findMedianSortedArrays(arr1, arr2));
    }

}

/**
 * 使用topK思路
 * 每进行一次循环 我们减少了k/2个元素，所以时间复杂度为O(log(k))  k = (m + n)/2 最终复杂度是O(log(m + n))
 * 空间复杂度上，使用了尾递归，为O(1)
 */
class Solution3{
    public double findMedianSortedArrays(int[] nums1, int[] nums2){
        int n = nums1.length;
        int m = nums2.length;
        int left = (n + m + 1) >> 1;
        int right = (n + m + 2) >> 1;
        //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
        return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left)
                + getKth(nums1, 0 ,n - 1, nums2, 0 , m - 1, right))
                * 0.5;
    }

    //两个有序数组的中位数可以转换为求第k小的数字问题
    //寻找两个有序数组nums1 nums2从start1位置到end1位置 start2位置到end2位置上第k小的数字
    private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k){
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        if (len1 > len2){
            return getKth(nums2, start2, end2, nums1, start1 ,end1, k);
        }
        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
        if (len1 == 0){
            return nums2[start2 + k - 1];
        }
        if (k == 1){
            return Math.min(nums1[start1], nums2[start2]);
        }

        int i = start1 + Math.min(len1, k >> 1) - 1;
        int j = start2 + Math.min(len2, k >> 1) - 1;

        return nums1[i] > nums2[j]
                ? getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1))
                : getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
    }

    public static void main(String[] args) {
        int[] arr1 = {1,2, 3,4,5,7,8,9,10};
        int[] arr2 = {2,3,4,5,6,7,8};

        System.out.println(new Solution3().findMedianSortedArrays(arr1, arr2));
    }
}
