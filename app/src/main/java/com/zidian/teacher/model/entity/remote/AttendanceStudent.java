package com.zidian.teacher.model.entity.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GongCheng on 2017/4/8.
 */

public class AttendanceStudent implements Serializable {

    /**
     * message : 成功
     * total : 1
     * data : [{"courseClass":"非金20151","studentId":"1006","attendance":"0","studentName":"景云","studentHeadPortrait":""}]
     * code : 200
     */

    private String message;
    private int total;
    private int code;
    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * courseClass : 非金20151
         * studentId : 1006
         * attendance : 0
         * studentName : 景云
         * studentHeadPortrait :
         */
        private String courseClass;
        private String studentId;
        private String attendance;
        private String studentName;
        private String studentHeadPortrait;
        @Expose(deserialize = false, serialize = false)
        private boolean isSelect;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public String getCourseClass() {
            return courseClass;
        }

        public void setCourseClass(String courseClass) {
            this.courseClass = courseClass;
        }

        public String getStudentId() {
            return studentId;
        }

        public void setStudentId(String studentId) {
            this.studentId = studentId;
        }

        public String getAttendance() {
            return attendance;
        }

        public void setAttendance(String attendance) {
            this.attendance = attendance;
        }

        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }

        public String getStudentHeadPortrait() {
            return studentHeadPortrait;
        }

        public void setStudentHeadPortrait(String studentHeadPortrait) {
            this.studentHeadPortrait = studentHeadPortrait;
        }
    }
}
