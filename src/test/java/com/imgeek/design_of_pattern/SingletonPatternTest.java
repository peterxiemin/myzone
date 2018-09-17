package com.imgeek.design_of_pattern;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * auth:    xiemin
 * date:    2018-09-17
 */

@Slf4j
public class SingletonPatternTest {
    @Test
    public void demoShow() {
        final int NUM = 10;
        for (int i = 0; i < NUM; i++) {
            log.info(String.valueOf(SingletonPattern.getInstance()));
        }
    }
}