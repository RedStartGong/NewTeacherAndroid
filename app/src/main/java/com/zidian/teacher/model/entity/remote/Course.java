package com.zidian.teacher.model.entity.remote;

/**
 * Created by GongCheng on 2017/4/7.
 */

public class Course {

    /**
     * classRoom : D3-506
     * beginEndWeek : 2
     * weeklyDay : 周一
     * weeklyQuarter : 1,2
     * courseId : 1000001
     * courseWeeklyId : 10650
     * courseName : 工程制图A1
     */

    private String classRoom;
    private String beginEndWeek;
    private String weeklyDay;
    private String weeklyQuarter;
    private String courseId;
    private String courseWeeklyId;
    private String courseName;

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public String getBeginEndWeek() {
        return beginEndWeek;
    }

    public void setBeginEndWeek(String beginEndWeek) {
        this.beginEndWeek = beginEndWeek;
    }

    public String getWeeklyDay() {
        return weeklyDay;
    }

    public void setWeeklyDay(String weeklyDay) {
        this.weeklyDay = weeklyDay;
    }

    public String getWeeklyQuarter() {
        return weeklyQuarter;
    }

    public void setWeeklyQuarter(String weeklyQuarter) {
        this.weeklyQuarter = weeklyQuarter;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseWeeklyId() {
        return courseWeeklyId;
    }

    public void setCourseWeeklyId(String courseWeeklyId) {
        this.courseWeeklyId = courseWeeklyId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
