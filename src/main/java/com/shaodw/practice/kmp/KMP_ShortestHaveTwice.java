package com.shaodw.practice.kmp;

/**
 * @Auther: shaodw
 * @Date: 2020/2/10 14:16
 * @Description: 给定一个字符串 只在末尾添加字符 要求使得新字符串包含两个原始串 且长度最短
 */
public class KMP_ShortestHaveTwice {
    /**
     * 只要求出多求一位next数组的值即可
     * @param original
     * @return
     */
    public static String shortestHaveTwice(String original){
        if (original == null || original.length() < 1)
            return "";
        if (original.length() == 1){
            return original + original;
        }
        if (original.length() == 2){
            return original.charAt(0) == original.charAt(1) ? original + original.charAt(0) : original + original;
        }
        int[] next = new int[original.length() + 1];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0;
        while (i < next.length){
            if (original.charAt(i - 1) == original.charAt(cn)){
                next[i++] = ++cn;
            }else if (cn > 0){
                cn = next[cn];
            }else {
                next[i++] = 0;
            }
        }
        return original + original.substring(next[next.length - 1]);
    }

    public static void main(String[] args) {
        String s0 = "a";
        System.out.println(shortestHaveTwice(s0));
        String s1 = "aa";
        System.out.println(shortestHaveTwice(s1));
        String s2 = "ab";
        System.out.println(shortestHaveTwice(s2));
        String s3 = "abcdabcd";
        System.out.println(shortestHaveTwice(s3));
        String s4 = "abracadabra";
        System.out.println(shortestHaveTwice(s4));
    }
}
