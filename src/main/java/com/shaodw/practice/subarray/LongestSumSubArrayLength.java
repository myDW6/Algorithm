package com.shaodw.practice.subarray;

import java.util.HashMap;

/**
 * @Auther: shaodw
 * @Date: 2020/2/22 10:13
 * @Description: 一个数组 有正有负有0 返回这个数组中累加和等于aim的最长子数组长度
 */


/**
 * 看到子数组 子串 即连续的一段 普遍的解题思路是必须以每个位置结尾的情况下 如果都能求出答案 答案一定在其中
 * 比如这个数组 arr = {7,3,2,1,1,7,-6,-1,7}
 * 如果能求必须以0位置的数结尾的累加和为aim的最长子数组
 *        必须以1位置的数结尾的累加和为aim的最长子数组
 *        必须以2位置的数结尾的累加和为aim的最长子数组
 *        必须以i位置的数结尾的累加和为aim的最长子数组
 *     答案一定在其中
 *
 *
 *     如果来到i位置 该如何求以i位置的数结尾的子数组累加和为aim的最长子数组长度呢？
 *     用一个sum表示从0位置累加到当前i位置 所有数的累加和
 *     我要求的累加和为aim  那么我只知道从0位置开始累加到哪个j位置是"最早"累加和为sum - aim的  那么j+1位置到i位置一定为以当前i位置元素结尾的最长子数组
 */
public class LongestSumSubArrayLength {

    public static int maxLength(int[] arr, int aim){
        if (arr == null || arr.length == 0){
            return 0;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,-1);//0这个累加和最早出现在-1位置 因为一个数也没有时累加和也为0
        int len = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {//必须以i位置结尾的子数组
            sum += arr[i];
            if (map.containsKey(sum - aim)){
                len = Math.max(len, i - map.get(sum - aim));
            }
            //如果map中没有 说明必须以i位置结尾的子数组无法加出aim
            if (!map.containsKey(sum)){
                map.put(sum, i);
            }
        }
        return len;
    }

    public static void main(String[] args) {
        int[] arr = {7,3,2,1,1,7,-6,-1,7};
        System.out.println(maxLength(arr, 7));
    }
}

/**
 * 上述为算法原型
 * 一个数组中 只有整数只有奇数和偶数 求奇数和偶数个数相等的 最长子数组的长度 （奇数->1 偶数->-1 求累加和为0的最长子数组长度）
 * 一个数组中 只有0和1 求0和1个数相等的最长子数组的长度 （1->1 0->-1 求累加和为0的最长子数组长度）
 * 一个数组中 只有0 1 2 求含有1和2个数相等的最长子数组的长度 (0->0 1->1 2->-1 求累加和为0的最长子数组)
 */
