package com.zidian.teacher.presenter.contract;

import com.zidian.teacher.base.BasePresenter;
import com.zidian.teacher.base.BaseView;
import com.zidian.teacher.model.entity.remote.MyTask;

import java.util.List;

/**
 * Created by GongCheng on 2017/4/12.
 */

public interface MyTaskContract {
    interface View extends BaseView {
        void showTasks(List<MyTask> tasks);

        void showEmpty();
    }

    interface Presenter extends BasePresenter<MyTaskContract.View> {
        void getTasks(String requestState);
    }
}
