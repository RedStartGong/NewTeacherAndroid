package com.zidian.teacher.model.entity.remote;

import java.util.List;

/**
 * Created by GongCheng on 2017/5/3.
 */

public class QuesSurveyList {

    /**
     * pages : 1
     * questionnaireList : [{"questionnaireExplain":"入学适应性","questionnaireId":121,"questionnaireTitle":"测试问卷模板","questionnaireURL":"","releaseTime":"2017-04-18 11:17:39","publisher":"陈婷"}]
     */

    private int pages;
    private List<QuestionnaireListBean> questionnaireList;

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<QuestionnaireListBean> getQuestionnaireList() {
        return questionnaireList;
    }

    public void setQuestionnaireList(List<QuestionnaireListBean> questionnaireList) {
        this.questionnaireList = questionnaireList;
    }

    public static class QuestionnaireListBean {
        /**
         * questionnaireExplain : 入学适应性
         * questionnaireId : 121
         * questionnaireTitle : 测试问卷模板
         * questionnaireURL :
         * releaseTime : 2017-04-18 11:17:39
         * publisher : 陈婷
         */

        private String questionnaireExplain;
        private int questionnaireId;
        private String questionnaireTitle;
        private String questionnaireURL;
        private String releaseTime;
        private String publisher;

        public String getQuestionnaireExplain() {
            return questionnaireExplain;
        }

        public void setQuestionnaireExplain(String questionnaireExplain) {
            this.questionnaireExplain = questionnaireExplain;
        }

        public int getQuestionnaireId() {
            return questionnaireId;
        }

        public void setQuestionnaireId(int questionnaireId) {
            this.questionnaireId = questionnaireId;
        }

        public String getQuestionnaireTitle() {
            return questionnaireTitle;
        }

        public void setQuestionnaireTitle(String questionnaireTitle) {
            this.questionnaireTitle = questionnaireTitle;
        }

        public String getQuestionnaireURL() {
            return questionnaireURL;
        }

        public void setQuestionnaireURL(String questionnaireURL) {
            this.questionnaireURL = questionnaireURL;
        }

        public String getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(String releaseTime) {
            this.releaseTime = releaseTime;
        }

        public String getPublisher() {
            return publisher;
        }

        public void setPublisher(String publisher) {
            this.publisher = publisher;
        }
    }
}
