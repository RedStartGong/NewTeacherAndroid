package com.zidian.teacher.model.entity.remote;

import java.io.Serializable;
import java.util.List;

/**
 * 学校
 * Created by GongCheng on 2017/3/30.
 */

public class School {


    /**
     * school : [{"schoolName":"孜点学院","schoolId":1}]
     * addressId : 1
     * addressName : 四川
     */

    private int addressId;
    private String addressName;
    private List<SchoolBean> school;

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public List<SchoolBean> getSchool() {
        return school;
    }

    public void setSchool(List<SchoolBean> school) {
        this.school = school;
    }

    public static class SchoolBean implements Serializable{
        /**
         * schoolName : 孜点学院
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
    }
}
