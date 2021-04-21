package com.shaodw.practice.subarray;

import java.util.HashMap;

/**
 * @Auther: shaodw
 * @Date: 2020/2/22 12:22
 * @Description: 定义数组的异或和的概念： 数组中所有的数异或起来，得到的结果叫做数组的异或和， 比如数组{3,2,1}的异或和是，3^2^1 = 0
 * 给定一个数组arr，你可以任意把arr分成很多不相容的子数组，你的目的是： 分出来的子数组中，异或和为0的子数组最多。
 * 分出来的子数组中，异或和为0的子数组最多是多少？
 */
public class Most_EOR {

    /**
     * 求0-i范围最多能分出几个异或和为0的几个子数组
     * 求0-i+1范围最多能分出几个异或和为0的几个子数组
     * 求0-N-1范围最多能分出几个异或和为0的几个子数组
     *
     * 面对的0-i范围  假设现在是最优划分
     * 只有两种可能 第一种i作为划分的最后一部分最后一个值 i所在的这部分不是异或和为0的子数组
     * 第二种可能 i所在的最后一部分不是异或和为0的子数组
     *
     * 如果i所在的最后一部分不是异或和为0的子数组 0-i最多能分多少块和0-i-1范围上能分多少块相等
     * 如果i所在的最后一部分是异或和为0的子数组  我们假设最优划分最后一部分从k开始 到i结束
     *      那么k一定会是距离i最近的异或起来和为0的位置 （因为假如还有j位置使得j-i为最优划分 那么
     *      j到i异或和为0 要想k到i异或和为0 k到j-1也必须为0  0^0=0 这和最优划分矛盾 因为此时最优划分是k和j位置各分一次）
     *
     *    那么问题就转化为了：
     *          假设我从0-i异或的和为sum
     *          我的目标就是0-i-1之间异或和还是sum的最晚的位置
     *          最晚的位置的下一个位置就是k
     *
     * @param arr
     * @return
     */

    /**
     * dp[i] = Math.max(dp[i - 1], dp[k - 1] + 1);
     * dp[k-1]指的0->k-1范围上的最优划分
     */
    public static int mostEor(int[] arr){
        int ans = 0;
        int xor = 0;
        int[] dp = new int[arr.length];//dp[i]表示0到i位置的最优划分
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,-1);//0这个异或和最晚出现在-1位置 因为现在还没有数
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];//到i位置形成的异或和为xor  现在决策为找到0->i-1最晚的异或和也为xor的位置
            if (map.containsKey(xor)){
                int pre = map.get(xor);
                dp[i] = pre == -1 ? 1: dp[pre] + 1;
            }
            if (i > 0){
                dp[i] = Math.max(dp[i - 1], dp[i]);
            }
            map.put(xor, i);
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {3,2,1,9,0,7,0,2,1,3};
        System.out.println(mostEor(arr));
    }
}
