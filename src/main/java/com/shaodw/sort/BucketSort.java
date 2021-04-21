package com.shaodw.sort;

/**
 * @Auther: shaodw
 * @Date: 2020/1/28 20:49
 * @Description:
 */
public class BucketSort {
    public static void bucketSort(int[] arr){
        if (arr == null || arr.length < 2)
            return;
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }

        int[] buckets = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            buckets[arr[i]]++;
        }

        int index = 0;
        for (int i = 0; i < buckets.length; i++) {
            while (buckets[i]-- > 0){
                arr[index++] = i;
            }
        }

        //O(N)
    }
}
