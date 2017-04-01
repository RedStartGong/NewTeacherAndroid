package com.zidian.teacher.model.entity.remote;

/**
 * Created by GongCheng on 2017/3/30.
 */

public class School {

    public School(String schoolName, int schoolId) {
        this.schoolName = schoolName;
        this.schoolId = schoolId;
    }

    /**
     * schoolName : 四川理工学院
     * schoolId : 1
     */

    private String schoolName;
    private int schoolId;

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    @Override
    public String toString() {
        return getSchoolName();
    }
}
