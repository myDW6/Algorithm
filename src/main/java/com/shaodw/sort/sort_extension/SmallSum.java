package com.shaodw.sort.sort_extension;

import com.shaodw.sort.Tool;

/**
 *  小和问题
 * 在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组的小和。求一个数组 的小和。
 * 例子： [1,3,4,2,5]
 * 1左边比1小的数，没有；
 * 3左边比3小的数，1；
 * 4左边比4小的数，1、3；
 * 2左边比2小的数，1；
 * 5左边比5小的数，1、3、4、2；
 * 所以小和为1+1+3+1+1+3+4+2=16
 */
public class SmallSum {
    /**
     * 使用归并排序的过程来加速 O(N * logN)
     * @param arr
     * @return
     */
    public static int smallSum(int[] arr){
        if (arr == null || arr.length < 2)
            return 0;
        return sort(arr, 0 ,arr.length - 1);
    }

    public static int sort(int[] arr, int l, int r){
        if (l == r)
            return 0;
        int mid = l + ((r  - l ) >> 1);
        return sort(arr, l, mid) + sort(arr, mid + 1, r) + merge(arr, l, mid, r);
    }

    public static int merge(int[] arr, int l, int mid, int r){
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = mid + 1;
        int sum = 0;
        while (p1 <= mid && p2 <= r){
            sum += arr[p1] < arr[p2] ? arr[p1] * (r - p2 + 1) : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];

        }
        while (p1 <= mid){
            help[i++] = arr[p1++];
        }
        while (p2 <= r){
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++){
            arr[i + l] = help[i];
        }
        return sum;
    }

    /**
     * 相对正确的方法 复杂度高  使用两层循环 O(N ^ 2)
     * @param arr
     * @return
     */
    public static int relativeRight(int[] arr){
        if (arr == null || arr.length < 2)
            return 0;
        int sum = 0;
        for (int i = 1; i < arr.length; i++){
            for (int j = 0; j < i; j++){
                sum += arr[j] < arr[i] ? arr[j] : 0;
            }
        }
        return sum;
    }

    //使用对数器检验
    public static void main(String[] args) {
        int times = 100000;
        int maxSize = 100;
        int maxValue = 1000;
        boolean succeed = true;

        for (int i = 0; i < times; i++){
            int[] arr1 = Tool.generateRandomArray(maxSize, maxValue);
            int[] arr2 = Tool.copyArray(arr1);
            int[] arrOri = Tool.copyArray(arr1);

            int res1 = relativeRight(arr1);
            int res2 = smallSum(arr2);
            if (res1 != res2){
                succeed = false;
                Tool.printArr(arrOri);
                System.out.println("right result: " + res1);
                System.out.println("your result: " + res2);
                break;
            }

        }

        System.out.println(succeed ? "测试通过" : "代码有误");
    }
}
