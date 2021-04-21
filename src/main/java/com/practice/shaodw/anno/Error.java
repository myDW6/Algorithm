package com.practice.shaodw.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Auther: shaodw
 * @Date: 2021/2/2 20:00
 * @Description: 错误的解法标注
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Error {
    String becauseOf();
}
