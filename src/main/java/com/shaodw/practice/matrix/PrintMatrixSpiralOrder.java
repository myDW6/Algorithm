package com.shaodw.practice.matrix;

/**
 * @Auther: shaodw
 * @Date: 2020/1/26 18:09
 * @Description: 。顺时针旋转打印矩阵
 * 宏观调度思想
 */
public class PrintMatrixSpiralOrder {

    public static void spiralOrderPrint(int[][] matrix){
        int l1 = 0;
        int r1 = 0;
        int l2 = matrix.length - 1;
        int r2 = matrix[0].length - 1;
        while (l1 <= l2 && r1 <= r2){
            printEdge(matrix, l1++, r1++, l2--, r2--);
        }

    }

    //左上角和右下角坐标 顺时针打印
    private static void printEdge(int[][] matrix, int l1, int r1, int l2, int r2){
        if (l1 == l2){
            //一列
            for (int i = r1; i <= r2 ; i++) {
                System.out.print(matrix[l1][i] + " ");
            }
        }else if (r1 == r2){
            //一行
            for (int i = l1; i <= l2 ; i++) {
                System.out.print(matrix[i][r1] + " ");
            }
        }else {
            //正常矩阵形状
            int curR = r1;
            int curL = l1;
            while (curR < r2){
                System.out.print(matrix[curL][curR++] + " ");
            }

            while (curL < l2){
                System.out.print(matrix[curL++][curR] + " ");
            }

            while (curR > r1){
                System.out.print(matrix[curL][curR--] + " ");
            }

            while (curL > l1){
                System.out.print(matrix[curL--][curR] + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                { 1, 2, 3, 4, 5, 5},
                { 5, 6, 7, 8, 6,7 },
                { 9, 10, 11, 12,5,1 },
                { 13, 14, 15, 16,1, 1 }
        };

        spiralOrderPrint(matrix);
    }
}
