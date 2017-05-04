package com.zidian.teacher.model.entity.remote;

import java.util.List;

/**
 * Created by GongCheng on 2017/5/3.
 */

public class MyQuesList {

    /**
     * pages : 2
     * list : [{"notChooseNumber":0,"chooseNumber":4,"questionnaireTitle":"你们好吗","releaseTime":"2016-8-8 17-47"},{"notChooseNumber":4,"chooseNumber":0,"questionnaireTitle":"问卷3","releaseTime":"2016/08/12 17:29:34"}]
     */

    private int pages;
    private List<ListBean> list;

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * notChooseNumber : 0
         * chooseNumber : 4
         * questionnaireTitle : 你们好吗
         * releaseTime : 2016-8-8 17-47
         * questionnaireId : 11
         */

        private int notChooseNumber;
        private int chooseNumber;
        private String questionnaireTitle;
        private String releaseTime;
        private int questionnaireId;

        public int getQuestionnaireId() {
            return questionnaireId;
        }

        public void setQuestionnaireId(int questionnaireId) {
            this.questionnaireId = questionnaireId;
        }

        public int getNotChooseNumber() {
            return notChooseNumber;
        }

        public void setNotChooseNumber(int notChooseNumber) {
            this.notChooseNumber = notChooseNumber;
        }

        public int getChooseNumber() {
            return chooseNumber;
        }

        public void setChooseNumber(int chooseNumber) {
            this.chooseNumber = chooseNumber;
        }

        public String getQuestionnaireTitle() {
            return questionnaireTitle;
        }

        public void setQuestionnaireTitle(String questionnaireTitle) {
            this.questionnaireTitle = questionnaireTitle;
        }

        public String getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(String releaseTime) {
            this.releaseTime = releaseTime;
        }
    }
}
