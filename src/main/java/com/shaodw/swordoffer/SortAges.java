package com.shaodw.swordoffer;

import java.util.Arrays;

/**
 * @Auther: shaodw
 * @Date: 2020-01-17 21:38
 * @Description:
 * A:请实现一个排序算法 要求时间复杂度为O(n)
 * B:对什么数字进行排序
 * A:对公司员工进行排序
 * B:也就是说数字的大小在一个较小范围内？
 * A:是的
 * B:可以使用辅助空间吗？
 * A:只能使用常量大小辅助空间 不得超过O(N)
 */
public class SortAges {
    //应该想到桶排序
    public static void sortAges(int[] ages){
        if (ages == null || ages.length < 2)
            return;
        final int OldestAge = 99;
        int[] buckets =  new int[OldestAge + 1];
        for (int i = 0; i <= OldestAge; i++) {
            buckets[i] = 0;
        }

        for (int i = 0; i < ages.length; i++) {
            if (ages[i] < 0 || ages[i] > OldestAge)
                throw new RuntimeException("age out of range");
            buckets[ages[i]]++;
        }

        int index = 0;
        for (int i = 0; i <= OldestAge; i++) {
            for (int j = 0; j < buckets[i]; j++) {
                ages[index++] = i;
            }
        }
    }

    public static void main(String[] args) {
        int[] ages = {43,23,13,0,11,54,89,99};
        sortAges(ages);
        Arrays.stream(ages).forEach(System.out::println);
    }
}
