package com.shaodw.swordoffer;

/**
 * @Auther: shaodw
 * @Date: 1/21/2020 22:45
 * @Description: 一组无序数 求相邻两数最大差值
 * 核心思路：利用桶排序的桶概念
 * 
 * n个数 范围从a -> b 那么我们使用n + 1个桶来划分这个范围 第一个和最后一个桶必有数 根据鸽笼原理 中间必有空桶 空桶右边第一个非空桶与空桶左边
 * 非空桶的差值一定大于桶内的差值 （桶的范围） 那么假若排序后相邻的两数之差一定最大在非空桶与非空桶之间或是 非空桶右边第一个非空桶与左边非空桶之间
 * 每个桶记录最大值和最小值 用后一个桶的最小值减去前一个桶的最大值
 */
public class MaxGap {
    public static int MaxGap(int[] arr){
        if (arr == null || arr.length < 2)
            return 0;
        int len = arr.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            max = Math.max(arr[i], max);
            min = Math.min(arr[i], min);
        }
        if (min == max)
            return 0;
        boolean[] hasNum = new boolean[len + 1];
        int[] maxs = new int[len + 1];
        int[] mins = new int[len + 1];
        int bid ;
        for (int i = 0; i < len; i++) {
            bid = bucket(arr[i], len, min, max);
            maxs[bid] = hasNum[bid] ? Math.max(arr[i], maxs[bid]) : arr[i];
            mins[bid] = hasNum[bid] ? Math.min(arr[i], mins[bid]) : arr[i];
            hasNum[bid] = true;
        }

        int res = 0;
        int lastMax = maxs[0];
        //遍历每一个桶
        for (int i = 1; i <= len ; i++) {
            if (hasNum[i]){
                res = Math.max(res, mins[i] - lastMax);
                lastMax = maxs[i];
            }
        }
        return res;
    }
    
    private static int bucket(long num, long len, long min, long max){
        return (int)((num - min) * len / (max - min) );
    }

    public static void main(String[] args) {
        int[] arr = {1,2,4,5,6};
        System.out.println(MaxGap(arr));
    }
    
}
