package com.zidian.teacher.ui.evaluate.event;

/**
 * Created by GongCheng on 2017/5/16.
 */

public class FeedbackEvent {
    private boolean isSuccess;

    public FeedbackEvent(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean isSuccess() {
        return isSuccess;
    }
}
