package com.imgeek.design_of_pattern;

import org.junit.Test;

public class AdapterPatternTest {

    @Test
    public void testAdapterPattern() {

        ElectricCompary electricCompary = new ElectricCompary();

        Computer computer = new Computer();
        computer.play(new ElectricAdapter(electricCompary));
    }
}