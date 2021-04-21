package com.shaodw.zuo_god_book.stackAndqueue;


/**
 * @Auther: shaodw
 * @Date: 2020/3/14 11:24
 * @Description: 使用全局变量记录steps 作为比较器看是否正确
 */
public class HanoiOtherRecordSteps {
    private static int steps = 0;

    private static void move(String from, String to, String help, int N){
        if (N == 1){
            System.out.println("move " + N + " from " + from + " to " + help);
            System.out.println("move " + N + " from " + help + " to " + to);
            steps += 2;
            return;
        }

        move(from, to, help, N - 1);
        System.out.println("move " + N + " from " + from + " to " + help);
        steps++;
        move(to, from, help, N - 1);
        System.out.println("move " + N + " from " + help + " to " + to);
        steps++;
        move(from, to, help, N - 1);
    }

    public static void moveHanoi(int N){
        if (N <= 0){
            return;
        }
        move("left", "right", "middle", N);
        System.out.println("move " + steps + " steps");
    }

    public static void main(String[] args) {
        int N = 3;
        moveHanoi(N);
    }
}
