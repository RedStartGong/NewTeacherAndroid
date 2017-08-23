package com.zidian.teacher.model.entity.remote;

import com.zidian.teacher.util.TimeUtils;

/**
 * Created by GongCheng on 2017/8/23.
 */

public class CoursePlan {

    /**
     * startTime : 1496037600000
     * classRoom : D3-506
     * whatWeek : 1
     * weekDay : 1
     * coursePlanId : 3257
     * endTime : 1496041800000
     */

    private long startTime;
    private String classRoom;
    private int whatWeek;
    private int weekDay;
    private int coursePlanId;
    private long endTime;

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public int getWhatWeek() {
        return whatWeek;
    }

    public void setWhatWeek(int whatWeek) {
        this.whatWeek = whatWeek;
    }

    public int getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(int weekDay) {
        this.weekDay = weekDay;
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

    @Override
    public String toString() {
        return "第" + whatWeek + "周  周" + getWeekDayString() +" " + TimeUtils.millis2String(startTime, "HH:mm --")
                +TimeUtils.millis2String(endTime, " HH:mm");
    }

    public String getWeekDayString() {
        if (weekDay == 1) {
            return "一";
        } else if (weekDay ==2) {
            return "二";
        } else if (weekDay == 3) {
            return "三";
        } else if (weekDay == 4) {
            return "四";
        } else if (weekDay == 5) {
            return "五";
        } else if (weekDay == 6) {
            return "六";
        } else {
            return "七";
        }
    }
}
