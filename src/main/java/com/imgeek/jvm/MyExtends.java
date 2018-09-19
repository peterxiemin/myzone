package com.imgeek.jvm;

/**
 * @author :    xiemin
 * @date: 2018-09-18
 */

class Parent {
    public String str = "parent";

    public String getStr() {
        return str;
    }

    public String getSelfStr() {
        return str;
    }
}

class Child extends Parent {
    private String str = "child";

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
