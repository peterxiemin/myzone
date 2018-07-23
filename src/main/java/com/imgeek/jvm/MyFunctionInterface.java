package com.imgeek.jvm;

/**
 * auth:  xiemin
 * date:    2018-07-17
 * desc:    函数接口
 */

@FunctionalInterface
interface FunctionInterface {
    public abstract void run();
}

class FunctionObject implements FunctionInterface {
    @Override
    public void run() {

    }

    public FunctionObject(FunctionInterface functionInterface1) {
    }
}

public class MyFunctionInterface {
    public static void main(String[] args) {
        /**
         * lambda表示函数接口
         */
        FunctionInterface functionInterface1 = () -> {
            System.out.println("Hello World");
        };

        /**
         * lambda表示内部匿名类
         */
        FunctionObject myObject = new FunctionObject(
                () -> {
                    System.out.println("Hello World");
                }
        );
    }
}