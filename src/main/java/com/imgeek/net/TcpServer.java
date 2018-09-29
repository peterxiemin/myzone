package com.imgeek.net;


/**
 * @author: xiemin
 * @date: 2018-09-19
 * @desc: socket服務端
 */

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class TcpServer {
    private String host;
    public static int port;

    TcpServer(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void createServerSocketAndWaitConnection() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket;
        try {
            socket = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            try {
                while (true) {
                    String line = in.readLine();
                    log.info(line);
                    if (line.equalsIgnoreCase("end")) break;
                    out.println(line);
                }
            } finally {
                socket.close();
            }
        } finally {
            serverSocket.close();
        }
    }

    public static void main(String[] args) throws IOException {
        int port = 5763;
        TcpServer tcpServer = new TcpServer(null, port);
        tcpServer.createServerSocketAndWaitConnection();
    }
}
