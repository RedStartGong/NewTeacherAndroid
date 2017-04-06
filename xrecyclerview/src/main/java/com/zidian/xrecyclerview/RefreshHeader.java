package com.zidian.xrecyclerview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zidian.student.xrecyclerview.R;

/**
 * Created by yangcai on 2016/1/27.
 */
public class RefreshHeader extends LinearLayout implements BaseRefreshHeader {
    private Context mContext;
    private TextView msg;
    private int mState = STATE_NORMAL;
    private int mMeasuredHeight;
    private LinearLayout mContainer;
    private ProgressBar progressBar;
    private ImageView imageView;

    public RefreshHeader(Context context) {
        this(context, null);
    }

    public RefreshHeader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.refresh_header, this);
         progressBar = (ProgressBar) findViewById(R.id.progress);
        imageView = (ImageView) findViewById(R.id.imageView);
        msg = (TextView) findViewById(R.id.msg);
        measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mMeasuredHeight = getMeasuredHeight();
        setGravity(Gravity.CENTER_HORIZONTAL);
        mContainer = (LinearLayout) findViewById(R.id.container);
        mContainer.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 0));
        this.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }


    @Override
    public void onMove(float delta) {
        if (getVisibleHeight() > 0 || delta > 0) {
            setVisibleHeight((int) delta + getVisibleHeight());
            if (mState <= STATE_RELEASE_TO_REFRESH) { // 未处于刷新状态，更新箭头
                if (getVisibleHeight() > mMeasuredHeight) {
                    setState(STATE_RELEASE_TO_REFRESH);
                } else {
                    setState(STATE_NORMAL);
                }
            }
        }
    }

    private void setState(int state) {
        if (state == mState) return;
        switch (state) {
            case STATE_NORMAL:
                msg.setText(R.string.list_header_hint_normal);
                progressBar.setVisibility(GONE);
                imageView.setVisibility(VISIBLE);
                imageView.setImageResource(R.drawable.ic_arrow_downward_24dp);
                break;
            case STATE_RELEASE_TO_REFRESH:
                if (mState != STATE_RELEASE_TO_REFRESH) {
                    msg.setText(R.string.list_header_hint_release);
                }
                progressBar.setVisibility(GONE);
                imageView.setVisibility(VISIBLE);
                imageView.setImageResource(R.drawable.ic_arrow_upward_24dp);
                break;
            case STATE_REFRESHING:
                msg.setText(R.string.refreshing);
                imageView.setVisibility(GONE);
                progressBar.setVisibility(VISIBLE);
                break;
            case STATE_DONE:
                msg.setText(R.string.refresh_done);
                progressBar.setVisibility(GONE);
                imageView.setVisibility(GONE);
                break;
            default:
        }
        mState = state;
    }

    @Override
    public boolean releaseAction() {
        boolean isOnRefresh = false;
        int height = getVisibleHeight();
        if (height == 0) // not visible.
            isOnRefresh = false;

        if (getVisibleHeight() > mMeasuredHeight && mState < STATE_REFRESHING) {
            setState(STATE_REFRESHING);
            isOnRefresh = true;
        }
        // refreshing and header isn't shown fully. do nothing.
        if (mState == STATE_REFRESHING && height <= mMeasuredHeight) {
            //return;
        }
        int destHeight = 0; // default: scroll back to dismiss header.
        // is refreshing, just scroll back to show all the header.
        if (mState == STATE_REFRESHING) {
            destHeight = mMeasuredHeight;
        }
        smoothScrollTo(destHeight);

        return isOnRefresh;
    }

    @Override
    public void refreshComplete() {
        setState(STATE_DONE);
        smoothScrollTo(0);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                reset();
            }
        }, 500);
    }

    /**
     * 刷新完成后重置状态
     */
    public void reset() {

        setState(STATE_NORMAL);
    }

    private void smoothScrollTo(int destHeight) {
        ValueAnimator animator = ValueAnimator.ofInt(getVisibleHeight(), destHeight);
        animator.setDuration(600).start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setVisibleHeight((int) animation.getAnimatedValue());
            }
        });
        animator.start();
    }

    private void setVisibleHeight(int height) {
        if (height < 0)
            height = 0;
//       `
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        lp.height = height;
        mContainer.setLayoutParams(lp);
    }

    @Override
    public int getVisibleHeight() {
        return mContainer.getHeight();
    }

    public int getState() {
        return mState;
    }
}
