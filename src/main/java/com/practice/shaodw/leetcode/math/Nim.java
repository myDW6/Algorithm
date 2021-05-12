package com.practice.shaodw.leetcode.math;

import com.shaodw.anno.Passed;

/**
 * @Auther: shaodw
 * @Date: 2021/5/12 18:51
 * @Description:
 * 游戏规则是这样的：你和你的朋友面前有一堆石子，你们轮流拿，一次至少拿一颗，最多拿三颗，谁拿走最后一颗石子谁获胜。
 * 假设你们都很聪明，由你第一个开始拿，请你写一个算法，输入一个正整数 n，返回你是否能赢（true 或 false）。
 * 比如现在有 4 颗石子，算法应该返回 false。因为无论你拿 1 颗 2 颗还是 3 颗，对方都能一次性拿完，拿走最后一颗石子，所以你一定会输。
 */
public class Nim {
    //如果我要赢 我需要做的是我拿完之后给朋友剩下4个石子 那样无论他怎么拿都是我赢
    //怎么让他剩下4个呢  我一次只能拿1/2/3个  所以要让我的选择是5 6 7 所以就必须让他面临剩下8个
    //怎么样让他剩8个呢  我可以选择9 10 11
    //所以 只要踩到 4 的倍数，就落入了圈套，永远逃不出 4 的倍数，而且一定会输
    @Passed(complex = "O(1)")
    public static boolean canWin(int n){
        return n % 4 != 0;
    }

}
