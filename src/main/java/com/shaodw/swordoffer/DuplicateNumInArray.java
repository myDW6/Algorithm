package com.shaodw.swordoffer;

import java.util.HashSet;

/**
 * @Auther: shaodw
 * @Date: 2020-01-15 12:48
 * @Description: 数组中重复的数字
 * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，但不知道有几个数字是重复的。
 * 也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
 * 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。
 */
public class DuplicateNumInArray {
    public boolean duplicate(int numbers[],int length,int [] duplication) {
        if (numbers == null || length <= 0)
            return false;
        for (int i = 0; i < length; i++) {
            if (numbers[i] < 0 || numbers[i] > length - 1)
                return false;
        }
        //对边界进行处理
        for (int i = 0; i < length; i++){
            while (numbers[i] != i){
                if (numbers[i] == numbers[numbers[i]]){
                    duplication[0] = numbers[i];
                    return true;
                }
                swap(numbers, i, numbers[i]);
            }
        }
        //算法核心：如果没有重复 那么可以依次填入数组 应该为有序的 且i位置上元素一定为i
        //0             3          0 ！= 3                                        0 == 0
        //i位置上元素值为k 那么先判断i==k是否成立 若是 i->i+1 若不是 判断下标k的元素值于i是否相等 若相等 则找到 若不等 交换两者位置 继续判断
        return false;
    }

    private void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public boolean duplicateUseSet(int numbers[],int length,int [] duplication){
        if (numbers == null || length < 1){
            return false;
        }
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] < 0 || numbers[i] >= length){
                return false;
            }
            if (hashSet.contains(numbers[i])){
                duplication[0] = numbers[i];
                return true;
            }else hashSet.add(numbers[i]);
        }
        return false;
    }


    public boolean dup(int numbers[],int length,int [] duplication){
        for (int i = 0; i < length; i++){
            while (numbers[i] != i){
                if (numbers[i] == numbers[numbers[i]]){
                    duplication[0] = numbers[i];
                    return true;
                }
                swap(numbers, i, numbers[i]);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {2,3,1,0,2,5,3};
        int[] d = new int[1];
        int[] d1 = new int[1];
        System.out.println(new DuplicateNumInArray().duplicate(arr, 7, d));
        System.out.println(new DuplicateNumInArray().duplicate(arr, 7, d1));
        System.out.println(d[0]);
        System.out.println(d1[0]);

    }

}
