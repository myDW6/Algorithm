package com.shaodw.practice.greedy;

import java.util.PriorityQueue;

/**
 * @Auther: shaodw
 * @Date: 2020/2/5 14:59
 * @Description:
 * 参数1,正数数组costs
 * 参数2,正数数组profits
 * 参数3,正数k
 * 参数4,正数m
 * costs[i]表示i号项目的花费 profits[i]表示i号项目在扣除花 费之后还能挣到的钱(利润) k表示你不能并行、只能串行的最多 做k个项目 m表示你初始的资金
 * 说明：你每做完一个项目，马上获得的收益，可以支持你去做下 一个 项目。
 * 输出： 你最后获得的最大钱数。
 */
public class
IPO {
    /**
     * 贪心策略：在收益能支持你做某个项目的情况下 选择收益最大的做
     */

    /**
     * 使用一个大根堆和一个小根堆 先将所有项目按花费加入小根堆 如果当前资金m能支持做该项目 弹出小根堆堆顶 进利润大根堆 每次取
     * 大根堆中堆顶项目做 当大根堆没有元素或已经做了k个项目 完成
     */

    public static class Node{
        int c;//cost
        int p;//profit

        Node(int c, int p){
            this.c = c;
            this.p = p;
        }
    }

    public static int findMaximizedCapital(int k, int W, int[] profits, int[] capital){
        Node[] nodes = new Node[profits.length];
        for (int i = 0; i < profits.length; i++) {
            nodes[i] = new Node(capital[i], profits[i]);
        }

        PriorityQueue<Node> lessCost = new PriorityQueue<>((a,b)->a.c - b.c);
        PriorityQueue<Node> moreProfit = new PriorityQueue<>((a,b)->b.p - a.p);

        for (Node node : nodes){
            lessCost.add(node);
        }

        for (int i = 0; i < k; i++) {
            while (!lessCost.isEmpty() && lessCost.peek().c <= W){
                moreProfit.add(lessCost.poll());
            }
            if (moreProfit.isEmpty()){
                return W;
            }
            W += moreProfit.poll().p;
        }
        return W;
    }

    public static void main(String[] args) {
        int k = 3;
        int W = 5;
        int[] capital = {7,2,3,5,9};
        int[] profits = {3,1,2,3,4};
        System.out.println(findMaximizedCapital(k, W, profits, capital));
    }
}
