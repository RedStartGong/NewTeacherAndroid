package com.zidian.teacher.ui.mine.activity;

import android.app.ProgressDialog;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.rxbinding.support.v7.widget.RxToolbar;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseActivity;
import com.zidian.teacher.presenter.FeedbackPresenter;
import com.zidian.teacher.presenter.contract.FeedbackContract;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import rx.functions.Action1;

import static dagger.internal.Preconditions.checkNotNull;

/**
 * Created by GongCheng on 2017/4/10.
 */

public class FeedbackActivity extends BaseActivity implements FeedbackContract.View{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.til_feedback)
    TextInputLayout tilFeedback;
    @BindView(R.id.et_feedback_content)
    EditText etFeedbackContent;

    @Inject
    FeedbackPresenter presenter;

    private ProgressDialog progressDialog;

    @Override
    protected int getLayout() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initViewAndData() {
        toolbar.setTitle(getString(R.string.mine_feedback));
        RxToolbar.navigationClicks(toolbar).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {

            }
        });
        setToolbarBack(toolbar);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.feedback_loading));
        tilFeedback.setCounterEnabled(true);
        tilFeedback.setCounterMaxLength(500);
        RxTextView.textChangeEvents(etFeedbackContent).subscribe(new Action1<TextViewTextChangeEvent>() {
            @Override
            public void call(TextViewTextChangeEvent textViewTextChangeEvent) {
                tilFeedback.setErrorEnabled(false);
            }
        });
        checkNotNull(presenter);
        presenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.deAttachView();
    }

    @OnClick(R.id.btn_feedback_submit)
    public void feedback() {
        String feedbackContent = etFeedbackContent.getText().toString().trim();
        if (TextUtils.isEmpty(feedbackContent)) {
            tilFeedback.setError("反馈信息不能为空");
            tilFeedback.setErrorEnabled(true);
            return;
        }
        presenter.feedback(feedbackContent);
    }

    @Override
    public void showError(Throwable e) {
        progressDialog.dismiss();
    }

    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void showSuccess() {
        progressDialog.dismiss();
        Toast.makeText(this, getString(R.string.feedback_succeed), Toast.LENGTH_SHORT).show();
        finish();
    }
}
