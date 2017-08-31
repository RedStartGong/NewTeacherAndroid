package com.zidian.teacher.ui.questionnaire.bean;

import com.zidian.teacher.base.GsonBean;

import java.util.List;

/**
 * 问卷题目bean
 * Created by GongCheng on 2017/8/25.
 */

public class QuestionnaireItem extends GsonBean{

    /**
     * itemName : 问题第一题
     * itemType : 0
     * itemRemark : 问题描述或备注
     * itemOptions : [{"optionName":"第一题的选项1","score":3},{"optionName":"第一题的选项2","score":2},{"optionName":"第一题的选项3","score":1}]
     */

    private String itemName;
    private int itemType;
    private String itemRemark;
    private List<ItemOptionsBean> itemOptions;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getItemRemark() {
        return itemRemark;
    }

    public void setItemRemark(String itemRemark) {
        this.itemRemark = itemRemark;
    }

    public List<ItemOptionsBean> getItemOptions() {
        return itemOptions;
    }

    public void setItemOptions(List<ItemOptionsBean> itemOptions) {
        this.itemOptions = itemOptions;
    }

    public static class ItemOptionsBean {
        /**
         * optionName : 第一题的选项1
         * score : 3
         */

        private String optionName;
        private int score;

        public ItemOptionsBean() {
        }

        public ItemOptionsBean(String optionName, int score) {
            this.optionName = optionName;
            this.score = score;
        }

        public String getOptionName() {
            return optionName;
        }

        public void setOptionName(String optionName) {
            this.optionName = optionName;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }
    }
}
