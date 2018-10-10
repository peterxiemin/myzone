package com.imgeek.ioc;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author: peterxiemin
 * @date: 2018/10/10 10:06
 * @desc:
 **/

public class DynamicProxy {
    /**
     * new MethodInterceptor() {
     *
     * @Override public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
     * return null;
     * }
     */
    public static Object jdkProxyCreate(Object target, InvocationHandler invocationHandler) {

        if (invocationHandler == null) {
            throw new RuntimeException("no methodInterceptor define");
        }
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), invocationHandler);
    }

    /**
     * new MethodInterceptor() {
     *
     * @Override public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
     * return null;
     * }
     * }
     */
    public static <T> T cjlibProxyCreate(Object target, MethodInterceptor methodInterceptor) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        if (methodInterceptor == null) {
            throw new RuntimeException("no methodInterceptor define");
        }
        enhancer.setCallback(methodInterceptor);
        return (T) enhancer.create();
    }
}
