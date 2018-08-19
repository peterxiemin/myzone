package com.imgeek.design_of_pattern;

import org.junit.Test;

public class AbstractfactoryPatternTest {

    @Test
    public void testAbstractFactoryPattern(){

        PhoneProductFactory factory = new AndriodPhoneProductFactory();
        factory.getCharger().chargerMessage();
        factory.getPhone().phoneMessage();

        factory = new IOSPhoneProductFactory();
        factory.getCharger().chargerMessage();
        factory.getPhone().phoneMessage();
    }

}