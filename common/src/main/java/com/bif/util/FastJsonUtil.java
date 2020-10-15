package com.bif.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FastJsonUtil {

    /**
     * String To Object
     *
     * @param json  string
     * @param clazz Class
     * @param <T>   T
     * @return Class
     */
    public static <T> T toObject(String json, Class<T> clazz) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        return JSONObject.parseObject(jsonObject.toJSONString(), clazz);
    }

    public static <T> T toObject(String json,TypeReference<T> type) {
        return JSONObject.parseObject(json, type);
    }

    /**
     * Object To String
     *
     * @param object Object
     * @return String
     */
    public static String toString(Object object) {
        return JSONObject.toJSONString(object);
    }

    /**
     * String to List<T>
     *
     * @param jsonArray arrayString
     * @param <T>       T
     * @return List<T>
     */
    public static <T> List<T> toList(String jsonArray) {
        return JSONArray.parseObject(jsonArray, new TypeReference<ArrayList<T>>() {
        });
    }

    /**
     * String To JSONObject
     *
     * @param json json
     * @return JSONObject
     */
    public static JSONObject toJSONObject(String json) {
        return JSONObject.parseObject(json);
    }

    /**
     * String To JSONArray
     *
     * @param json String
     * @return JSONArray
     */
    public static JSONArray toJSONArray(String json) {
        return JSONArray.parseArray(json);
    }

    /**
     * JSONArray to List
     *
     * @param jsonArray jsonArray
     * @return List
     */
    public static List<JSONObject> toJSONObjectList(JSONArray jsonArray) {
        List<JSONObject> list = new ArrayList<>();
        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            list.add(jsonObject);
        }
        return list;
    }

    /**
     * String to Map
     *
     * @param json json
     * @return map
     */
    public static Map toMap(String json) {
        return JSON.parseObject(json, Map.class);
    }
}
