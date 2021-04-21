package com.shaodw.practice.matrix;

/**
 * @Auther: shaodw
 * @Date: 2020/1/29 21:06
 * @Description: 之”字形打印矩阵
 * 给定一个矩阵matrix，按照“之”字形的方式打印这个矩阵，例如：
 * 1  2  3  4
 * 5  6  7  8
 * 9 10 11 12 “之”字形打印的结果为：1，2，5，9，6，3，4，7，10，11， 8，12 【要求】 额外空间复杂度为O(1)。
 */
public class ZigZagPrintMatrix {
    public static void printZigZagMatrix(int[][] matrix){
        int lr = 0;
        int lc = 0;
        int rr = 0;
        int rc = 0;
        int rEnd = matrix.length - 1;
        int cEnd = matrix[0].length - 1;
        boolean fromUp = false;
        while (lc != cEnd + 1){
            /**
             *  这里的结束条件必须是先动的那个 不然会数组越界
             *  lc != cEnd + 1正确 rr != rEnd + 1正确
             *  但是 lr != rEnd + 1 或 rc != cEnd + 1就会数组越界
             */
            printLevel(matrix, lr, lc, rr, rc, fromUp);
            lc = lr == rEnd ? lc + 1 : lc;
            lr = lr == rEnd ? lr : lr + 1;
            /**
             *  这里要注意 lc和lr的求法位置 不能颠倒
             */
            rr = rc == cEnd ? rr + 1 : rr;
            rc = rc == cEnd ? rc : rc + 1;
            fromUp = !fromUp;
        }
    }

    //给定两个点的横纵坐标 以及一个控制方向变量 打印对角线 left row / right col
    private static void printLevel(int[][] matrix, int lr, int lc, int rr, int rc, boolean fromUp){
        if (fromUp){
            while (rr <= lr){
                System.out.print(matrix[rr++][rc--] + " ");
            }
        }else {
            while (lr >= rr){
                System.out.print(matrix[lr--][lc++] + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9,10,11,12}
        };
        printZigZagMatrix(matrix);
    }
}
