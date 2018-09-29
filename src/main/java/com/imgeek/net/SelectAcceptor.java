package com.imgeek.net;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Queue;

/**
 * @author: xiemin
 * @date: 2018/9/26 20:49
 */
@Slf4j
class SelectAcceptor implements Runnable {
    private int port = 5763;

    private Queue<SocketChannel> socketChannelQueue;

    SelectAcceptor(Queue socketChannelQueue) {
        this.socketChannelQueue = socketChannelQueue;
    }

    @Override
    public void run() {
        ServerSocketChannel serverSocketChannel = null;
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(port));
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                SocketChannel socketChannel = serverSocketChannel.accept();
                log.info("accept");
                socketChannelQueue.add(socketChannel);
                Thread.sleep(100);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}