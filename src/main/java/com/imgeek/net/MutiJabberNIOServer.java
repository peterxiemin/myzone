package com.imgeek.net;


/**
 * @author: xiemin
 * @date: 2018-09-19
 * @desc: 多线程nio_socket服務端
 */

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;


@Slf4j
class SelectAcceptor implements Runnable {
    private int port = 5763;

    private int capacity = 1024;

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

@Slf4j
class SelectProcess implements Runnable {

    private Queue<SocketChannel> socketChannelQueue;

    private Selector readSelector;

    private Selector writeSelector;

    private ByteBuffer readByteBuffer;

    private ByteBuffer writeByteBuffer;

    private int capacity = 1024;

    SelectProcess(Queue socketChannelQueue) throws IOException {
        this.socketChannelQueue = socketChannelQueue;
        this.readSelector = Selector.open();
        this.writeSelector = Selector.open();
        this.readByteBuffer = ByteBuffer.allocate(capacity);
        this.writeByteBuffer = ByteBuffer.allocate(capacity);
    }

    @Override
    public void run() {
        while (true) {
            try {
                registerReadSocket();
                readFromSockets();
                writeToSockets();
                Thread.sleep(100);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void writeToSockets() throws IOException {
        int readyWrite = writeSelector.selectNow();
        log.debug("write_from_socket, readyWrite:{}", readyWrite);
        if (readyWrite > 0) {
            Set<SelectionKey> selectionKeys = writeSelector.selectedKeys();
            Iterator<SelectionKey> itr = selectionKeys.iterator();
            while (itr.hasNext()) {
                SelectionKey key = itr.next();
                writeToSocket(key);
                itr.remove();
            }
            selectionKeys.clear();
        }
    }

    private int writeToSocket(SelectionKey key) throws IOException {
        int bytesWriten = 0;
        int totalBytesWriten = bytesWriten;
        SocketChannel socketChannel = (SocketChannel) key.attachment();
        writeByteBuffer.put("Hello I am server!\n".getBytes());
        writeByteBuffer.flip();
        bytesWriten = socketChannel.write(writeByteBuffer);
        totalBytesWriten += bytesWriten;
        while (bytesWriten > 0) {
            bytesWriten = socketChannel.write(writeByteBuffer);
            totalBytesWriten += bytesWriten;
        }
        key.cancel();
        writeByteBuffer.clear();
        log.info("totalBytesWriten :{}", totalBytesWriten);
        return totalBytesWriten;
    }

    private void readFromSockets() throws IOException {
        int readyRead = readSelector.selectNow();
        if (readyRead > 0) {
            Set<SelectionKey> selectionKeySet = readSelector.selectedKeys();
            Iterator<SelectionKey> itr = selectionKeySet.iterator();
            log.info("readyRead : {}, itrSize: {}", readyRead, selectionKeySet.size());
            while (itr.hasNext()) {
                SelectionKey selectionKey = itr.next();
                readFromSocket(selectionKey);
                itr.remove();
            }
            selectionKeySet.clear();
        }
    }

    private void registerWriteSocket(SelectionKey selectionKey) throws ClosedChannelException {
        SocketChannel socketChannel = (SocketChannel) selectionKey.attachment();
        if (socketChannel != null) {
            log.info("register write socket");
            socketChannel.register(writeSelector, SelectionKey.OP_WRITE, socketChannel);
        }
    }

    private int readFromSocket(SelectionKey selectionKey) throws IOException {
        log.info("read_from_socket");
        int bytesReaded = 0;
        int totalBytesReaded = bytesReaded;

        try {
            SocketChannel socketChannel = (SocketChannel) selectionKey.attachment();
            bytesReaded = socketChannel.read(readByteBuffer);
            totalBytesReaded += bytesReaded;
            while (bytesReaded > 0) {
                bytesReaded = socketChannel.read(readByteBuffer);
                totalBytesReaded += bytesReaded;
            }
            readByteBuffer.flip();
            //receive client socket close
            if (bytesReaded == -1) {
                throw new IOException("socketchannel read ret bytesReaded:".concat(String.valueOf(bytesReaded)));
            }
            log.info("bytesReaded : {}, receive {} from client.", bytesReaded, byteBufferToString(readByteBuffer));
            registerWriteSocket(selectionKey);
        } catch (IOException e) {
            log.info(e.getMessage());
            unRegisterReadSocket(selectionKey);
        } finally {
            log.info("readByteBuffer clear");
            readByteBuffer.clear();
            log.info("totalBytesReaded:{}", totalBytesReaded);
        }
        return totalBytesReaded;
    }

    private void unRegisterReadSocket(SelectionKey selectionKey) throws IOException {
        log.info("unRegisterReadSocket");
        selectionKey.attach(null);
        selectionKey.cancel();
        selectionKey.channel().close();
    }

    private String byteBufferToString(ByteBuffer readByteBuffer) {
        int i = 0;
        byte[] bytes = readByteBuffer.array();
        for (; i < bytes.length; i++) {
            if (bytes[i] == 0) break;
        }
        return (new String(bytes)).substring(0, i);
    }

    private void registerReadSocket() throws IOException {
        SocketChannel socketChannel = socketChannelQueue.poll();
        while (socketChannel != null) {
            log.info("register");
            socketChannel.configureBlocking(false);
            SelectionKey key = socketChannel.register(readSelector, SelectionKey.OP_READ);
            key.attach(socketChannel);
            socketChannel = socketChannelQueue.poll();
        }
    }
}

@Slf4j
public class MutiJabberNIOServer {
    private String host;

    public static int port;

    private int capacity = 1024;

    private Queue<SocketChannel> socketChannelQueue;

    MutiJabberNIOServer(String host, int port) {
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
        MutiJabberNIOServer mutiJabberServer = new MutiJabberNIOServer(null, port);
        mutiJabberServer.createServerSocketAndWaitConnection();
    }
}
