package com.zidian.teacher.model.entity.remote;

import java.io.Serializable;

/**
 * 个人信息
 * Created by GongCheng on 2017/4/6.
 */

public class PersonInfo implements Serializable {


    /**
     * aliasName :
     * birthday :
     * sex : 0
     * phone : 18281965033
     * toTchEvalNum : 0
     * signName :
     * toSupEvalNum : 0
     * byStuEvalNum : 0
     * name : 景云
     * iconUrl :
     * byTchEvalNum : 0
     * bySupEvalNum : 0
     */

    private String aliasName;
    private String birthday;
    private int sex;
    private String phone;
    private int toTchEvalNum;
    private String signName;
    private int toSupEvalNum;
    private int byStuEvalNum;
    private String name;
    private String iconUrl;
    private int byTchEvalNum;
    private int bySupEvalNum;

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getToTchEvalNum() {
        return toTchEvalNum;
    }

    public void setToTchEvalNum(int toTchEvalNum) {
        this.toTchEvalNum = toTchEvalNum;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public int getToSupEvalNum() {
        return toSupEvalNum;
    }

    public void setToSupEvalNum(int toSupEvalNum) {
        this.toSupEvalNum = toSupEvalNum;
    }

    public int getByStuEvalNum() {
        return byStuEvalNum;
    }

    public void setByStuEvalNum(int byStuEvalNum) {
        this.byStuEvalNum = byStuEvalNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public int getByTchEvalNum() {
        return byTchEvalNum;
    }

    public void setByTchEvalNum(int byTchEvalNum) {
        this.byTchEvalNum = byTchEvalNum;
    }

    public int getBySupEvalNum() {
        return bySupEvalNum;
    }

    public void setBySupEvalNum(int bySupEvalNum) {
        this.bySupEvalNum = bySupEvalNum;
    }
}
