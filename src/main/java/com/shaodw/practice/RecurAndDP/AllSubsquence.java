package com.shaodw.practice.RecurAndDP;

/**
 * @Auther: shaodw
 * @Date: 2020/2/5 19:24
 * @Description: 打印一个字符串全部的子序列 包括空字符串
 */
public class AllSubsquence {
    public static void printAllSubsquence(String str){
        if (str == null)
            return;
        char[] chs = str.toCharArray();
        process(chs, 0, "");
    }


    /**
     * 尝试的过程： 一开始是空串 0 1 2位置
     * 0位置上有两个决策 要a 不要a
     * 要a 生成字符串 "a" 往下一级扔 "a"
     * 不要a 生成字符串 "" 往下一级扔 ""
     *
     * 1位置上有两个决策 要b 不要b
     *要b 生成字符串 "ab" 往下一级扔 "ab"
     *要b 生成字符串 "b" 往下一级扔 "b"
     *不要b 生成字符串 "a" 往下一级扔 "a"
     *不要b 生成字符串 "" 往下一级扔 ""
     *
     * 2位置上有两个决策 要c 不要c
     * 要c 生成字符串 "abc" 往下一级扔 "abc"
     * 要c 生成字符串 "bc" 往下一级扔 "bc"
     * 要c 生成字符串 "ac" 往下一级扔 "ac"
     * 要c 生成字符串 "c" 往下一级扔 "c"
     * 不要c 生成字符串 "ab" 往下一级扔 "ab"
     * 不要c 生成字符串 "b" 往下一级扔 "b"
     * 不要c 生成字符串 "a" 往下一级扔 "a"
     * 不要c 生成字符串 "" 往下一级扔 ""
     *
     * i来到3位置 == length 没有决策  打印res
     * @param chs
     * @param i
     * @param res
     */

    //res表示已经生成的字符串 i表示当前推进的位置
    private static void process(char[] chs, int i, String res){
        if (i == chs.length){
            System.out.println(res);
            return;
        }
        //拼接上当前字符 | 或者不拼接上当前字符 生成新的res
        process(chs, i + 1, res);
        process(chs, i + 1, res + chs[i]);
    }

    public static void main(String[] args) {
        printAllSubsquence("abc");
    }
}
