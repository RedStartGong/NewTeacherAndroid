package com.zidian.teacher.model.entity.remote;

import java.io.Serializable;

/**
 * 登录返回值
 * Created by GongCheng on 2017/3/16.
 */

public class LoginResult implements Serializable {

    /**
     * jPush : js1006_1
     * message : 成功
     * teacherType : 1
     * teacherNumber : 1006
     * token :  07d37f9acdba3ad042a52c05bac49fae
     * code : 200
     * teacherName : 景云
     * teacherId : 17
     */

    private String jPush;
    private String message;
    private int teacherType;
    private String teacherNumber;
    private String token;
    private int code;
    private String teacherName;
    private int teacherId;

    public String getJPush() {
        return jPush;
    }

    public void setJPush(String jPush) {
        this.jPush = jPush;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTeacherType() {
        return teacherType;
    }

    public void setTeacherType(int teacherType) {
        this.teacherType = teacherType;
    }

    public String getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(String teacherNumber) {
        this.teacherNumber = teacherNumber;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }
}
