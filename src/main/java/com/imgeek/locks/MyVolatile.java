package com.imgeek.locks;

import lombok.extern.slf4j.Slf4j;

/**
 * @author :    xiemin
 * @date: 2018-09-18
 * @desc: https://www.cnblogs.com/chengxiao/p/6528109.html
 * 2)
 */

@Slf4j
public class MyVolatile {
    private boolean status = true;
    private int var = 0;

    private void changeStatus() {
        var = 1;
        status = !status;
    }

    /**
     * jmm 导致两个线程访问共享变量时，其中一个线程更改变量内容，另一个线程不一定第一时间看到
     * 所以有可能出现第二个线程仍然不能打印“run...."
     */
    private void run() {
        if (!status) {
            /**
             * 编译器优化会导致不影响执行结果的前提下进行代码的位置变换，
             * var可能是2，var=1先执行，status=!status后执行
             * var可能是1，status=!status先执行，var=1后执行
             */
            log.info("run....{}", (++var));
        }
    }
}
