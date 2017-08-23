package com.zidian.teacher.model.entity.remote;

/**
 * Created by GongCheng on 2017/8/23.
 */

public class College {

    /**
     * collegeId : 1
     * collegeName : 材料与化学工程学院
     */

    private int collegeId;
    private String collegeName;

    public int getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(int collegeId) {
        this.collegeId = collegeId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    @Override
    public String toString() {
        return collegeName;
    }
}
