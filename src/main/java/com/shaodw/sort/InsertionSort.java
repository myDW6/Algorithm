package com.shaodw.sort;

/**
 * 插入排序
 * 时间复杂度：和数据状况有关
 *            最好情况O(N)   有序
 *            最坏情况O(N * N) 逆序  (算法流程按最差情况估计时间复杂度)
 *            额外空间复杂度 O(1)
 */
public class InsertionSort {
    public static void insertionSort(int[] arr){
        if (arr == null || arr.length < 2)
            return;
        for (int i = 1; i < arr.length; i++){
            for (int j = i ; j > 0 && arr[j - 1] > arr[j]; j--){
                Tool.swap(arr, j - 1, j);
            }
        }
    }

    public static void main(String[] args) {
        int times = 1000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < times; i++){
            int[] arr1 = Tool.generateRandomArray(maxSize, maxValue);
            int[] arr2 = Tool.copyArray(arr1);
            insertionSort(arr1);
            Tool.rightSort(arr2);
            if (!Tool.isEqual(arr1, arr2)){
                succeed = false;
                break;
            }
        }

        System.out.println(succeed ? "测试通过" : "代码有误");
    }
}
