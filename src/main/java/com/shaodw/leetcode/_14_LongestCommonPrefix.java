package com.shaodw.leetcode;

/**
 * @Auther: shaodw
 * @Date: 2020/7/7 18:57
 * @Description: 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 */
public class _14_LongestCommonPrefix {
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0){
            return "";
        }
        if (strs.length == 1){
            return strs[0];
        }
        String common = "";
        String pre = strs[0];
        for (int i = 1; i < strs.length; i++) {
            String cur = strs[i];
            common = commonPrefix(pre, cur);
            pre = cur;
        }
        return common;
    }

    private static String commonPrefix(String a, String b){
        if (a != null && b!= null){
            StringBuilder prefix = new StringBuilder();
            for (int i = 0; i < a.length() && i < b.length(); i++) {
                if (a.charAt(i) == b.charAt(i)){
                    prefix.append(a.charAt(i));
                }else break;
            }
            return prefix.toString();
        }return "";
    }

    public static void main(String[] args) {
        String[] strs = {"flower","flow","flight"};
        System.out.println(longestCommonPrefix(strs));

        String[] strs1 = {"dog","racecar","car"};
        System.out.println(longestCommonPrefix(strs1));

        String[] strs2 = {"leetcode", "leets", "leet","leeds"};
        System.out.println(longestCommonPrefix(strs2));

        String[] strs4 = {"a"};
        System.out.println(longestCommonPrefix(strs4));

    }
}
