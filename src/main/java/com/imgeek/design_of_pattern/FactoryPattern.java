package com.imgeek.design_of_pattern;

/**
 * auth:    xiemin
 * date:    2018-07-28
 * desc:    工厂类模式
 */

abstract class Product {
    public abstract void method();
}

class ConcreteProduct extends Product {

    @Override
    public void method() {
        System.out.println("I am ConcreteProduct");
    }
}

abstract class Creator {
    public abstract <T extends Product> T createProduct(Class<T> c);
}

class ConcreCreator extends Creator {

    @Override
    public <T extends Product> T createProduct(Class<T> c) {
        Product product = null;
        try {
            product = (Product) Class.forName(c.getName()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return (T) product;
    }
}



public class FactoryPattern {
}
