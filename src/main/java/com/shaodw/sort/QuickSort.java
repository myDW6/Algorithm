package com.shaodw.sort;

/**
 * partition过程实现快速排序
 * 时间复杂度O(N * logN) 最差O(N^2)
 * 额外空间复杂度 O(logN) 最差O(N) 记录断点位置
 */
public class QuickSort {

    public static void quickSort(int[] arr){
        if (arr == null || arr.length < 2)
            return;
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int l, int r){
        if (l < r ){
            Tool.swap(arr, r, (int)(Math.random() * (r - l + 1)) + l);
            //将随机位置上的值与末尾位置的值交换 实现随机快速排序
            int[] tmp = partition(arr, l, r);
            sort(arr, l, tmp[0] - 1);
            sort(arr, tmp[1] + 1, r);
        }

    }

    private static int[] partition(int[] arr, int l, int r){
        int less = l - 1;
        int more = r;
        int cur = l;
        while (cur < more){
            if (arr[cur] < arr[r]){
                Tool.swap(arr, cur++, ++less);
            }else if (arr[cur] > arr[r]){
                Tool.swap(arr, cur, --more);
            }else cur++;
        }

        Tool.swap(arr, r, more);
        return new int[]{less + 1, more};
    }

    public static void main(String[] args) {
        int times = 1000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < times; i++){
            int[] arr1 = Tool.generateRandomArray(maxSize, maxValue);
            int[] arr2 = Tool.copyArray(arr1);
            quickSort(arr1);
            Tool.rightSort(arr2);
            if (!Tool.isEqual(arr1, arr2)){
                succeed = false;
                break;
            }
        }

        System.out.println(succeed ? "测试通过" : "代码有误");
    }
}
