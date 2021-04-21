package com.shaodw.practice.manacher;

/**
 * @Auther: shaodw
 * @Date: 2020/2/10 15:37
 * @Description: 求最长回文子串的长度
 */
public class Manacher {
    private static char[] manacherString(String s){
        char[] chs = s.toCharArray();
        char[] manacherStr = new char[chs.length * 2 + 1];
        for (int i = 0; i < manacherStr.length; i++) {
            manacherStr[i] = (i & 1) == 0 ? '#' : chs[i >> 1];
        }
        return manacherStr;
    }

    /**
     * Manacher O(N)
     */
    public static int maxLcpsLength(String str){
        if (str == null || str.length() == 0){
            return 0;
        }
        char[] charArr = manacherString(str);
        int[] pArr = new int[charArr.length];//回文半径数组
        int C = -1;  //回文右边界的中心
        int R = -1;  //回文右边界的下一个位置
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < charArr.length; i++) {
            /**
             * 当i在回文右边界R外面 暴力扩
             * 当i在回文右边界R里面 分为三种
             *  1 i的对称点的回文范围在大的回文里
             *  2 i的对称点的回文范围在大的回文外
             *  3 i的对称点的回文范围与大回文压线
             *  2 * C - i即是i关乎C的对称点
             *  R - i是i到回文右边界的距离  两者小的那部分便是我们不需要验证即是回文的部分
             *
             */


            //四种情况一句代码解决
            pArr[i] = R > i ? Math.min(pArr[(C << 1) - i], R - i) : 1;
            /**
             * 这一句代码大的区分了情况1 和 情况2
             * 我不用验证的区域 当我在R外面 我自己
             *                 当我在R里面 i的对称点的回文半径和我到R的距离
             */

            /**
             *  本分为4种情况
             * 1 暴力括
             * 2-1 不用验 O(1)出结果
             * 2-2 不用验 O(1)出结果
             * 2-3 暴力括
             * 为了代码简洁
             *
             * 现在已经有了两种不用括的情况
             * 如果是情况2-1 2-2 是扩充不了的 让它括一下 会失败 直接break
             * 但是1 和 2-3括的过程是可能成功的 那回文半径就会++
             */
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


    /**
     * 暴力方法 每个位置向两边括 不断扩充最大的回文长度 O(N * N)
     */
    public static int maxLcpsLengthViolence(String str){
        if (str == null || str.length() == 0){
            return 0;
        }
        char[] mchr = manacherString(str);
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < mchr.length; i++) {
            int left = i - 1;
            int right = i + 1;
            int count = 0;
            while (left >= 0 && right < mchr.length){
                if (mchr[left--] == mchr[right++]){
                    res = Math.max(res, ++count);
                }else {
                    break;
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        String s1 = "abcbaabcba";
        System.out.println(maxLcpsLengthViolence(s1));
        System.out.println(maxLcpsLength(s1));
        String s2 = "abc1234321ab";
        System.out.println(maxLcpsLengthViolence(s2));
        System.out.println(maxLcpsLength(s2));
    }
}
