package com.imgeek.jvm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


/**
 * 对象中clone方法的使用
 *
 * @author: xiemin
 * @Date: 2018-08-24
 */

class DepObj {
    public int a;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}

class Thing implements Cloneable {
    private final static Logger log = LoggerFactory.getLogger(Thing.class);
    private int i;
    private char c;
    private float f;
    private double d;
    private long l;
    private boolean bo;
    private short s;
    private byte by;

    private String str;
    private List<String> arrayList = new ArrayList<>();

    private DepObj depObj;

    public Thing() {
        log.info("construct");
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }

    public float getF() {
        return f;
    }

    public void setF(float f) {
        this.f = f;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public long getL() {
        return l;
    }

    public void setL(long l) {
        this.l = l;
    }

    public boolean isBo() {
        return bo;
    }

    public void setBo(boolean bo) {
        this.bo = bo;
    }

    public short getS() {
        return s;
    }

    public void setS(short s) {
        this.s = s;
    }

    public byte getBy() {
        return by;
    }

    public void setBy(byte by) {
        this.by = by;
    }

    public List<String> getArrayList() {
        return arrayList;
    }

    public void setArrayList(List<String> arrayList) {
        this.arrayList = arrayList;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public DepObj getDepObj() {
        return depObj;
    }

    public void setDepObj(DepObj depObj) {
        this.depObj = depObj;
    }

    @Override
    public Thing clone() {
        Thing thing = null;
        try {
            thing = (Thing) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return thing;
    }
}

public class MyClone {

}
