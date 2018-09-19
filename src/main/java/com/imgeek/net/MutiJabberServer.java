package com.imgeek.net;


/**
 * @author: xiemin
 * @date: 2018-09-19
 * @desc: 多线程socket服務端
 */

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class MutiJabberServer {
    private String host;
    public static int port;

    MutiJabberServer(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void createServerSocketAndWaitConnection() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket;
        try {
            while (true) {
                socket = serverSocket.accept();
                (new ThreadHanler(socket)).start();
            }
        } finally {
            serverSocket.close();
        }
    }

    private class ThreadHanler extends Thread {
        private Socket socket;

        ThreadHanler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                String line;
                while (!(line = in.readLine()).equalsIgnoreCase("end")) {
                    log.info("receive {} from server", line);
                    out.println(line);
                }
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int port = 5763;
        MutiJabberServer mutiJabberServer = new MutiJabberServer(null, port);
        mutiJabberServer.createServerSocketAndWaitConnection();
    }
}
