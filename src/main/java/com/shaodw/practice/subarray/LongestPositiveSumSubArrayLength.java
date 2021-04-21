package com.shaodw.practice.subarray;

/**
 * @Auther: shaodw
 * @Date: 2020/2/22 12:36
 * @Description: 问题同未排序数组中所有子数组和等于aim的问题 不同在所有数都是正数
 * 双指针
 */
public class LongestPositiveSumSubArrayLength {
    public static int maxLength(int[] arr, int k){
        if (arr == null || arr.length == 0 || k <= 0){
            return 0;
        }
        int len = 0;
        int sum = arr[0];
        int left = 0;
        int right = 0;
        while (right < arr.length){
            if (sum == k){
                len = Math.max(len, right - left + 1);
                sum -= arr[left++];
            }else if (sum < k){
                right++;
                if (right == arr.length){
                    break;
                }
                sum += arr[right];
            }else {
                sum -= arr[left++];
            }
        }
        return len;
    }

    public static void main(String[] args) {
        int[] arr = {7,3,2,1,1,7,7,7};
        System.out.println(maxLength(arr, 7));
    }
}
