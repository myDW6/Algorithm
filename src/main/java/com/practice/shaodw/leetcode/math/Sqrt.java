package com.practice.shaodw.leetcode.math;

import com.shaodw.anno.Better;
import com.shaodw.anno.Passed;

/**
 * @author shaodw
 * @date 2021/5/9 15:28
 * @description 求x的平方根的整数
 */
public class Sqrt {

    @Passed(note = "这要是不通过 写jdk的人是吃屎的吗")
    public static int mustRight(int x){
        return (int) Math.sqrt(x);
    }

    //根号N
    @Passed(complex = "根号N", note = "其实还好 当然二分和牛顿迭代更优")
    public static int violence(int x){
        if (x <= 0){
            return 0;
        }
        int i = 1;
        while (i <= x / i){
            //此处必须判断是否越界
            i++;
        }
        return i - 1;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(mustRight(i));
            System.out.println(violence(i));
            System.out.println(bs(i));
            System.out.println(newton(i));
            System.out.println("-----");
        }
        System.out.println(mustRight(2147395600));
        System.out.println(violence(2147395600));
        System.out.println(bs(2147395600));
        System.out.println(newton(2147395600));
        System.out.println(newton(21));

    }

    //既然暴力可以 完全可以二分  这个值一定在2->x-1之间
    //lgN
    @Passed(complex = "logN", note = "特别需要注意的是m*m会乘法溢出")
    public static int bs(int x){
        if (x == 0) {
            return 0;
        }
        if (x == 1) {
            return 1;
        }
        int l = 0;
        int r = x;
        int index = -1;
        while (l <= r){
            int m = l + (r-l>>1);
            //这里m*m会乘法溢出
            if (m <= x/m){
                index = m;
                l = m + 1;
            } else{
                r = m - 1;
            }
        }
        return index;
    }

    //牛顿迭代 n * n = x
    //12 = 1*12  2*6  3*4 根号12*根号12  4*3 6*2
    //比如 2与6   2加6除以2得到4   4*4 比 2*2 6*6更趋近于12
    //n 和 x/n 是两个因子   那么( n + x/n) / 2这个数比这两个因子本身更趋近于根x
    //这个基础上递归 ? = ( n + x/n) / 2  这个?不一定对 我们最终的目的是让 n 与 x/n相等
    //将这个?带入到   ? + x/?递归 直到n 与 x/n相等
    @Better
    @Passed
    public static int newton(int x){
        if (x == 0){
            return 0;
        }
        //首先随便猜一个近似值 x，然后不断令 x 等于 x 和 n/x的平均数，迭代个六七次后 x的值就已经相当精确了
        return (int) sqrt(x,x);
    }

    //这个递归就是获取到那个趋近于根x的那个数  然后拿那个数去递归调用
    public static double sqrt(double n, int x){
        double ret = (n + x/n) /2;
        return ret == n ? n : sqrt(ret, x);
    }
}
