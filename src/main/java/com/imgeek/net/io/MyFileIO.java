package com.imgeek.net.io;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: xiemin
 * @date: 2018/9/26 15:27
 * io读文件处理 reader方法和nio方法
 */

@Slf4j
public class MyFileIO {
    private BufferedReader bufferedReader;
    private ILineHandler iLineHandler;

    private String lineStr;
    private int line;

    private ByteBuffer readBuffer;
    private int capacity = 1024;
    private FileChannel fileChannel;

    private boolean isNIO;

    public MyFileIO(String filePath, ILineHandler iLineHandler, boolean isNIO) throws IOException {
        new MyFileIO(new File(filePath), iLineHandler, isNIO);
    }

    MyFileIO(File file, ILineHandler iLineHandler, boolean isNIO) throws IOException {
        this.isNIO = isNIO;
        if (!this.isNIO) {
            bufferedReader = new BufferedReader(new FileReader(file));
        } else {
            readBuffer = ByteBuffer.allocate(capacity);
            fileChannel = (new FileInputStream(file)).getChannel();
        }
        this.iLineHandler = iLineHandler;
        line = 0;
        run();
    }

    public void run() throws IOException {
        if (!isNIO) {
            while ((lineStr = bufferedReader.readLine()) != null) {
                iLineHandler.apply(lineStr);
                line++;
            }
        } else {
            char CR = '\r';
            char LF = '\n';
            int bytesReaded = fileChannel.read(readBuffer);
            byte[] bytes = readBuffer.array();
            int i, k;
            for (i = 1, k = 0; i < readBuffer.position(); i++) {
                if (bytes[i - 1] == CR && bytes[i] == LF) {
                    iLineHandler.apply(new String(bytes, k, i - k + 1));
                    k = i + 1;
                    line++;
                }
            }
            //EOF
            if (k != i) {
                iLineHandler.apply(new String(bytes, k, i - k));
                line++;
            }
        }

    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }
}
