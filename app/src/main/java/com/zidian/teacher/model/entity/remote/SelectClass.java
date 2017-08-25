package com.zidian.teacher.model.entity.remote;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by GongCheng on 2017/5/8.
 */

public class SelectClass implements Serializable {

    /**
     * classId : 10
     * className : 工商20151
     */

    private int classId;
    private String className;
    @Expose(serialize = false, deserialize = false)
    private boolean isSelected;

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
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
