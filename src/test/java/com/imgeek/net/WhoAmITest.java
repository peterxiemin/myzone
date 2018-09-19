package com.imgeek.net;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.junit.Assert.*;

@Slf4j
public class WhoAmITest {

    @Test
    public void ipTest() {
        String ret = "";
        try {
            ret = inetAddressGetNameTest("localhost");
        } catch (UnknownHostException e) {
            log.error(e.getMessage(), e);
        }
        assertEquals("localhost", ret);
    }

    @Test
    public void localhostTest() {
        String ret = "";
        try {
            ret = inetAddressGetNameTest(null);
        } catch (UnknownHostException e) {
            log.error(e.getMessage(), e);
        }
        assertEquals("localhost", ret);
    }

    public String inetAddressGetNameTest(String name) throws UnknownHostException {
        InetAddress inetAddress = null;
        inetAddress = InetAddress.getByName(name);
        return String.valueOf(inetAddress.getHostName());
    }
}