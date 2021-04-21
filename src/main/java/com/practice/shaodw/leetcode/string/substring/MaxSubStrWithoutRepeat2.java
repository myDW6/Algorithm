package com.practice.shaodw.leetcode.string.substring;


import com.practice.shaodw.anno.Better;

/**
 * @Auther: shaodw
 * @Date: 2021/2/2 21:30
 * @Description:
 */
public class MaxSubStrWithoutRepeat2 {


    @Better(note = "使用数组位置为0替代set查找重复值 很优雅的写法", complex = "N")
    public static int lengthOfLongestSubstring(String s) {
        int[] map = new int[256]; // 8位
        int max = 0;
        int l = 0, r = 0;
        while (l <= r && l < s.length()){
            if (r < s.length() && map[s.charAt(r)] == 0){
                max = Math.max(r - l + 1, max);
                map[s.charAt(r++)]++;
            }else {
                map[s.charAt(l++)]--;
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
