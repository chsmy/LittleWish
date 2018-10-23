package com.chs.core.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.chs.core.app.Wish;

/**
 * Created by 傅令杰 on 2017/4/2
 */

public final class DimenUtil {

    public static int getScreenWidth() {
        final Resources resources = Wish.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = Wish.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
