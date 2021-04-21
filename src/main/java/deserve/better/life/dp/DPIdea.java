package deserve.better.life.dp;

/**
 * @author shaodw
 * @date 2020/12/25 13:12
 * @description 动态规划套路
 */
public class DPIdea {
    //首先，动态规划问题的一般形式就是求最值
    //既然是要求最值，核心问题是什么呢？求解动态规划的核心问题是穷举。因为要求最值，肯定要把所有可行的答案穷举出来，然后在其中找最值
    //动态规划的穷举有点特别，因为这类问题存在「重叠子问题」，如果暴力穷举的话效率会极其低下，
    // 所以需要「备忘录」或者「DP table」来优化穷举过程，避免不必要的计算
    //动态规划问题一定会具备「最优子结构」，才能通过子问题的最值得到原问题的最值。
    //虽然动态规划的核心思想就是穷举求最值，但是问题可以千变万化，穷举所有可行解其实并不是一件容易的事，只有列出正确的「状态转移方程」才能正确地穷举。

    //重叠子问题、最优子结构、状态转移方程就是动态规划三要素

    /**
     * 明确 base case -> 明确「状态」-> 明确「选择」 -> 定义 dp 数组/函数的含义。
     * # 初始化 base case
     * dp[0][0][...] = base
     * # 进行状态转移
     * for 状态1 in 状态1的所有取值：
     *     for 状态2 in 状态2的所有取值：
     *         for ...
     *             dp[状态1][状态2][...] = 求最值(选择1，选择2...)
     */


    //斐波那契
    int fib(int n){
        return n == 1 || n == 2 ? 1 : fib(n - 1) + fib(n - 2);
    }
    //画出递归树 大量的重复计算
    //递归算法的时间复杂度怎么计算？就是用子问题个数乘以解决一个子问题需要的时间。
    //计算子问题个数，即递归树中节点的总数。显然二叉树节点总数为指数级别，所以子问题个数为 O(2^n)。
    //计算解决一个子问题的时间，在本算法中，没有循环，只有 f(n - 1) + f(n - 2) 一个加法操作，时间为 O(1)。
    //算法的时间复杂度为二者相乘，即 O(2^n)，指数级别

    //带备忘录的递归解法
    //即然耗时的原因是重复计算，那么我们可以造一个「备忘录」，
    // 每次算出某个子问题的答案后别急着返回，先记到「备忘录」里再返回；
    // 每次遇到一个子问题先去「备忘录」里查一查，如果发现之前已经解决过这个问题了，
    // 直接把答案拿出来用，不要再耗时去计算了。
    //一般使用一个数组充当这个「备忘录」，当然你也可以使用哈希表

    int fibWithMem(int n){
        if (n < 1) return 0;
        //备忘录全初始化为0
        int[] memo = new int[n + 1];
        //带备忘录的递归
        return helper(n, memo);
    }

    int helper(int n, int[] memo){
        if (n == 1 || n == 2)
            return 1;
        if (memo[n] == 0) {
            memo[n] = helper(n - 1, memo) + helper(n - 2, memo);
        }
        return memo[n];
    }

    /**
     * 抽象出dp表
     * @param
     */
    int fib_dp(int n){
        if (n < 0) return 0;
        if (n == 1 || n == 2) return 1;
        int[] dp = new int[n + 1];
        dp[1] = dp[2] = 1;
        for (int i = 3; i <= n ; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 根据斐波那契数列的状态转移方程，当前状态只和之前的两个状态有关，其实并不需要那么长的一个 DP table 来存储所有的状态，
     * 只要想办法存储之前的两个状态就行了。所以，可以进一步优化，把空间复杂度降为 O(1)： 状态压缩
     * 如果我们发现每次状态转移只需要 DP table 中的一部分，那么可以尝试用状态压缩来缩小 DP table 的大小，只记录必要的数据，
     * 相当于把DP table 的大小从 n 缩小到 2。一般来说是把一个二维的 DP table 压缩成一维，即把空间复杂度从 O(n^2) 压缩到 O(n)。
     * @param
     */
    int fib_dp_better(int n){
        if (n < 0) return 0;
        if (n == 1 || n == 2) return 1;
        int pre = 1;
        int prepre = 1;
        int res = 0;
        for (int i = 3; i <= n ; i++) {
            res = pre + prepre;
            prepre = pre;
            pre = res;
        }
        return res;
    }

    /**
     * 凑零钱问题 最优子结构
     * 给你 k 种面值的硬币，面值分别为 c1, c2 ... ck，每种硬币的数量无限，再给一个总金额 amount，
     * 问你最少需要几枚硬币凑出这个金额，如果不可能凑出，算法返回 -1
     * @param coins 中是可选硬币面值，amount 是目标金额
     *
     *              dp(n) 的定义：输入一个目标金额 n，返回凑出目标金额 n 的最少硬币数量。
     *              dp[n] = {
                            0 , n == 0
                            -1 , n < 0
                            min(dp[n - coin] + 1 | coin in coins , n > 0 )
     *              }
     */
    private int progress_dp(int[] coins, int n) {
        if (n == 0) return 0; //base case
        if (n < 0) return -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int subproblem = progress_dp(coins, n - coins[i]);
            if (subproblem == -1) continue;
            min = Math.min(min, 1 + subproblem);
        }
        return min;
    }
    //上述为暴力解法  使用备忘录优化


    int progress_recur(int i, int amount, int[] coins){
        if (amount == 0) return 0;
        return Math.min(1 + progress_recur(i + 1, amount - coins[i], coins), progress_recur(i, amount, coins));



    }

    int process(int amount){
        //1、确定 base case  amount=0 不需要任何硬币就已经凑出目标金额了。
        if (amount == 0){
            return 0;
        }
        //2.确定「状态」，也就是原问题和子问题中会变化的变量
        //由于硬币数量无限，硬币的面额也是题目给定的，只有目标金额会不断地向 base case 靠近，所以唯一的「状态」就是目标金额 amount

        //3、确定「选择」，也就是导致「状态」产生变化的行为
        //目标金额为什么变化呢，因为你在选择硬币，你每选择一枚硬币，就相当于减少了目标金额。所以说所有硬币的面值，就是你的「选择」。

        //4 明确 dp 函数/数组的定义
        return -1;
    }




    public static void main(String[] args) {
        DPIdea dpidea = new DPIdea();
        testFib(dpidea);

        int[] coins = {1,2,5};
        int amount = 11;
        System.out.println(dpidea.progress_dp(coins, amount));
    }







    private static void testFib(DPIdea dpidea) {
        System.out.println(dpidea.fibWithMem(30));
        System.out.println(dpidea.fib(30));
        System.out.println(dpidea.fib_dp(30));
        System.out.println(dpidea.fib_dp_better(30));
    }


}
