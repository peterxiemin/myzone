package com.imgeek.util.json;

import com.google.gson.Gson;

/**
 *  author: xiemin
 *  date:   2018-07-28
 */

public class GJson implements JsonUtilinterface {
    private final Gson gson = new Gson();
    @Override
    public Object encode(String arg) {
        return gson.fromJson(arg, Object.class);
    }

    @Override
    public <T> T encode(String arg, Class<T> type) throws IllegalAccessException, InstantiationException {
        return gson.fromJson(arg, type);
    }

    @Override
    public String decode(Object obj) {
        return gson.toJson(obj);
    }
}
