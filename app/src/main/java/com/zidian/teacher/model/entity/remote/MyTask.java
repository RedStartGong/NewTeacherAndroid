package com.zidian.teacher.model.entity.remote;

import com.google.gson.annotations.SerializedName;

/**
 * Created by GongCheng on 2017/4/12.
 */

public class MyTask {

    /**
     * requestState : 1
     * recordId : 1
     * myRole : 1
     * courseClassRoom : 操场1
     * toTeacherName : 陈婷
     * requestExplain : 来评价我！！！
     * requestType : 3
     * toTeacherId : 1001
     * evaluationType : 2
     * courseId : 1000002
     * teachingCalendar : 第12周周三第1-2节
     * courseName : 商务导论
     */

    private int requestState;
    private int recordId;
    private int myRole;
    @SerializedName("courseClassRoom")
    private String courseLocation;
    private String toTeacherName;
    private String requestExplain;
    private int requestType;
    private String toTeacherId;
    private int evaluationType;
    private String courseId;
    private String teachingCalendar;
    private String courseName;

    public int getRequestState() {
        return requestState;
    }

    public void setRequestState(int requestState) {
        this.requestState = requestState;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getMyRole() {
        return myRole;
    }

    public void setMyRole(int myRole) {
        this.myRole = myRole;
    }

    public String getCourseLocation() {
        return courseLocation;
    }

    public void setCourseLocation(String courseLocation) {
        this.courseLocation = courseLocation;
    }

    public String getToTeacherName() {
        return toTeacherName;
    }

    public void setToTeacherName(String toTeacherName) {
        this.toTeacherName = toTeacherName;
    }

    public String getRequestExplain() {
        return requestExplain;
    }

    public void setRequestExplain(String requestExplain) {
        this.requestExplain = requestExplain;
    }

    public int getRequestType() {
        return requestType;
    }

    public void setRequestType(int requestType) {
        this.requestType = requestType;
    }

    public String getToTeacherId() {
        return toTeacherId;
    }

    public void setToTeacherId(String toTeacherId) {
        this.toTeacherId = toTeacherId;
    }

    public int getEvaluationType() {
        return evaluationType;
    }

    public void setEvaluationType(int evaluationType) {
        this.evaluationType = evaluationType;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getTeachingCalendar() {
        return teachingCalendar;
    }

    public void setTeachingCalendar(String teachingCalendar) {
        this.teachingCalendar = teachingCalendar;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
