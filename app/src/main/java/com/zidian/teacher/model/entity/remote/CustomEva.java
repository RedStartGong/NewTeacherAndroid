package com.zidian.teacher.model.entity.remote;

/**
 * Created by GongCheng on 2017/4/27.
 */

public class CustomEva {
    /**
     * aliasName :
     * evaluateContent : 这里荒芜寸草不生
     * evaluateCustomId : 56
     * likeNum : 1
     * studentId : 129
     * evaluateTime : 1502675136000
     * iconUrl :
     */

    private String aliasName;
    private String evaluateContent;
    private int evaluateCustomId;
    private int likeNum;
    private int studentId;
    private long evaluateTime;
    private String iconUrl;

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent;
    }

    public int getEvaluateCustomId() {
        return evaluateCustomId;
    }

    public void setEvaluateCustomId(int evaluateCustomId) {
        this.evaluateCustomId = evaluateCustomId;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public long getEvaluateTime() {
        return evaluateTime;
    }

    public void setEvaluateTime(long evaluateTime) {
        this.evaluateTime = evaluateTime;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
