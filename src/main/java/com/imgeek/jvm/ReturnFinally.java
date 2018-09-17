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
     * mustFinally4/mustFinally5 证明finally发生在try的return前
     * 如果有catch，会发生在catch和return之间
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

    public int mustFinally7() throws Exception {
        try {
            return 1 / 0;
        } catch (Exception e) {
            log.info("mustFinally7 catch");
        } finally {
            log.info("mustFinally7 finally");
        }
        return 1;
    }
}
