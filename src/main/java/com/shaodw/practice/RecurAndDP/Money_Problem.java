package com.shaodw.practice.RecurAndDP;


/**
 * @Auther: shaodw
 * @Date: 2020/2/7 21:00
 * @Description: 给定数组arr 和一个整数aim 可以任意选择arr中的数字 能不能累加得到aim 返回true/false
 */
public class Money_Problem {

    public static boolean isSum(int[] arr, int i, int sum, int aim){
        if (i == arr.length)
            return aim == sum;
        return isSum(arr, i + 1, sum, aim) || isSum(arr, i + 1, sum + arr[i], aim);
    }

    /**
     * 首先该问题是无后效性问题 所以可以进行dp
     * basecase i 来到了 arr.length位置 只有dp[i][aim] 值才为true
     * 否则需要我下一行该位置 || 下一行 该位置 + arr[i]位置
     * @param arr
     * @param aim
     * @return
     */
    public static boolean isSumDP(int[] arr, int aim){
        if (arr == null)
            return false;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        if (aim > sum)
            return false;
        boolean[][] dp = new boolean[arr.length + 1][sum + 1];
        dp[dp.length - 1][aim] = true;

        for (int i = arr.length - 1; i >= 0 ; i--) {
            for (int j = dp[0].length - 2; j >= 0 ; j--) {
                if (j + arr[i] <= dp[0].length - 1){
                    dp[i][j] = dp[i + 1][j] || dp[i + 1][j + arr[i]];
                }
            }
        }
        return dp[0][0];
    }

    private static void printBooleanMatrix(boolean[][] m){
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,2,7,13};
        int aim = 16;
        System.out.println(isSum(arr, 0, 0, aim));
        System.out.println(isSumDP(arr, aim));
    }
}
