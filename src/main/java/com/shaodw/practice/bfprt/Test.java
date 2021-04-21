package com.shaodw.practice.bfprt;

import java.util.Arrays;

/**
 * @Auther: shaodw
 * @Date: 2020/2/16 14:49
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
        // 1 1 1 1 2 2 2 3 3 5 5 5 6 6 6 7 9 9 9
        int k = 8;
        int[] arr = { 6, 9, 1, 3, 1, 2, 2, 5, 6, 1, 3, 5, 9, 7, 2, 5, 6, 1, 9 };
        int[] res = getMinKNums(arr, k);
        Arrays.stream(res).forEach(System.out::print);
    }

    public static int[] getMinKNums(int[] arr, int k){
        if (k > arr.length || k < 1){
            return arr;
        }
        int[] copyArr = Arrays.copyOfRange(arr, 0, arr.length);
        int kValue = getMinKNum(copyArr, k);
        int[] res = new int[k];
        int index = 0;
        for (int i = 0; i < arr.length; i++){
            if (arr[i] < kValue){
                res[index++] = arr[i];
            }
        }

        for (; index < res.length; index++){
            res[index] = kValue;
        }

        return res;
    }

    private static int getMinKNum(int[] arr, int k){
        return bfprt(arr, 0, arr.length - 1, k - 1);
    }

    //arr中第k小的元素的index
    private static int bfprt(int[] arr, int l, int r, int i){
        if (l == r){
            return arr[l];
        }
        int[] range = partiton(arr, l, r, medianOfMeadians(arr, l, r));
        if (i >= range[0] && i <= range[1]){
            return arr[i];
        }else if (i < range[0]){
            return bfprt(arr, l, range[0] - 1, i);
        }else return bfprt(arr, range[1] + 1, r, i);
    }

    private static int medianOfMeadians(int[] arr, int l, int r){
        int num = r - l + 1;
        int offset = num % 5 == 0 ? 0 : 1;
        int[] middleArr = new int[num / 5 + offset];
        for (int i = 0; i < middleArr.length; i++) {
            int startI = l + i * 5;
            int endI = startI + 4;
            middleArr[i] = getMedian(arr, startI, Math.min(endI, r));
        }
        return bfprt(middleArr, 0, middleArr.length - 1, middleArr.length >> 1);
    }

    private static int getMedian(int[] arr, int l, int r){
        insertionSort(arr, l, r);
        return arr[(r + l) >> 1];
    }

    private static int[] partiton(int[] arr, int l, int r, int pivot){
        int less = l - 1;
        int more = r + 1;
        while (l < more){
            if (arr[l] < pivot){
                swap(arr, l++, ++less);
            }else if (arr[l] > pivot){
                swap(arr, l, --more);
            }else {
                l++;
            }
        }

        return new int[]{less + 1, more - 1};
    }

    private static void insertionSort(int[] arr, int l, int r){
        for (int i = l + 1; i < r; i++){
            for (int j = i; j > l && arr[j] > arr[j - 1]; j--){
                swap(arr, j - 1, j);
            }
        }
    }

    private static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
