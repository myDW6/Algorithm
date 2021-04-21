package com.shaodw.leetcode;

import java.util.Arrays;

/**
 * @Auther: shaodw
 * @Date: 2020/7/6 21:47
 * @Description: 罗马数字转整数
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
 */
public class _13_RomanToInt {
    /**
     * 从左到右, 如果拉丁字母出现在比它大的拉丁字母之前,减去这个拉丁字母代表的数值.
     *  其他情况,增加这个拉丁字母代表的数值.
     * @param s
     * @return
     */
    public static int romanToInt(String s) {
        int[] ints = romanStringToInteger(s);
        int res = 0;
        int i;
        for (i = 0; i < ints.length - 1;){
            if (ints[i] < ints[i + 1]){
                res += ints[i + 1] - ints[i];
                i += 2;
            }else {
                res += ints[i];
                i++;
            }
        }
        return res += i + 1 == ints.length ? ints[ints.length - 1] : 0;
    }

    private static int[] romanStringToInteger(String s){
        int[] res = new int[s.length()];
        for (int i = 0; i < res.length; i++){
            switch (s.charAt(i)){
                case 'I' : res[i] = 1; break;
                case 'V' : res[i] = 5; break;
                case 'X' : res[i] = 10; break;
                case 'L' : res[i] = 50; break;
                case 'C' : res[i] = 100; break;
                case 'D' : res[i] = 500; break;
                case 'M' : res[i] = 1000; break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "III";
        System.out.println(romanToInt(s));
        System.out.println(romanToInt1(s));
         s = "IV";
        System.out.println(romanToInt(s));
        System.out.println(romanToInt1(s));
         s = "IX";
        System.out.println(romanToInt(s));
        System.out.println(romanToInt1(s));
         s = "LVIII";
        System.out.println(romanToInt(s));
        System.out.println(romanToInt1(s));
         s = "MCMXCIV";
        System.out.println(romanToInt(s));
        System.out.println(romanToInt1(s));
        s = "CXXIII";
        System.out.println(romanToInt(s));
        System.out.println(romanToInt1(s));
        s = "XXXV";
        System.out.println(romanToInt(s));
        System.out.println(romanToInt1(s));
    }

    /**
     *  version 2
     *
     */
    public static int romanToInt1(String s){
        int sum = 0;
        int preNum = getValue(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            int curNum = getValue(s.charAt(i));
            sum = curNum > preNum ? sum - preNum : sum + preNum;
            preNum = curNum;
        }
        return sum + preNum;
    }

    private static int getValue(char c){
        switch (c){
            case 'I' : return 1;
            case 'V' : return 5;
            case 'X' : return 10;
            case 'L' : return 50;
            case 'C' : return 100;
            case 'D' : return 500;
            case 'M' : return 1000;
            default:return 0;
        }
    }
}
