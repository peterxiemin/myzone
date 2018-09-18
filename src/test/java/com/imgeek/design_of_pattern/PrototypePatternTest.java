package com.imgeek.design_of_pattern;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;

/**
 * auth:    xiemin
 * date:    2018-09-17
 */

@Slf4j
public class PrototypePatternTest {

    @Test
    public void testPrototypePattern() throws CloneNotSupportedException, IOException, ClassNotFoundException {

        PersonComputer personComputer = new PersonComputer();
        personComputer.cpu = "4核心8线程";
        personComputer.ram = "16G,DDR4";

        PersonComputer personComputer1 = (PersonComputer) personComputer.getObjectByClone();
        log.info("cpu:" + personComputer1.cpu + ",ram:" + personComputer1.ram);

        PersonComputer personComputer2 = (PersonComputer) personComputer.getObjectBySerializable();
        log.info("cpu:" + personComputer2.cpu + ",ram:" + personComputer2.ram);
    }

}