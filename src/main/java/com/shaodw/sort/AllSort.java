package com.shaodw.sort;


import java.util.Arrays;
import java.util.Random;

/**
 * @Auther: shaodw
 * @Date: 2020/3/12 10:58
 * @Description:
 */
public class AllSort {

    public static void main(String[] args) {
        int[] arr1 = generateRandomArray(100000, 100);
        int[] arr2 = copyArr(arr1);
        int[] arr3 = copyArr(arr1);
        int[] arr4 = copyArr(arr1);
        int[] arr5 = copyArr(arr1);
        int[] arr6 = copyArr(arr1);
        int[] arr7 = copyArr(arr1);
        int[] arr8 = copyArr(arr1);

        long s0 = System.nanoTime();
        rightMethod(arr1);
        long s1 = System.nanoTime();

        long s2 = System.nanoTime();
        quickSort(arr2);
        long s3 = System.nanoTime();

        long s4 = System.nanoTime();
        mergeSort(arr3);
        long s5 = System.nanoTime();

        long s6 = System.nanoTime();
        heapSort(arr4);
        long s7 = System.nanoTime();

        long s8 = System.nanoTime();
        bubbleSort(arr5);
        long s9 = System.nanoTime();

        long s10 = System.nanoTime();
        insertionSort(arr6);
        long s11 = System.nanoTime();

        long s12 = System.nanoTime();
        selectionSort(arr7);
        long s13 = System.nanoTime();

        long s14 = System.nanoTime();
        bucketSort(arr8);//如果是负数 需要偏移下标
        long s15 = System.nanoTime();

        int checkTimes = 100000;

        System.out.println("System provided sort takes " + (s1 - s0) / 1000000000.0 + " s");

        boolean success = true;
        for (int i = 0; i < checkTimes; i++) {
            if (!isequals(arr1, arr2)){
                success = false;
                break;
            }
        }

        System.out.println(success ? "QuickSort Nice takes " + (s3 - s2) / 1000000000.0 + " s" : "Fucking ! ");

        success = true;
        for (int i = 0; i < checkTimes; i++) {
            if (!isequals(arr1, arr3)){
                success = false;
                break;
            }
        }

        System.out.println(success ? "MergeSort Nice takes " + (s5 - s4) / 1000000000.0 + " s" : "Fucking !");


        success = true;
        for (int i = 0; i < checkTimes; i++) {
            if (!isequals(arr1, arr4)){
                success = false;
                break;
            }
        }

        System.out.println(success ? "HeapSort Nice takes " + (s7 - s6) / 1000000000.0 + " s" : "Fucking !");

        success = true;
        for (int i = 0; i < checkTimes; i++) {
            if (!isequals(arr1, arr5)){
                success = false;
                break;
            }
        }

        System.out.println(success ? "BubbleSort Nice takes " + (s9 - s8) / 1000000000.0 + " s" : "Fucking !");

        success = true;
        for (int i = 0; i < checkTimes; i++) {
            if (!isequals(arr1, arr6)){
                success = false;
                break;
            }
        }

        System.out.println(success ? "Insertion Nice takes " + (s11 - s10) / 1000000000.0 + " s" : "Fucking !");

        success = true;
        for (int i = 0; i < checkTimes; i++) {
            if (!isequals(arr1, arr7)){
                success = false;
                break;
            }
        }

        System.out.println(success ? "SelectionSort Nice takes " + (s13 - s12) / 1000000000.0 + " s" : "Fucking !");

        success = true;
        for (int i = 0; i < checkTimes; i++) {
            if (!isequals(arr1, arr8)){
                success = false;
                break;
            }
        }

        System.out.println(success ? "BucketSort Nice takes " + (s15 - s14) / 1000000000.0 + " s" : "Fucking !");
    }

    public static void quickSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        sort(arr, 0, arr.length - 1);

    }

    //l -> r quickSort
    private static void sort(int[] arr, int l, int r){
        if (l < r){
            swap(arr, r, (int)(l +(r - l + 1)* Math.random()));
            int[] p = partition(arr, l, r);
            sort(arr, l, p[0] - 1);
            sort(arr, p[1] + 1, r);
        }
    }

    private static int[] partition(int[] arr, int l, int r){
        int less = l - 1;
        int more = r + 1;
        int pivot = arr[r];
        while (l < more){
            if (arr[l] < pivot){
                swap(arr, ++less, l++);
            }else if (arr[l] > pivot){
                swap(arr, --more, l);
            }else l++;
        }
        return new int[]{less + 1, more - 1};
    }

    private static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static void rightMethod(int[] arr){
        Arrays.sort(arr);
    }

    private static int[] generateRandomArray(int size, int maxValue){
        Random random = new Random();
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = random.nextInt(maxValue + 1) - random.nextInt(maxValue);
        }
        return res;
    }

    private static boolean isequals(int[] arr1, int[] arr2){
        if (arr1 == null && arr2 == null){
            return true;
        }
        if (arr1 == null || arr2 == null){
            return false;
        }
        if (arr1.length != arr2.length){
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]){
                return false;
            }
        }
        return true;
    }

    private static int[] copyArr(int[] arr){
        int[] copy = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            copy[i] = arr[i];
        }
        return copy;
    }
    
    public static void mergeSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        mergeSort(arr, 0, arr.length - 1);
    }
    
    private static void mergeSort(int[] arr, int l, int r){
        if (l < r){
            int mid = l + ((r - l) >> 1);
            mergeSort(arr, l, mid);
            mergeSort(arr, mid + 1, r);
            merge(arr, l,mid, r);
        }
    }
    
    private static void merge(int[] arr, int l, int mid, int r){
        int[] tmp = new int[r - l + 1];
        int p1 = l;
        int p2 = mid + 1;
        int i = 0;
        while (p1 <= mid && p2 <= r){
            tmp[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid){
            tmp[i++] = arr[p1++];
        }
        while (p2 <= r){
            tmp[i++] = arr[p2++];
        }
        for (i = 0; i < tmp.length; i++){
            arr[i + l] = tmp[i];
        }
    }

    public static void heapSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        //big heap
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }

        int heapSize = arr.length;
        do {
            swap(arr, 0, --heapSize);
            heapify(arr, 0, heapSize);
        }while (heapSize > 0);

    }

    private static void heapInsert(int[] arr, int i){
        while (arr[i] > arr[(i - 1) / 2]){
            swap(arr, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    private static void heapify(int[] arr, int i, int heapSize){
        int left = (i << 1) + 1;
        while (left < heapSize){
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[i] > arr[largest] ? i : largest;
            if (i == largest){
                break;
            }
            swap(arr, i, largest);
            i = largest;
            left = (i << 1) + 1;
        }
    }

    public static void bubbleSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        for (int end = arr.length - 1; end > 0; end--) {
            boolean hasExchanged = false;
            for (int i = 0; i < end; i++) {
                if (arr[i] > arr[i + 1]){
                    swap(arr, i, i + 1);
                    hasExchanged = true;
                }
            }

            if (!hasExchanged){
                break;
            }
        }
    }

    public static void insertionSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0 && arr[j - 1] > arr[j]; j--){
                swap(arr, j, j - 1);
            }
        }
    }

    public static void selectionSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i; j < arr.length; j++) {
                minIndex = arr[minIndex] < arr[j] ? minIndex : j;
            }
            swap(arr, minIndex, i);
        }
    }

    public static void bucketSort(int[] arr){
        if (arr == null || arr.length < 2) {
            return;
        }
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }

        //对于有负数的处理
        int offset = Math.abs(min);
        int[] bucket = new int[max - min + 1];
        for (int i = 0; i < arr.length; i++) {
            bucket[arr[i] + offset]++;
        }

        int index = 0;
        for (int i = 0; i < bucket.length; i++) {
            while (bucket[i]-- > 0){
                arr[index++] = i - offset;
            }
        }

    }
}
