package com.zidian.teacher.presenter.contract;

import com.zidian.teacher.base.BasePresenter;
import com.zidian.teacher.base.BaseView;
import com.zidian.teacher.model.entity.remote.EvaTwoIndex;

import java.util.List;

/**
 * Created by GongCheng on 2017/4/28.
 */

public interface ColleagueEvaTwoIndexContract {
    interface View extends BaseView {
        void showEvaTwoIndex(List<EvaTwoIndex> evaTwoIndices);
    }

    interface Presenter extends BasePresenter<ColleagueEvaTwoIndexContract.View> {
        void getEvaTwoIndex(String evaluateType, String indexName);
    }
}
