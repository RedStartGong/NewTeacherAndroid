package com.zidian.teacher.ui.questionnaire.event;

/**
 * Created by GongCheng on 2017/5/5.
 */

public class QuesSurveyFinishEvent {
    private boolean isSuccess;

    public QuesSurveyFinishEvent(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean isSuccess() {
        return isSuccess;
    }
}
