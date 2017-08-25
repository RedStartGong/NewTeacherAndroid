package com.zidian.teacher.model.entity.remote;

import java.util.List;

/**
 * Created by GongCheng on 2017/5/3.
 */

public class MyQuesDetail {

    /**
     * questionnaireSelectionList : [{"selectionName":"第一题的选项1","selectionId":10,"selectionNum":1},{"selectionName":"第一题的选项2","selectionId":11,"selectionNum":1},{"selectionName":"第一题的选项3","selectionId":12,"selectionNum":2}]
     * questionnaireItemId : 6
     * questionnaireItemName : 这是单选题
     * questionnaireItemType : 0
     */

    private int questionnaireItemId;
    private String questionnaireItemName;
    private int questionnaireItemType;
    private List<QuestionnaireSelectionListBean> questionnaireSelectionList;

    public int getQuestionnaireItemId() {
        return questionnaireItemId;
    }

    public void setQuestionnaireItemId(int questionnaireItemId) {
        this.questionnaireItemId = questionnaireItemId;
    }

    public String getQuestionnaireItemName() {
        return questionnaireItemName;
    }

    public void setQuestionnaireItemName(String questionnaireItemName) {
        this.questionnaireItemName = questionnaireItemName;
    }

    public int getQuestionnaireItemType() {
        return questionnaireItemType;
    }

    public void setQuestionnaireItemType(int questionnaireItemType) {
        this.questionnaireItemType = questionnaireItemType;
    }

    public List<QuestionnaireSelectionListBean> getQuestionnaireSelectionList() {
        return questionnaireSelectionList;
    }

    public void setQuestionnaireSelectionList(List<QuestionnaireSelectionListBean> questionnaireSelectionList) {
        this.questionnaireSelectionList = questionnaireSelectionList;
    }

    public static class QuestionnaireSelectionListBean {
        /**
         * selectionName : 第一题的选项1
         * selectionId : 10
         * selectionNum : 1
         */

        private String selectionName;
        private int selectionId;
        private int selectionNum;

        public String getSelectionName() {
            return selectionName;
        }

        public void setSelectionName(String selectionName) {
            this.selectionName = selectionName;
        }

        public int getSelectionId() {
            return selectionId;
        }

        public void setSelectionId(int selectionId) {
            this.selectionId = selectionId;
        }

        public int getSelectionNum() {
            return selectionNum;
        }

        public void setSelectionNum(int selectionNum) {
            this.selectionNum = selectionNum;
        }
    }
}
