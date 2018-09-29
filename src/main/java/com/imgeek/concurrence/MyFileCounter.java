package com.imgeek.concurrence;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author: xiemin
 * @date: 2018/9/29 9:59
 */
public class MyFileCounter implements Callable<Integer> {

    private File file;
    private String keywords;
    public MyFileCounter(File file, String keywords) {
        this.file = file;
        this.keywords = keywords;
    }

    @Override
    public Integer call() throws Exception {
        int count = 0;
        File[] files = file.listFiles();
        List<Integer> results = new ArrayList<>();
        for (File itemFile : files) {
            if (itemFile.isDirectory()) {
                MyFileCounter myFileCounter = new MyFileCounter(itemFile, keywords);
                FutureTask<Integer> futureTask = new FutureTask<>(myFileCounter);
                Thread thread = new Thread(futureTask);
                thread.start();
                results.add(futureTask.get());
            } else {
                if (search(itemFile)) count++;
            }
        }

        for (Integer result : results) {
            count += result;
        }

        return count;
    }

    private boolean search(File file) throws FileNotFoundException {
        boolean found = false;
        Scanner in = new Scanner(file);
        while (found == false && in.hasNext()) {
            String line = in.nextLine();
            if (line.contains(keywords)) found = true;
        }
        return found;
    }
}
