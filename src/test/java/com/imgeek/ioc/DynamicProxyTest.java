package com.imgeek.ioc;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * @author: peterxiemin
 * @date: 2018/10/10 19:01
 * @desc:
 **/

interface ISubject {
    void add();
    int getCounter();
}

class Subject implements ISubject {
    public int counter = 0;
    @Override
    public void add() {
        counter++;
    }

    @Override
    public int getCounter() {
        return counter;
    }
}

@Slf4j
public class DynamicProxyTest {
    private ISubject iSubject;
    @Before
    public void setup() {
        iSubject = new Subject();
    }

    /**
     * 注意这里的subjectProxy一定要重现创建对象引用,否则会报错
     */
    @Test
    public void jdkProxyCreateTest() {
        ISubject subjectProxy = (ISubject) DynamicProxy.jdkProxyCreate(iSubject, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                log.info("jdkproxy invoke");
                return method.invoke(iSubject, args);
            }
        });
        subjectProxy.add();
        subjectProxy.add();
        assertEquals(2, subjectProxy.getCounter());
    }

    @Test
    public void cjlibProxyCreateTest() {
        ISubject subjectProxy = (ISubject) DynamicProxy.cjlibProxyCreate(iSubject, new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                log.info("jdkproxy invoke");
                return method.invoke(iSubject, objects);
            }
        });
        subjectProxy.add();
        subjectProxy.add();
        assertEquals(2, subjectProxy.getCounter());
    }
}