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
public class MutiTcpClient implements ITcpClient {
    private String host;
    private int port;
    private int threadNum;
    private CountDownLatch countDownLatch;
    private ITcpConmunicateToServerHandler iTcpConmunicateToServerHandler;

    public MutiTcpClient(String host, int port, int threadNum, ITcpConmunicateToServerHandler iTcpConmunicateToServerHandler) {
        this.host = host;
        this.port = port;
        this.threadNum = threadNum;
        this.iTcpConmunicateToServerHandler = iTcpConmunicateToServerHandler;
        this.countDownLatch = new CountDownLatch(threadNum);
    }

    @Override
    public void sendToServer() throws IOException, InterruptedException {
        for (int i = 0; i < threadNum; i++) {
            (new Thread(() -> {
                Socket socket = null;
                try {
                    socket = new Socket(InetAddress.getByName(host), port);
                    iTcpConmunicateToServerHandler.handle(socket);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
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
        MutiTcpClient mutiJabberClient = new MutiTcpClient(null, port, threadNum, new ITcpConmunicateToServerHandler() {

            @Override
            public void handle(Socket socket) throws IOException {
                BufferedReader in;
                PrintWriter out;
                String sendMsg = "I am client";
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                out.println(sendMsg);
                log.info("receive {} from server", in.readLine());
                out.println("END");
            }
        });
        mutiJabberClient.sendToServer();
    }


}
