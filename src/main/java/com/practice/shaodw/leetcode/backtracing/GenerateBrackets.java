package com.practice.shaodw.leetcode.backtracing;


import com.shaodw.anno.Passed;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shaodw
 * @date 2021/2/19 14:20
 * @description 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 * n = 3
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 */
public class GenerateBrackets {
    @Passed(complex = "不知道", note = "回溯")
    public static List<String> generateBrackets(int n){
        List<String> res = new ArrayList<>();
        backTracking(res, 0, 0, n, new StringBuilder());
        return res;
    }

    private static void backTracking(List<String> res,
                                     int l,
                                     int r,
                                     int n,
                                     StringBuilder builder){
        if (l == r && l == n){
            res.add(builder.toString());
            return;
        }
        if (l > r){
            builder.append(")");
            backTracking(res, l, r + 1, n, builder);
            builder.deleteCharAt(builder.length() - 1);
        }
        if (l < n){
            builder.append("(");
            backTracking(res, l + 1, r, n, builder);
            builder.deleteCharAt(builder.length() - 1);
        }
    }

    public static void main(String[] args) {
        int n = 3;
        System.out.println(generateBrackets(3));
    }

}
