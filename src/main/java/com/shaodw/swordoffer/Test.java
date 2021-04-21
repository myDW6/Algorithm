package com.shaodw.swordoffer;

/**
 * @Auther: shaodw
 * @Date: 1/21/2020 23:14
 * @Description:
 */
public class Test {
    private static int bucket(int num, int len, int min, int max){
        return (int)((num - min) * len / (max - min));
    }

    private static int bucket(int num, int min, int max, int[] arr){
        return (int)((num - min) / (max - min) * arr.length);
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,5,6};
        int k = arr[1];
        System.out.println(bucket(2,5,1,6) == bucket(2, 1,6,arr));
    }

}
