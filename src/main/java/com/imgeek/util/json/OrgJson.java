package com.imgeek.util.json;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;

/**
 * author: xiemin
 * date:   2018-07-28
 * desc:   org.json android java 原生json编码工具封装
 */

public class OrgJson implements JsonUtilinterface {

    private Logger log = LoggerFactory.getLogger(OrgJson.class);

    private enum CLASSTYPE {
        INTEGER, LONG, STRING, DOUBLE, ARRAY, LIST, MAP, UNKNOW
    }

    ;

    /**
     * @param arg
     * @return
     */
    @Override
    public Object encode(String arg) {
        return new JSONObject(arg);
    }

    @Override
    public <T> T encode(String arg, Class<T> classtype) throws IllegalAccessException, InstantiationException {
        JSONObject jsonObject = (JSONObject) encode(arg);
        T data = classtype.newInstance();
        Field[] fields = classtype.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            field.set(data, JSONObjectToObject(jsonObject, field));
        }
        return data;
    }

    @Override
    public String decode(Object obj) {
        JSONObject jsonObject = new JSONObject(obj);
        return jsonObject.toString();
    }

    /**
     * 判断ClassType类型
     *
     * @param classType
     * @return
     */
    private CLASSTYPE getClassType(Class<?> classType) {
        if (Integer.class.isAssignableFrom(classType)) {
            return CLASSTYPE.INTEGER;
        }
        if (Long.class.isAssignableFrom(classType)) {
            return CLASSTYPE.LONG;
        }
        if (String.class.isAssignableFrom(classType)) {
            return CLASSTYPE.STRING;
        }
        if (Double.class.isAssignableFrom(classType)) {
            return CLASSTYPE.DOUBLE;
        }
        if (Array.class.isAssignableFrom(classType)) {
            return CLASSTYPE.ARRAY;
        }
        if (List.class.isAssignableFrom(classType)) {
            return CLASSTYPE.LIST;
        }
        if (Map.class.isAssignableFrom(classType)) {
            return CLASSTYPE.MAP;
        }
        return CLASSTYPE.UNKNOW;
    }

    /**
     * 根据java.lang.reflect.Field，将jsonObject中对应的name的value解析出来
     *
     * @param jsonObject
     * @param field
     * @return
     */
    private Object JSONObjectToObject(JSONObject jsonObject, Field field) {
        Class<?> cls = field.getType();
        String fieldName = field.getName();
        switch (getClassType(cls)) {
            case INTEGER:
                return jsonObject.optInt(fieldName);
            case LONG:
                return jsonObject.optLong(fieldName);
            case STRING:
                return jsonObject.optString(fieldName);
            case DOUBLE:
                return jsonObject.optDouble(fieldName);
            case ARRAY:
                return jsonObject.optJSONArray(fieldName);
            case LIST:
                return JsonArrayToList(jsonObject.optJSONArray(fieldName));
            case MAP:
                return JsonObjecttoMap(jsonObject.optJSONObject(fieldName));
        }
        return null;
    }

    /**
     * 将jsonArray转化为java.util.List
     *
     * @param argJsonArray
     * @return
     */
    private List JsonArrayToList(JSONArray argJsonArray) {
        ArrayList<Object> list = new ArrayList<>();
        if (argJsonArray == null) {
            return null;
        }
        for (int i = 0; i < argJsonArray.length(); i++) {

            if (argJsonArray.get(i) instanceof JSONArray) {
                list.add(JsonArrayToList((JSONArray) argJsonArray.get(i)));
            } else if (argJsonArray.get(i) instanceof JSONObject) {
                list.add(JsonObjecttoMap((JSONObject) argJsonArray.get(i)));
            } else {
                list.add(argJsonArray.get(i));
            }
        }
        return list;
    }

    /**
     * 将jsonobject转化为hashmap<String, Object>
     *
     * @param argObject
     * @return
     */
    private HashMap<String, Object> JsonObjecttoMap(JSONObject argObject) {
        HashMap<String, Object> hashMap = new HashMap<>();
        if (argObject == null) {
            return null;
        }
        Iterator<String> keys = argObject.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            Object val = argObject.get(key);
            if (val != null) {
                if (val instanceof JSONObject) {
                    hashMap.put(key, JsonObjecttoMap((JSONObject) val));
                } else if (val instanceof JSONArray) {
                    hashMap.put(key, JsonArrayToList((JSONArray) val));
                } else {
                    hashMap.put(key, val);
                }
            } else {
                hashMap.put(key, null);
            }
        }
        return hashMap;
    }

}
