package com.shaodw.sort;

/**
 * 选择排序
 * 时间复杂度 O(N * N)
 * 额外空间复杂度 O(1)
 */
public class SelectionSort {
    public static void selectionSort(int[] arr){
        if (arr == null || arr.length <= 1)
            return;
        for (int i = 0; i < arr.length - 1; i++){
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++){
                minIndex = arr[j] < arr[minIndex] ? j :minIndex;
            }
            Tool.swap(arr, i, minIndex);
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
            selectionSort(arr1);
            Tool.rightSort(arr2);
            if (!Tool.isEqual(arr1, arr2)){
                succeed = false;
                break;
            }
        }

        System.out.println(succeed ? "测试通过" : "代码有误");
    }
}
