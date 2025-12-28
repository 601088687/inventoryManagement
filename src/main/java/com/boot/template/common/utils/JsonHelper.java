package com.boot.template.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


/**
 * 基于Gson封装工具
 *
 * @author yishuai
 */
public final class JsonHelper {

    private static Gson gson;

    static {
        gson = new GsonBuilder()
                // 对value为null的属性也进行序列化
//                .serializeNulls()
//                .setDateFormat(DateHelper.YYYY_MM_DD_HH_MM_SS)
                .create();
    }

    /**
     * 获取GsonBuilder实例
     *
     * @return
     */
    public static GsonBuilder builder() {
        return new GsonBuilder();
    }

    /**
     * 将对象转为json字符串
     *
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        String json = null;
        if (gson != null) {
            json = gson.toJson(object);
        }
        return json;
    }

    /**
     * 将json字符串转为指定类型的实例
     *
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T parseObject(String json, Class<T> cls) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(json, cls);
        }
        return t;
    }

    /**
     * 将json字符串转为指定类型的实例
     *
     * @param json
     * @param <T>
     * @return
     */
    public static <T> T parseObject(String json, TypeToken<T> tTypeToken) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(json, tTypeToken.getType());
        }
        return t;
    }


}
