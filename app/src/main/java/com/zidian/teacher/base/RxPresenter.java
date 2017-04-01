package com.zidian.teacher.base;

import android.support.annotation.NonNull;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * 基于RxJava的Presenter
 * Created by GongCheng on 2017/3/14.
 */

public class RxPresenter<T extends BaseView> implements BasePresenter<T> {
    protected T view;
    private CompositeSubscription compositeSubscription;

    protected void addSubscribe(@NonNull Subscription subscription) {
        if (compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(subscription);

    }

    private void unSubscribe() {
        if (compositeSubscription != null) {
            compositeSubscription.unsubscribe();
        }
    }

    @Override
    public void attachView(T view) {
        this.view = view;
    }

    @Override
    public void deAttachView() {
        this.view = null;
        unSubscribe();
    }
}
