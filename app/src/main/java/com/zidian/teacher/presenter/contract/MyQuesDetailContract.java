package com.zidian.teacher.presenter.contract;

import com.zidian.teacher.base.BasePresenter;
import com.zidian.teacher.base.BaseView;
import com.zidian.teacher.model.entity.remote.MyQuesDetail;

import java.util.List;

/**
 * Created by GongCheng on 2017/5/4.
 */

public interface MyQuesDetailContract {
    interface View extends BaseView {
        void showMyQuesDetail(List<MyQuesDetail> myQuesDetails);
    }

    interface Presenter extends BasePresenter<MyQuesDetailContract.View> {
        void getMyQuesDetail(int questionnaireId, long releaseTime);
    }
}
