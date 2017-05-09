package com.zidian.teacher.model.entity.remote;

import java.io.Serializable;

/**
 * 无data的返回值
 * Created by GongCheng on 2017/4/6.
 */

public class NoDataResult implements Serializable{
    private String message;
    private int code;

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
}
