package com.imgeek.util.json;

import com.alibaba.fastjson.JSONObject;

/**
 *  author: xiemin
 *  date:   2018-07-28
 */

public class FastJson implements IJsonUtil {
    @Override
    public Object encode(String arg) {
        return JSONObject.parseObject(arg);
    }

    @Override
    public <T> T encode(String arg, Class<T> type) throws IllegalAccessException, InstantiationException {
        return JSONObject.parseObject(arg, type);
    }

    @Override
    public String decode(Object obj) {
        return JSONObject.toJSONString(obj);
    }
}
