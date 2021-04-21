package com.shaodw.sort;

/**
 * 归并排序
 * 时间复杂度：O(N * logN)
 * 额外空间复杂度: O(N)
 */
public class MergeSort {
    public static void mergeSort(int[] arr){
        if (arr == null || arr.length < 2)
            return;
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int l, int r){
        if (l == r)
            return;
        int mid = l + ((r - l) >> 1);
        sort(arr, l, mid);
        sort(arr, mid + 1, r);
        merge(arr,l, mid, r);
    }

    private static void merge(int[] arr, int l, int mid, int r){
        int[] help = new int[r - l + 1];
        int p1 = l;
        int p2 = mid + 1;
        int i = 0;
        while (p1 <= mid && p2 <= r){
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid){
            help[i++] = arr[p1++];
        }
        while (p2 <= r){
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++){
            arr[l + i] = help[i];
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
            mergeSort(arr1);
            Tool.rightSort(arr2);
            if (!Tool.isEqual(arr1, arr2)){
                succeed = false;
                break;
            }
        }

        System.out.println(succeed ? "测试通过" : "代码有误");
    }
}
