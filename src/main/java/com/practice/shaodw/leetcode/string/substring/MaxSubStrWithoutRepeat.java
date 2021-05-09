package com.practice.shaodw.leetcode.string.substring;



import com.shaodw.anno.Passed;

import java.util.HashSet;

/**
 * @Auther: shaodw
 * @Date: 2021/2/2 20:45
 * @Description: 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */
public class MaxSubStrWithoutRepeat {

    @Passed(complex = "N * N",
            note = "思路就是这样, 不断扩大右边界 阔一次计算一次值 但是用hashSet找范围上的复杂度太高",
    better = MaxSubStrWithoutRepeat1.class)
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }
        int max = 0;
        char[] chars = s.toCharArray();
        for (int i = 0, j = 0; i < chars.length && j < chars.length;) {
            if (dump(chars, i, j)){
                i++;
            }else {
                max = Math.max(j - i + 1, max);
                j++;
            }
        }
        return max;
    }

    private static boolean dump(char[] arr, int i, int j){
        HashSet<Character> hashSet = new HashSet<>();
        for (int k = i; k <= j; k++) {
            if (hashSet.contains(arr[k])){
                return true;
            }
            hashSet.add(arr[k]);
        }
        return false;
    }

    public static void main(String[] args) {
        String bbbb = "bbbb";
        String abcabcbb = "abcabcbb";
        String pwwkew = "pwwkew";
        System.out.println(lengthOfLongestSubstring(bbbb));
        System.out.println(lengthOfLongestSubstring(abcabcbb));
        System.out.println(lengthOfLongestSubstring(pwwkew));
    }
}
