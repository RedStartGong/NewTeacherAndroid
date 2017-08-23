package com.zidian.teacher.model.entity.remote;

import java.util.List;

/**
 * Created by GongCheng on 2017/8/23.
 */

public class CourseTime {

    private List<CoursePlanTimeBean> coursePlanTime;
    private List<DayTimeBean> dayTime;

    public List<CoursePlanTimeBean> getCoursePlanTime() {
        return coursePlanTime;
    }

    public void setCoursePlanTime(List<CoursePlanTimeBean> coursePlanTime) {
        this.coursePlanTime = coursePlanTime;
    }

    public List<DayTimeBean> getDayTime() {
        return dayTime;
    }

    public void setDayTime(List<DayTimeBean> dayTime) {
        this.dayTime = dayTime;
    }

    public static class CoursePlanTimeBean {
        /**
         * startTime : 1496278800000
         * coursePlanId : 1
         * endTime : 1496280600000
         */

        private long startTime;
        private int coursePlanId;
        private long endTime;

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public int getCoursePlanId() {
            return coursePlanId;
        }

        public void setCoursePlanId(int coursePlanId) {
            this.coursePlanId = coursePlanId;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }
    }

    public static class DayTimeBean {
        /**
         * time : 1497229200000
         * day : 1
         */

        private long time;
        private int day;

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }
    }
}
