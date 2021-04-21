package com.shaodw.practice.bfprt;


import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Auther: shaodw
 * @Date: 2020/2/12 13:28
 * @Description: BFPRT是求解topK问题的最优解  O(N)代价
 * 求一个数组中最小的K个数
 */
public class BFPRT {

    //BFPRT 严格的O(N)代价  快排随机划分的代价长期期望为O(N)
    public static int[] getMinKNumsByBFPRT(int[] arr, int k){
        if (k < 1 || k > arr.length) {
            return arr;
        }
        int minKth = getMinKthByBFPRT(arr, k);
        int[] res = new int[k];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < minKth){
                res[index++] = arr[i];
            }
        }
        for (; index < res.length; index++){
            res[index] = minKth;
        }
        return res;
    }

    public static int getMinKthByBFPRT(int[] arr, int k){
        //必须使用拷贝的数组
        int[] copyArr = Arrays.copyOfRange(arr, 0, arr.length);
        return select(copyArr, 0, copyArr.length - 1, k - 1);
    }

    public static int medianOfMedians(int[] arr, int begin, int end){
        //1 逻辑分组 五个一组 最后一组不满5个也为一组 O(1)
        int num = end - begin + 1;
        int offset = num % 5 == 0 ? 0 : 1;
        int[] middleArr = new int[num / 5 + offset];
        //取出每个数组的中位数 组成新的数组 O(N)
        for (int i = 0; i < middleArr.length; i++) {
            int beginI = begin + i * 5;
            int endI = beginI + 4;
            middleArr[i] = getMedian(arr, beginI, Math.min(end, endI));
        }
        //递归调用bfprt获取精准划分值
        return select(middleArr, 0, middleArr.length - 1, middleArr.length / 2);
    }

    public static int select(int[] arr, int begin, int end, int i){
        if (begin == end){
            return arr[end];
        }
        int pivot = medianOfMedians(arr, begin, end);
        int[] pivotRange = partition(arr, begin, end, pivot);
        if (i >= pivotRange[0] && i <= pivotRange[1]){
            return arr[i];
        }else if (i < pivotRange[0]){
            return select(arr, begin, pivotRange[0] - 1, i);
        }else return select(arr, pivotRange[1] + 1, end, i);
    }

    public static int getMedian(int[] arr, int begin, int end){
        insertionSort(arr, begin, end);
        int sum = end + begin;
        int mid = (sum / 2) + (sum % 2);
        return arr[mid];
    }

    public static int[] partition(int[] arr, int begin, int end, int pivotValue){
        int less = begin - 1;
        int more = end + 1;
        while (begin < more){
            if (arr[begin] < pivotValue){
                swap(arr, ++less, begin++);
            }else if (arr[begin] > pivotValue){
                swap(arr, --more, begin);
            }else {
                begin++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    private static void insertionSort(int[] arr, int begin, int end){
        for (int i = begin + 1; i <= end; i++) {
            for (int j = i; j > begin && arr[j - 1] > arr[j] ; j--) {
                swap(arr, j - 1, j);
            }
        }
    }

    //O(N * logK)  使用大根堆要比小根堆好 理由很简单 减少了很多堆的维护成本
    public static int[] getMinKNumsByHeap(int[] arr, int k){
        if (k < 1 || k > arr.length){
            return arr;
        }
        int[] kHeap = new int[k];
        for (int i = 0; i < k; i++) {
            kHeap[i] = arr[i];
            heapInsert(kHeap, i);
        }

        for (int i = k; i < arr.length; i++) {
            if (arr[i] < kHeap[0]){
                kHeap[0] = arr[i];
                heapify(kHeap, 0, k);
            }
        }
        return kHeap;
    }

    public static void heapify(int[] arr, int i, int heapSize){
        int left = (i << 1) + 1;
        while (left < heapSize){
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[i] > arr[largest] ? i : largest;
            if (largest == i)
                break;
            swap(arr, largest, i);
            i = largest;
            left = (i << 1) + 1;
        }
    }

    private static void heapInsert(int[] arr, int i){
        while (arr[i] > arr[(i - 1) / 2]){
            swap(arr, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    private static void swap(int[] arr, int index1, int index2){
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static int[] useMinHeap(int[] arr, int k){
        PriorityQueue<Integer> pq = new PriorityQueue<>(k);
        for (int i : arr){
            pq.add(i);
        }
        int[] newArr = new int[k];
        for (int i = 0; i < k; i++) {
            newArr[i] = pq.poll();
        }
        return newArr;
    }

    public static int[] useMaxHeap(int[] arr, int k){
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, (a,b)->b - a);
        for (int i = 0; i < k; i++) {
            pq.add(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (arr[i] < pq.peek()){
                pq.poll();
                pq.add(arr[i]);
            }
        }
        int[] newArr = new int[k];
        for (int i = 0; i < k; i++) {
            newArr[i] = pq.poll();
        }
        return newArr;
    }

    public static void main(String[] args) {
        int k = 10;
        int[] arr = { 6, 9, 1, 3, 1, 2, 2, 5, 6, 1, 3, 5, 9, 7, 2, 5, 6, 1, 9 };
        printArray(getMinKNumsByHeap(arr, 10));
        printArray(arr);
        printArray(useMinHeap(arr, 10));
        printArray(arr);
        printArray(useMaxHeap(arr, 10));
        printArray(arr);
        printArray(getMinKNumsByBFPRT(arr, k));
        printArray(arr);

        System.out.println(select(arr, 0, arr.length - 1, k));
}
}
