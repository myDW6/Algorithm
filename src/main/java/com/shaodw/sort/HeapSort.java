package com.shaodw.sort;

/**
 * 堆排序及数据结构堆
 * 时间复杂度O(N*logN)，
 * 额外空间复杂度O(1)
 *      1，堆结构的heapInsert与heapify
 *      2，堆结构的增大和减少
 *      3，如果只是建立堆的过程，时间复杂度为O(N)
 *      4，优先级队列结构，就是堆结构
 */
public class HeapSort {

    /**
     * 维护大根堆
     * @param arr
     */
    public static void heapSort(int[] arr){
        if (arr == null || arr.length < 2)
            return;

        //O(N)  依次加入大根堆
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }

        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        while (heapSize > 0){
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    //将i位置的元素插入大根堆并调整到正确位置 i以前的元素已经构成大根堆 即使i到了0代码也正确 (0 - 1)/ 2 = 0;  复杂度O(logH) H为i - 1形成的堆的高度
    //那么n个元素  log1 + log2 + log3 + ... + log N - 1 => O(N)
    private static void heapInsert(int[] arr, int i){
        while (arr[i] > arr[(i - 1) / 2]){
            swap(arr, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    /**
     *
     * @param arr
     * @param i  当前需要调整（往下沉）的元素下标
     * @param heapSize 0 -- heapSize-1 是有效的堆范围
     */
    private static void heapify(int[] arr, int i, int heapSize){
        int left = (i << 1) + 1;
        while (left < heapSize){
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            //右孩子不越界 且 比左孩子大 则先选择较大的两个孩子之一
            largest = arr[i] > arr[largest] ? i : largest;
            //较大的孩子的值与i位置值进行比较 找出较大的下标
            if (largest == i)
                break;
            //若largest还是当前小标i则不需要调整 直接结束
            swap(arr, largest, i);//潜台词是你与你的两个孩子中较大的那个交换
            i = largest;//你变成较大的那个孩子的下标
            left = (i << 1) + 1;
        }
    }

    private static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {2,1,4,4,5,1,5};
        heapSort(arr);
        for (int i : arr){
            System.out.println(i);
        }
    }

}
