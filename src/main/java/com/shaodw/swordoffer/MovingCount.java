package com.shaodw.swordoffer;

/**
 * @Auther: shaodw
 * @Date: 2020/1/28 21:54
 * @Description: 回溯法应用
 * 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，
 * 但是不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。
 * 但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
 */
public class MovingCount {
    public int movingCount(int threshold, int rows, int cols){
        if (threshold < 0 || rows <= 0 || cols <= 0)
            return 0;
       // boolean[] visited = new boolean[rows * cols]; 这里用一维数组表示二维有坑 注意
        boolean[][] visited = new boolean[rows][cols];
        return movingCountCore(threshold, rows, cols, 0, 0, visited);
    }

    private int movingCountCore(int threshold, int rows, int cols, int i, int j, boolean[][] visited){
        int path = 0;
        if (i >= 0 && i < rows && j >= 0 && j < cols && getRCSum(i) + getRCSum(j) <= threshold && !visited[i][j]){
            visited[i][j] = true;
           path  =  1
                    + movingCountCore(threshold, rows, cols,i + 1, j, visited)
                    + movingCountCore(threshold, rows, cols,i - 1, j, visited)
                    + movingCountCore(threshold, rows, cols, i,j + 1, visited)
                    + movingCountCore(threshold, rows, cols, i,j - 1, visited);
        }

        return path;
    }

    private int getRCSum(int i){
        int res = 0;
        while (i > 0){
            res += i % 10;
            i /= 10;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new MovingCount().movingCount(6,3,4));
    }
}
