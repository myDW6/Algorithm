package com.shaodw.practice.matrix;

/**
 * @Auther: shaodw
 * @Date: 2020/1/27 11:28
 * @Description: 将方阵顺时针旋转90度
 * 宏观调度思想
 */
public class RotateMatrix {

    public static void rorateMatrix(int[][] matrix){
        int l1 = 0;
        int r1 = 0;
        int l2 = matrix.length - 1;
        int r2 = matrix[0].length - 1;
        while (l1 <= l2 && r1 <= r2){
            rototeEdge(matrix, l1++, r1++, l2--, r2--);
        }
    }

    private static void rototeEdge(int[][] matrix, int l1, int r1, int l2, int r2){
        int times = r2 - r1;
        int tmp = 0;
        for (int i = 0; i < times; i++) {
            tmp = matrix[l1][r1 + i];
            matrix[l1][r1 + i] = matrix[l2 - i][r1];
            matrix[l2 - i][r1] = matrix[l2][r2 - i];
            matrix[l2][r2 - i] = matrix[l1 + i][r2];
            matrix[l1 + i][r2] = tmp;
        }
    }

    private static void printMatrix(int[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,16}
        };

        rorateMatrix(matrix);
        printMatrix(matrix);
    }
}
