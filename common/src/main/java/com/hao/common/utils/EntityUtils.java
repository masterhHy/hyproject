package com.hao.common.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EntityUtils {

    public static <T> T  convert(Object obj,Class<T> t){
        JSONObject json = (JSONObject) JSONObject.toJSON(obj);
        return JSONObject.toJavaObject(json,t);
    }

    public static <T>List<T>  convert(List<? extends Object> list, Class<T> t){
        List<T> coverList = new ArrayList<>();
        for (Object obj:list){
            T entity = convert(obj,t);
            coverList.add(entity);
        }
        return coverList;
    }
}
