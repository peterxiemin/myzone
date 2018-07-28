package com.imgeek.design_of_pattern;

import org.junit.Test;

import static org.junit.Assert.*;

public class SingletonPatternTest {
    @Test
    public void demoShow() {
        final int NUM = 10;
        for (int i = 0; i < NUM; i++) {
            System.out.println(SingletonPattern.getInstance());
        }
    }
}