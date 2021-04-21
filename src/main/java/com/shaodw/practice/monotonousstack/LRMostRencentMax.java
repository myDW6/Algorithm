package com.shaodw.practice.monotonousstack;

import java.util.Stack;

/**
 * @Auther: shaodw
 * @Date: 2020/2/18 13:38
 * @Description: 单调栈的使用场景 一个数组中 所有的数左边离他最近的和右边离他最近的比他大数是啥  在O(N)内解决
 */
public class LRMostRencentMax {

    /**
     * 维护一个栈 使得栈底到栈顶从大到小
     * 5 4 3 6 5 3
     * left cur right
     * null 5  6
     * 5    4  6
     * 4    3  6
     * null 6 null
     * 6    5 null
     * 5    3 null
     * 第一个数5直接进栈  0位置5
     * 1位置4 可以直接进栈
     * 2位置3 可以直接进栈
     * 3位置6 不能直接进栈 栈顶弹出 记录信息 谁让它弹出的谁就是其右边离它最近的比他大的数  所以3右边离他最近的是6 左边是3底下的那个数 4
     * 现在栈为5 4  3位置6还是不能落在进栈 栈顶4弹出 记录4是因为6弹出 6是4右边离它最近的比它大的数 4左边离它最近的比他大的就是它压得那个数5
     * 现在栈为5  5弹出 5右边6 5底下没值  5左边离他最近的比他大的为空
     * 6进栈  5进栈 3进栈 数组遍历完毕 接着处理栈
     * 3不因为谁而弹出 仅仅是因为数组遍历完毕 所以3右边为空 左边为底下的5
     * 5右边空 左边为地下的6
     * 6左边空 右边空
     *
     *
     * 特殊情况 相等时候 5 5 6
     * 将下标压在一起 入栈时 0 1位置一起都是5
     * 6使得 0 1一起弹出 弹出时一起结算
     * 1位置右边是6 左边null
     * 0位置右边是6 左边null
     */
    //这里假设不存在连续两个相等的数
    public static Node[] getLRMostRecntMax(int[] arr){
        if (arr == null || arr.length == 0){
            return null;
        }
        Node[] res = new Node[arr.length];
        int index = 0;
        Stack<Integer> bigStack = new Stack<>();
        bigStack.push(0);
        for (int i = 1; i < arr.length; i++) {
            while (!bigStack.isEmpty() && arr[i] > arr[bigStack.peek()]){
                int cur = bigStack.pop();
                res[index++] = new Node(cur, bigStack.size() == 0 ? null : bigStack.peek(), i);
            }
            bigStack.push(i);
        }

        while (!bigStack.isEmpty()){
            int cur = bigStack.pop();
            res[index++] = new Node(cur, bigStack.size() == 0 ? null : bigStack.peek(), null);
        }
        return res;
    }

    private static class Node{
        Integer left;
        Integer right;
        Integer cur;

        Node( Integer cur, Integer left, Integer right){
            this.left = left;
            this.right = right;
            this.cur = cur;
        }
    }

    public static void main(String[] args) {
        int[] arr = {5,4,3,6,5,3};
        Node[] res = getLRMostRecntMax(arr);
        for (Node r : res){
            System.out.println(r.left + " <- " + r.cur + " -> " +r.right);
        }
    }
}
