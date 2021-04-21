package com.shaodw.sort;

/**
 * 冒泡排序
 * 时间复杂度 O(N * N)
 * 额外空间复杂度 O(1)
 */
public class BubbleSort {
    public static void bubbleSort(int[] arr){
        if (arr == null || arr.length <= 1){
            return ;
        }
        for (int end = arr.length - 1; end > 0; end--){//每一次将一个位置的数排好  放在end位置
            boolean flag = false;//是否有数据交换  冒泡排序的优化  提前退出冒泡循环的标志位
            for (int i = 0; i < end; i++){//相邻位置的数进行比较 前面的数小于后面则要交换
                if (arr[i] > arr[i + 1]){
                    Tool.swap(arr, i, i + 1);
                    flag = true;
                }
            }
            if (!flag){
                break;//如果这一次冒泡没有数据交换 直接终止  当某次冒泡操作已经没有数据交换时 说明已经完全有序 不需要再继续执行之后的操作
            }
        }
    }

    /**
     * main方法使用对数器验证代码
     * @param args
     */
    public static void main(String[] args) {
        int times = 1000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < times; i++){
            int[] arr1 = Tool.generateRandomArray(maxSize, maxValue);
            int[] arr2 = Tool.copyArray(arr1);
            Tool.rightSort(arr1);
            bubbleSort(arr2);
            if (!Tool.isEqual(arr1, arr2)){
                succeed = false;
                break;
            }
        }

        System.out.println(succeed ? "测试通过" : "代码有误");
    }
}
