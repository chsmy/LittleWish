package com.chs.wish.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;

import com.chs.core.app.AccountManager;
import com.chs.core.app.IUserChecker;
import com.chs.core.base.BaseActivity;
import com.chs.core.timer.BaseTimerTask;
import com.chs.core.timer.ITimerListener;
import com.chs.core.utils.StatusBarUtil;
import com.chs.core.utils.WishPreference;
import com.chs.wish.R;
import com.chs.wish.R2;
import com.chs.wish.main.HomeActivity;
import com.chs.wish.user.LoginActivity;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：chs
 * 时间：2018-10-22 14:29
 * 描述：欢迎页
 */
public class WelcomeActivity extends BaseActivity implements ITimerListener {

    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer;

    private Timer mTimer = null;
    private int mCount = 5;

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }
    @Override
    protected Object setContentLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        StatusBarUtil.setTransparent(this);
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);
    }
    //判断是否显示滑动启动页
    private void checkIsShowScroll() {
        if (!WishPreference.getAppFlag(LauncherTag.HAS_FIRST_FINISHED.name())) {
            //去引导页
            startActivity(GuideActivity.class);
        } else {
            //检查用户是否登录了APP
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    //去主页
                   startActivity(HomeActivity.class);
                }

                @Override
                public void onNotSignIn() {
                  //去登录页
                    startActivity(LoginActivity.class);
//                    startActivity(HomeActivity.class);
                }
            });
        }
    }
    @Override
    public void onTimer() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null) {
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }
}
