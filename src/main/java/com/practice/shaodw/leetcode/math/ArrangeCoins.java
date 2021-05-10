package com.practice.shaodw.leetcode.math;


/**
 * @author shaodw
 * @date 2021/5/9 20:02
 * @description 排列硬币 总共有n枚硬币 将他们排成阶梯状 第k行就有k枚硬币
 * 给定n 返回完整阶梯的行数
 * n>=0不会越界
 */
public class ArrangeCoins {
    public static int arrangeCoins(int n){
        for (int level = 1; level <= n; level++) {
            //这一行还剩n-level个硬币  这够不够放呢 如果剩余的比我要放的level小或者等 那下一层就没法放了
            n -= level;
            if (n <= level){
                return level;
            }
        }
        return 0;
    }

    //其实可以使用二分来优化  我们一下放n层
    public static int arrangeCoins1(int n){
        int l = 0;
        int r = n;
        while (l <= r){
            int mid = l + (r - l >> 1);
            int cost = mid * mid + mid >> 1; //等差数列求和 mid这一行耗费的硬币数
            if (cost == n){
                return mid;
            }else if (cost < n){
                l += 1;
            }else if (cost > n){
                r -= 1;
            }
        }
        return r;
    }

    //牛顿迭代
//    n =(x*x + x ) / 2这一步 其实可以使用牛顿迭代来优化
    //x*x  = 2n - x  意思就是求 根号2n-x的值
    public static int arrangeCoins2(int n){
        if (n == 0){
            return 0;
        }
        return (int) newTon(n, n);
    }

    private static double newTon(double x, int n){
        double ret = (x + (2 * n - x)/x) / 2;
        if (ret == x){
            return x;
        }else return newTon(ret, n);
    }

    public static void main(String[] args) {
        System.out.println(arrangeCoins(10));
        System.out.println(arrangeCoins1(10));
        System.out.println(arrangeCoins2(10));
    }


}
