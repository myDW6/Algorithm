package com.shaodw.sort.sort_extension;

import com.shaodw.sort.Tool;

/**
 * 演示下大根堆的建立
 */
public class BuildBigRootHeap {

    /**
     * 数组生成大根堆
     */
    public static int[] getHeap(int[] arr){
        for (int i =  0; i < arr.length; i++){
            heapInsert(arr, i);
        }
        return arr;
    }

    public static void heapInsert(int[] heap, int index){
        while (heap[index] > heap[(index - 1) / 2]){
            Tool.swap(heap, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void main(String[] args) {
        int[] arr = {5,7,0,6,8};
        int[] heap =getHeap(arr);
        Tool.printArr(heap);
    }

}
