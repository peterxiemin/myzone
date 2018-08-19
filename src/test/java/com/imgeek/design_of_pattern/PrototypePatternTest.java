package com.imgeek.design_of_pattern;

import org.junit.Test;

import java.io.IOException;

public class PrototypePatternTest {

    @Test
    public void testPrototypePattern() throws CloneNotSupportedException, IOException, ClassNotFoundException {

        PersonComputer personComputer = new PersonComputer();
        personComputer.cpu = "4核心8线程";
        personComputer.ram = "16G,DDR4";

        PersonComputer personComputer1 = (PersonComputer) personComputer.getObjectByClone();
        System.out.println("cpu:" + personComputer1.cpu + ",ram:" + personComputer1.ram);

        PersonComputer personComputer2 = (PersonComputer) personComputer.getObjectBySerializable();
        System.out.println("cpu:" + personComputer2.cpu + ",ram:" + personComputer2.ram);
    }

}