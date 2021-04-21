package com.shaodw.practice.RecurAndDP;

import java.util.ArrayList;

/**
 * @Auther: shaodw
 * @Date: 2020/2/5 19:48
 * @Description: 打印字符串的全排列
 */
public class AllPermutations {
    private static void process1(char[] chs, int i){
        if (i == chs.length){
            System.out.println(String.valueOf(chs));
            return;
        }
        for (int j = i; j < chs.length; j++) {
            swap(chs, i, j);
            process1(chs, i + 1);
            swap(chs, i, j);
        }
    }

    private static void swap(char[] chs, int i, int j){
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;
    }

    public static void main(String[] args) {
        process1("abc".toCharArray(), 0);
    }

    /**
     * 解法1 字符串S 以字符序列a1a2...an表示
     *  终止条件 n = 1 S1 = a1
     *          n = 2 S2 = a1a2  a2a1    将a2插入到S1的所有位置
     *          n = 3 S3 = a3a1a2 a1a3a2 a1a2a3 a3a2a1 a2a3a1 a2a1a3 将a3插入到S2的所有位置
     *
     *
     *          n = n Sn = an 插入到Sn-1的所有位置
     */
    public static ArrayList<String> getPermutation1(String A){
       int n = A.length();
       ArrayList<String> res = new ArrayList<>();
        res.add(A.charAt(0) + "");

        for (int i = 1; i < n; i++) {
            ArrayList<String> res_new = new ArrayList<>();
            char c = A.charAt(i);
            for (String str : res){
                String newStr = c + str;
                res_new.add(newStr);
                newStr = str + c;
                res_new.add(newStr);

                for (int j = 1; j < str.length(); j++){
                    newStr = str.substring(0, j) + c + str.substring(j);
                    res_new.add(newStr);
                }

            }
            res = res_new;
        }
        return res;
    }
}
