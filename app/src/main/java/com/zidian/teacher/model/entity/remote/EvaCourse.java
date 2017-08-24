package com.zidian.teacher.model.entity.remote;

/**
 * Created by GongCheng on 2017/8/23.
 */

public class EvaCourse {

    /**
     * courseId : 143
     * courseName : 应用物理
     */

    private int courseId;
    private String courseName;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return courseName;
    }
}
