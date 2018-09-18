package com.imgeek.util.json;

/**
 *  author: xiemin
 *  date:   2018-07-28
 */

public interface IJsonUtil {
    public Object encode(String arg);

    public <T> T encode(String arg, Class<T> type) throws IllegalAccessException, InstantiationException;

    public String decode(Object obj);
}


