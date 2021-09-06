package com.hh.core.basic.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by hh on 2020/1/3.
 * 定义一个注解，可以在class、interface、enum上标注
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyTargetType {

    /**
     * 定义注解的一个元素，并给定默认值
     */
    String value() default "我是定义在类接口枚举类上的注解元素value的默认值";

}
