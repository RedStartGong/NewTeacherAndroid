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
import android.os.Build;
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

    private Paint paint; // 画笔,包含了画几何图形、文本等的样式和颜色信息
    private int startX = 0;//画布的原点X（所有的画图操作，都是基于这个原点的，touch中只要修改这个值）
    private int startY = 0;//画布的原点Y（所有的画图操作，都是基于这个原点的，touch中只要修改这个值）
    private int sideYWidth;//上面b格子的宽度
    private int sideXWidth;//左边各自宽度
    private int eachBoxH;//每个格子的高度，设置可以后面根据屏幕对它做了均分
    private int eachBoxW;//每个格子的宽度，后面根据屏幕对它做了均分
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
    private List<String> dateList;

    // 颜色
    public static final int contentBg = Color.argb(255, 255, 255, 255);
    public static final int barBg = Color.argb(255, 233, 241, 237);
    public static final int bayText = Color.argb(255, 150, 150, 150);
    public static final int barBgHrLine = Color.argb(255, 150, 150, 150);
    public static final int classBorder = Color.argb(180, 150, 150, 150);
    public static final int markerBorder = Color.argb(100, 150, 150, 150);

    //预设格子背景颜色数组
    public static final int[] classBgColors = {
            Color.argb(200, 71, 154, 199),
            Color.argb(200, 230, 91, 62),
            Color.argb(200, 50, 178, 93),
            Color.argb(200, 255, 225, 0),
            Color.argb(200, 102, 204, 204),
            Color.argb(200, 51, 102, 153),
            Color.argb(200, 102, 153, 204)};

    public ScheduleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        weekdays = context.getResources().getStringArray(R.array.weekdays);
        paint = new Paint();
        paint.setAntiAlias(true);
        sideYWidth = DensityUtils.dip2px(context, 40);
        sideXWidth = DensityUtils.dip2px(context, 35);
        eachBoxH = DensityUtils.dip2px(context, 50);
        eachBoxW = DensityUtils.dip2px(context, 60);
        setOnTouchListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        eachBoxW = (getWidth() - sideXWidth) / 7;
        eachBoxH = (getHeight() - sideYWidth) / 12;
//        printMarker(canvas);
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
        paint.setAntiAlias(true);
        paint.setColor(markerBorder);
        for (int i = 0; i < dayTotal - 1; i++) {
            for (int j = 0; j < classTotal - 1; j++) {
                // 画交线处的十字
                paint.setStyle(Style.STROKE);
                canvas.drawRect(startX + sideXWidth + eachBoxW * (i + 1)
                        - eachBoxW / 20, startY + sideYWidth + eachBoxH
                        * (j + 1) - 1, startX + sideXWidth + eachBoxW * (i + 1)
                        + eachBoxW / 20, startY + sideYWidth + eachBoxH
                        * (j + 1), paint);
                canvas.drawRect(
                        startX + sideXWidth + eachBoxW * (i + 1) - 1,
                        startY + sideYWidth + eachBoxH * (j + 1) - eachBoxW / 20,
                        startX + sideXWidth + eachBoxW * (i + 1), startY
                                + sideYWidth + eachBoxH * (j + 1) + eachBoxW
                                / 20, paint);
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
                int fromX = startX + sideXWidth + eachBoxW
                        * (classInfo.getWeekday() - 1);
                int fromY = startY + sideYWidth + eachBoxH
                        * (classInfo.getFromClassNum() - 1);
                int toX = startX + sideXWidth + eachBoxW
                        * classInfo.getWeekday();
                int toY = startY
                        + sideYWidth
                        + eachBoxH
                        * (classInfo.getFromClassNum()
                        + classInfo.getClassNumLen() - 1);
                classInfo.setPoint(fromX, fromY, toX, toY);
                // 画课程背景
                paint.setStyle(Style.FILL);
                paint.setColor(classBgColors[i % classBgColors.length]);
                int rx = DensityUtils.dip2px(context, 5);
                int ry = DensityUtils.dip2px(context, 5);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    canvas.drawRoundRect(fromX, fromY, toX - 2, toY - 2, rx, ry, paint);
                } else {
                    canvas.drawRect(fromX, fromY, toX - 2, toY - 2, paint);
                }
                // 画文字
                paint.setColor(Color.WHITE);
                paint.setTextSize(DensityUtils.dip2px(context, 13));
                String className = classInfo.getClassname() + "@"
                        + classInfo.getClassRoom();
                Rect textRect1 = new Rect();
                paint.getTextBounds(className, 0, className.length(),
                        textRect1);
                paint.setTextSize(DensityUtils.dip2px(context, 10));
                int th = textRect1.bottom - textRect1.top;
                int tw = textRect1.right - textRect1.left;
                //计算行数
                int row = (int) ((tw + DensityUtils.dip2px(context, 45)) / eachBoxW + 1);
                int length = className.length() / row;
                //逐行写字
                for (int j = 0; j < row - 1; j++) {
                    canvas.drawText(className, length * j, length * (j + 1),
                            fromX + 5, fromY + 10 + th * (j + 1), paint);
                }
                //最后一行文字特殊处理
                canvas.drawText(className, length * (row - 1),
                        className.length(), fromX + 5, fromY + 10 + th * row,
                        paint);
                // 画边框
//                paint.setColor(classBorder);
//                paint.setStyle(Style.STROKE);
//                canvas.drawRect(fromX, fromY, toX - 2, toY - 2, paint);
            }
        }
    }

    /**
     * 画左边课时bar
     */
    private void printLeftBar(Canvas canvas) {
        // =================画左边课时栏=================
        paint.setColor(barBg);
        paint.setStyle(Style.FILL);
        paint.setTextSize(DensityUtils.dip2px(context, 13));
        // 课时栏背景
        canvas.drawRect(0, startY + sideYWidth, sideXWidth, sideYWidth + startY
                + eachBoxH * classTotal, paint);
        paint.setColor(barBgHrLine);
        // 画第一个边框线
        canvas.drawRect(0, startY + sideYWidth + eachBoxH - 1, sideXWidth, startY
                + eachBoxH + sideYWidth, paint);
        // 居中处理
        Rect textRect1 = new Rect();
        paint.getTextBounds("1", 0, 1, textRect1);
        int th = textRect1.bottom - textRect1.top;
        int tw1 = textRect1.right - textRect1.left;
        paint.getTextBounds("10", 0, 2, textRect1);
        int tw2 = textRect1.right - textRect1.left;
        // 画第一个文字
        canvas.drawText("1", sideXWidth / 2 - tw1, startY + sideYWidth + eachBoxH
                / 2 + th / 2, paint);
        for (int i = 2; i < classTotal + 1; i++) {
            // 画边框
            canvas.drawRect(0, startY + sideYWidth + eachBoxH * i - 1,
                    sideXWidth, startY + eachBoxH * i + sideYWidth, paint);
            // 画文字
            int tw = tw1 * 2 + (tw2 - tw1) * (i / 10);
            canvas.drawText(i + "", sideXWidth / 2 - tw / 2, startY + sideYWidth
                    + eachBoxH * (i - 1) + eachBoxH / 2 + th / 2, paint);
        }
        // =========左上角矩形============
        canvas.drawRect(0, 0, sideXWidth, sideYWidth, paint);
    }


    /**
     * 画顶部星期bar
     */
    private void printTopBar(Canvas canvas) {

        // =================画顶部星期栏==================
        paint.setColor(barBg);
        paint.setStyle(Style.FILL);
        // 星期栏背景
        canvas.drawRect(startX + sideXWidth, 0, sideXWidth + startX + eachBoxW
                * dayTotal, sideYWidth, paint);
        paint.setColor(barBgHrLine);
        // 画第一个边框线
        paint.setTextSize(DensityUtils.dip2px(context, 13));
        canvas.drawRect(startX + sideXWidth + eachBoxW - 1, 0, startX + eachBoxW
                + sideXWidth, sideYWidth, paint);
        // 居中处理
        Rect textBounds = new Rect();
        paint.getTextBounds(weekdays[0], 0, weekdays[0].length(), textBounds);
        int textHeight = textBounds.bottom - textBounds.top;
        int textWidth = textBounds.right - textBounds.left;
        // 画第一个文字
        canvas.drawText(weekdays[0], startX + sideXWidth + eachBoxW / 2
                - textWidth / 2, sideYWidth / 2, paint);
        for (int i = 2; i < dayTotal + 1; i++) {
            // 画边框线
            canvas.drawRect(startX + sideXWidth + eachBoxW * i - 1, 0, startX
                    + eachBoxW * i + sideXWidth, sideYWidth, paint);
            // 画文字
            canvas.drawText(weekdays[i - 1], startX + sideXWidth + eachBoxW
                            * (i - 1) + eachBoxW / 2 - textWidth / 2, sideYWidth / 2
                    , paint);
        }
        if (dateList != null && dateList.size() > 0) {
            paint.setTextSize(DensityUtils.dip2px(context, 11));
            Rect textBounds2 = new Rect();
            paint.getTextBounds(dateList.get(0), 0, dateList.get(0).length(), textBounds2);
            int dateY = textBounds2.bottom - textBounds2.top;
            int dateX = textBounds2.right - textBounds2.left;
            canvas.drawText(dateList.get(0), startX + sideXWidth + eachBoxW / 2 - dateX / 2,
                    sideYWidth / 2 + textHeight, paint);
            for (int i = 1; i < dateList.size(); i++) {
                canvas.drawText(dateList.get(i), startX + sideXWidth + eachBoxW * i + eachBoxW / 2 - dateX / 2,
                        sideYWidth / 2 + textHeight, paint);
            }
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
                    && startX + dx + eachBoxW * dayTotal + sideXWidth >= getWidth()) {
                startX += dx;
            }
            //判断是否超出上下边框
            if (startY + dy < 0
                    && startY + dy + eachBoxH * classTotal + sideYWidth >= getHeight()) {
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

    public void setData(List<CourseInfo> classList, List<String> dateList) {
        this.classList = classList;
        this.dateList = dateList;
        invalidate();// 刷新页面
    }

}
