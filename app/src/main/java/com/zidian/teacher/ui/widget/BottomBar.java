package com.zidian.teacher.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;


/**
 * 底部导航栏item
 * Created by YoKeyword on 16/6/3.
 */
public class BottomBar extends LinearLayout {
    private static final int TRANSLATE_DURATION_MILLIS = 200;

    private final Interpolator interpolator = new AccelerateDecelerateInterpolator();
    private boolean visible = true;

    private LinearLayout tabLayout;
    private LayoutParams tabParams;
    private int currentPosition = 0;
    private OnTabSelectedListener listener;

    public BottomBar(Context context) {
        this(context, null);
    }

    public BottomBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setOrientation(VERTICAL);

//        ImageView shadowView = new ImageView(context);
//        shadowView.setBackgroundResource(R.drawable.actionbar_shadow_up);
//        addView(shadowView, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        tabLayout = new LinearLayout(context);
        tabLayout.setBackgroundColor(Color.WHITE);
        tabLayout.setOrientation(LinearLayout.HORIZONTAL);
        addView(tabLayout, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        tabParams = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        tabParams.weight = 1;
    }

    public BottomBar addItem(final BottomBarTab tab) {
        tab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener == null) return;

                int pos = tab.getTabPosition();
                if (currentPosition == pos) {
                    listener.onTabReselected(pos);
                } else {
                    listener.onTabSelected(pos, currentPosition);
                    tab.setSelected(true);
                    listener.onTabUnselected(currentPosition);
                    tabLayout.getChildAt(currentPosition).setSelected(false);
                    currentPosition = pos;
                }
            }
        });
        tab.setTabPosition(tabLayout.getChildCount());
        tab.setLayoutParams(tabParams);
        tabLayout.addView(tab);
        return this;
    }

    public void setOnTabSelectedListener(OnTabSelectedListener onTabSelectedListener) {
        listener = onTabSelectedListener;
    }

    public void setCurrentItem(final int position) {
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.getChildAt(position).performClick();
            }
        });
    }

    public int getCurrentItemPosition() {
        return currentPosition;
    }

    public interface OnTabSelectedListener {
        void onTabSelected(int position, int prePosition);

        void onTabUnselected(int position);

        void onTabReselected(int position);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        return new SavedState(superState, currentPosition);
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        if (currentPosition != ss.position) {
            tabLayout.getChildAt(currentPosition).setSelected(false);
            tabLayout.getChildAt(ss.position).setSelected(true);
        }
        currentPosition = ss.position;
    }

    static class SavedState extends BaseSavedState {
        private int position;

        public SavedState(Parcel source) {
            super(source);
            position = source.readInt();
        }

        public SavedState(Parcelable superState, int position) {
            super(superState);
            this.position = position;
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(position);
        }

        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }


    public void hide() {
        hide(true);
    }

    public void show() {
        show(true);
    }

    public void hide(boolean anim) {
        toggle(false, anim, false);
    }

    public void show(boolean anim) {
        toggle(true, anim, false);
    }

    public boolean isVisible() {
        return visible;
    }

    private void toggle(final boolean visible, final boolean animate, boolean force) {
        if (this.visible != visible || force) {
            this.visible = visible;
            int height = getHeight();
            if (height == 0 && !force) {
                ViewTreeObserver vto = getViewTreeObserver();
                if (vto.isAlive()) {
                    // view树完成测量并且分配空间而绘制过程还没有开始的时候播放动画。
                    vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                        @Override
                        public boolean onPreDraw() {
                            ViewTreeObserver currentVto = getViewTreeObserver();
                            if (currentVto.isAlive()) {
                                currentVto.removeOnPreDrawListener(this);
                            }
                            toggle(visible, animate, true);
                            return true;
                        }
                    });
                    return;
                }
            }
            int translationY = visible ? 0 : height;
            if (animate) {
                animate().setInterpolator(interpolator)
                        .setDuration(TRANSLATE_DURATION_MILLIS)
                        .translationY(translationY);
            } else {
                ViewCompat.setTranslationY(this, translationY);
            }
        }
    }
}
