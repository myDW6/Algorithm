package com.practice.shaodw.leetcode.string.substring;



import com.shaodw.anno.Passed;

import java.util.HashSet;

/**
 * @Auther: shaodw
 * @Date: 2021/2/2 21:30
 * @Description:
 */
public class MaxSubStrWithoutRepeat1 {
    @Passed(complex = "N", note = "set判断重复与否 还可以优化", better =MaxSubStrWithoutRepeat2.class)
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }
        int max = 0;
        char[] chars = s.toCharArray();
        HashSet<Character> set = new HashSet<>();
        for (int i = 0, j = 0; i < chars.length && j < chars.length;) {
           if (set.contains(chars[j])){
               set.remove(chars[i]);
               i++;
           }else {
               set.add(chars[j]);
               max = Math.max(j - i + 1, max);
               j++;
           }
        }
        return max;
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
