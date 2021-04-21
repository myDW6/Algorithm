package com.shaodw.datastructure.hash;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 哈希函数
 */
public class Main {
    public static void main(String[] args) {
        //java的hashcode返回一个int值 有符号
        // 将hashcode值转化为一个索引 需要在哈希表的类中处理
        int a = 42;
        System.out.println(((Integer) a).hashCode());

        int b = -42;
        System.out.println(((Integer) b).hashCode());

        double c = 3.1415926;
        System.out.println(((Double)c).hashCode());

        String shaodw = "shaodw";
        System.out.println(shaodw.hashCode());
        System.out.println(new String("shaodw").hashCode());

        Stu s1 = new Stu(3,2,"dw", "shao");
        System.out.println(s1.hashCode());//DEC 3720111 == HEX 38c3af
        Stu s2 = new Stu(3,2,"DW", "SHao");
        System.out.println(s2.hashCode());//com.shaodw.datastructure.hash.Stu@38c3af

        HashSet<Stu> set = new HashSet<>();//java提供的底层为哈希表的集合
        HashMap<Stu,Integer> map = new HashMap<>();//java提供的底层为哈希表的映射
        set.add(s1);//将对象加入哈希表
        map.put(s2,100);//将对象加入哈希表
        //当使用java提供的哈希表hashset hashmap时，自己写的hashcode只是用于帮助计算哈希函数的值
        //在产生哈希冲突时同样需要比较两个不同对象是否相等 哈希函数值相等 但产生了哈希冲突 为了真正辨别两个类的不同 使得类称为哈希表的键 则需要实现equals方法
    }
}
