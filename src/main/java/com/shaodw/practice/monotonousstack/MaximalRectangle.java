package com.shaodw.practice.monotonousstack;

import java.util.Stack;

/**
 * @Auther: shaodw
 * @Date: 2020/2/19 14:58
 * @Description: 给定一个整型矩阵map 其中的值只有0 1 求其中所有全是1的所有矩形区域中 最大的矩形区域为1的数量
 * 1 1 1 0
 * 最大的矩形区域为 1 1 1  3个1返回3
 * 1 0 1 1
 * 1 1 1 1
 * 1 1 1 0
 * 最大的矩形区域有6个1 返回6
 */
public class MaximalRectangle {


    /**
     * O(M * N)
     *
     * 对每一行行成的数组 都看成以该行为底形成的矩形
     * 第1行 1 0 1 1
     * 第2行 2 1 2 2
     * 第3行 3 2 3 0
     * 每一行都去跑那个函数一次
     * @param map
     * @return
     */
    public static int maxRecSize(int[][] map){
        if (map == null || map.length <= 0 || map[0].length <= 0){
            return 0;
        }
        int maxArea = 0;
        int[] height = new int[map[0].length];//每一列作为底
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                height[j] = map[i][j] == 0 ? 0 : height[j] + 1; //当前位置为0 则是0 否则为之前数+1
            }
            maxArea = Math.max(maxRecFromBottom(height), maxArea); //以每一行作为底的直方图都计算一遍
        }
        return maxArea;
    }

    //假如一个数组代表直方图 返回该数组代表的直方图形成的矩形最大面积
    public static int maxRecFromBottom(int[] height){
        if (height == null || height.length <= 0){
            return 0;
        }
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();//维护一个单调递减栈

        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] <= height[stack.peek()]){
                int j = stack.pop();//结算 弹出的数j  i是当前遍历到的数 是因为i使得你弹出 所以i是右边界
                int left = stack.isEmpty() ? -1 : stack.peek();//左边界 当一个数弹出后 它底下没有数 那就是-1 否则就是底下那个数代表的下标
                int curArea = (i - left - 1) * height[j];
                maxArea = Math.max(maxArea, curArea);
            }
            stack.push(i);
        }

        //结算栈中剩余的数
        while (!stack.isEmpty()){
            int j = stack.pop();
            int left = stack.isEmpty() ? -1 : stack.peek();
            int curArea = (height.length - left - 1) * height[j];
            maxArea = Math.max(maxArea, curArea);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[][] map = {
                {1,0,1,1},
                {1,1,1,1},
                {1,1,1,0}
        };
        System.out.println(maxRecSize(map));
    }
}
