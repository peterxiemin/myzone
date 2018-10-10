package com.imgeek.net;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author :    xiemin
 * @date: 2018-09-19
 * @desc: socket客戶端
 */

@Slf4j
public class TcpClient implements ITcpClient {
    private String host;
    private int port;
    private Socket socket;
    private ITcpConmunicateToServerHandler iTcpConmunicateToServerHandler;

    public TcpClient(String host, int port, ITcpConmunicateToServerHandler iTcpConmunicateToServerHandler) throws IOException {
        this.host = host;
        this.port = port;
        this.socket = new Socket(InetAddress.getByName(host), port);
        this.iTcpConmunicateToServerHandler = iTcpConmunicateToServerHandler;
    }

    public void sendToServer() throws IOException {
        iTcpConmunicateToServerHandler.handle(socket);
    }

    public static void main(String[] args) throws IOException {
        int port = 5763;
        TcpClient jabberClient = new TcpClient(null, port, new ITcpConmunicateToServerHandler() {
            @Override
            public void handle(Socket socket) throws IOException {
                BufferedReader in;
                PrintWriter out;
                String sendMsg = "I am client";
                try {
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                    out.println(sendMsg);
                    log.info("receive {} from server", in.readLine());
                    out.println("END");
                } finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        log.error(e.getMessage(), e);
                    }
                }
            }
        });
        try {
            jabberClient.sendToServer();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
