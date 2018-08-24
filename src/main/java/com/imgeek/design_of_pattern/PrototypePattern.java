package com.imgeek.design_of_pattern;

import java.io.*;

/**
 * 功能描述: 原型模式
 *
 * @author sunzhiqiang
 * @create 2018-08-19
 */
public class PrototypePattern {
}

/**
 * 抽象原型
 */
interface Prototype extends Cloneable, Serializable {

    Object getObjectByClone() throws CloneNotSupportedException;

    Object getObjectBySerializable() throws IOException, ClassNotFoundException;
}

/**
 * 具体原型
 */
class PersonComputer implements Prototype {

    String cpu;
    String ram;

    @Override
    public Object getObjectByClone() throws CloneNotSupportedException{

        Object obj = clone();
        return obj;
    }

    @Override
    public Object getObjectBySerializable() throws IOException, ClassNotFoundException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream objOut = new ObjectOutputStream(out);
        objOut.writeObject(this);

        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        ObjectInputStream objIn = new ObjectInputStream(in);
        Object obj = objIn.readObject();

        return obj;
    }

    /*
     * 继续使用protected修饰,不对外界直接暴露,让外界的程序不能通过这个方法来使用原型模式
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

