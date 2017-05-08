package com.zidian.teacher.ui.questionnaire.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/12 0012.
 */
public class QuestionAddBean {

    private String questionName;
    private String itemName;
    private int id;
    private int pId = -1;
    private boolean isChild = false;
    private boolean isLastChild = false;
    private boolean hasItem = false;
    private List<QuestionAddBean> child = new ArrayList<>();

    public QuestionAddBean() {

    }

    public QuestionAddBean(int pId, int id, boolean isChild, boolean isLastChild) {
        this.isChild = isChild;
        this.isLastChild = isLastChild;
        this.id = id;
        this.pId = pId;
    }

    public boolean isHasItem() {
        return child.size() > 0;
    }

    public void setHasItem(boolean hasItem) {
        this.hasItem = hasItem;
    }

    public boolean isLastChild() {
        return isLastChild;
    }

    public void setIsLastChild(boolean isLastChild) {
        this.isLastChild = isLastChild;
    }

    public boolean isChild() {
        return isChild;
    }

    public void setIsChild(boolean isChild) {
        this.isChild = isChild;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public List<QuestionAddBean> getChild() {
        return child;
    }

    public void setChild(List<QuestionAddBean> child) {
        this.child = child;
    }


}
