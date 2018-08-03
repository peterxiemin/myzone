package com.imgeek.design_of_pattern;

import org.junit.Test;

import static org.junit.Assert.*;

public class DecoratorPatternTest {
    @Test
    public void demoShow() {
        ConcreateComponent concreateComponent = new ConcreateComponent();
        ConcreateDecorator concreateDecorator = new ConcreateDecorator(concreateComponent);
        concreateDecorator.operation();
    }
}