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


/**
 * double-check防止多綫程初始化失败
 */
class SingletonPatternWithDoubleCheck {
    public volatile SingletonPatternWithDoubleCheck singletonPatternWithDoubleCheck = null;

    public SingletonPatternWithDoubleCheck getInstance() {
        SingletonPatternWithDoubleCheck spwdc = singletonPatternWithDoubleCheck;
        if (spwdc == null) {
            synchronized (this) {
                spwdc = singletonPatternWithDoubleCheck;
                if (spwdc == null) {
                    spwdc = new SingletonPatternWithDoubleCheck();
                    singletonPatternWithDoubleCheck = spwdc;
                    return singletonPatternWithDoubleCheck;
                }
            }
        }
        return null;
    }
}
