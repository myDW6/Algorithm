package com.practice.shaodw.leetcode.backtracing;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * @author shaodw
 * @date 2021/2/7 17:18
 * @description 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 */
public class PhoneNumberCombine {
    public static List<String> letterCombinations(String digits) {
        if (digits.length() == 0){
            return new ArrayList<>();
        }
        String[] map = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> res = new LinkedList<>();
        backtracing(map, res, digits, 0, new StringBuilder());
        return res;
    }

    //回溯
    private static void backtracing(String[] map,List<String> res,  String digits, int index, StringBuilder builder){
        if (index == digits.length()){
            res.add(builder.toString());
            return;
        }
        char[] chs = map[digits.charAt(index) - '0'].toCharArray();
        for (char ch : chs) {
            builder.append(ch);
            backtracing(map, res, digits, index + 1, builder);
            builder.deleteCharAt(builder.length() - 1);
        }
    }

    public static void main(String[] args) {
        List<String> list = letterCombinations("23");
        System.out.println(list.toString());
    }

}
