package com.imgeek.design_of_pattern;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author :xiemin
 * @date: 2018-09-19
 */
public class DecoratorPatternTest {
    @Test
    public void demoShow() {
        ConcreateComponent concreateComponent = new ConcreateComponent();
        ConcreateDecorator concreateDecorator = new ConcreateDecorator(concreateComponent);
        concreateDecorator.operation();
    }
}