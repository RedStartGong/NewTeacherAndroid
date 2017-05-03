package com.zidian.teacher.model.entity.remote;

import java.util.List;

/**
 * Created by GongCheng on 2017/5/3.
 */

public class MyQuesDetail {

    /**
     * questionNum : 1
     * questionContent : 你煞笔
     * statistical : [{"stisNum":3,"option":"2","optionsDescribe":"你猜煞笔"},{"stisNum":1,"option":"1","optionsDescribe":"你煞笔"},{"stisNum":1,"option":"3","optionsDescribe":"你最煞笔"}]
     */

    private int questionNum;
    private String questionContent;
    private List<StatisticalBean> statistical;

    public int getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(int questionNum) {
        this.questionNum = questionNum;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public List<StatisticalBean> getStatistical() {
        return statistical;
    }

    public void setStatistical(List<StatisticalBean> statistical) {
        this.statistical = statistical;
    }

    public static class StatisticalBean {
        /**
         * stisNum : 3
         * option : 2
         * optionsDescribe : 你猜煞笔
         */

        private int stisNum;
        private String option;
        private String optionsDescribe;

        public int getStisNum() {
            return stisNum;
        }

        public void setStisNum(int stisNum) {
            this.stisNum = stisNum;
        }

        public String getOption() {
            return option;
        }

        public void setOption(String option) {
            this.option = option;
        }

        public String getOptionsDescribe() {
            return optionsDescribe;
        }

        public void setOptionsDescribe(String optionsDescribe) {
            this.optionsDescribe = optionsDescribe;
        }
    }
}
