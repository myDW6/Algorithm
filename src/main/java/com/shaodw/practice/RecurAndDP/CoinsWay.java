package com.shaodw.practice.RecurAndDP;

import java.util.HashMap;

/**
 * @Auther: shaodw
 * @Date: 2020/2/26 20:29
 * @Description: 换钱的方法数
 * 给定数组arr，arr中所有的值都为正数且不重复。每个值代表 一种面值的货币，每种面值的货币可以使用任意张，再给定一个整数aim代表要找的钱数，求换钱有多少种方法。
 *
 * arr=[5,10,25,1]，aim=0。 组成0元的方法有1种，就是所有面值的货币都不用。所以返回1。
 * arr=[5,10,25,1]，aim=15。 组成15元的方法有6种，分别为3张5元、1张10元+1张5元、1张 10元+5张1元、10张1元+1张5元、2张5元+5张1元和15张1元。所 以返回6。
 * arr=[3,5]，aim=2。 任何方法都无法组成2元。所以返回0。
 */
public class CoinsWay {

    /**
     * 从暴力递归到动态规划的核心：先写出尝试的递归版本 再搭积木般写出动态规划
     */

    /**
     * 尝试的思路是啥？
     * arr = {200, 100, 50, 20, 5, 1}       aim = 1000
     * 使用0张200 后续搞出1000 方法数为a
     * 使用1张200 后续搞出800 方法数为b
     * 使用2张200 后续搞出600 方法数为c
     * 使用3张200 后续搞出400 方法数为d
     * 使用4张200 后续搞出200 方法数为e
     * 使用5张200 后续搞出0 方法数为f
     * a+b+c+d+e+f即为答案
     */

    /**
     * 采用上面的试法有了我们的暴力递归版本
     */
    public static int coins_recur(int[] arr, int aim){
        if (arr == null || arr.length == 0 || aim < 0){
            return 0;
        }
        return process_recur(arr, 0, aim);
    }

    /**
     *
     * @param arr 钱的数组,不变
     * @param index 表示index及其之后位置的钱可以任意自由使用
     * @param aim 目标钱数
     * @return 方法数
     */
    private static int process_recur(int[] arr, int index, int aim){
        int res = 0;
        if (index == arr.length){
            return aim == 0 ? 1 : 0;
        }
        for (int i = 0; arr[index] * i <= aim; i++) {//i代表张数
            res += process_recur(arr, index + 1, aim - arr[index] * i); //index+1 当前货币已经考虑过了 做出选择了 下面的货币能自由使用 你去选择
        }
        return res;
    }

    /**
     * 上面存在的最大问题就是大量的重复计算
     * arr = {200, 100, 50, 20, 5, 1}       aim = 1000
     * 2张200 从50开始后面的货币自由选择  需要搞定的钱数都是600
     * 1张200 2张100 从50开始后面的货币自由选择   需要搞定的钱数都是600
     * 4张100 从50开始后面的货币自由选择   需要搞定的钱数都是600
     * 上述返回值都是一样 但问题是又重新计算了几遍
     *
     * 那么对于这种无后效性问题
     * 不管你前面是2张200    1张200 2张100 还是4张100的 选的这些不影响我搞定50及其后面的钱搞定600的问题 返回值都一样
     * 即当你到达一个状态 这个状态和你到达它的路径没有关系  返回值和怎么到达的没有关系  这即是无后效性问题
     *
     * 第一个优化版本：分析哪些参数一旦固定 返回值即固定
     * index和aim一旦固定 返回值即确定
     * 那么使用map来记录index和aim 下次需要时直接在map中取返回值即可
     * @param
     */

    public static int coins_map(int[] arr, int aim){
        if (arr == null || arr.length == 0 || aim < 0){
            return 0;
        }
        return process_map(arr, 0, aim);
    }
    public static HashMap<String, Integer> map = new HashMap<>();  //map相当于一个缓存 任何一个子过程两个参数代表了这个状态 两个参数确定 返回值也固定
    private static int process_map(int[] arr, int index, int aim){
        int res = 0;
        if (index == arr.length){
            return aim == 0 ? 1: 0;
        }
        for (int i = 0; i * arr[index] <= aim; i++){
            int newAim = aim - i * arr[index];
            String key = (index + 1) + "_" + newAim;//生成下一次递归的key
            res += map.containsKey(key) ? map.get(key) : process_map(arr, index + 1, newAim);
        }
        map.put(index + "_" + aim, res);//返回前将计算结果写进map
        return res;
    }

    /**
     * 上述方法叫记忆化搜索
     * 下一步推出动态规划
     * 参数的变化可以得出所有返回值的变化 参数有index和aim两个  那么如果我使用一张二维表  index从上之下 aim从左到右
     * index从0变化到arr.length-1
     * aim从0变化到aimmax 就是最大的aim值
     *   0 1 2 3 4 5 6 7 8 .. aimmax
     * 0
     * 1
     * 2
     * 3
     * ..
     * N
     * 将返回值填进二维表
     * 那么我们开始填表过程：
     * 先看目标：主函数需要的是0和aim状态  表示第0行最后一列的值即是最终返回值
     * return process_recur(arr, 0, aim);
     *   0 1 2 3 4 5 6 7 8 .. aimmax
     * 0                       ?
     * 1
     * 2
     * 3
     * ..
     * N
     * 再看这张表中哪些位置的值是直接确定的 不依赖任何其他位置 即递归的basecase
     * if (index == arr.length){return aim == 0 ? 1 : 0;}
     * 当index=n时 值确定   即最后一行 aim等于0时为1 其他位置全是0
     *   0 1 2 3 4 5 6 7 8 .. aimmax
     * 0
     * 1
     * 2
     * 3
     * ..
     * N 1 0 0 0 0 0 0 0 0 .. 0
     * 再看位置依赖 怎么调用递归的
     * res += process_recur(arr, index + 1, aim - arr[index] * i)
     * 假设x是(index, aim)位置 看这个位置的值需要哪些位置
     * int process_recur(int[] arr, int index, int aim){
     * res += process_recur(arr, index + 1, aim - arr[index] * i); }
     * 可知(index,aim)需要(index+1, aim-arr[index]*i)   也就是我需要我下一行的某些值
     * 哪些值呢？即是你的当前aim减去一张这个面值的位置 减去两张这个面值的位置 减去n张这个面值的位置 一直到越界 对应填表如下
     *    0 1 2 3 4 5 6 7 8 .. aimmax
     * 0
     * 1
     * 2          y   x
     * 3  x4 x3  x2  x1
     * ..
     * N 1 0 0 0 0 0 0 0 0 .. 0
     * 利用最后一行 推出倒数第二行 推出倒数第三行 ... 推出终点位置 就是动态规划
     *
     * 由于我们填表过程为从走到右 自底向上 那么求x之前一定会先求得之前某个y位置 那么x的值不需要再累加x1 x2 x3 x4而是可以优化成 x1+y
     */

    /**
     * 将填表过程转为代码
     * @param arr
     * @param aim
     * @return
     */
    public static int coins_dp1(int[] arr, int aim){
        if (arr == null || arr.length == 0 || aim < 0){
            return 0;
        }
        int[][] dp = new int[arr.length + 1][aim + 1];
        for (int i = 0; i <= arr.length; i++){
            dp[i][0] = 1;
        }
        for (int i = arr.length - 1; i >= 0; i--){
            for (int j = 1; j <= aim; j++) {
                int num = 0;
                for (int k = 0; k * arr[i] <= j ; k++) {
                    num += dp[i + 1][j - k * arr[i]];
                }
                dp[i][j] = num;
            }
        }
        return dp[0][aim];
    }

    /**
     * 时间优化 dp[i][j] = dp[i+1][j] + dp[i][j - arr[i]]
     */
    public static int coins_dp2(int[] arr, int aim){
        if (arr == null || arr.length == 0 || aim < 0){
            return 0;
        }
        int[][] dp = new int[arr.length + 1][aim + 1];
        for (int i = 0; i <= arr.length; i++){
            dp[i][0] = 1;
        }
        for (int i = arr.length - 1; i >= 0; i--){
            for (int j = 1; j <= aim; j++) {
                dp[i][j] = dp[i + 1][j];
                dp[i][j] += j - arr[i] >= 0 ? dp[i][j - arr[i]] : 0;
            }
        }
        return dp[0][aim];
    }

    /**
     * 另一种递归版本改出的动态规划
     * @param arr
     * @param aim
     * @return
     */
    public static int coins_dp1_othter(int[] arr, int aim){
        if (arr == null || arr.length == 0 || aim < 0){
            return 0;
        }
        int[][] dp = new int[arr.length][aim + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; arr[0] * j <= aim; j++){
            dp[0][j * arr[0]] = 1;
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                int num = 0;
                for (int k = 0; j - arr[i] * k >= 0; k++) {
                    num += dp[i - 1][j - arr[i] * k];
                }
                dp[i][j] = num;
            }
        }
        return dp[dp.length - 1][aim];
    }

    public static int coins_dp2_othter(int[] arr, int aim){
        if (arr == null || arr.length == 0 || aim < 0){
            return 0;
        }
        int[][] dp = new int[arr.length][aim + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; j * arr[0] <= aim; j++){
            dp[0][j * arr[0]] = 1;
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j] = dp[i - 1][j];
                dp[i][j] += j - arr[i] >= 0 ? dp[i][j - arr[i]] : 0;
            }
        }
        return dp[dp.length - 1][aim];
    }


    public static int coins_Recur_other(int[] arr, int aim){
        if (arr == null || arr.length == 0 || aim < 0){
            return 0;
        }
        return processOtherRecur(arr, arr.length - 1, aim);
    }
    private static int processOtherRecur(int[] arr, int index, int aim){
        int res = 0;
        if (index == -1){
            return aim == 0 ? 1 : 0;
        }
        for (int i = 0; arr[index] * i <= aim; i++) {
            res += processOtherRecur(arr, index - 1, aim - arr[index] * i);
        }
        return res;
    }


    public static void main(String[] args) {
        int[] arr = {1,2,5};
        int aim = 10;
        long start = System.nanoTime();
        System.out.println(coins_recur(arr, aim));
        long end = System.nanoTime();
        System.out.println("recur cost : " + (end - start) / 1000000000.0 + " s");

        start = System.nanoTime();
        System.out.println(coins_map(arr, aim));
        end = System.nanoTime();
        System.out.println("map cost : " + (end - start) / 1000000000.0 + " s");

        //aim from 2000 -> 20000
        aim = 20000;
        start = System.nanoTime();
        System.out.println(coins_dp1(arr, aim));
        end = System.nanoTime();
        System.out.println("dp cost : " + (end - start) / 1000000000.0 + " s");

        start = System.nanoTime();
        System.out.println(coins_dp2(arr, aim));
        end = System.nanoTime();
        System.out.println("better dp cost : " + (end - start) / 1000000000.0 + " s");
    }

}
