package com.hao.common.pojo;

import com.hao.common.utils.EntityUtils;

import java.util.List;

public class ResponseData<T> {

    /**
     * 返回码
     */
    private Integer code;
    public static final  Integer SUCCESS_CODE =200;
    public static final  Integer ERROR_CODE =500;
    /**
     * 返回描述
     */
    private String message;
    private Boolean status =true;
    private T data;

    public ResponseData() {
    }

    public ResponseData(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseData(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 把对象内属性转移到目标对象,(利用属性名相同转移)
     * @param data
     */
    public<E> List<E> convertData(List<? extends Object> data,Class<E> t){
       return EntityUtils.convert(data,t);
    }
    public<E> E convertData(Object data,Class<E> t){
        return EntityUtils.convert(data,t);
    }
}
