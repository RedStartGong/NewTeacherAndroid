package com.zidian.teacher.model.entity.remote;

/**
 * Created by GongCheng on 2017/4/13.
 */

public class InviteTeacher {

    /**
     * teacherCollege : 经济与管理学院
     * teacherName : 陈婷
     * teacherId : 1001
     */

    private String teacherCollege;
    private String teacherName;
    private String teacherId;

    public String getTeacherCollege() {
        return teacherCollege;
    }

    public void setTeacherCollege(String teacherCollege) {
        this.teacherCollege = teacherCollege;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }
}
