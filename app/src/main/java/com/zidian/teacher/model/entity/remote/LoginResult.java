package com.zidian.teacher.model.entity.remote;

import java.io.Serializable;

/**
 * 登录返回值
 * Created by GongCheng on 2017/3/16.
 */

public class LoginResult implements Serializable {

    /**
     * message : 成功
     * code : 200
     * teacherId : 6283
     * teacherName : 6283
     * token :  9662804431d7090a35942d5f8d6c8569
     * teacherType : 2为教师 9为督导
     */

    private String message;
    private int code;
    private String teacherId;
    private String teacherName;
    private String token;

    public int getTeacherType() {
        return teacherType;
    }

    public void setTeacherType(int teacherType) {
        this.teacherType = teacherType;
    }

    private int teacherType;

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

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
