package com.imgeek.design_of_pattern;

/**
 * 功能描述: 抽象工厂模式
 *
 * @author sunzhiqiang
 * @create 2018-08-19
 */
public class AbstractfactoryPattern {
}

/**
 * 功能描述: 抽象产品(充电器)
 */
interface Charger {

    void chargerMessage();
}

/**
 * 功能描述: 抽象产品(手机)
 */
interface Phone {

    void phoneMessage();
}

/**
 * 功能描述: 具体产品(安卓充电器)
 */
class AndroidCharger implements Charger {

    @Override
    public void chargerMessage() {

        System.out.println("这是安卓手机充电器.");
    }
}

/**
 * 功能描述: 具体产品(安卓手机)
 */
class AndroidPhone implements Phone {

    @Override
    public void phoneMessage() {

        System.out.println("这是安卓手机.");
    }
}

/**
 * 功能描述: 具体产品(ios充电器)
 */
class IOSCharger implements Charger {

    @Override
    public void chargerMessage() {

        System.out.println("这是苹果手机充电器");
    }
}

/**
 * 功能描述: 具体产品(ios手机)
 */
class IOSPhone implements Phone {

    @Override
    public void phoneMessage() {

        System.out.println("这是苹果手机.");
    }
}

/**
 * 功能描述: 抽象工厂
 */
interface PhoneProductFactory {

    Phone getPhone();

    Charger getCharger();
}

/**
 * 功能描述: 具体工厂(安卓手机产品工厂)
 */
class AndriodPhoneProductFactory implements PhoneProductFactory {

    @Override
    public Phone getPhone() {

        return new AndroidPhone();
    }

    @Override
    public Charger getCharger() {

        return new AndroidCharger();
    }
}

/**
 * 功能描述: 具体工厂(ios手机产品工厂)
 */
class IOSPhoneProductFactory implements PhoneProductFactory{

    @Override
    public Phone getPhone() {

        return new IOSPhone();
    }

    @Override
    public Charger getCharger() {

        return new IOSCharger();
    }
}



