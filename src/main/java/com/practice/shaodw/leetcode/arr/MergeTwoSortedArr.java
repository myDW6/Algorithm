package com.practice.shaodw.leetcode.arr;

import com.shaodw.anno.Better;
import com.shaodw.anno.Passed;

import java.util.Arrays;

/**
 * @Auther: shaodw
 * @Date: 2021/5/11 23:04
 * @Description: 合并两个有序数组
 *给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
 * 初始化 A 和 B 的元素数量分别为 m 和 n。
 * A = [1,2,3,0,0,0], m = 3
 * B = [2,5,6],       n = 3
 *
 * 输出: [1,2,2,3,5,6]
 */
public class MergeTwoSortedArr {
    public static void main(String[] args) {
         int[] A = {1,2,3,0,0,0};
         int m = 3;
         int[] B = {2,5,6};
         int n = 3;
        int[] C = {1,2,3,0,0,0};
        int[] E = {1,2,3,0,0,0};
        int[] D = {2,5,6};
        int[] F = {2,5,6};
        System.out.println(Arrays.toString(mustRight(A, m, B, n)));
        System.out.println(Arrays.toString(merge(C, m, D, n)));
        System.out.println(Arrays.toString(mergeFromEnd(E, m, F, n)));
    }

    @Passed(complex = "排序代价NLogN")
    public static int[] mustRight(int[] A, int m, int[] B, int n){
        //对数器直接使用jdk api
        System.arraycopy(B, 0, A, m, n);
        Arrays.sort(A);
        return A;
    }

    @Passed(complex = "m + n, 空间复杂度O(M+N)" , note = "merge过程写烂了的写法")
    public static int[] merge(int[] A, int m, int[] B, int n) {
        int[] A_bak = new int[A.length];
        //需要返回的是A
        System.arraycopy(A, 0, A_bak, 0, m);
        int i = 0;
        int p1 = 0;
        int p2 = 0;
        while (p1 < m && p2 < n){
            A[i++] = A_bak[p1] < B[p2] ?  A_bak[p1++] :B[p2++];
        }
        while (p1 < m){
            A[i++] = A_bak[p1++];
        }
        while (p2 < n){
            A[i++] = B[p2++];
        }
        return A;
    }
    //但是归并这种写法就是耗费空间   其实这个过程可以反过来  从后往前遍历
    @Better
    @Passed(complex = "时间复杂度O(M+N), 空间复杂度O(1)")
    public static int[] mergeFromEnd(int[] A, int m, int[] B, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        int i = A.length - 1;
        while (p1 >= 0 && p2 >= 0){
            A[i--] = A[p1] > B[p2] ? A[p1--] : B[p2--];
        }
        while (p1 >= 0){
            A[i--] = A[p1--];
        }
        while (p2 >= 0){
            A[i--] = B[p2--];
        }
        return A;
    }
}
