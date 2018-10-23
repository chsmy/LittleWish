package com.chs.core.app;

import android.app.Activity;
import android.app.Application;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.blankj.utilcode.util.Utils;
import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;
import com.lzy.okgo.OkGo;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * Created by 傅令杰 on 2017/3/29
 */

public final class Configurator {

    private static final HashMap<Object, Object> WISH_CONFIGS = new HashMap<>();
    private static final Handler HANDLER = new Handler();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    private Configurator() {
        WISH_CONFIGS.put(ConfigKeys.CONFIG_READY, false);
        WISH_CONFIGS.put(ConfigKeys.HANDLER, HANDLER);
    }

    static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    final HashMap<Object, Object> getLatteConfigs() {
        return WISH_CONFIGS;
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public final void configure() {
        initHttp();
        initIcons();
        Logger.addLogAdapter(new AndroidLogAdapter());
        WISH_CONFIGS.put(ConfigKeys.CONFIG_READY, true);
        Utils.init(Wish.getApplicationContext());
    }

    public final Configurator withApiHost(String host) {
        WISH_CONFIGS.put(ConfigKeys.API_HOST, host);
        return this;
    }

    public final Configurator withLoaderDelayed(long delayed) {
        WISH_CONFIGS.put(ConfigKeys.LOADER_DELAYED, delayed);
        return this;
    }

    private void initHttp() {
        OkGo.getInstance().init((Application) WISH_CONFIGS.get(ConfigKeys.APPLICATION_CONTEXT));
    }

    private void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        WISH_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        WISH_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    public final Configurator withWeChatAppId(String appId) {
        WISH_CONFIGS.put(ConfigKeys.WE_CHAT_APP_ID, appId);
        return this;
    }

    public final Configurator withWeChatAppSecret(String appSecret) {
        WISH_CONFIGS.put(ConfigKeys.WE_CHAT_APP_SECRET, appSecret);
        return this;
    }

    public final Configurator withActivity(Activity activity) {
        WISH_CONFIGS.put(ConfigKeys.ACTIVITY, activity);
        return this;
    }

    public Configurator withJavascriptInterface(@NonNull String name) {
        WISH_CONFIGS.put(ConfigKeys.JAVASCRIPT_INTERFACE, name);
        return this;
    }

//    public Configurator withWebEvent(@NonNull String name, @NonNull Event event) {
//        final EventManager manager = EventManager.getInstance();
//        manager.addEvent(name, event);
//        return this;
//    }

    private void checkConfiguration() {
        final boolean isReady = (boolean) WISH_CONFIGS.get(ConfigKeys.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = WISH_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) WISH_CONFIGS.get(key);
    }
}
