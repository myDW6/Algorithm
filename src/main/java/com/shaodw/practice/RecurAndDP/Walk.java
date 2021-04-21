package com.shaodw.practice.RecurAndDP;

/**
 * @Auther: shaodw
 * @Date: 2020/2/27 11:25
 * @Description: 给定1-N范围 机器人初始停在M位置 可以走P步 如果机器人在1位置 只能往右走 如果机器人在N位置 只能往走左 其余位置既可以往左也可以往右
 * 问机器人走P步后停在K位置的情况有多少种
 */
public class Walk {
    public static int ways(int N, int M, int P, int K){
        if (N < 2 || M < 1 || M > N || P < 0 || K < 1 || K > N){
            return 0;
        }
        return walk(N, M, P, K);
    }

    private static int walk(int N, int M, int P, int K){
        if (P == 0){
            return M == K ? 1 : 0;
        }
        if (M == 1){
            return walk(N, M + 1, P - 1, K);//碰边了依赖一边
        } else if (M == N){
            return walk(N, M - 1, P - 1, K);
        } else {
            return walk(N, M + 1, P - 1, K) + walk(N, M - 1, P - 1, K);//杨辉三角
        }
    }

    public static int walk_dp(int N, int M, int P, int K){
        if (N < 2 || M < 1 || M > N || P < 0 || K < 1 || K > N){
            return 0;
        }
        int[][] dp = new int[P + 1][N];
        for (int j = 0; j < N; j++) {
            dp[0][j] = j == K ? 1 : 0;
        }
        for (int i = 1; i <= P; i++) {
            for (int j = 0; j < N; j++) {
                if (j == 0){
                    dp[i][j] = dp[i - 1][j + 1];
                }else if (j == N - 1){
                    dp[i][j] = dp[i - 1][j - 1];
                }else {
                    dp[i][j] = dp[i - 1][j + 1] + dp[i - 1][j - 1];
                }
            }
        }
        return dp[P][M];
    }

    private static int another(int N, int M, int P, int K){
        if (N < 2 || M < 1 || M > N || P < 0 || K < 1 || K > N){
            return 0;
        }
        if (P == 0){
            return M == K ? 1 : 0;
        }
        int res = 0;
        if (M == 1){
            res += walk(N, M + 1, P - 1, K);
        } else if (M == N){
            res += walk(N, M - 1, P - 1, K);
        } else {
            res+= walk(N, M + 1, P - 1, K) + walk(N, M - 1, P - 1, K);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(ways(4, 2, 2, 2));
        System.out.println(another(4, 2, 2, 2));
        System.out.println(walk_dp(4, 2, 2, 2));
    }
}
