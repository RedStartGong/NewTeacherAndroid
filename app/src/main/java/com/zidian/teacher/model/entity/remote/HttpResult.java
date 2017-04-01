package com.zidian.teacher.model.entity.remote;

import java.io.Serializable;

/**
 * Created by GongCheng on 2017/3/21.
 */

public class HttpResult<T> implements Serializable {

    /**
     * message : 成功
     * data : T
     * code : 200
     */

    private String message;
    private int code;
    private T data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
