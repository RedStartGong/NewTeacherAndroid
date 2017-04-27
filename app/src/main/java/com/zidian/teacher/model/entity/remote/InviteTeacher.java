package com.zidian.teacher.model.entity.remote;

import java.io.Serializable;

/**
 * Created by GongCheng on 2017/4/13.
 */

public class InviteTeacher implements Serializable {

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

    @Override
    public String toString() {
        return teacherName + "(" + teacherCollege + ")";
    }

    /**
     * 用教师名字判断是否是同一个对象(去重)
     */
    @Override
    public boolean equals(Object obj) {
        InviteTeacher teacher = (InviteTeacher) obj;
        return teacherName.equals(teacher.teacherName);
    }

    @Override
    public int hashCode() {
        return teacherName.hashCode();
    }
}
