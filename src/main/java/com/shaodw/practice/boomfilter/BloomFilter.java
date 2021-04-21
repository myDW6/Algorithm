package com.shaodw.practice.boomfilter;

/**
 * @Auther: shaodw
 * @Date: 2020/2/4 20:07
 * @Description: 布隆过滤器
 * 主要用在黑名单过滤 爬虫去重
 * 实现这样一个逻辑： 假若现在有一批url 有些是不想被访问到的 那我们将其加入黑名单
 * 布隆过滤器可以实现是黑名单上的url不会误报 但是不是黑名单上的url可能被误报成需要过滤掉  （错杀一千 不放一个）
 * 布隆过滤器是一个大的bit数组
 */
public class BloomFilter {
    public static void main(String[] args) {
        //如何表示bit数组 使用基本数据类型造出bit数组
        int[] arr = new int[1000];//1000 * 32 = 32000bit
        //将17662号bit上描黑
        int black = 17662;
        int intIndex = black / 32;  //551
        int bitIndex = black % 32;  //30
        arr[intIndex] = (arr[intIndex] | (1 << bitIndex));
        //1 左移bitIndex位 那么该bitIndex位上值描黑

        //我们得到一个0 - m-1 bit的数组
        //接下来使用k个hash函数对url求出hashcode 再%m 得到的值位置上描黑 已经为黑的继续为黑
        //查询一个url是否加入布隆过滤器的逻辑是： 用这k个hash函数对url运算 对m取模 得到的位置若全为1 （描黑） 才认为是黑名单上的url 只要有一个不为黑都不是
        System.out.println(intIndex); //551
        System.out.println(bitIndex); //50
        System.out.println(arr[intIndex]);//1073741824 -> 0100 0000 0000 0000 0000 0000 0000 0000 arr[511]偏移30bit位上已经描黑（为1）

        //将数组做大
        long[] longArr = new long[1000];//1000 * 64
        long[][] longMatrix = new long[1000][1000];//1000 * 1000 * 64
    }
}
