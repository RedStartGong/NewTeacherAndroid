package com.zidian.teacher.model.entity.remote;

import com.google.gson.annotations.SerializedName;

/**
 * Created by GongCheng on 2017/4/7.
 */

public class Course {

    /**
     * startTime : 1497597600000
     * id : 3133
     * classroom : D3-306
     * weekDay : 5
     * weekPartBegin : 7
     * classEnd : 8
     * endTime : 1497601800000
     * teacherId : 18
     */

    private long startTime;
    private int coursePlanId;
    private String classroom;
    private int weekDay;
    @SerializedName("weekPartBegin")
    private int classBegin;
    @SerializedName("weekPartEnd")
    private int classEnd;
    private long endTime;
    private int teacherId;
    private String courseName;

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public int getCoursePlanId() {
        return coursePlanId;
    }

    public void setCoursePlanId(int coursePlanId) {
        this.coursePlanId = coursePlanId;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public int getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(int weekDay) {
        this.weekDay = weekDay;
    }

    public int getClassBegin() {
        return classBegin;
    }

    public void setClassBegin(int classBegin) {
        this.classBegin = classBegin;
    }

    public int getClassEnd() {
        return classEnd;
    }

    public void setClassEnd(int classEnd) {
        this.classEnd = classEnd;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
