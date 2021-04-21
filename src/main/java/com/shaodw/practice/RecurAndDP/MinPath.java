package com.shaodw.practice.RecurAndDP;

/**
 * @Auther: shaodw
 * @Date: 2020/2/7 13:49
 * @Description: 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 */
public class MinPath {
    public static int minPathSumRec(int[][] grid) {
        return grid == null ? 0 : walk(grid, 0, 0);
    }

    //表示从i j位置走到矩阵右下角的最短路径和
    private static int walk(int[][] grid, int i, int j){
        int res = grid[i][j];
        if (i == grid.length - 1 && j == grid[0].length - 1)
            return res;
        if (i == grid.length - 1){
            return res + walk(grid, i, j + 1);
        }
        if (j == grid[0].length - 1){
            return res + walk(grid, i + 1, j);
        }
        return res + Math.min(walk(grid, i + 1, j), walk(grid, i, j + 1));
    }

    public static int minPathSumDP(int[][] grid){
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0)
            return 0;
        int N = grid.length;
        int M = grid[0].length;
        int[][] dp = new int[N][M];
        dp[N - 1][M - 1] = grid[N - 1][M - 1];
        for (int j = M - 2; j >= 0; j--) {
            dp[N - 1][j] = dp[N - 1][j + 1] + grid[N - 1][j];
        }

        for (int i = N - 2; i >= 0; i--) {
            dp[i][M - 1] = dp[i + 1][M - 1] + grid[i][M - 1];
        }

        for (int i = M - 2; i >= 0; i--){
            for (int j = N - 2; j >= 0 ; j--) {
                dp[i][j] = Math.min(dp[i][j + 1], dp[i + 1][j]) + grid[i][j];
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1,2,5},
            {3,2,1}
        };
        System.out.println(minPathSumRec(matrix));
        System.out.println(minPathSumDP(matrix));
        System.out.println(minPath1(matrix));
        System.out.println(minPath1DP(matrix));
    }

    public static int minPath1(int[][] matrix){
        return process(matrix, matrix.length - 1, matrix[0].length - 1);
    }

    //从m[0][0]走到m[i][j]位置的最小路径和
    private static int process(int[][] matrix, int i, int j){
        int res = matrix[i][j];
        if (i == 0 && j == 0){
            return res;
        }
        if (i == 0){
            return res + process(matrix, i, j - 1);
        }
        if (j == 0){
            return res + process(matrix, i - 1, j);
        }
        return res + Math.min(process(matrix, i - 1, j), process(matrix, i, j - 1));
    }

    //dp数组保存所有从0 0 位置到i j位置的最小路径和
    public static int minPath1DP(int[][] m){
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0)
            return 0;
        int row = m.length;
        int col = m[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = m[0][0];
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + m[i][0];
        }
        for (int j = 1; j < col; j++){
            dp[0][j] = dp[0][j - 1] + m[0][j];
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = m[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[row - 1][col - 1];
    }
}
