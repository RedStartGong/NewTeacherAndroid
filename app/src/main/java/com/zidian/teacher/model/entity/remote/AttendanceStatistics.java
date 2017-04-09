package com.zidian.teacher.model.entity.remote;

/**
 * Created by GongCheng on 2017/4/9.
 */

public class AttendanceStatistics {

    /**
     * LateNum : 0
     * studentId : 1006
     * studentName : 景云
     * leaveNum : 0
     * truantNum : 0
     * studentUrl :
     * leaveEarlyNum : 0
     */

    private String LateNum;
    private String studentId;
    private String studentName;
    private String leaveNum;
    private String truantNum;
    private String studentUrl;
    private String leaveEarlyNum;

    public String getLateNum() {
        return LateNum;
    }

    public void setLateNum(String LateNum) {
        this.LateNum = LateNum;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getLeaveNum() {
        return leaveNum;
    }

    public void setLeaveNum(String leaveNum) {
        this.leaveNum = leaveNum;
    }

    public String getTruantNum() {
        return truantNum;
    }

    public void setTruantNum(String truantNum) {
        this.truantNum = truantNum;
    }

    public String getStudentUrl() {
        return studentUrl;
    }

    public void setStudentUrl(String studentUrl) {
        this.studentUrl = studentUrl;
    }

    public String getLeaveEarlyNum() {
        return leaveEarlyNum;
    }

    public void setLeaveEarlyNum(String leaveEarlyNum) {
        this.leaveEarlyNum = leaveEarlyNum;
    }
}
