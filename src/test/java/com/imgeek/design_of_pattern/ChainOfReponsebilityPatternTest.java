package com.imgeek.design_of_pattern;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChainOfReponsebilityPatternTest {

    @Test
    public void demoShow() throws Exception {
        Husband husband = new Husband();
        Father father = new Father();
        Son son = new Son();
        husband.setIHandlerNext(father);
        father.setIHandlerNext(son);

        Women women = new Women();
        women.addHandler(husband);
        women.chainHandle();
    }
}