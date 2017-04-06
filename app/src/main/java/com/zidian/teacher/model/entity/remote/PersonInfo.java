package com.zidian.teacher.model.entity.remote;

import com.google.gson.annotations.SerializedName;

/**
 * Created by GongCheng on 2017/4/6.
 */

public class PersonInfo {

    /**
     * becomments : 18
     * personalizedsignature : 那么多的人你要去哪里
     * mynickname : 哈哈哈哈
     * teacherphonenumber : 133333333
     * teachersex : 女
     * beviewed : 9053
     * teacherAge : 15
     * teacherpassword : 96e79218965eb72c92a549dd5a330112
     * teacherheadportrait : http://ling-zhi-jie.oss-cn-shanghai.aliyuncs.com/6283-HeadPortrait-1484710478250.JPEG?Expires=1800070491&OSSAccessKeyId=LTAIMUy5LebAYfzN&Signature=lpe2t%2B7gaBV04cQpf%2BsApOKyMO4%3D
     * mybirthd : 2001-10-31
     * bedownloaded : 0
     * teachername : 谢文玲
     */

    @SerializedName("becomments")
    private int evaluatedCount;
    @SerializedName("personalizedsignature")
    private String personSignature;
    @SerializedName("mynickname")
    private String nickName;
    @SerializedName("teacherphonenumber")
    private String phoneNumber;
    @SerializedName("teachersex")
    private String sex;
    private int beviewed;
    @SerializedName("teacherAge")
    private String age;
    @SerializedName("teacherpassword")
    private String password;
    @SerializedName("teacherheadportrait")
    private String portrait;
    @SerializedName("mybirthd")
    private String birthday;
    @SerializedName("bedownloaded")
    private int downloaded;
    @SerializedName("teachername")
    private String name;

    public int getEvaluatedCount() {
        return evaluatedCount;
    }

    public void setEvaluatedCount(int evaluatedCount) {
        this.evaluatedCount = evaluatedCount;
    }

    public String getPersonSignature() {
        return personSignature;
    }

    public void setPersonSignature(String personSignature) {
        this.personSignature = personSignature;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getBeviewed() {
        return beviewed;
    }

    public void setBeviewed(int beviewed) {
        this.beviewed = beviewed;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getDownloaded() {
        return downloaded;
    }

    public void setDownloaded(int downloaded) {
        this.downloaded = downloaded;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
