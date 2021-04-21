package com.shaodw.sort.sort_extension;

import com.shaodw.sort.Tool;

/**
 * 给定一个数组arr，和一个数num，请把小于等于num的数放在数组的左边，大于num的数放在数组的右边。
 * 要求额外空间复杂度O(1)，时间复杂度O(N)
 */
public class DivideNum {
    public static void divide(int[] arr, int num){
        int less = -1;
        int cur = 0;
        while (cur <= arr.length - 1){
            if (arr[cur] <= num){
                Tool.swap(arr, cur++, ++less);
            }else {
                cur++;
            }
        }
    }

    public static boolean check(int[] arr, int num){
        if (arr == null || arr.length < 2)
            return true;
        int flag = 0;
        for (int i = 0; i < arr.length; i++){
            if (arr[i] > num){
                flag = i;
                break;
            }
        }
        if (flag == 0 || flag == arr.length - 1){
            return true;
        }
        for (int i = flag; i < arr.length; i++){
            if (arr[i] < num){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
       boolean success = true;
        for (int i = 0; i < 50000; i++){
            int[] arr = Tool.generatePositiveRandomArray(7,100);
            int[] backupArr = Tool.copyArray(arr);
            int num = 50;
            divide(arr, num);
            if (!check(arr, num)){
                success = false;
                Tool.printArr(backupArr);
                Tool.printArr(arr);
                break;
            }
        }
        System.out.println(success ? "通过" : "失败");
    }
}
