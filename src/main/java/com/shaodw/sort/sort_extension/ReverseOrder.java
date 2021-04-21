package com.shaodw.sort.sort_extension;


import com.shaodw.sort.Tool;

/**
 * 在一个数组中，左边的数如果比右边的数大，则折两个数构成一个逆序对，请打印所有逆序对
 * [1,3,4,2,5]
 */
public class ReverseOrder {
    /**
     * 使用归并排序来加速 产生逆序对就在merge过程中 时间复杂度为排序复杂度O(N * logN)
     */

    public static void printReverseOrder(int[] arr){
        if (arr == null || arr.length < 2)
            return;
        sort(arr, 0, arr.length -1);
    }

    public static void sort(int[] arr, int l, int r){
        if (l == r)
            return;
        int mid = l + ((r - l) >> 1);
        sort(arr, l, mid);
        sort(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    public static void merge(int[] arr, int l, int mid, int r){
        int[] help = new int[r - l + 1];
        int p1 = l;
        int p2 = mid + 1;
        int i = 0;
        while (p1 <= mid && p2 <= r){
            if (arr[p1] > arr[p2]){
                for (int j = p1; j <= mid; j++){
                    System.out.println("[" + arr[j] +" " + arr[p2] + "]");
                }
            }
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

    /**
     * 相对正确但复杂度高的方法 O(N * N)
     * @param arr
     */
    public static void relativeRight(int[] arr){
        if (arr == null || arr.length < 2)
            return;
        for (int i = 1; i < arr.length; i++){
            for (int j = 0; j < i; j++){
                if (arr[j] > arr[i]){
                    System.out.println("[" + arr[j] + " " + arr[i] + "]");
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = Tool.generateRandomArray(10, 10);
        relativeRight(arr);
        System.out.println("==============");
        printReverseOrder(arr);
    }
}
