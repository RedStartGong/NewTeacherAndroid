package com.zidian.teacher.model.entity.remote;

import java.util.List;

/**
 * Created by GongCheng on 2017/4/18.
 */

public class EvaluateCourse {

    /**
     * collegeOtherInformation : [{"course":[{"courseCollege":"经济与管理学院","teachingCalendarTime":[{"time":"第1周--周日--第7,8节"},{"time":"第1周--周日--第1,2节"},{"time":"第1周--周日--第5,6节"},{"time":"第1周--周日--第3,4节"},{"time":"第2周--周日--第1,2节"},{"time":"第2周--周日--第7,8节"},{"time":"第2周--周日--第3,4节"},{"time":"第2周--周日--第5,6节"},{"time":"第3周--周日--第3,4节"},{"time":"第3周--周日--第7,8节"},{"time":"第3周--周日--第1,2节"},{"time":"第3周--周日--第5,6节"},{"time":"第4周--周日--第1,2节"},{"time":"第4周--周日--第7,8节"},{"time":"第4周--周日--第5,6节"},{"time":"第4周--周日--第3,4节"},{"time":"第5周--周日--第1,2节"},{"time":"第5周--周日--第3,4节"},{"time":"第5周--周日--第7,8节"},{"time":"第5周--周日--第5,6节"},{"time":"第6周--周日--第7,8节"},{"time":"第6周--周日--第5,6节"},{"time":"第6周--周日--第1,2节"},{"time":"第6周--周日--第3,4节"},{"time":"第7周--周日--第7,8节"},{"time":"第7周--周日--第3,4节"},{"time":"第7周--周日--第5,6节"},{"time":"第7周--周日--第1,2节"},{"time":"第8周--周日--第3,4节"},{"time":"第8周--周日--第1,2节"},{"time":"第8周--周日--第7,8节"},{"time":"第8周--周日--第5,6节"},{"time":"第9周--周日--第1,2节"},{"time":"第9周--周日--第3,4节"},{"time":"第9周--周日--第7,8节"},{"time":"第9周--周日--第5,6节"},{"time":"第10周--周日--第3,4节"},{"time":"第10周--周日--第7,8节"},{"time":"第10周--周日--第5,6节"},{"time":"第10周--周日--第1,2节"},{"time":"第11周--周日--第1,2节"},{"time":"第11周--周日--第5,6节"},{"time":"第11周--周日--第3,4节"},{"time":"第11周--周日--第7,8节"},{"time":"第12周--周日--第7,8节"},{"time":"第12周--周日--第5,6节"},{"time":"第12周--周日--第1,2节"},{"time":"第12周--周日--第3,4节"},{"time":"第13周--周日--第3,4节"},{"time":"第13周--周日--第7,8节"},{"time":"第13周--周日--第1,2节"},{"time":"第13周--周日--第5,6节"},{"time":"第14周--周日--第1,2节"},{"time":"第14周--周日--第5,6节"},{"time":"第14周--周日--第7,8节"},{"time":"第14周--周日--第3,4节"},{"time":"第15周--周日--第1,2节"},{"time":"第15周--周日--第5,6节"},{"time":"第15周--周日--第3,4节"},{"time":"第15周--周日--第7,8节"},{"time":"第16周--周日--第3,4节"},{"time":"第16周--周日--第7,8节"},{"time":"第16周--周日--第5,6节"},{"time":"第16周--周日--第1,2节"},{"time":"第17周--周日--第5,6节"},{"time":"第17周--周日--第1,2节"},{"time":"第17周--周日--第7,8节"},{"time":"第17周--周日--第3,4节"},{"time":"第18周--周日--第1,2节"},{"time":"第18周--周日--第7,8节"},{"time":"第18周--周日--第3,4节"},{"time":"第18周--周日--第5,6节"}],"courseId":"10000013","courseName":"商务谈判"}],"teacherCollege":"经济与管理学院","teacherName":"景云","teacherId":"1006"}]
     * collegeName : 经济与管理学院
     */

    private String collegeName;
    private List<CollegeOtherInformationBean> collegeOtherInformation;

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public List<CollegeOtherInformationBean> getCollegeOtherInformation() {
        return collegeOtherInformation;
    }

    public void setCollegeOtherInformation(List<CollegeOtherInformationBean> collegeOtherInformation) {
        this.collegeOtherInformation = collegeOtherInformation;
    }

    public static class CollegeOtherInformationBean {
        /**
         * course : [{"courseCollege":"经济与管理学院","teachingCalendarTime":[{"time":"第1周--周日--第7,8节"},{"time":"第1周--周日--第1,2节"},{"time":"第1周--周日--第5,6节"},{"time":"第1周--周日--第3,4节"},{"time":"第2周--周日--第1,2节"},{"time":"第2周--周日--第7,8节"},{"time":"第2周--周日--第3,4节"},{"time":"第2周--周日--第5,6节"},{"time":"第3周--周日--第3,4节"},{"time":"第3周--周日--第7,8节"},{"time":"第3周--周日--第1,2节"},{"time":"第3周--周日--第5,6节"},{"time":"第4周--周日--第1,2节"},{"time":"第4周--周日--第7,8节"},{"time":"第4周--周日--第5,6节"},{"time":"第4周--周日--第3,4节"},{"time":"第5周--周日--第1,2节"},{"time":"第5周--周日--第3,4节"},{"time":"第5周--周日--第7,8节"},{"time":"第5周--周日--第5,6节"},{"time":"第6周--周日--第7,8节"},{"time":"第6周--周日--第5,6节"},{"time":"第6周--周日--第1,2节"},{"time":"第6周--周日--第3,4节"},{"time":"第7周--周日--第7,8节"},{"time":"第7周--周日--第3,4节"},{"time":"第7周--周日--第5,6节"},{"time":"第7周--周日--第1,2节"},{"time":"第8周--周日--第3,4节"},{"time":"第8周--周日--第1,2节"},{"time":"第8周--周日--第7,8节"},{"time":"第8周--周日--第5,6节"},{"time":"第9周--周日--第1,2节"},{"time":"第9周--周日--第3,4节"},{"time":"第9周--周日--第7,8节"},{"time":"第9周--周日--第5,6节"},{"time":"第10周--周日--第3,4节"},{"time":"第10周--周日--第7,8节"},{"time":"第10周--周日--第5,6节"},{"time":"第10周--周日--第1,2节"},{"time":"第11周--周日--第1,2节"},{"time":"第11周--周日--第5,6节"},{"time":"第11周--周日--第3,4节"},{"time":"第11周--周日--第7,8节"},{"time":"第12周--周日--第7,8节"},{"time":"第12周--周日--第5,6节"},{"time":"第12周--周日--第1,2节"},{"time":"第12周--周日--第3,4节"},{"time":"第13周--周日--第3,4节"},{"time":"第13周--周日--第7,8节"},{"time":"第13周--周日--第1,2节"},{"time":"第13周--周日--第5,6节"},{"time":"第14周--周日--第1,2节"},{"time":"第14周--周日--第5,6节"},{"time":"第14周--周日--第7,8节"},{"time":"第14周--周日--第3,4节"},{"time":"第15周--周日--第1,2节"},{"time":"第15周--周日--第5,6节"},{"time":"第15周--周日--第3,4节"},{"time":"第15周--周日--第7,8节"},{"time":"第16周--周日--第3,4节"},{"time":"第16周--周日--第7,8节"},{"time":"第16周--周日--第5,6节"},{"time":"第16周--周日--第1,2节"},{"time":"第17周--周日--第5,6节"},{"time":"第17周--周日--第1,2节"},{"time":"第17周--周日--第7,8节"},{"time":"第17周--周日--第3,4节"},{"time":"第18周--周日--第1,2节"},{"time":"第18周--周日--第7,8节"},{"time":"第18周--周日--第3,4节"},{"time":"第18周--周日--第5,6节"}],"courseId":"10000013","courseName":"商务谈判"}]
         * teacherCollege : 经济与管理学院
         * teacherName : 景云
         * teacherId : 1006
         */

        private String teacherCollege;
        private String teacherName;
        private String teacherId;
        private List<CourseBean> course;

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

        public List<CourseBean> getCourse() {
            return course;
        }

        public void setCourse(List<CourseBean> course) {
            this.course = course;
        }

        public static class CourseBean {
            /**
             * courseCollege : 经济与管理学院
             * teachingCalendarTime : [{"time":"第1周--周日--第7,8节"},{"time":"第1周--周日--第1,2节"},{"time":"第1周--周日--第5,6节"},{"time":"第1周--周日--第3,4节"},{"time":"第2周--周日--第1,2节"},{"time":"第2周--周日--第7,8节"},{"time":"第2周--周日--第3,4节"},{"time":"第2周--周日--第5,6节"},{"time":"第3周--周日--第3,4节"},{"time":"第3周--周日--第7,8节"},{"time":"第3周--周日--第1,2节"},{"time":"第3周--周日--第5,6节"},{"time":"第4周--周日--第1,2节"},{"time":"第4周--周日--第7,8节"},{"time":"第4周--周日--第5,6节"},{"time":"第4周--周日--第3,4节"},{"time":"第5周--周日--第1,2节"},{"time":"第5周--周日--第3,4节"},{"time":"第5周--周日--第7,8节"},{"time":"第5周--周日--第5,6节"},{"time":"第6周--周日--第7,8节"},{"time":"第6周--周日--第5,6节"},{"time":"第6周--周日--第1,2节"},{"time":"第6周--周日--第3,4节"},{"time":"第7周--周日--第7,8节"},{"time":"第7周--周日--第3,4节"},{"time":"第7周--周日--第5,6节"},{"time":"第7周--周日--第1,2节"},{"time":"第8周--周日--第3,4节"},{"time":"第8周--周日--第1,2节"},{"time":"第8周--周日--第7,8节"},{"time":"第8周--周日--第5,6节"},{"time":"第9周--周日--第1,2节"},{"time":"第9周--周日--第3,4节"},{"time":"第9周--周日--第7,8节"},{"time":"第9周--周日--第5,6节"},{"time":"第10周--周日--第3,4节"},{"time":"第10周--周日--第7,8节"},{"time":"第10周--周日--第5,6节"},{"time":"第10周--周日--第1,2节"},{"time":"第11周--周日--第1,2节"},{"time":"第11周--周日--第5,6节"},{"time":"第11周--周日--第3,4节"},{"time":"第11周--周日--第7,8节"},{"time":"第12周--周日--第7,8节"},{"time":"第12周--周日--第5,6节"},{"time":"第12周--周日--第1,2节"},{"time":"第12周--周日--第3,4节"},{"time":"第13周--周日--第3,4节"},{"time":"第13周--周日--第7,8节"},{"time":"第13周--周日--第1,2节"},{"time":"第13周--周日--第5,6节"},{"time":"第14周--周日--第1,2节"},{"time":"第14周--周日--第5,6节"},{"time":"第14周--周日--第7,8节"},{"time":"第14周--周日--第3,4节"},{"time":"第15周--周日--第1,2节"},{"time":"第15周--周日--第5,6节"},{"time":"第15周--周日--第3,4节"},{"time":"第15周--周日--第7,8节"},{"time":"第16周--周日--第3,4节"},{"time":"第16周--周日--第7,8节"},{"time":"第16周--周日--第5,6节"},{"time":"第16周--周日--第1,2节"},{"time":"第17周--周日--第5,6节"},{"time":"第17周--周日--第1,2节"},{"time":"第17周--周日--第7,8节"},{"time":"第17周--周日--第3,4节"},{"time":"第18周--周日--第1,2节"},{"time":"第18周--周日--第7,8节"},{"time":"第18周--周日--第3,4节"},{"time":"第18周--周日--第5,6节"}]
             * courseId : 10000013
             * courseName : 商务谈判
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
                 * time : 第1周--周日--第7,8节
                 */

                private String time;

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }
            }
        }
    }
}
