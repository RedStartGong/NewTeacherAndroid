package com.zidian.teacher.ui.questionnaire.bean;

/**
 * Created by GongCheng on 2017/5/8.
 */

public class ClassBean {
    private String className;
    private boolean isSelected;

    public ClassBean(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
