package com.zidian.teacher.base;

/**
 * Presenter基类
 * Created by GongCheng on 2017/3/14.
 */

public interface BasePresenter<T extends BaseView> {

    void attachView(T view);

    void deAttachView();
}
