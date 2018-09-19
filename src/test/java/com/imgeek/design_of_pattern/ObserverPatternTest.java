package com.imgeek.design_of_pattern;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author :xiemin
 * @date: 2018-09-19
 */
public class ObserverPatternTest {
    @Test
    public void demoShow() {
        Lisi lisi = new Lisi();
        HanFeizi hanFeizi = new HanFeizi();
        hanFeizi.register(lisi);
        hanFeizi.haveBreakfast();
    }
}