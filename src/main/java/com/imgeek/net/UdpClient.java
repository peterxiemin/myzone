package com.imgeek.net;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.CountDownLatch;

/**
 * @author :    xiemin
 * @date: 2018-09-19
 * @desc: udp 客戶端
 */

@Slf4j
public class UdpClient {
    private int port;
    private CountDownLatch countDownLatch;
    private int threadNum;
    private InetAddress inetAddress;
    private DatagramSocket socket;

    UdpClient(String host, int port, int threadNum) throws UnknownHostException, SocketException {
        this.port = port;
        this.threadNum = threadNum;
        this.countDownLatch = new CountDownLatch(threadNum);
        this.inetAddress = InetAddress.getByName(host);
        this.socket = new DatagramSocket();
    }

    public void sendToServer(String str) throws InterruptedException {
        for (int i = 0; i < threadNum; i++) {
            (new Thread(() -> {
                DatagramPacket dp = Datagram.toDatagram(str, inetAddress, port);
                try {
                    socket.send(dp);
                    socket.receive(dp);
                    log.info("src_host:"
                            .concat(String.valueOf(dp.getAddress()))
                            .concat(" src_port:")
                            .concat(String.valueOf(dp.getPort()))
                            .concat(" content:")
                            .concat(Datagram.toString(dp)));
                } catch (SocketException e) {
                    log.error(e.getMessage(), e);
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
                countDownLatch.countDown();
            })).start();
        }

        countDownLatch.await();
    }

    public static void main(String[] args) throws SocketException, UnknownHostException, InterruptedException {

        int port = 5763;
        int threadNum = 50;
        UdpClient chatterClient = new UdpClient(null, port, threadNum);
        chatterClient.sendToServer("xieminshitiancai");
    }
}
