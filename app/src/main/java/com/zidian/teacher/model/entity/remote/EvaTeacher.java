package com.zidian.teacher.model.entity.remote;

/**
 * Created by GongCheng on 2017/8/23.
 */

public class EvaTeacher {

    /**
     * teacherName : xxx
     * teacherId : 15
     */

    private String teacherName;
    private int teacherId;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return teacherName;
    }
}
