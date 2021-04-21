package com.shaodw.practice.RecurAndDP;

/**
 * @Auther: shaodw
 * @Date: 2020/2/5 18:50
 * @Description: 汉诺塔
 * 打印N层汉诺塔的移动过程
 */
public class Hanoi {

    public static void hanoi(int n){
        if (n > 0){
            func(n, "左", "右","中");
        }
    }

    //N表示1~N层汉诺塔       复杂度O(2 ^ N)
    private static void func(int N, String from, String to, String help){
        if (N == 1){
            System.out.println("move " + N + " from " + from + " to " + to);
        }else {
            func(N - 1, from, help, to);
            System.out.println("move " + N + " from " + from + " to " + to);
            func(N - 1, help, to, from);
        }
    }

    public static void main(String[] args) {
        int N = 3;
        hanoi(N);

        System.out.println("如果想不出上面这么好的抽象 使用下面这种试法也可以解决这个问题");
        fromLeftToRight(N);
    }

    private static void fromLeftToMiddle(int N){
        if (N == 1){
            System.out.println("move " + N + " from 左 to 中");
            return;
        }
        fromLeftToRight(N - 1);
        System.out.println("move " + N + " from 左 to 中");
        fromRightToMiddle(N - 1);
    }

    private static void fromLeftToRight(int N){
        if (N == 1){
            System.out.println("move " + N + " from 左 to 右");
            return;
        }
        fromLeftToMiddle(N - 1);
        System.out.println("move " + N + " from 左 to 右");
        fromMiddleToRight(N - 1);
    }

    private static void fromRightToMiddle(int N){
        if (N == 1){
            System.out.println("move " + N + " from 右 to 中");
            return;
        }
        fromRightToLeft(N - 1);
        System.out.println("move " + N + " from 右 to 中");
        fromLeftToMiddle(N - 1);
    }

    private static void fromRightToLeft(int N){
        if (N == 1){
            System.out.println("move " + N + " from 右 to 左");
            return;
        }
        fromRightToMiddle(N - 1);
        System.out.println("move " + N + " from 右 to 左");
        fromMiddleToLeft(N - 1);
    }

    private static void fromMiddleToLeft(int N){
        if (N == 1){
            System.out.println("move " + N + " from 中 to 左");
            return;
        }
        fromMiddleToRight(N - 1);
        System.out.println("move " + N + " from 中 to 左");
        fromRightToLeft(N - 1);
    }

    private static void fromMiddleToRight(int N){
        if (N == 1){
            System.out.println("move " + N + " from 中 to 右");
            return;
        }
        fromMiddleToLeft(N - 1);
        System.out.println("move " + N + " from 中 to 右");
        fromLeftToRight(N - 1);
    }


}
