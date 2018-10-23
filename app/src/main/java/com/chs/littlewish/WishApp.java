package com.chs.littlewish;

import android.app.Application;

import com.chs.core.app.Wish;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * 作者：chs
 * 时间：2018-10-18 16:34
 * 描述：
 */
public class WishApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Wish.init(this)
                .withIcon(new FontAwesomeModule())
                .configure();
    }
}
