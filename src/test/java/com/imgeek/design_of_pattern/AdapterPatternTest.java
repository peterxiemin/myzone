package com.imgeek.design_of_pattern;

import org.junit.Test;

/**
 * @author :xiemin
 * @date: 2018-09-19
 */
public class AdapterPatternTest {

    @Test
    public void testAdapterPattern() {

        ElectricCompary electricCompary = new ElectricCompary();

        Computer computer = new Computer();
        computer.play(new ElectricAdapter(electricCompary));
    }
}