package com.zidian.teacher.model.entity.remote;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by GongCheng on 2017/4/8.
 */

public class AttendanceStudent implements Serializable {

    /**
     * studentId : 131
     * studentNumber : 1012
     * studentIconUrl :
     * studentName : 严英
     * attendanceType : 0
     */

    private int studentId;
    private String studentNumber;
    private String studentIconUrl;
    private String studentName;
    private int attendanceType;
    @Expose(serialize = false, deserialize = false)
    private boolean isSelect;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentIconUrl() {
        return studentIconUrl;
    }

    public void setStudentIconUrl(String studentIconUrl) {
        this.studentIconUrl = studentIconUrl;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getAttendanceType() {
        return attendanceType;
    }

    public void setAttendanceType(int attendanceType) {
        this.attendanceType = attendanceType;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
