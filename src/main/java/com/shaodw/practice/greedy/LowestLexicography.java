package com.shaodw.practice.greedy;

import java.util.Arrays;

/**
 * @Auther: shaodw
 * @Date: 2020/2/5 16:35
 * @Description: 给定一个字符串类型的数组strs，找到一种拼接方式，使得把所有字符串拼起来之后形成的字符串具有最低的字典序。
 * 字典序:将其看成26进制的数字 进行比较 如果数字位数不相等 补齐位数后比较
 */
public class LowestLexicography {
    public static String lowestString(String[] strs){
        if (strs == null || strs.length <= 0){
            return "";
        }

        /**
         * 贪心策略：按照字符串拼接后从小到大排序后 依次拼接返回
         */
        Arrays.sort(strs, (a,b)->(a + b).compareTo(b + a));
        String res = "";
        for (int i = 0; i < strs.length; i++) {
            res += strs[i];
        }
        return res;
    }

    public static void main(String[] args) {
        String[] strs1 = { "jibw", "ji", "jp", "bw", "jibw" };
        System.out.println(lowestString(strs1));

        String[] strs2 = { "ba", "b" };
        System.out.println(lowestString(strs2));
    }
}
