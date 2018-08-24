package com.imgeek.jvm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Parent {
    private final static Logger log = LoggerFactory.getLogger(Parent.class);
    public String str;

    public Parent(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}

class Child extends Parent {
    private final static Logger log = LoggerFactory.getLogger(Child.class);
    private String str;

    public Child(String str) {
        super(str);
    }

    @Override
    public String getStr() {
        return str;
    }

    public String getParentStr() {
        return super.getStr();
    }
}

public class MyExtends {
}
