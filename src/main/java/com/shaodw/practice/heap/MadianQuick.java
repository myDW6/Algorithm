package com.shaodw.practice.heap;


import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Auther: shaodw
 * @Date: 2020/2/5 15:30
 * @Description: 一个数据流中，随时可以取得中位数
 */
public class MadianQuick {

    /**
     * 使用两个堆维护边界
     */
    public static class MedianHolder{
        private PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b)->b-a);
        private PriorityQueue<Integer> minHeap = new PriorityQueue<>();//默认小根堆

        private void modifyTwoHeapSize(){
            if (this.maxHeap.size() == this.minHeap.size() + 2){
                this.minHeap.add(this.maxHeap.poll());
            }
            if (this.minHeap.size() == this.maxHeap.size() + 2){
                this.maxHeap.add(this.minHeap.poll());
            }
        }

        public void addNumber(int num){
            if (this.maxHeap.isEmpty()){
                this.maxHeap.add(num);
                return;
            }
            if (this.maxHeap.peek() >= num){
                this.maxHeap.add(num);
            }else {
                if (this.minHeap.isEmpty()){
                    this.minHeap.add(num);
                    return;
                }
                if (this.minHeap.peek() > num){
                    this.maxHeap.add(num);
                }else {
                    this.minHeap.add(num);
                }
            }
            modifyTwoHeapSize();
        }

        public Integer getMedian(){
            int maxHeapSize = this.maxHeap.size();
            int minHeapSize = this.minHeap.size();
            if (maxHeapSize + minHeapSize == 0){
                return null;
            }
            Integer maxHeapHead = this.maxHeap.peek();
            Integer minHeapHead = this.minHeap.peek();
            if (((maxHeapSize + minHeapSize) & 1) == 0){
                return (maxHeapHead + minHeapHead) >> 1;
            }
            return maxHeapSize > minHeapSize ? maxHeapHead : minHeapHead;
        }


        //for test
        public static int[] getRandomArray(int maxLen, int maxValue){
            int[] res = new int[(int)(Math.random() * maxLen) + 1];
            for (int i = 0; i < res.length; i++) {
                res[i] = (int)(Math.random() * maxValue);
            }
            return res;
        }

        // for test, this method is ineffective but absolutely right
        public static int getMedianOfArray(int[] arr) {
            int[] newArr = Arrays.copyOf(arr, arr.length);
            Arrays.sort(newArr);
            int mid = (newArr.length - 1) / 2;
            return (newArr.length & 1) == 0 ? (newArr[mid] + newArr[mid + 1]) / 2 : newArr[mid];
        }

        public static void printArray(int[] arr) {
            for (int i = 0; i != arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }

        public static void main(String[] args) {
            boolean err = false;
            int testTimes = 200000;
            for (int i = 0; i != testTimes; i++) {
                int len = 30;
                int maxValue = 1000;
                int[] arr = getRandomArray(len, maxValue);
                MedianHolder medianHolder = new MedianHolder();
                for (int j = 0; j != arr.length; j++) {
                    medianHolder.addNumber(arr[j]);
                }
                if (medianHolder.getMedian() != getMedianOfArray(arr)) {
                    err = true;
                    printArray(arr);
                    break;
                }
            }
            System.out.println(err ? "Oops..what a fuck!" : "today is a beautiful day^_^");
        }
    }



}
