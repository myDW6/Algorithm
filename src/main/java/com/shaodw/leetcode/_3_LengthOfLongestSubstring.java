package com.shaodw.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: shaodw
 * @Date: 2019-12-29 17:12
 * @Description:哈希表加滑动窗口可解决
 *
 */
public class _3_LengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        int end = 0, start = 0, length = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (end < s.length()){
            char c = s.charAt(end);
            if (map.containsKey(c)){
                start = Math.max(map.get(c), start);
            }
            length = Math.max(length, end - start + 1);
            map.put(c, ++end);
            //map中存放下一个start的位置 即end + 1位置, 因为[start, end)中在end位置重复
        }
        return length;
    }

    public static void main(String[] args) {
        System.out.println(new _3_LengthOfLongestSubstring().lengthOfLongestSubstring("abcabcbb"));
        System.out.println(new _3_LengthOfLongestSubstring().lengthOfLongestSubstring("abcdef"));
        System.out.println(new _3_LengthOfLongestSubstring().lengthOfLongestSubstring("bbbbbb"));
        System.out.println(new _3_LengthOfLongestSubstring().lengthOfLongestSubstring("abb"));
        System.out.println(new _3_LengthOfLongestSubstring().lengthOfLongestSubstring("pwwkew"));
        System.out.println(new _3_LengthOfLongestSubstring().lengthOfLongestSubstring("abba"));
    }
}
