package com.shaodw.sort;

/**
 * @Auther: shaodw
 * @Date: 2020/1/28 20:53
 * @Description: 用于检测排序代码的正确性
 */
public class IsRight {
   public static void main(String[] args){
       int times = 1000;
       int maxSize = 100;
       int maxValue = 100;
       boolean succeed = true;
       for (int i = 0; i < times; i++){
           int[] arr1 = Tool.generateRandomArray(maxSize, maxValue);
           int[] arr2 = Tool.copyArray(arr1);
           /**
            * 此处调用排序代码对arr1进行排序
            */
           //BubbleSort.bubbleSort(arr1);
           //MergeSort.mergeSort(arr1);
           //AllSort.quickSort(arr1);
           //InsertionSort.insertionSort(arr1);
           //HeapSort.heapSort(arr1);
           //SelectionSort.selectionSort(arr1);


           Tool.rightSort(arr2);
           if (!Tool.isEqual(arr1, arr2)){
               succeed = false;
               break;
           }
       }

       System.out.println(succeed ? "测试通过" : "代码有误");
   }
}
