package com.imgeek.net;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * @author :    xiemin
 * @date: 2018-09-19
 * @desc: udp服务端
 */

@Slf4j
public class UdpServer {
    private int port;
    private DatagramPacket dp;
    private byte[] buf;

    public UdpServer(int port, int bufLength) throws SocketException {
        this.port = port;
        buf = new byte[bufLength];
        dp = new DatagramPacket(buf, bufLength);

    }

    public void createDatagramSocketAndWaitConnection() throws IOException {
        DatagramSocket socket = new DatagramSocket(port);
        while (true) {
            socket.receive(dp);
            String str = Datagram.toString(dp);
            InetAddress inetAddress = dp.getAddress();
            int port = dp.getPort();
            log.info("src_host:".concat(inetAddress.getHostName()).concat(" src_port:").concat(String.valueOf(port)).concat(" content:").concat(str));

            dp = Datagram.toDatagram(str, inetAddress, port);
            socket.send(dp);
        }
    }

    public static void main(String[] args) throws SocketException {
        int port = 5763;
        int bufLength = 1024;
        UdpServer chatterServer = new UdpServer(port, bufLength);
        try {
            chatterServer.createDatagramSocketAndWaitConnection();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
