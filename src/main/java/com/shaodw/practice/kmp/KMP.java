package com.shaodw.practice.kmp;

import java.util.Arrays;

/**
 * @Auther: shaodw
 * @Date: 2020/2/9 21:38
 * @Description: KMP算法
 */
public class KMP {
    /**
     * next数组记录每一个位置的最长前缀和最长后缀匹配长度
     * 规定0位置为-1
     *     1位置为0
     * @param ms
     * @return
     */
    private static int[] getNextArray(char[] ms){
        if (ms.length == 1)
            return new int[]{-1};
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0;//cn表示跳到的位置  同时cn也代表了前缀的长度 （0 - cn-1)长度为cn 因为cn跳到了next[cn] 当前位置的next数组中存储的是cn位置的最长前缀长度
        //那么cn的值被赋值为长度 意思就是cn到了其前缀的后一个字符位置  若cn = 5 next[5] = 2  那么cn = 2实际上来到的是第三个字符（0 1 2）其中 0和1是前缀
        //那么cn成功跳到了前缀的后一个字符  此时拿该字符和i -1位置字符进行比较  若匹配 那说明至少我的前缀也是i的前缀 长度+1 cn本身又代表了长度  所以++cn
        /**
         * ababcababtk
         * 求k的最长前后缀长度
         * 不断拿k（i位置）的前一字符（i - 1位置）与 前一字符（i - 1）的分出的两坨前缀中前面那个的下一个字符比较
         * k的前一个字符为t 其最长前后缀为abab next数组中值为4  abab前缀的下一个字符为c 和t不相等
         * 那么 找c的前缀 ab ab 前面那个ab的下一个字符的下一个字符为a 和t不相等
         * a的前后缀已经没有了  所以再k位置值为0
         *
         *
         * 若字符串为ababcababak
         * 那么k的前缀求法：先找k的前一个字符的前缀 为abab 和 abab 前一个前缀的下一个字符为c 和i - 1位置的a不等
         * c继续往前跳 跳出两坨 ab ab 前一个ab的下一个字符为a 和 i - 1位置的a相等
         * 那么k位置的前缀长度就是c位置的前缀长度+1  c的前缀长度为ab 值为2  那么k位置值为3  aba
         */
        while (i < next.length){
            if (ms[i - 1] == ms[cn]){
                next[i++] = ++cn;  //++cn的值
            }else if (cn > 0){
                /**
                 * cn跳到得位置和我前一个位置的字符不等 如果还能往前跳 则往前跳
                 */
                cn = next[cn];
            }else {
                /**
                 * 不能往前跳 当前位置的最长前置就是0
                 */
                next[i++] = 0;
            }
        }
        return next;
    }

    //字符串m在字符串s出现的下标
    public static int getIndexOf(String str, String match){
        if (str == null || match == null || match.length() < 1 || str.length() < match.length())
            return -1;
        int p1 = 0; //遍历str中的字符
        int p2 = 0; //遍历match中的字符
        /**
         * 得到next数组后 分别使用两个指针p1 p2遍历两个字符串
         * 如果两个指针所指的位置的字符相等 匹配 直接下一个字符
         * 不相等时 如果next数组值为-1 说明是match的第一个字符  此时p1直接取下一个字符 因为两个字符不等 match又是第一个
         *          如果不是-1 那么p2跳到对应位置 若next[i]值为k 那么说明i位置的最长前缀值为k  match从0到k - 1位置上的一个k个字符 p2直接来到next[p2]即可
         *          p2来到next[i]位置代表者将match往右推了next[i]个位置后再比较
         */
        int[] next = getNextArray(match.toCharArray());
        while (p1 < str.length() && p2 < match.length()){
            if (str.charAt(p1) == match.charAt(p2)){
                p1++;
                p2++;
            }else if (next[p2] == -1){
                p1++;
            }else {
                p2 = next[p2];
            }
        }
        return p2 == match.length() ? p1 - p2 : -1;
    }

    public static void main(String[] args) {
        String s = "abcabcababaccc";
        String m = "ababa";
        Arrays.stream(getNextArray(m.toCharArray())).forEach(str -> System.out.print(str + " "));
        System.out.println("\n" + getIndexOf(s,m));
        /**
         * abcabcababaccc
         * ababa
         *
         * abcabcababaccc
         *   ababa
         *
         * abcabcababaccc
         *    ababa
         *
         * abcabcababaccc
         *      ababa
         *
         * abcabcababaccc
         *       ababa
         *
         */

        //debug看next数组的求的过程
        String test = "ababcababak";
        getNextArray(test.toCharArray());
    }
}
