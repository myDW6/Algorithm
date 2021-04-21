package com.shaodw.practice.binarysearch;

/**
 * @Auther: shaodw
 * @Date: 2020/3/17 10:26
 * @Description:
 */
public class Bse {


    static int bs1(int[] arr, int target){
        int l = 0;
        int r = arr.length;//r是可行域的下一个位置   终止条件是 l < r
        //这样如果区间往左侧转移 l = l  r = mid 往右侧转移 l = mid + 1  r = r
        //这样写的好处是如果区间缩小为1为答案的话 那l就是答案
        int mid;
        while (l < r){
            mid = l + (r - l >> 1);
            if (arr[mid] == target){
                return mid;
            } else if (arr[mid] > target){
              r = mid;
            } else {
              l = mid + 1;
            }
        }
        System.out.println(l);
        System.out.println(r);
        return -1;
    }

    static int bs2(int[] arr, int target){
        int l = 0;
        int r = arr.length - 1;
        int mid;
        while (l <= r){   //l <= r代表区间还有1的长度 这1的长度有可能行也有可能不行  如果行返回答案  如果不行 让区间长度缩小为0再跳出循环
            mid = l + (r - l >> 1);
            if (arr[mid] == target){
                return mid;
            } else if (arr[mid] > target){
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        System.out.println(l);
        System.out.println(r);
        return -1;
    }


    public static void main(String[] args) {
        int[] arr = {1,2,2,4,5,6};
        bs1(arr, 3);
        bs2(arr, 3);


        bs1(arr, 7);
        bs2(arr, 7);
//        System.out.println(bs1(arr, 0));
//        System.out.println(bs1(arr, 1));
//        System.out.println(bs1(arr, 2));
//        System.out.println(bs1(arr, 3));
//        System.out.println(bs1(arr, 4));
//        System.out.println(bs1(arr, 5));
//        System.out.println(bs1(arr, 6));
//        System.out.println(bs1(arr, 7));
//
//        System.out.println("==========");
//        System.out.println(bs2(arr, 0));
//        System.out.println(bs2(arr, 1));
//        System.out.println(bs2(arr, 2));
//        System.out.println(bs2(arr, 3));
//        System.out.println(bs2(arr, 4));
//        System.out.println(bs2(arr, 5));
//        System.out.println(bs2(arr, 6));
//        System.out.println(bs2(arr, 7));
    }
}
