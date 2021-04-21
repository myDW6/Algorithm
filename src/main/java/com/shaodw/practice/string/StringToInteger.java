package com.shaodw.practice.string;

/**
 * @Auther: shaodw
 * @Date: 2020/3/17 08:29
 * @Description: 字符串转为整数
 */
public class StringToInteger {
    private static boolean invalid = false;//表示遇到非法输入
    /**
     *
     * @param str
     * @return -1 表示非法字符
     *          0 表示输入的是空串
     */
    public static int atoi(String str){
        if (str == null || "".equals(str) || "+".equals(str) || "-".equals(str)){
            invalid = true;
            return 0;
        }

        int res = 0;
        boolean pos = true;
        if (str.charAt(0) == '+') {
            pos = true;
        }else if (str.charAt(0) == '-'){
            pos = false;
        }else {
            res = str.charAt(0) - '0';
        }
        for (int i = 1; i < str.length(); i++) {
            char cur = str.charAt(i);
            if (cur < '0' || cur > '9'){
                invalid = true;
                return 0;
            }
            res = 10 * res + str.charAt(i) - '0';
        }
        invalid = false;
        return pos ? res : -res;
    }

    public static void main(String[] args) {
        String s = "123";
        String s1 = "+123";
        String s2 = "-123";
        String s3 = "-12+3";
        System.out.println(atoi(null) + " " + invalid);
        System.out.println(atoi("") + " " + invalid);
        System.out.println(atoi("+") + " " + invalid);
        System.out.println(atoi("-") + " " + invalid);
        System.out.println(atoi(s) + " " + invalid);
        System.out.println(atoi(s1) + " " + invalid);
        System.out.println(atoi(s2) + " " + invalid);
        System.out.println(atoi(s3) + " " + invalid);
    }
}
