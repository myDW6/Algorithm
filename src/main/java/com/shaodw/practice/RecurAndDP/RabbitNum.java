package com.shaodw.practice.RecurAndDP;

/**
 * @Auther: shaodw
 * @Date: 2020/2/28 21:15
 * @Description:
 */
public class RabbitNum {
    public static long rabbitNum(long N){
       return N == 1 || N == 2 || N == 3 || N == 4 ? 1 : rabbitNum(N - 1) + rabbitNum(N - 4);
    }

    public static long rabbitNums(long N){
        if (N == 1 || N == 2 || N == 3 || N == 4){
            return 1;
        }
        long res = 1;
        long pre = 1;
        long prepre = 1;
        long preprepre = 1;
        long tmp1;
        long tmp2;
        long tmp3;
        for (int i = 5; i <= N; i++) {
            tmp1 = res;
            tmp2 = pre;
            tmp3 = prepre;
            res += preprepre;
            preprepre = tmp3;
            prepre = tmp2;
            pre = tmp1;
        }
        return res;
    }

    public static long rabbitNumsSelf(long N){
        if (N == 1 || N == 2 || N == 3 || N == 4){
            return 1;
        }
        long res = 0;
        long pre = 1;
        long prepre = 1;
        long preprepre = 1;
        long prepreprepre = 1;
        for (int i = 5; i <= N; i++){
            res = pre + prepreprepre;
            prepreprepre = preprepre;
            preprepre = prepre;
            prepre = pre;
            pre = res;
        }
        return res;
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            //System.out.println(rabbitNum(i));
            System.out.println(rabbitNums(i));
        }
    }

    //fn = fn - x ... fn- y
}
