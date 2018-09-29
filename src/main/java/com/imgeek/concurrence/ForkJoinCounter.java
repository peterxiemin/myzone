package com.imgeek.concurrence;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RecursiveTask;

/**
 * @author: xiemin
 * @date: 2018/9/29 18:31
 */

interface Filter {
    boolean apply(double var);
}

@Slf4j
public class ForkJoinCounter extends RecursiveTask<Integer> {

    private int start;
    private int last;
    private double[] d;
    private Filter filter;
    private int groupNum;

    public ForkJoinCounter(
            double[] d,
            int start,
            int last,
            Filter filter,
            int groupNum
    ) {
        this.start = start;
        this.last = last;
        this.d = d;
        this.filter = filter;
        this.groupNum = groupNum;
    }

    @Override
    protected Integer compute() {
        int count = 0;
        if ((last - start) < groupNum) {
            for (int i = start; i < last; i++) {
                if (filter.apply(d[i])) {
                    count++;
                }
            }
        } else {
            int mid = (last + start) / 2;
            ForkJoinCounter forkJoinCounter1 = new ForkJoinCounter(d, start, mid, filter, groupNum);
            ForkJoinCounter forkJoinCounter2 = new ForkJoinCounter(d, mid, last, filter, groupNum);
            invokeAll(forkJoinCounter1,forkJoinCounter2);
            count = forkJoinCounter1.join() + forkJoinCounter2.join();
        }

        return count;
    }
}
