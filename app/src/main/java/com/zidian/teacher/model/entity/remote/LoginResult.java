package com.zidian.teacher.model.entity.remote;

import java.io.Serializable;

/**
 * Created by GongCheng on 2017/3/16.
 */

public class LoginResult implements Serializable {

    /**
     * message : 成功
     * code : 200
     * studentId : 11011040105
     * token :
     */

    private String message;
    private int code;
    private String studentId;
    private String token;

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

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
