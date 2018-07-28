package com.imgeek.design_of_pattern;

/**
 * autor :xiemin
 * date  :2018-07-16
 * desc  :单例模式 这里注意static、final、synchronized的使用
 */
public class SingletonPattern {
    public static SingletonPattern _instance = null;

    public static final synchronized SingletonPattern getInstance() {
        if (_instance == null) {
            _instance = new SingletonPattern();
            return _instance;
        } else {
            return _instance;
        }
    }
}
