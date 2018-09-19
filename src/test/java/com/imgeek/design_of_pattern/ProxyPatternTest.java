package com.imgeek.design_of_pattern;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author :xiemin
 * @date: 2018-09-19
 */
public class ProxyPatternTest {
    @Test
    public void demoShow() {
        IGamePlayer iGamePlayer = new GamerPlayerProxy("peter");
        iGamePlayer.killBoss();
    }
}