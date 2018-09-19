package com.imgeek.net;

import java.net.DatagramPacket;
import java.net.InetAddress;

/**
 * @author :    xiemin
 * @date: 2018-09-19
 * @desc: 数据报工具类
 */

public class Datagram {
    public static DatagramPacket toDatagram(String str, InetAddress inetAddress, int dstPort) {
        byte[] buf = new byte[str.length() + 1];
        str.getBytes(0, str.length(), buf, 0);
        return new DatagramPacket(buf, buf.length, inetAddress, dstPort);
    }

    public static String toString(DatagramPacket dp) {
        byte[] buf = dp.getData();

        int i = 0;
        for (; i < buf.length; i++) {
            if (buf[i] == 0) break;
        }
        return new String(buf, 0, i);
    }
}
