package com.zidian.teacher.model.entity.remote;

import com.zidian.teacher.util.TimeUtils;

/**
 * Created by GongCheng on 2017/4/12.
 */

public class MyTask {

    /**
     * evaluateType : 3
     * recordId : 14
     * requestMessage : requestMessage: I want to sup evaluate you!!
     * weekDay : 1
     * myRole : 0
     * courseClassRoom : D3-3阶
     * requestType : 0
     * teacherName : 邓燕
     * courseEndTime : 1503286200000
     * courseStartTime : 1503282000000
     * requestState : 0
     * whatWeek : 13
     * weekPartBegin : 3
     * anotherTeacher : 18
     * coursePlanId : 3089
     * weekPartEnd : 4
     * courseName : 商务导论
     */

    private int evaluateType;
    private int recordId;
    private String requestMessage;
    private int weekDay;
    private int myRole;
    private String courseClassRoom;
    private int requestType;
    private String teacherName;
    private long courseEndTime;
    private long courseStartTime;
    private int requestState;
    private int whatWeek;
    private int weekPartBegin;
    private int anotherTeacher;
    private int coursePlanId;
    private int weekPartEnd;
    private String courseName;

    public int getEvaluateType() {
        return evaluateType;
    }

    public void setEvaluateType(int evaluateType) {
        this.evaluateType = evaluateType;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getRequestMessage() {
        return requestMessage;
    }

    public void setRequestMessage(String requestMessage) {
        this.requestMessage = requestMessage;
    }

    public int getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(int weekDay) {
        this.weekDay = weekDay;
    }

    public int getMyRole() {
        return myRole;
    }

    public void setMyRole(int myRole) {
        this.myRole = myRole;
    }

    public String getCourseClassRoom() {
        return courseClassRoom;
    }

    public void setCourseClassRoom(String courseClassRoom) {
        this.courseClassRoom = courseClassRoom;
    }

    public int getRequestType() {
        return requestType;
    }

    public void setRequestType(int requestType) {
        this.requestType = requestType;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public long getCourseEndTime() {
        return courseEndTime;
    }

    public void setCourseEndTime(long courseEndTime) {
        this.courseEndTime = courseEndTime;
    }

    public long getCourseStartTime() {
        return courseStartTime;
    }

    public void setCourseStartTime(long courseStartTime) {
        this.courseStartTime = courseStartTime;
    }

    public int getRequestState() {
        return requestState;
    }

    public void setRequestState(int requestState) {
        this.requestState = requestState;
    }

    public int getWhatWeek() {
        return whatWeek;
    }

    public void setWhatWeek(int whatWeek) {
        this.whatWeek = whatWeek;
    }

    public int getWeekPartBegin() {
        return weekPartBegin;
    }

    public void setWeekPartBegin(int weekPartBegin) {
        this.weekPartBegin = weekPartBegin;
    }

    public int getAnotherTeacher() {
        return anotherTeacher;
    }

    public void setAnotherTeacher(int anotherTeacher) {
        this.anotherTeacher = anotherTeacher;
    }

    public int getCoursePlanId() {
        return coursePlanId;
    }

    public void setCoursePlanId(int coursePlanId) {
        this.coursePlanId = coursePlanId;
    }

    public int getWeekPartEnd() {
        return weekPartEnd;
    }

    public void setWeekPartEnd(int weekPartEnd) {
        this.weekPartEnd = weekPartEnd;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeachingCalendar() {
        return TimeUtils.millis2String(courseStartTime, "yyyy/MM/dd HH:mm") + "-" + TimeUtils.millis2String(courseEndTime, "HH:mm");
    }
}
