package com.shaodw.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Auther: shaodw
 * @Date: 2021/2/2 20:10
 * @Description: 更好的解法
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Better {
    String note() default "";

    String complex() default "";
}
