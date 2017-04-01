/*
 * Copyright (c) 2016 EEFUNG Software Co.Ltd. All rights reserved.
 * 版权所有(c)2016湖南蚁坊软件有限公司。保留所有权利。
 */

package com.zidian.teacher.ui.widget;


import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.zidian.teacher.util.DensityUtils;
import com.zidian.teacher.util.StringConstants;


/**
 * 带有清除按钮的 EditText
 * Created by Zuoji on 2015/11/5.
 */
public class ClearEditText extends AppCompatEditText {

    private Rect rBounds;
    private Drawable rightDrawable;
    private Drawable leftDrawable;
    private boolean isShowRightDrawable = false;

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initEditText();
    }

    /**
     * 初始化
     */
    private void initEditText() {
        int paddingValue = DensityUtils.dip2px(getContext(), 8);
        setPadding(paddingValue, getPaddingTop(), paddingValue, getPaddingBottom());

        setEditTextDrawable();
        addTextChangedListener(new TextWatcher() { // 对文本内容改变进行监听
            public void afterTextChanged(Editable paramEditable) {
            }

            public void beforeTextChanged(CharSequence paramCharSequence,
                                          int paramInt1, int paramInt2, int paramInt3) {
            }

            public void onTextChanged(CharSequence paramCharSequence,
                                      int paramInt1, int paramInt2, int paramInt3) {
                setEditTextDrawable();
            }
        });
        setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (getText().toString().length() == 0) {
                        setCompoundDrawables(leftDrawable, null, null, null);
                    } else {
                        setCompoundDrawables(leftDrawable, null, rightDrawable, null);
                    }
                } else {
                    setCompoundDrawables(leftDrawable, null, null, null);
                }
            }
        });
    }

    /**
     * 控制图片的显示
     */
    private void setEditTextDrawable() {
        if (getText().toString().length() != 0 && isFocused()) {
            setCompoundDrawables(this.leftDrawable, null, this.rightDrawable, null);
        } else {
            setCompoundDrawables(this.leftDrawable, null, null, null);
        }

    }

    protected void finalize() throws Throwable {
        super.finalize();
        this.rightDrawable = null;
        this.leftDrawable = null;
        this.rBounds = null;
    }


    /**
     * 添加触摸事件
     */
    public boolean onTouchEvent(MotionEvent paramMotionEvent) {
        if ((this.rightDrawable != null) && (paramMotionEvent.getAction() == 1) && (getText().length() != 0) && isShowRightDrawable) {
            this.rBounds = this.rightDrawable.getBounds();
            int i = (int) paramMotionEvent.getX();
            if (i > getWidth() - 2 * this.rBounds.width()) {
                setText(StringConstants.STRING_EMPTY);
                paramMotionEvent.setAction(MotionEvent.ACTION_CANCEL);
            }
        }
        return super.onTouchEvent(paramMotionEvent);
    }

    /**
     * 设置显示的图片资源
     */
    public void  setCompoundDrawables(Drawable left, Drawable top, Drawable right, Drawable bottom) {
        if (right != null) {
            this.rightDrawable = right;
            isShowRightDrawable = true;
        } else {
            isShowRightDrawable = false;
        }
        if (left != null) {
            this.leftDrawable = left;
        }
        super.setCompoundDrawables(left, top, right, bottom);
    }
}