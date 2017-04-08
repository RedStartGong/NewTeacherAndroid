/**
 * @author yxw
 * date : 2014年4月17日 下午7:27:35
 */
package com.zidian.teacher.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.zidian.teacher.R;
import com.zidian.teacher.util.DensityUtils;

import java.util.List;


/**
 * 课程界面的布局控件
 */
public class ScheduleView extends View implements OnTouchListener {

    private Paint mPaint; // 画笔,包含了画几何图形、文本等的样式和颜色信息
    private int startX = 0;//画布的原点X（所有的画图操作，都是基于这个原点的，touch中只要修改这个值）
    private int startY = 0;//画布的原点Y（所有的画图操作，都是基于这个原点的，touch中只要修改这个值）
    private int sideWidth;//左边，上面bar的宽度
    private int eachBoxH ;//每个格子的高度，设置可以后面根据屏幕对它做了均分
    private int eachBoxW ;//每个格子的宽度，后面根据屏幕对它做了均分
    private int focusX = -1;//当前手指焦点的位置坐标
    private int focusY = -1;//当前手指焦点的位置坐标
    private static int classTotal = 12;//左边栏总格子数
    private static int dayTotal = 7;//顶部栏总共格子数
    private String[] weekdays;//星期
    private boolean isMove = false; // 判断是否移动
    private Context context;

    // 监听器
    private OnItemClassClickListener onItemClassClickListener;

    // 数据
    private List<CourseInfo> classList;

    // 颜色
    public static final int contentBg = Color.argb(255, 255, 255, 255);
    public static final int barBg = Color.argb(255, 225, 225, 225);
    public static final int bayText = Color.argb(255, 150, 150, 150);
    public static final int barBgHrLine = Color.argb(255, 150, 150, 150);
    public static final int classBorder = Color.argb(180, 150, 150, 150);
    public static final int markerBorder = Color.argb(100, 150, 150, 150);

    //预设格子背景颜色数组
    public static final int[] classBgColors = {Color.argb(200, 71, 154, 199),
            Color.argb(200, 230, 91, 62), Color.argb(200, 50, 178, 93),
            Color.argb(200, 255, 225, 0), Color.argb(200, 102, 204, 204),
            Color.argb(200, 51, 102, 153), Color.argb(200, 102, 153, 204)

    };

    public ScheduleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        weekdays = context.getResources().getStringArray(R.array.weekdays);
        mPaint = new Paint();
        sideWidth = DensityUtils.dip2px(context,35);
        eachBoxH = DensityUtils.dip2px(context,50);
        eachBoxW = DensityUtils.dip2px(context,60);
        setOnTouchListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        eachBoxW = (getWidth() - sideWidth) / 7;
        eachBoxH = (getHeight()- sideWidth)/12;
        printMarker(canvas);
        printContent(canvas);
        printTopBar(canvas);
        printLeftBar(canvas);
    }

    /**
     * 区分课间隔，画交线处的十字
     *
     * @param canvas
     */
    private void printMarker(Canvas canvas) {
        mPaint.setAntiAlias(true);
        mPaint.setColor(markerBorder);
        for (int i = 0; i < dayTotal - 1; i++) {
            for (int j = 0; j < classTotal - 1; j++) {
                // 画交线处的十字
                mPaint.setStyle(Style.STROKE);
                canvas.drawRect(startX + sideWidth + eachBoxW * (i + 1)
                        - eachBoxW / 20, startY + sideWidth + eachBoxH
                        * (j + 1) - 1, startX + sideWidth + eachBoxW * (i + 1)
                        + eachBoxW / 20, startY + sideWidth + eachBoxH
                        * (j + 1), mPaint);
                canvas.drawRect(
                        startX + sideWidth + eachBoxW * (i + 1) - 1,
                        startY + sideWidth + eachBoxH * (j + 1) - eachBoxW / 20,
                        startX + sideWidth + eachBoxW * (i + 1), startY
                                + sideWidth + eachBoxH * (j + 1) + eachBoxW
                                / 20, mPaint);
            }
        }
    }

    /**
     * 画中间主体部分
     *
     * @param canvas
     */
    private void printContent(Canvas canvas) {
        if (classList != null && classList.size() > 0) {
            CourseInfo classInfo;
            for (int i = 0; i < classList.size(); i++) {
                classInfo = classList.get(i);
                int fromX = startX + sideWidth + eachBoxW
                        * (classInfo.getWeekday() - 1);
                int fromY = startY + sideWidth + eachBoxH
                        * (classInfo.getFromClassNum() - 1);
                int toX = startX + sideWidth + eachBoxW
                        * classInfo.getWeekday();
                int toY = startY
                        + sideWidth
                        + eachBoxH
                        * (classInfo.getFromClassNum()
                        + classInfo.getClassNumLen() - 1);
                classInfo.setPoint(fromX, fromY, toX, toY);
                // 画classbg
                mPaint.setStyle(Style.FILL);
                mPaint.setColor(classBgColors[i % classBgColors.length]);
                canvas.drawRect(fromX, fromY, toX - 2, toY - 2, mPaint);
                // 画文字
                mPaint.setColor(Color.WHITE);
                mPaint.setTextSize(DensityUtils.dip2px(context,10));
                String className = classInfo.getClassname() + "@"
                        + classInfo.getClassRoom() + "#" + classInfo.getBeginEndWeek();
                Rect textRect1 = new Rect();
                mPaint.getTextBounds(className, 0, className.length(),
                        textRect1);
                mPaint.setTextSize(DensityUtils.dip2px(context,10));
                int th = textRect1.bottom - textRect1.top;
                int tw = textRect1.right - textRect1.left;
                //计算行数
                int row = (int) ((tw + DensityUtils.dip2px(context,45)) / eachBoxW + 1);
                int length = className.length() / row;
                //逐行写字
                for (int j = 0; j < row - 1; j++) {
                    canvas.drawText(className, length * j, length * (j + 1),
                            fromX + 5, fromY + 10 + th * (j + 1), mPaint);
                }
                //最后一行文字特殊处理
                canvas.drawText(className, length * (row - 1),
                        className.length(), fromX + 5, fromY + 10 + th * row,
                        mPaint);
                // 画边框
                mPaint.setColor(classBorder);
                mPaint.setStyle(Style.STROKE);
                canvas.drawRect(fromX, fromY, toX - 2, toY - 2, mPaint);
            }
        }
    }

    /**
     * 画左边课时bar
     */
    private void printLeftBar(Canvas canvas) {
        // =================画左边课时栏=================
        mPaint.setColor(barBg);
        mPaint.setStyle(Style.FILL);
        mPaint.setTextSize(DensityUtils.dip2px(context,13));
        // 课时栏背景
        canvas.drawRect(0, startY + sideWidth, sideWidth, sideWidth + startY
                + eachBoxH * classTotal, mPaint);
        mPaint.setColor(barBgHrLine);
        // 画第一个边框线
        canvas.drawRect(0, startY + sideWidth + eachBoxH - 1, sideWidth, startY
                + eachBoxH + sideWidth, mPaint);
        // 居中处理
        Rect textRect1 = new Rect();
        mPaint.getTextBounds("1", 0, 1, textRect1);
        int th = textRect1.bottom - textRect1.top;
        int tw1 = textRect1.right - textRect1.left;
        mPaint.getTextBounds("10", 0, 2, textRect1);
        int tw2 = textRect1.right - textRect1.left;
        // 画第一个文字
        canvas.drawText("1", sideWidth / 2 - tw1, startY + sideWidth + eachBoxH
                / 2 + th / 2, mPaint);
        for (int i = 2; i < classTotal + 1; i++) {
            // 画边框
            canvas.drawRect(0, startY + sideWidth + eachBoxH * i - 1,
                    sideWidth, startY + eachBoxH * i + sideWidth, mPaint);
            // 画文字
            int tw = tw1 * 2 + (tw2 - tw1) * (i / 10);
            canvas.drawText(i + "", sideWidth / 2 - tw / 2, startY + sideWidth
                    + eachBoxH * (i - 1) + eachBoxH / 2 + th / 2, mPaint);
        }
        // =========左上角正方形============
        canvas.drawRect(0, 0, sideWidth, sideWidth, mPaint);
    }

    /**
     * 画顶部星期bar
     */
    private void printTopBar(Canvas canvas) {
        // =================画顶部星期栏==================
        mPaint.setColor(barBg);
        mPaint.setStyle(Style.FILL);
        // 星期栏背景
        canvas.drawRect(startX + sideWidth, 0, sideWidth + startX + eachBoxW
                * dayTotal, sideWidth, mPaint);
        mPaint.setColor(barBgHrLine);
        // 画第一个边框线
        mPaint.setTextSize(DensityUtils.dip2px(context,13));
        canvas.drawRect(startX + sideWidth + eachBoxW - 1, 0, startX + eachBoxW
                + sideWidth, sideWidth, mPaint);
        // 居中处理
        Rect textBounds = new Rect();
        mPaint.getTextBounds(weekdays[0], 0, weekdays[0].length(), textBounds);
        int textHeight = textBounds.bottom - textBounds.top;
        int textWidth = textBounds.right - textBounds.left;
        // 画第一个文字
        canvas.drawText(weekdays[0], startX + sideWidth + eachBoxW / 2
                - textWidth / 2, sideWidth / 2 + textHeight / 2, mPaint);
        for (int i = 2; i < dayTotal + 1; i++) {
            // 画边框线
            canvas.drawRect(startX + sideWidth + eachBoxW * i - 1, 0, startX
                    + eachBoxW * i + sideWidth, sideWidth, mPaint);
            // 画文字
            canvas.drawText(weekdays[i - 1], startX + sideWidth + eachBoxW
                    * (i - 1) + eachBoxW / 2 - textWidth / 2, sideWidth / 2
                    + textHeight / 2, mPaint);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            focusX = (int) event.getX();
            focusY = (int) event.getY();
            isMove = false;
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            int dx = (int) (event.getX() - focusX);
            int dy = (int) (event.getY() - focusY);
            if (!isMove && Math.abs(dx) < 5 && Math.abs(dy) < 5) {
                isMove = false;
                return false;
            }
            isMove = true;
            //判断是否超出左右边框
            if (startX + dx < 0
                    && startX + dx + eachBoxW * dayTotal + sideWidth >= getWidth()) {
                startX += dx;
            }
            //判断是否超出上下边框
            if (startY + dy < 0
                    && startY + dy + eachBoxH * classTotal + sideWidth >= getHeight()) {
                startY += dy;
            }
            //重新获得焦点坐标
            focusX = (int) event.getX();
            focusY = (int) event.getY();
            //重绘
            invalidate();
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (!isMove) {
                int focusX = (int) event.getX();
                int focusY = (int) event.getY();
                // 是点击效果，遍历是哪个课程的点击效果
                if (classList == null) {
                    return true;
                }
                for (int i = 0; i < classList.size(); i++) {
                    CourseInfo classInfo = classList.get(i);
                    if (focusX > classInfo.getFromX()
                            && focusX < classInfo.getToX()
                            && focusY > classInfo.getFromY()
                            && focusY < classInfo.getToY()) {
                        if (onItemClassClickListener != null) {
                            onItemClassClickListener.onClick(classInfo);
                        }
                        break;
                    }
                }
            }
        }
        return true;
    }

    public interface OnItemClassClickListener {
        public void onClick(CourseInfo classInfo);
    }

    public OnItemClassClickListener getOnItemClassClickListener() {
        return onItemClassClickListener;
    }

    public void setOnItemClassClickListener(
            OnItemClassClickListener onItemClassClickListener) {
        this.onItemClassClickListener = onItemClassClickListener;
    }

    public List<CourseInfo> getClassList() {
        return classList;
    }

    public void setClassList(List<CourseInfo> classList) {
        this.classList = classList;
        invalidate();// 刷新页面
    }

}
