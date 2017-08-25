package com.zidian.teacher.ui.evaluate.activity;

import android.app.ProgressDialog;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.zidian.teacher.R;
import com.zidian.teacher.base.BaseActivity;
import com.zidian.teacher.presenter.SupervisorFeedbackPresenter;
import com.zidian.teacher.presenter.contract.SupervisorFeedbackContract;
import com.zidian.teacher.ui.evaluate.event.FeedbackEvent;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;

import static com.zidian.teacher.util.Preconditions.checkNotNull;

/**
 * Created by GongCheng on 2017/4/26.
 */

public class SupervisorFeedbackActivity extends BaseActivity implements SupervisorFeedbackContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_supervisor_feedback)
    AppCompatEditText etSupervisorFeedback;
    @BindView(R.id.til_supervisor_feedback)
    TextInputLayout tilSupervisorFeedback;

    @Inject
    SupervisorFeedbackPresenter presenter;

    private int requestEvalMessageId;
    private ProgressDialog progressDialog;

    @Override
    protected int getLayout() {
        return R.layout.activity_supervisor_feedback;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initViewAndData() {
        requestEvalMessageId = getIntent().getIntExtra("requestEvalMessageId", 0);
        toolbar.setTitle("申诉");
        setToolbarBack(toolbar);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("提交中...");
        tilSupervisorFeedback.setCounterEnabled(true);
        tilSupervisorFeedback.setCounterMaxLength(100);
        Subscription textSubscription = RxTextView.textChanges(etSupervisorFeedback)
                .subscribe(new Action1<CharSequence>() {
                    @Override
                    public void call(CharSequence charSequence) {
                        tilSupervisorFeedback.setErrorEnabled(false);
                    }
                });
        addSubscribe(textSubscription);
        checkNotNull(presenter);
        presenter.attachView(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.deAttachView();

    }

    @OnClick(R.id.btn_submit)
    public void submit() {
        if (TextUtils.isEmpty(etSupervisorFeedback.getText().toString().trim())) {
            tilSupervisorFeedback.setEnabled(true);
            tilSupervisorFeedback.setError("反馈内容不得为空！");
            return;
        }
        presenter.feedback(requestEvalMessageId, etSupervisorFeedback.getText().toString().trim());
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
        EventBus.getDefault().post(new FeedbackEvent(true));
        finish();
    }
}
