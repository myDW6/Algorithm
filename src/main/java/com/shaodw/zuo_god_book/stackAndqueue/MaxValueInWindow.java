package com.shaodw.zuo_god_book.stackAndqueue;

import com.shaodw.sort.Tool;

import java.util.LinkedList;

/**
 * @Auther: shaodw
 * @Date: 2020/3/14 13:19
 * @Description: 有一个整型数组arr和一个大小为w的窗口从数组的最左边滑到最右边，窗口每次向右边滑一个位置。
 * 如果数组长度为n，窗口大小为w，则一共产生n-w+1个窗口的最大值。请实现一个函数。
 * 输入：整型数组arr，窗口大小为w。● 输出：一个长度为n-w+1的数组res，res[i]表示每一种窗口状态下的最大值
 */
public class MaxValueInWindow {
    public static int[] maxValue(int[] arr, int w){
        if (arr == null || w <= 0 || w > arr.length){
            return null;
        }
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        LinkedList<Integer> qmax = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            while (!qmax.isEmpty() && arr[i] >= arr[qmax.peekLast()]){
                qmax.pollLast();
            }
            qmax.addLast(i);

            if (qmax.peekFirst() == i - w){
                qmax.pollFirst();
            }

            if (i >= w - 1){
                res[index++] = arr[qmax.peekFirst()];
            }
        }
        return res;
    }


    public static int[] maxValueVolice(int[] arr, int w){
        if (arr == null || w <= 0 || w > arr.length){
            return null;
        }
        int index = 0;
        int[] res = new int[arr.length - w + 1];
        int i;
        int j;
        for (i = 0, j = i + w - 1 ; j < arr.length; i++, j++) {
            res[index++] = getMax(arr, i, j);
        }

        return res;
    }

    private static int getMax(int[] arr, int i, int j){
        int max = 0;
        for (int k = i; k <= j; k++) {
            max = Math.max(arr[k], max);
        }
        return max;
    }

    public static int[] minValue(int[] arr, int w){
        if (arr == null || w <= 0 || w > arr.length){
            return null;
        }
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        LinkedList<Integer> qmin = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            while (!qmin.isEmpty() && arr[i] <= arr[qmin.peekLast()]){
                qmin.pollLast();
            }
            qmin.addLast(i);

            if (i - w == qmin.peekFirst()){
                qmin.pollFirst();
            }

            if (i >= w - 1){
                res[index++] = arr[qmin.peekFirst()];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = Tool.generatePositiveRandomArray(1000, 100);
        int[] test = Tool.copyArray(arr);
        int times = 1000;

        int[] res1 = maxValue(test, 5);
        int[] res2 = maxValueVolice(arr, 5);

        boolean success = true;
        for (int i = 0; i < times; i++) {
            if (!Tool.isEqual(res1, res2)){
                success = false;
                break;
            }
        }

        System.out.println(success ? " Nice " : " Fucking");
    }
}
