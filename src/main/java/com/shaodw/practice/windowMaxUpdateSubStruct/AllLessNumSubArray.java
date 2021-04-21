package com.shaodw.practice.windowMaxUpdateSubStruct;

import java.util.LinkedList;

/**
 * @Auther: shaodw
 * @Date: 2020/2/18 10:43
 * @Description: 最大值减去最小值小于等于num的子数组的数量
 * 数组长度为N 实现复杂度为O(N)的解法
 */
public class AllLessNumSubArray {


    /**
     * O(N)解法 使用窗口内最大值最小值的更新结构
     * 1： 如果一个子数组l-r达标（max - min <= num） 其内部任意子数组都达标
     * 2： 如果一个子数组l-r不达标 往外任意阔都不达标
     */

    /**
     * 让窗口l停留在最左 r往右阔 阔到再往下阔一个不达标 停  窗口内最大值使用窗口内最大值的更新结构获取 最小值使用窗口内最小值的更新结构获取
     * 0 -> x范围达标 那么以0开头的子数组个数为x+1个  0开头到x+1不达标 以后的位置都不达标
     * 窗口缩一个位置 l往右移一位 然后计算1-> x范围达标的子数组数量
     * l每缩以下 r往右走 直到l走完所有
     */
    public static int getSubArrayNumByWindow(int[] arr, int num){
        if (arr == null || arr.length == 0){
            return 0;
        }
        int l = 0;
        int r = 0;
        int res = 0;
        LinkedList<Integer> maxQueue = new LinkedList<>();
        LinkedList<Integer> minQueue = new LinkedList<>();
        for (; l < arr.length; l++){
            for (; r < arr.length; r++){
                //窗口内最大值最小值加数逻辑
                while (!maxQueue.isEmpty() && arr[r] >= arr[maxQueue.peekLast()]){
                    maxQueue.pollLast();
                }
                maxQueue.addLast(r);

                while (!minQueue.isEmpty() && arr[r] <= arr[minQueue.peekLast()]){
                    minQueue.pollLast();
                }
                minQueue.addLast(r);

                //不达标 开始统计
                if (arr[maxQueue.peekFirst()] - arr[minQueue.peekFirst()] > num){
                    break;
                }
            }

            //两个头节点代表的值是否过期 过期需要弹出 缩小窗口
            if (maxQueue.peekFirst() == l){
                maxQueue.pollFirst();
            }
            if (minQueue.peekFirst() == l){
                minQueue.pollFirst();
            }

            //此时r = r + 1了 因为r多走了一步再判断
            res += r - l;
        }
        return res;
    }



    /**
     * 暴力解法子数组数量为
     * 0->0 0->1 0->2 0->N-1  N
     * 1->1 1->2 1->3 1->N-1  N - 1
     * 2->2 2->3 2->4 2->N-1  N - 2
     * O(N * N)
     */

    public static int getSubArrayNumViolence(int[] arr, int num){
        if (arr == null || arr.length == 0){
            return 0;
        }
        int res = 0;
        for (int i = 0; i < arr.length; i++){
            for (int j = i; j < arr.length; j++){
                if (isValid(arr, i, j, num)){
                    res++;
                }
            }
        }
        return res;
    }

    private static boolean isValid(int[] arr, int l, int r, int num){
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = l; i <= r; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
        }
        return max - min <= num;
    }

    public static void main(String[] args) {
        int[] arr ={2,1,3};
        System.out.println(getSubArrayNumViolence(arr, 2));
        System.out.println(getSubArrayNumByWindow(arr, 2));
    }
}
