

package com.zidian.teacher.util;


import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;


/**
 * 提供 dip 和 px 相互转换及其他一些实用方法
 * Created by Zuo Ji on 2015/8/25.
 */
public final class DensityUtils {

    /**
     * 获取屏幕宽度
     * @param context 上下文
     * @return 屏幕宽度
     */
    public static int getDisplayWidth(Context context) {
        // 获取屏幕分辨率,并且作为请求头传给业务服务
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }


    /**
     * 获取屏幕高度
     * @param context 上下文
     * @return 屏幕高度
     */
    public static int getDisplayHeight(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     * @param context 上下文
     * @param dpValue dip 为单位的数值
     * @return 转换后的 px 值
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }



    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     * @param context 上下文
     * @param pxValue px 数值
     * @return 转换后的 dip 为单位的数值
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    /**
     * 获取当前分辨率下指定单位对应的像素大小（根据设备信息）
     * px,dip,sp -> px
     * @param context 上下文
     * @param size unit 对应单位的数值
     * @param unit TypedValue.COMPLEX_UNIT_* 需要转换的单位
     * @return 转换后 px 的大小
     */
    public float getRawSize(Context context, float size, int unit) {
        return TypedValue.applyDimension(unit, size, context.getResources().getDisplayMetrics());
    }
}