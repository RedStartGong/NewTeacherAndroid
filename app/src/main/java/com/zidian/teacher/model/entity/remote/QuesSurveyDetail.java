package com.zidian.teacher.model.entity.remote;

import java.util.List;

/**
 * Created by GongCheng on 2017/5/3.
 */

public class QuesSurveyDetail {

    /**
     * questionnaireExplain : 入学适应性
     * questionnaireTitle : 测试问卷模板
     * question : [{"questionNumber":1,"questionId":226,"questionTypes":"选择题","questioncontent":"第一题","options":[{"option":"1","optionsDescribe":"1111111"},{"option":"2","optionsDescribe":"2222222"},{"option":"3","optionsDescribe":"3333333"}]},{"questionNumber":2,"questionId":227,"questionTypes":"选择题","questioncontent":"第二题","options":[{"option":"1","optionsDescribe":"aaaaaa"},{"option":"2","optionsDescribe":"bbbbbb"},{"option":"3","optionsDescribe":"ccccccc"}]}]
     * releaseTime : 2017-04-18 11:17:39
     * publisher : 陈婷
     */

    private String questionnaireExplain;
    private String questionnaireTitle;
    private String releaseTime;
    private String publisher;
    private List<QuestionBean> question;

    public String getQuestionnaireExplain() {
        return questionnaireExplain;
    }

    public void setQuestionnaireExplain(String questionnaireExplain) {
        this.questionnaireExplain = questionnaireExplain;
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public List<QuestionBean> getQuestion() {
        return question;
    }

    public void setQuestion(List<QuestionBean> question) {
        this.question = question;
    }

    public static class QuestionBean {
        /**
         * questionNumber : 1
         * questionId : 226
         * questionTypes : 选择题
         * questioncontent : 第一题
         * options : [{"option":"1","optionsDescribe":"1111111"},{"option":"2","optionsDescribe":"2222222"},{"option":"3","optionsDescribe":"3333333"}]
         */

        private int questionNumber;
        private int questionId;
        private String questionTypes;
        private String questioncontent;
        private List<OptionsBean> options;

        public int getQuestionNumber() {
            return questionNumber;
        }

        public void setQuestionNumber(int questionNumber) {
            this.questionNumber = questionNumber;
        }

        public int getQuestionId() {
            return questionId;
        }

        public void setQuestionId(int questionId) {
            this.questionId = questionId;
        }

        public String getQuestionTypes() {
            return questionTypes;
        }

        public void setQuestionTypes(String questionTypes) {
            this.questionTypes = questionTypes;
        }

        public String getQuestioncontent() {
            return questioncontent;
        }

        public void setQuestioncontent(String questioncontent) {
            this.questioncontent = questioncontent;
        }

        public List<OptionsBean> getOptions() {
            return options;
        }

        public void setOptions(List<OptionsBean> options) {
            this.options = options;
        }

        public static class OptionsBean {
            /**
             * option : 1
             * optionsDescribe : 1111111
             */

            private String option;
            private String optionsDescribe;

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
}
