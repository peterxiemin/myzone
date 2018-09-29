package com.imgeek.concurrence;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 * @author: xiemin
 * @date: 2018/9/29 9:59
 */
public class MyFileCounterWithThreadPool implements Callable<Integer> {

    private File directory;
    private String keywords;
    private ExecutorService pool;

    public MyFileCounterWithThreadPool(File directory, String keywords, ExecutorService pool) {
        this.directory = directory;
        this.keywords = keywords;
        this.pool = pool;
    }

    @Override
    public Integer call() throws Exception {
        int count = 0;
        File[] files = directory.listFiles();
        List<Integer> results = new ArrayList<>();
        for (File file : files) {
            if (file.isDirectory()) {
                MyFileCounterWithThreadPool myFileCounter = new MyFileCounterWithThreadPool(file, keywords, pool);
                Future<Integer> future = pool.submit(myFileCounter);
                results.add(future.get());
            } else {
                if (search(file)) count++;
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
