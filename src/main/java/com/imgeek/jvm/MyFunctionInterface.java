package com.imgeek.jvm;

/**
 * auth:  xiemin
 * date:    2018-07-17
 * desc:    函数接口
 */

/**
 * 与其他接口区别是
 * 1、编译器只允许一个函数定义
 * 2、可以使用lamda表达式
 */

@FunctionalInterface
public interface MyFunctionInterface {
    void apply();
}