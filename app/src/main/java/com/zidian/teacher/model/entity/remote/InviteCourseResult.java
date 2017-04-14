package com.zidian.teacher.model.entity.remote;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GongCheng on 2017/4/13.
 */

public class InviteCourseResult implements Serializable {

    /**
     * message : 成功
     * course : [{"courseCollege":"经济与管理学院","teachingCalendarTime":[{"time":"第2周--周一--第3,4节"},{"time":"第15周--周一--第3,4节"},{"time":"第15周--周五--第5,6节"},{"time":"第17周--周一--第3,4节"},{"time":"第11周--周一--第3,4节"},{"time":"第12周--周一--第3,4节"},{"time":"第3周--周一--第3,4节"},{"time":"第14周--周一--第3,4节"},{"time":"第11周--周五--第5,6节"},{"time":"第18周--周一--第3,4节"},{"time":"第1周--周五--第5,6节"},{"time":"第4周--周五--第5,6节"},{"time":"第7周--周一--第3,4节"},{"time":"第16周--周一--第3,4节"},{"time":"第7周--周五--第5,6节"},{"time":"第17周--周五--第5,6节"},{"time":"第14周--周五--第5,6节"},{"time":"第3周--周五--第5,6节"},{"time":"第16周--周五--第5,6节"},{"time":"第1周--周一--第3,4节"},{"time":"第13周--周一--第3,4节"},{"time":"第8周--周五--第5,6节"},{"time":"第12周--周五--第5,6节"},{"time":"第6周--周一--第3,4节"},{"time":"第10周--周五--第5,6节"},{"time":"第18周--周五--第5,6节"},{"time":"第10周--周一--第3,4节"},{"time":"第13周--周五--第5,6节"},{"time":"第8周--周一--第3,4节"},{"time":"第2周--周五--第5,6节"},{"time":"第6周--周五--第5,6节"},{"time":"第4周--周一--第3,4节"},{"time":"第9周--周五--第5,6节"},{"time":"第5周--周一--第3,4节"},{"time":"第9周--周一--第3,4节"},{"time":"第5周--周五--第5,6节"}],"courseId":"1000002","courseName":"商务导论"},{"courseCollege":"经济与管理学院","teachingCalendarTime":[{"time":"第2周--周二--第7,8节"},{"time":"第15周--周二--第7,8节"},{"time":"第11周--周二--第7,8节"},{"time":"第15周--周四--第1,2节"},{"time":"第12周--周二--第7,8节"},{"time":"第3周--周二--第7,8节"},{"time":"第17周--周二--第7,8节"},{"time":"第14周--周二--第7,8节"},{"time":"第11周--周四--第1,2节"},{"time":"第18周--周二--第7,8节"},{"time":"第1周--周四--第1,2节"},{"time":"第16周--周二--第7,8节"},{"time":"第4周--周四--第1,2节"},{"time":"第7周--周二--第7,8节"},{"time":"第16周--周四--第1,2节"},{"time":"第7周--周四--第1,2节"},{"time":"第14周--周四--第1,2节"},{"time":"第17周--周四--第1,2节"},{"time":"第3周--周四--第1,2节"},{"time":"第1周--周二--第7,8节"},{"time":"第13周--周二--第7,8节"},{"time":"第12周--周四--第1,2节"},{"time":"第8周--周四--第1,2节"},{"time":"第10周--周四--第1,2节"},{"time":"第6周--周二--第7,8节"},{"time":"第10周--周二--第7,8节"},{"time":"第13周--周四--第1,2节"},{"time":"第8周--周二--第7,8节"},{"time":"第9周--周四--第1,2节"},{"time":"第4周--周二--第7,8节"},{"time":"第2周--周四--第1,2节"},{"time":"第6周--周四--第1,2节"},{"time":"第5周--周二--第7,8节"},{"time":"第9周--周二--第7,8节"},{"time":"第5周--周四--第1,2节"},{"time":"第18周--周四--第1,2节"}],"courseId":"1000008","courseName":"商务伦理"}]
     * code : 200
     */

    private String message;
    private int code;
    private List<CourseBean> course;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<CourseBean> getCourse() {
        return course;
    }

    public void setCourse(List<CourseBean> course) {
        this.course = course;
    }

    public static class CourseBean {
        /**
         * courseCollege : 经济与管理学院
         * teachingCalendarTime : [{"time":"第2周--周一--第3,4节"},{"time":"第15周--周一--第3,4节"},{"time":"第15周--周五--第5,6节"},{"time":"第17周--周一--第3,4节"},{"time":"第11周--周一--第3,4节"},{"time":"第12周--周一--第3,4节"},{"time":"第3周--周一--第3,4节"},{"time":"第14周--周一--第3,4节"},{"time":"第11周--周五--第5,6节"},{"time":"第18周--周一--第3,4节"},{"time":"第1周--周五--第5,6节"},{"time":"第4周--周五--第5,6节"},{"time":"第7周--周一--第3,4节"},{"time":"第16周--周一--第3,4节"},{"time":"第7周--周五--第5,6节"},{"time":"第17周--周五--第5,6节"},{"time":"第14周--周五--第5,6节"},{"time":"第3周--周五--第5,6节"},{"time":"第16周--周五--第5,6节"},{"time":"第1周--周一--第3,4节"},{"time":"第13周--周一--第3,4节"},{"time":"第8周--周五--第5,6节"},{"time":"第12周--周五--第5,6节"},{"time":"第6周--周一--第3,4节"},{"time":"第10周--周五--第5,6节"},{"time":"第18周--周五--第5,6节"},{"time":"第10周--周一--第3,4节"},{"time":"第13周--周五--第5,6节"},{"time":"第8周--周一--第3,4节"},{"time":"第2周--周五--第5,6节"},{"time":"第6周--周五--第5,6节"},{"time":"第4周--周一--第3,4节"},{"time":"第9周--周五--第5,6节"},{"time":"第5周--周一--第3,4节"},{"time":"第9周--周一--第3,4节"},{"time":"第5周--周五--第5,6节"}]
         * courseId : 1000002
         * courseName : 商务导论
         */

        private String courseCollege;
        private String courseId;
        private String courseName;
        private List<TeachingCalendarTimeBean> teachingCalendarTime;

        public String getCourseCollege() {
            return courseCollege;
        }

        public void setCourseCollege(String courseCollege) {
            this.courseCollege = courseCollege;
        }

        public String getCourseId() {
            return courseId;
        }

        public void setCourseId(String courseId) {
            this.courseId = courseId;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public List<TeachingCalendarTimeBean> getTeachingCalendarTime() {
            return teachingCalendarTime;
        }

        public void setTeachingCalendarTime(List<TeachingCalendarTimeBean> teachingCalendarTime) {
            this.teachingCalendarTime = teachingCalendarTime;
        }

        public static class TeachingCalendarTimeBean {
            /**
             * time : 第2周--周一--第3,4节
             */

            private String time;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            @Override
            public String toString() {
                return time;
            }
        }
    }
}
