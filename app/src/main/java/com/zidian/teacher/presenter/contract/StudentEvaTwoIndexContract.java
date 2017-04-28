package com.zidian.teacher.presenter.contract;

import com.zidian.teacher.base.BasePresenter;
import com.zidian.teacher.base.BaseView;
import com.zidian.teacher.model.entity.remote.EvaTwoIndex;

import java.util.List;

/**
 * Created by GongCheng on 2017/4/28.
 */

public interface StudentEvaTwoIndexContract {
    interface View extends BaseView {
        void showEvaTwoIndex(List<EvaTwoIndex> evaTwoIndexes);
    }

    interface Presenter extends BasePresenter<StudentEvaTwoIndexContract.View> {
        void getStudentEvaTwoIndex(String indexName);
    }
}
