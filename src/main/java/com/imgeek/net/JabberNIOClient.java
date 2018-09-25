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
public class JabberNIOClient {
    private String host;
    private int port;
    private Socket socket;

    public JabberNIOClient(String host, int port) throws IOException {
        this.host = host;
        this.port = port;
        socket = new Socket(InetAddress.getByName(host), port);
    }

    public void sendToServer(String str) throws IOException {
        BufferedReader in;
        PrintWriter out;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            out.println(str);
            log.info("receive {} from server", in.readLine());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int port = 5763;
        JabberNIOClient jabberClient = new JabberNIOClient(null, port);
        try {
            jabberClient.sendToServer("xieminshitiancai");
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
