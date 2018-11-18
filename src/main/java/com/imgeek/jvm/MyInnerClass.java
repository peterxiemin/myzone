package com.imgeek.jvm;

/**
 * @author： xiemin
 * @date: 2018-09-17
 * @desc: 内部类
 */

class A {
    public static void echo() {
        System.out.println("aaa");
    }
}

public class MyInnerClass {
    public int var = 1;

    class InnerClass1 {
        public void add() {
            var++;
        }
    }

    class InnerClass2 {
        public int var = 1;

        public void add() {
            var++;
        }
    }

    /**
     * 外部类变量对内部类可见
     */
    public void add1() {
        InnerClass1 innerClass1 = new InnerClass1();
        innerClass1.add();
    }

    /**
     * 内部类出现同名或者同类型变量，则覆盖外部类变量
     */
    public void add2() {
        InnerClass2 innerClass2 = new InnerClass2();
        innerClass2.add();
    }

    /**
     * 内部类实例共享外部类变量
     */
    public void add3() {
        int size = 10;
        for (int i = 0; i < size; i++) {
            InnerClass1 innerClass1 = new InnerClass1();
            innerClass1.add();
        }
    }

    private static class LazyHolder {
        private static int i = 0;
        public static void incr() {
            System.out.println(i++);
        }

    }

    private static class A {
        static {
            System.out.printf("bbb");
        }
        public static void foo() {

        }
    }

    public static void main(String[] args) {
//        for (int i = 0; i < 10000; i++) {
//            new Thread(()-> {
//                LazyHolder.incr();
//            }).start();
//        }
        A.foo();
    }
}
