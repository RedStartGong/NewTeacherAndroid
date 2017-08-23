package com.zidian.teacher.model.entity.remote;

/**
 * Created by GongCheng on 2017/4/9.
 */

public class AttendanceStatistics {

    /**
     * classId : 5
     * studentId : 135
     * normalNum : 0
     * studentNumber : 1010
     * leaveNum : 0
     * studentIconUrl :
     * studentName : 凌仔芥
     * beLateNum : 0
     * truantNum : 0
     * leaveEarlyNum : 0
     */

    private int classId;
    private int studentId;
    private int normalNum;
    private String studentNumber;
    private int leaveNum;
    private String studentIconUrl;
    private String studentName;
    private int beLateNum;
    private int truantNum;
    private int leaveEarlyNum;

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getNormalNum() {
        return normalNum;
    }

    public void setNormalNum(int normalNum) {
        this.normalNum = normalNum;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public int getLeaveNum() {
        return leaveNum;
    }

    public void setLeaveNum(int leaveNum) {
        this.leaveNum = leaveNum;
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

    public int getBeLateNum() {
        return beLateNum;
    }

    public void setBeLateNum(int beLateNum) {
        this.beLateNum = beLateNum;
    }

    public int getTruantNum() {
        return truantNum;
    }

    public void setTruantNum(int truantNum) {
        this.truantNum = truantNum;
    }

    public int getLeaveEarlyNum() {
        return leaveEarlyNum;
    }

    public void setLeaveEarlyNum(int leaveEarlyNum) {
        this.leaveEarlyNum = leaveEarlyNum;
    }
}
