package com.zidian.teacher.model.entity.remote;

import java.util.List;

/**
 * Created by GongCheng on 2017/4/27.
 */

public class StudentEva {

    /**
     * collegeScore : 3.59
     * teacherAvg : 3.64
     * teacherRanking : 2
     * label : [{"indexName":"（学科）专业建设与课程设置","twoIndexName":["课程设置"]},{"indexName":"师德评价","twoIndexName":["师生互动","教学态度/能力（师生和谐）"]},{"indexName":"教学过程评价","twoIndexName":["教学方法","教学管理","教学内容","课堂教学"]},{"indexName":"教学效果评价","twoIndexName":["教学目标达成度","综合能力提升度"]}]
     * schoolScore : 3.81
     */

    private float collegeScore;
    private float teacherAvg;
    private int teacherRanking;
    private float schoolScore;
    private List<LabelBean> label;

    public float getCollegeScore() {
        return collegeScore;
    }

    public void setCollegeScore(float collegeScore) {
        this.collegeScore = collegeScore;
    }

    public float getTeacherAvg() {
        return teacherAvg;
    }

    public void setTeacherAvg(float teacherAvg) {
        this.teacherAvg = teacherAvg;
    }

    public int getTeacherRanking() {
        return teacherRanking;
    }

    public void setTeacherRanking(int teacherRanking) {
        this.teacherRanking = teacherRanking;
    }

    public float getSchoolScore() {
        return schoolScore;
    }

    public void setSchoolScore(float schoolScore) {
        this.schoolScore = schoolScore;
    }

    public List<LabelBean> getLabel() {
        return label;
    }

    public void setLabel(List<LabelBean> label) {
        this.label = label;
    }

    public static class LabelBean {
        /**
         * indexName : （学科）专业建设与课程设置
         * twoIndexName : ["课程设置"]
         */

        private String indexName;
        private List<String> twoIndexName;

        public String getIndexName() {
            return indexName;
        }

        public void setIndexName(String indexName) {
            this.indexName = indexName;
        }

        public List<String> getTwoIndexName() {
            return twoIndexName;
        }

        public void setTwoIndexName(List<String> twoIndexName) {
            this.twoIndexName = twoIndexName;
        }
    }
}
