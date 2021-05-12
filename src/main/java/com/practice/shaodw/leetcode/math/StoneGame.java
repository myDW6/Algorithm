package com.practice.shaodw.leetcode.math;

import com.shaodw.anno.Passed;

/**
 * @Auther: shaodw
 * @Date: 2021/5/12 19:18
 * @Description:
 * 亚历克斯和李用几堆石子在做游戏。偶数堆石子排成一行，每堆都有正整数颗石子 piles[i] 。
 * 游戏以谁手中的石子最多来决出胜负。石子的总数是奇数，所以没有平局。
 * 亚历克斯和李轮流进行，亚历克斯先开始。 每回合，玩家从行的开始或结束处取走整堆石头。 这种情况一直持续到没有更多的石子堆为止，此时手中石子最多的玩家获胜。
 * 假设亚历克斯和李都发挥出最佳水平，当亚历克斯赢得比赛时返回 true ，当李赢得比赛时返回 false 
 */
public class StoneGame {

    //我们以 piles=[2, 1, 9, 5] 讲解，假设这四堆石头从左到右的索引分别是 1，2，3，4。
    //把这四堆石头按照奇偶性分为两组 1 3 和 2 4 那么肯定会是一堆多 一堆少
    //先拿的人完全可以先观察奇数多还是偶数多
    //如果奇数多 我就拿第一堆  如果偶数多  我就拿第四堆
    @Passed(complex = "O(1)")
    public boolean stoneGame(int[] piles) {
        return true;
    }

}
