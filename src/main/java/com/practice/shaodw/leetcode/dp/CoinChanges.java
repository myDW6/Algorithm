package com.practice.shaodw.leetcode.dp;

/**
 * @author shaodw
 * @date 2021/5/14 09:57
 * @description
 * 给你 k 种面值的硬币，面值分别为 c1, c2 ... ck，每种硬币的数量无限，再给一个总金额 amount，
 * 问你最少需要几枚硬币凑出这个金额，如果不可能凑出，算法返回 -1 。
 */
public class CoinChanges {

    // coins 中是可选硬币面值，amount 是目标金额
    //1 2 5 凑出10
    //使用0枚1硬币 问题-> 1 2 5凑出 10 - 1 * 0
    //使用1枚1硬币 问题-> 1 2 5凑出 10 - 1 * 1
    //使用2枚1硬币 问题-> 1 2 5凑出 10 - 1 * 2
    //使用3枚1硬币 问题-> 1 2 5凑出 10 - 1 * 3
    //使用i枚1硬币 问题-> 1 2 5凑出 10 - 1 * i;
    //使用1枚2硬币 问题-> 2 5 凑出 10 - 2
    //使用2枚2硬币 问题-> 2 5 凑出 10 - 2 * 2
    static int coinChange(int[] coins, int amount){
        if (coins == null || amount < 0 ){
            return -1;
        }
        return process(coins, 0, amount);
    }

    static int min = Integer.MAX_VALUE;

    private static int process(int[] coins, int index, int amount) {
        if (index == coins.length){
            return amount == 0 ? 0 : -1;
        }

        for (int i = 0; i * coins[index] <= amount; i++) {
            int sub = process(coins, index + 1, amount - i * coins[index]);
            if (sub == -1){
                continue;
            }
            min = Math.min(sub + 1, min);
        }
        return min;
    }


    public static void main(String[] args) {
        int[] arr = {1,2,5};
        int amount = 10;
        System.out.println(coinChange(arr,amount ));
    }
}
