package com.practice.shaodw.leetcode.string.palin;


import com.shaodw.anno.Better;

/**
 * @author shaodw
 * @date 2021/2/3 16:58
 * @description 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 */
public class LongestPalindrome {
    //manacher 暴力扩解决不了长度为偶数的回文(虚轴)
    @Better(complex = "N", note = "加速经典的暴力解(N*N)  这个写出来的代码是真的秀" )
    public static int longestPalindrome(String str) {
        //R记录右侧最右的边界  C记录R的中心  两个概念伴生 R更新C也要更新 R不更新C也不能更新


        //1 来到中心点 没在最右回文右边界里 暴力扩
        //2 来到中心点 在最右回文右边界可以优化:     L   j   C   i   R
        // i关于c的对称点 j
        //1. 若j的回文左边界 在 L内  则 i 的回文半径就是j的回文半径
        //因为LR范围整体是回文 那么j的那一串回文子串A关于C的对称子串B有:B是A的逆序 有AB都是回文 所以AB相等
        //有没有可能i的回文子串半径更大  不可能   L   xjy  C   min  R   :j的回文子串是这么大决定了x!=y
        // 对称关系: x==n y==m  所以m != n  所以i的回文子串值就是j的值
        //2. 若j的回文左边界 在 L左边去了  此时可以得出结论:  i的回文右边界值一定是R
        //做L关于j的对称点 L1   R关于i的对称点R1
        //      L    j    L1    C   R1    i    R
        //   [L,L1] 和 [R1, R]关于C对称 那么肯定是逆序关系  又[L,L1]在j形成的整个回文子串里  所以[L,L1]是回文 [R1,R]也是回文
        //  假设L前一个字符为x
        //   xL    j    L1y    C   zR1    i    Rp
        //x==y  y和z关于C对称 且 在C中心的回文 所以y==z   那么以C为中心扩出来的区域之所以为R 而不是更远 原因是因为 x!=p  所以z!=p
        //所以i的区域即为R
        //3 左边界压线 首先i到R一定满足 分析同2 但是不至于是否可以接着往右扩  所以只能暴力扩

        if (str == null || str.length() == 0){
            return 0;
        }
        char[] charArr = manacherString(str);
        int[] pArr = new int[charArr.length];//回文半径数组
        int C = -1;  //回文右边界的中心
        int R = -1;  //回文右边界的下一个位置
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < charArr.length; i++) {
            //四种情况一句代码解决
            pArr[i] = R > i ? Math.min(pArr[(C << 1) - i], R - i) : 1;
            while (i + pArr[i] < charArr.length && i - pArr[i] > -1){
                //尝试扩充  扩不出去的扩一次就会失败
                if (charArr[i + pArr[i]] == charArr[i - pArr[i]]){
                    pArr[i]++;
                    //括出来的值相等 回文半径++
                }else break;
            }

            /**
             * 括出来的区域超过了R 有了新的回文有边界和回文中心
             */
            if (i + pArr[i] > R){
                R = i + pArr[i];
                C = i;
            }

            max = Math.max(max, pArr[i]);
        }
        return max - 1;
    }

    private static char[] manacherString(String s){
        char[] chs = s.toCharArray();
        char[] manacherStr = new char[chs.length * 2 + 1];
        for (int i = 0; i < manacherStr.length; i++) {
            manacherStr[i] = (i & 1) == 0 ? '#' : chs[i >> 1];
        }
        return manacherStr;
    }
}
