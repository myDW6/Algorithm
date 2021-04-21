package com.shaodw.practice.bfprt;

import java.io.InputStream;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * @Auther: shaodw
 * @Date: 2020-01-11 22:23
 * @Description:在10万个数中选择10个最大的  第K大就是第n - K + 1小 n
 */
public class TopK {
    public static void main(String[] args) {
        int[] arr = new int[100000];
        int k = 10;

        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Math.abs(random.nextInt());
        }
        int[] arr1 = copyArr(arr);
        int[] arr2 = copyArr(arr);
        int[] arr3 = copyArr(arr);

        System.out.println("======AllSort==========");
        Arrays.stream(TopKBySort.topK(arr, k)).sorted().forEach(System.out::println);
        System.out.println("======PriorityQueue==========");
        Arrays.stream(TopKByJavaHeap.topK(arr1, k)).sorted().forEach(System.out::println);
        System.out.println("======HeapInsert heapify==========");
        Arrays.stream(TopKByHeapSort.topKNums(arr2, k)).sorted().forEach(System.out::println);
        System.out.println("======BFPRT==========");
        Arrays.stream(TopKByBFPRT.topKNums(arr3, k)).sorted().forEach(System.out::println);

    }

    private static int[] copyArr(int[] arr){
        int[] newArr = new int[arr.length];
        for (int i = 0; i < newArr.length; i++) {
            newArr[i] = arr[i];
        }
        return newArr;
    }

}

class TopKByBFPRT{
    public static int[] topKNums(int[] arr, int k){
        if (k < 1 || k > arr.length)
            return arr;
        int v = getKMaxNum(arr, arr.length - k + 1);
        int[] res = new int[k];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > v){
                res[index++] = arr[i];
            }
        }
        for (; index < res.length; index++){
            res[index] = v;
        }
        return res;
    }

    public static int getKMaxNum(int[] arr, int k){
        int[] copyArr = Arrays.copyOfRange(arr, 0, arr.length);
        return bfprt(copyArr, 0, copyArr.length - 1, k - 1);
    }

    private static int bfprt(int[] arr, int l, int r, int i){
        if (l == r)
            return arr[l];
        int[] range = partition(arr, l, r, medianOfMedians(arr, l, r));
        if (i >= range[0] && i <= range[1]){
            return arr[i];
        }else if (i < range[0]){
            return bfprt(arr, l, range[0] - 1, i);
        }else return bfprt(arr, range[1] + 1, r, i);
    }

    private static int medianOfMedians(int[] arr, int l, int r){
        int num = r - l + 1;
        int[] middleArr = new int[num / 5 + num % 5 == 0 ? 0 : 1];
        for (int i = 0; i < middleArr.length; i++) {
            int startIndex = i * 5 + l;
            int endIndex = startIndex + 4;
            middleArr[i] = getMedian(arr, startIndex, Math.min(endIndex, r));
        }

        return bfprt(middleArr, 0, middleArr.length - 1, middleArr.length >> 1);
    }

    private static int getMedian(int[] arr, int l, int r){
        insertionSort(arr, l, r);
        return arr[(l + r) >> 1];
    }

    private static int[] partition(int[] arr, int l, int r, int pivot){
        int less = l - 1;
        int more = r + 1;
        while (l < more){
            if (arr[l] < pivot){
                swap(arr, l++, ++less);
            }else if (arr[l] > pivot){
                swap(arr, l, --more);
            }else l++;
        }
        return new int[]{less + 1, more - 1};
    }

    private static void insertionSort(int[] arr, int l, int r){
        for (int i = l + 1; i <= r ; i++) {
            for (int j = i; j > l && arr[j] < arr[j - 1] ; j--) {
                swap(arr, j, j - 1);
            }
        }
    }

    private static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}

class TopKByHeapSort{
    public static int[] topKNums(int[] arr, int k){
        if (k < 0 || k > arr.length){
            return arr;
        }
        int[] kHeap = Arrays.copyOfRange(arr, 0, k);
        for (int i = 0; i < k; i++) {
            heapInsert(kHeap, i);
        }
        for (int i = k; i < arr.length; i++) {
            if (arr[i] > kHeap[0]){
                kHeap[0] = arr[i];
                heapify(kHeap, 0,k);
            }
        }

        return kHeap;
    }

    private static void heapInsert(int[] arr, int i){
        while (arr[(i - 1) / 2] > arr[i]){
            swap(arr, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    private static void heapify(int[] arr, int i, int heapSize){
        int left = (i << 1) + 1;
        while (left < heapSize){
            int less = left + 1 < heapSize && arr[left + 1] < arr[left] ? left + 1 : left;
            less = arr[left] < arr[i] ? less : i;
            if (i == less){
                break;
            }
            swap(arr, i, less);
            i = less;
            left = (i << 1) + 1;
        }
    }

    private static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}

class TopKByJavaHeap{
    public static int[] topK(int[] arr, int k){
        //申请小根堆
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);
        for (int i = 0; i < k; i++) {
            minHeap.add(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (arr[i] > minHeap.peek()){
                minHeap.poll();
                minHeap.add(arr[i]);
            }
        }

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = minHeap.poll();
        }

        return res;
    }
}

class TopKBySort{
    public static int[] topK(int[] arr, int k){
        Integer[] integers = new Integer[arr.length];
        for (int i = 0; i < integers.length; i++) {
            integers[i] = arr[i];
        }
        Arrays.sort(integers, (a,b)->b - a);
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = integers[i];
        }
        return res;
    }
}
