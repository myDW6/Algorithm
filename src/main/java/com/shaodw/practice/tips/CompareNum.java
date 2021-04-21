package com.shaodw.practice.tips;

/**
 * @Auther: shaodw
 * @Date: 2020/3/17 08:20
 * @Description: 不使用大小号 比较两个数大小
 */
public class CompareNum {

    /**
     * x = max(a,b);
     * y = min(a,b);
     * x + y = a + b;
     * x - y = |a - b|
     * x = (a + b + |a - b|) >> 1
     * y = (a + b - |a - b|) >> 1
     */

    public static int getMax(int a, int b){
        return (a + b + Math.abs(a - b)) >> 1;
    }

    public static int getMin(int a, int b){
        return (a + b - Math.abs(a - b)) >> 1;
    }

    public static void main(String[] args) {
        int a = 3;
        int b = 5;
        System.out.println(getMax(a,b));
        System.out.println(getMin(a,b));
    }
}
