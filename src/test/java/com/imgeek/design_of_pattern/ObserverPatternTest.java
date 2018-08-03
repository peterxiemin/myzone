package com.imgeek.design_of_pattern;

import org.junit.Test;

import static org.junit.Assert.*;

public class ObserverPatternTest {
    @Test
    public void demoShow() {
        Lisi lisi = new Lisi();
        HanFeizi hanFeizi = new HanFeizi();
        hanFeizi.register(lisi);
        hanFeizi.haveBreakfast();
    }
}