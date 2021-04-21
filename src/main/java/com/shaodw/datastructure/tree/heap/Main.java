package com.shaodw.datastructure.tree.heap;

import java.util.Random;

/**
 * 比较heapify将数组转换为堆 和 直接将数组中每一个元素添加进堆的区别
 *              O(N)                    O(NlogN)
 */

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        int n = 1000000;
        Integer[] testData1 = new Integer[n];
        Integer[] testData2 = new Integer[n];
        for (int i = 0; i < n; i++) {
            testData1[i] = random.nextInt(Integer.MAX_VALUE);
        }
        for (int i = 0; i < n; i++) {
            testData2[i] = testData1[i];
        }
        System.out.println("Heapify taste " + testHeap(testData1, true));
        System.out.println("Without Heapify taste " + testHeap(testData2, false));
    }

    public static double testHeap(Integer[] testData, boolean isHeapify){
        long startTime = System.nanoTime();

        MaxHeap<Integer> maxHeap;
        if (isHeapify){
            maxHeap = new MaxHeap<>(testData);
        }else {
            maxHeap = new MaxHeap<>();
            for (int i : testData){
                maxHeap.add(i);
            }
        }

        int[] arr = new int[testData.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = maxHeap.extractMax();
        }
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] < arr[i]){
                System.out.println("fuck");
                break;
            }
        }
        System.out.println("Test maxHeap completed");

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }
}
