package com.shaodw.practice.windowMaxUpdateSubStruct;

/**
 * 窗口：一个数组 l r代表边界 r往右移动 一个数进窗口 r不能回退 l往右走 窗口左边减数 l不能到r右边
 * 形成窗口 就会有最值  生成一种结构 很便捷的得到窗口的最值 而不用经历遍历的代价
 * 窗口内最大值或最小值的更新结构（单调双向队列）
 * 使用双向链表 如果要求窗口内的最大值 要保证双向链表从头到尾依次变小 若进来一个数 比尾部小 直接落在尾部
 * 若大于等于尾部的数 从尾部开始弹出数 直到能落在尾部后面或者弹为空  双向链表的头部就是窗口内的最大值
 * 窗口减数的逻辑：l往右动了 检查双端队列的头部的index是否过期  若过期 从头部弹出
 *
 */

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @Auther: shaodw
 * @Date: 2020/2/17 08:45
 * @Description:  在一个整型数组arr和一个大小为w的窗口从数组的最左边滑到最右边，窗口每次向右滑动一个位置
 * 实现一个函数 输入整型数组arr和窗口大小w
 * 输出一个长度为n-w+1个窗口的最大值
 */
public class MaxArrayInWindow {
    public static int[] getMaxWindow(int[] arr, int w){
        if (arr == null || w < 1 || arr.length < w)
            return null;
        LinkedList<Integer> qmax = new LinkedList<>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for (int i = 0; i < arr.length; i++){
            //加数逻辑
            while (!qmax.isEmpty() && arr[i] >= arr[qmax.peekLast()]){
                qmax.pollLast();
            }
            qmax.addLast(i);

            //先形成窗口 然后每次加一个减一个 这个if表示还没有形成大小为w的窗口 头部就还没过期
            if (qmax.peekFirst() == i - w){
                qmax.pollFirst();
            }

            //窗口形成就开始收集当前的最大值
            if (i >= w - 1){
                res[index++] = arr[qmax.peekFirst()];//头节点的下标代表的值
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {4,3,5,4,3,3,6,7};
        Arrays.stream(getMaxWindow(arr, 3)).forEach(System.out::print);
    }
}
