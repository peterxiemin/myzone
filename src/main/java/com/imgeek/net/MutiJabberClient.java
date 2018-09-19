package com.imgeek.net;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.CountDownLatch;

/**
 * @author :    xiemin
 * @date: 2018-09-19
 * @desc: 多线程socket客戶端
 */

@Slf4j
public class MutiJabberClient {
    private String host;
    private int port;
    private int threadNum;
    private CountDownLatch countDownLatch;

    public MutiJabberClient(String host, int port, int threadNum) throws IOException {
        this.host = host;
        this.port = port;
        this.threadNum = threadNum;
        countDownLatch = new CountDownLatch(threadNum);
    }

    public void sendToServer(String str) throws InterruptedException {
        for (int i = 0; i < threadNum; i++) {
            (new Thread(() -> {
                BufferedReader in;
                PrintWriter out;
                Socket socket = null;
                try {
                    socket = new Socket(InetAddress.getByName(host), port);
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                    out.println(str);
                    log.info("receive {} from server", in.readLine());
                    out.println("END");
                } catch (UnknownHostException e) {
                    log.error(e.getMessage(), e);
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                } finally {
                    try {
                        if (socket != null)
                            socket.close();
                    } catch (IOException e) {
                        log.error(e.getMessage(), e);
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            })).start();
        }
        countDownLatch.await();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        int port = 5763;
        int threadNum = 50;
        MutiJabberClient mutiJabberClient = new MutiJabberClient(null, port, threadNum);
        mutiJabberClient.sendToServer("xieminshitiancai");
    }
}
