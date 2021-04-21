package com.shaodw.practice.unionfind;

/**
 * @Auther: shaodw
 * @Date: 2020/2/4 20:42
 * @Description: 岛问题
 * 一个矩阵中只有0和1两种值，每个位置都可以和自己的上、下、左、右 四个位置相连，如果有一片1连在一起，这个部分叫做一个岛，求一个 矩阵中有多少个岛？
 * 0 0 1 0 1 0
 * 1 1 1 0 1 0
 * 1 0 0 1 0 0
 * 0 0 0 0 0 0
 * 这个矩阵中有三个岛。
 */
public class IsLandNum {
    public static int islandNum(int[][] matrix){
        if (matrix == null) return 0;
        int res = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++){
                if (matrix[i][j] == 1){
                    res++;
                    infect(matrix, i, j);
                }
            }
        }
        return res;
    }

    //感染函数 当前元素若为1 则其相连接的所有的1都会被该函数感染为2
    private static void infect(int[][] matrix, int i, int j){
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length - 1 || matrix[i][j] != 1)
            return;
        matrix[i][j] = 2;
        infect(matrix, i + 1, j);
        infect(matrix, i - 1, j);
        infect(matrix, i ,j + 1);
        infect(matrix, i, j - 1);
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {0,0,1,0,1,0},
                {1,1,1,0,1,0},
                {1,0,0,1,0,0},
                {0,0,0,0,0,0}
        };
        System.out.println(islandNum(matrix));
    }

    /**
     * 这里介绍一种并行算法的思路
     * 基于分治思想
     * 使用并查集来处理边界
     * 0 0 1  1 1 0
     * 1 1 1  0 1 0
     * 1 0 0  0 0 0
     * 0 0 0  0 0 1
     * 基本思路： 分成上述左右两个边界
     * 左边区域一个岛假设其并查集Head为matrix[0][2] 记为A 岛数量加1
     * 右边区域两个岛设置并查集Head分别为matrix[0][3]和matrix[3][5] 为B和C 岛数量+2 为3
     * 对于左右边界 1 1 0 0 和 0 0 1 0只有两个同时为1时调用并查集的isSameSet判断是不是一个中心 若是 岛数量减一 为2 并将两个集合union （对应并查集就是将其中一个集合连接到另一个上）
     * 0 1 或 0 0 则不用管 则最终上述矩阵为两个岛
     */
}
