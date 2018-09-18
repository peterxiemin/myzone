package com.imgeek.locks;

public class MyThreadLocal<T> {
    private ThreadLocal<T> threadLocal;
    MyThreadLocal() {
        threadLocal = new ThreadLocal<>();
    }

    public void set(T value) {
        threadLocal.set(value);
    }

    public T get() {
        return threadLocal.get();
    }
}