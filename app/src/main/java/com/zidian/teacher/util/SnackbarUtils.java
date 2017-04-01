package com.zidian.teacher.util;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by GongCheng on 2017/3/20.
 */

public class SnackbarUtils {
    public static void showLong(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }

    public static void showShort(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }
}
