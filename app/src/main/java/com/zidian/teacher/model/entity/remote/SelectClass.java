package com.zidian.teacher.model.entity.remote;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GongCheng on 2017/5/8.
 */

public class SelectClass implements Serializable {

    /**
     * message : 查询成功
     * code : 200
     * date : ["应化20151","古建20151","工商20151","应数20151","非金20151","网球20157","网球20151"]
     */

    private String message;
    private int code;
    private List<String> date;

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

    public List<String> getDate() {
        return date;
    }

    public void setDate(List<String> date) {
        this.date = date;
    }
}
