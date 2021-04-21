package com.shaodw.sort.sort_extension;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Auther: shaodw
 * @Date: 2020/3/24 18:11
 * @Description:
 */
public class Student implements Comparable<Student>{
    String name;
    int id;
    int[] grade = new int[2];

    @Override
    public int compareTo(Student o) {
        return this.grade[0] - o.grade[0];
    }





}
