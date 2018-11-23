package com.imgeek.algorithm;

/**
 * auth : xiemin
 * date : 2018-08-04
 * desc ：实现Integer.parseInt
 */
public class MyInteger {
    public static int parseInt(String str) {
        int retInt = 0;
        int diff = -48;//diff char and int is 48
        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) {
            int num = (int) c[i] + diff;
            if (num < 0 || num > 9) {
                throw new NumberFormatException("invaild char :".concat(String.valueOf(c[i])));
            }
            retInt += num * Math.pow(10, c.length - i - 1);
        }
        return retInt;
    }
}