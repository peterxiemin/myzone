package com.imgeek.design_of_pattern;

import org.junit.Test;

/**
 * @author :xiemin
 * @date: 2018-09-19
 */
public class AbstractfactoryPatternTest {

    @Test
    public void testAbstractFactoryPattern() {

        PhoneProductFactory factory = new AndriodPhoneProductFactory();
        factory.getCharger().chargerMessage();
        factory.getPhone().phoneMessage();

        factory = new IOSPhoneProductFactory();
        factory.getCharger().chargerMessage();
        factory.getPhone().phoneMessage();
    }

}