package com.shaodw.leetcode;

/**
 * @author shaodw
 * @date 2021/1/25 20:54
 * @description 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
 * 输入: 3
 * 输出:
 * [
 *  [ 1, 2, 3 ],
 *  [ 8, 9, 4 ],
 *  [ 7, 6, 5 ]
 * ]
 */
public class _59_Spiral_Matrix_ii {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int startX = 0, startY = 0;
        int loop = n / 2; //n = 3就转1次  n = 4就转2次  n=5转2次
        int center = n / 2;//中心位置  n = 3  [1,1]  n = 5 [2,2]奇数时有效
        int val = 1; //赋值
        int offset = 1; //每一圈循环，需要控制每一条边遍历的长度

        int i,j;
        while (loop-- > 0){
            i = startX;
            j = startY;
            for (j = startY; j < startY + n - offset; j++){
                res[i][j] = val++;
            }
            for (i = startX; i < startX + n - offset; i++){
                res[i][j] = val++;
            }
            for (; j > startY; j--){
                res[i][j] = val++;
            }
            for (; i > startY; i--){
                res[i][j] = val++;
            }

            startX++;
            startY++;

            offset += 2;
        }

        if ((n & 1) != 0){
            res[center][center] = val;
        }
        return res;
    }

//思路 收缩边界

    public int[][] generateMatrix_another(int n){
        int[][] res = new int[n][n];
        int initVal = 1;
        int leftX = 0;
        int leftY = 0;
        int rightX = res.length - 1;
        int righty = res[0].length - 1;
        while (leftX <= rightX && leftY <= righty){
            initVal = generateEdge(res, leftX++, leftY++, rightX--, righty--, initVal);
        }
        //奇数
        if ((n & 1) != 0){
            res[n>>1][n>>1] = initVal;
        }
        return res;
    }

    //处理一个圈
    int generateEdge(int[][] matrix, int leftX, int leftY, int rightX, int rightY, int initVal){
        int i = leftX;
        int j = leftY;

        while (j <= rightY - 1){
            matrix[i][j++] = initVal++;
        }
        while (i <= rightX - 1){
            matrix[i++][j] = initVal++;
        }
        while (j > leftY){
            matrix[i][j--] = initVal++;
        }
        while (i > leftX){
            matrix[i--][j] = initVal++;
        }
        return initVal;
    }

    public static void main(String[] args) {
        _59_Spiral_Matrix_ii spiral_matrix_ii = new _59_Spiral_Matrix_ii();
        int n = 4;
        int[][] matrix1 = spiral_matrix_ii.generateMatrix(n);
        printMatrix(matrix1);
        System.out.println("------");
        int[][] matrix2 = spiral_matrix_ii.generateMatrix_another(n);
        printMatrix(matrix2);
    }

    static void printMatrix(int[][] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

}
