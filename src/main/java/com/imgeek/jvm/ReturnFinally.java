package com.imgeek.jvm;

/**
 * @author: xiemin
 * @date: 2018-09-17
 * @desc: 验证try-catch-finally机制
 */

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReturnFinally {
    /**
     * 证明finally可以通过return方法，抑制住throw传递性
     * @return
     */
    public int mustFinally3() {
        try {
            return 1 / 0;
        } finally {
            return 1;
        }
    }

    /**
     * try和catch/finally之间后面发生异常，将会跳过所有的try-catch/finally之间的代码，
     * 如果有catch，进入catch。但一定会进入finally
     * @return
     */
    public int mustFinally4() {
        try {
            return 1;
        } finally {
            return 2;
        }
    }

    public int mustFinally5() {
        try {
            return 1;
        } finally {
            log.info("finally");
        }
    }

    public int mustFinally6() {
        try {
            return 1 / 0;
        } finally {
            log.info("mustFinally6 finally");
        }
    }

    public int mustFinally7() {
        try {
            return 1 / 0;
        } catch (Exception e) {
            log.info("mustFinally7 catch");
        } finally {
            log.info("mustFinally7 finally");
        }
        return 1;
    }


    public int mustFinally8() {
        try {
            int a = 1 / 0;
            log.info("mustFinally8 log info");
            return 1;
        } catch (Exception e) {
            log.info("mustFinally8 catch");
        } finally {
            log.info("mustFinally8 finally");
            return 2;
        }
    }

}
