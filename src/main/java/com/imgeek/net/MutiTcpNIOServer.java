package com.imgeek.net;


/**
 * @author: xiemin
 * @date: 2018-09-19
 * @desc: 多线程nio_socket服務端
 */

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;


@Slf4j
public class MutiTcpNIOServer {
    private String host;

    public static int port;

    private int capacity = 1024;

    private Queue<SocketChannel> socketChannelQueue;

    MutiTcpNIOServer(String host, int port) {
        this.host = host;
        this.port = port;
        socketChannelQueue = new ArrayBlockingQueue(capacity);
    }

    public void createServerSocketAndWaitConnection() throws IOException {
        (new Thread(new SelectAcceptor(socketChannelQueue))).start();
        (new Thread(new SelectProcess(socketChannelQueue))).start();
    }

    public static void main(String[] args) throws IOException {
        int port = 5763;
        MutiTcpNIOServer mutiJabberServer = new MutiTcpNIOServer(null, port);
        mutiJabberServer.createServerSocketAndWaitConnection();
    }
}
