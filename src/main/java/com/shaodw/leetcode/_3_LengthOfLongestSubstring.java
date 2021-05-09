package com.shaodw.leetcode;

import com.shaodw.anno.Better;
import com.shaodw.anno.Passed;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: shaodw
 * @Date: 2019-12-29 17:12
 * @Description: 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 */
public class _3_LengthOfLongestSubstring {

    @Passed(note = "哈希表加滑动窗口可解决", complex = "N")
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


    @Better(note = "在leetcode的提交上 该写法效率更优")
    @Passed(note = "使用数组位置为0替代set查找重复值 很优雅的写法", complex = "N")
    public static int lengthOfLongestSubstring1(String s) {
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
        System.out.println(new _3_LengthOfLongestSubstring().lengthOfLongestSubstring("abcabcbb"));//3
        System.out.println(new _3_LengthOfLongestSubstring().lengthOfLongestSubstring("abcdef"));//6
        System.out.println(new _3_LengthOfLongestSubstring().lengthOfLongestSubstring("bbbbbb"));//1
        System.out.println(new _3_LengthOfLongestSubstring().lengthOfLongestSubstring("abb"));//2
        System.out.println(new _3_LengthOfLongestSubstring().lengthOfLongestSubstring("pwwkew"));//3
        System.out.println(new _3_LengthOfLongestSubstring().lengthOfLongestSubstring("abba"));//2

        System.out.println("-----方法2的测试------");
        System.out.println(new _3_LengthOfLongestSubstring().lengthOfLongestSubstring1("abcabcbb"));//3
        System.out.println(new _3_LengthOfLongestSubstring().lengthOfLongestSubstring1("abcdef"));//6
        System.out.println(new _3_LengthOfLongestSubstring().lengthOfLongestSubstring1("bbbbbb"));//1
        System.out.println(new _3_LengthOfLongestSubstring().lengthOfLongestSubstring1("abb"));//2
        System.out.println(new _3_LengthOfLongestSubstring().lengthOfLongestSubstring1("pwwkew"));//3
        System.out.println(new _3_LengthOfLongestSubstring().lengthOfLongestSubstring1("abba"));//2
    }
}
