package com.practice.shaodw.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Auther: shaodw
 * @Date: 2021/2/2 20:10
 * @Description: 通过leetcode测试后可以标注
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Passed {
    String note(); //笔记
    String idea() default ""; //想法
    String complex(); //复杂度
    Class<?> better() default Void.class; //是否有更好的解法
}
