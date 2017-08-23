/**
 * @author yxw
 * date : 2014年4月17日 下午11:11:11
 */
package com.zidian.teacher.ui.widget;

import java.io.Serializable;

/**
 * 课程页面的实体类
 */
public class CourseInfo implements Serializable{
    private int fromX;
    private int fromY;
    private int toX;
    private int toY;

    private String classname;
    private int fromClassNum;
    private int classNumLen;
    private int weekday;
    private String classRoom;
    private String beginEndWeek;
    private int courseId;
    private int coursePlanId;

    public void setPoint(int fromX, int fromY, int toX, int toY) {
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
    }

    public int getCoursePlanId() {
        return coursePlanId;
    }

    public void setCoursePlanId(int coursePlanId) {
        this.coursePlanId = coursePlanId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getFromX() {
        return fromX;
    }

    public void setFromX(int fromX) {
        this.fromX = fromX;
    }

    public int getFromY() {
        return fromY;
    }

    public void setFromY(int fromY) {
        this.fromY = fromY;
    }

    public int getToX() {
        return toX;
    }

    public void setToX(int toX) {
        this.toX = toX;
    }

    public int getToY() {
        return toY;
    }

    public void setToY(int toY) {
        this.toY = toY;
    }


    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public int getFromClassNum() {
        return fromClassNum;
    }

    public void setFromClassNum(int fromClassNum) {
        this.fromClassNum = fromClassNum;
    }

    public int getClassNumLen() {
        return classNumLen;
    }

    public void setClassNumLen(int classNumLen) {
        this.classNumLen = classNumLen;
    }

    public int getWeekday() {
        return weekday;
    }

    public void setWeekday(int weekday) {
        this.weekday = weekday;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public void setBeginEndWeek(String beginEndWeek) {
        this.beginEndWeek = beginEndWeek;
    }

    public String getBeginEndWeek() {
        return beginEndWeek;
    }

}
