package com.zidian.teacher.model.entity.remote;

import java.util.List;

/**
 * Created by GongCheng on 2017/4/27.
 */

public class StudentEva {


    /**
     * teacherRank : 2
     * collegeScore : 3.4
     * myAvg : 4.4
     * schoolScore : 3.4
     * indexOneMapList : [{"indexTwoList":[{"indexTwoName":"课程设置","indexTwoId":1}],"indexOneId":1,"indexOneName":"（学科）专业建设与课程设置"},{"indexTwoList":[{"indexTwoName":"师生互动","indexTwoId":2},{"indexTwoName":"教学态度/能力（师生和谐）","indexTwoId":3}],"indexOneId":2,"indexOneName":"师德评价"},{"indexTwoList":[{"indexTwoName":"教学方法","indexTwoId":4},{"indexTwoName":"教学管理","indexTwoId":5},{"indexTwoName":"教学内容","indexTwoId":6},{"indexTwoName":"课堂教学","indexTwoId":7}],"indexOneId":3,"indexOneName":"教学过程评价"},{"indexTwoList":[{"indexTwoName":"教学目标达成度","indexTwoId":8},{"indexTwoName":"综合能力提升度","indexTwoId":9}],"indexOneId":4,"indexOneName":"教学效果评价"},{"indexTwoList":[{"indexTwoName":"学校教学质量总体满意度评价","indexTwoId":10}],"indexOneId":5,"indexOneName":"学校教学质量总体满意度"}]
     */

    private int teacherRank;
    private double collegeScore;
    private double myAvg;
    private double schoolScore;
    private List<IndexOneMapListBean> indexOneMapList;

    public int getTeacherRank() {
        return teacherRank;
    }

    public void setTeacherRank(int teacherRank) {
        this.teacherRank = teacherRank;
    }

    public double getCollegeScore() {
        return collegeScore;
    }

    public void setCollegeScore(double collegeScore) {
        this.collegeScore = collegeScore;
    }

    public double getMyAvg() {
        return myAvg;
    }

    public void setMyAvg(double myAvg) {
        this.myAvg = myAvg;
    }

    public double getSchoolScore() {
        return schoolScore;
    }

    public void setSchoolScore(double schoolScore) {
        this.schoolScore = schoolScore;
    }

    public List<IndexOneMapListBean> getIndexOneMapList() {
        return indexOneMapList;
    }

    public void setIndexOneMapList(List<IndexOneMapListBean> indexOneMapList) {
        this.indexOneMapList = indexOneMapList;
    }

    public static class IndexOneMapListBean {
        /**
         * indexTwoList : [{"indexTwoName":"课程设置","indexTwoId":1}]
         * indexOneId : 1
         * indexOneName : （学科）专业建设与课程设置
         */

        private int indexOneId;
        private String indexOneName;
        private List<IndexTwoListBean> indexTwoList;

        public int getIndexOneId() {
            return indexOneId;
        }

        public void setIndexOneId(int indexOneId) {
            this.indexOneId = indexOneId;
        }

        public String getIndexOneName() {
            return indexOneName;
        }

        public void setIndexOneName(String indexOneName) {
            this.indexOneName = indexOneName;
        }

        public List<IndexTwoListBean> getIndexTwoList() {
            return indexTwoList;
        }

        public void setIndexTwoList(List<IndexTwoListBean> indexTwoList) {
            this.indexTwoList = indexTwoList;
        }

        public static class IndexTwoListBean {
            /**
             * indexTwoName : 课程设置
             * indexTwoId : 1
             */

            private String indexTwoName;
            private int indexTwoId;

            public String getIndexTwoName() {
                return indexTwoName;
            }

            public void setIndexTwoName(String indexTwoName) {
                this.indexTwoName = indexTwoName;
            }

            public int getIndexTwoId() {
                return indexTwoId;
            }

            public void setIndexTwoId(int indexTwoId) {
                this.indexTwoId = indexTwoId;
            }
        }
    }
}
