package com.chs.wish.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.chs.core.app.AccountManager;
import com.chs.core.app.IUserChecker;
import com.chs.core.base.BaseActivity;
import com.chs.core.utils.StatusBarUtil;
import com.chs.core.utils.WishPreference;
import com.chs.wish.R;
import com.chs.wish.main.home.HomeActivity;
import com.chs.wish.user.LoginActivity;

import java.util.ArrayList;

/**
 * 作者：chs
 * 时间：2018-10-22 15:49
 * 描述：引导页
 */
public class GuideActivity extends BaseActivity implements OnItemClickListener {

    private ConvenientBanner<Integer> mConvenientBanner;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();
    @Override
    protected Object setContentLayout() {
        mConvenientBanner = new ConvenientBanner<Integer>(this);
        return mConvenientBanner;
    }

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        StatusBarUtil.setTransparent(this);
        initBanner();
    }
    private void initBanner() {
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        INTEGERS.add(R.mipmap.launcher_05);
        mConvenientBanner
                .setPages(new CBViewHolderCreator() {
                    @Override
                    public Holder createHolder(View itemView) {
                        return new LauncherHolder(itemView);
                    }

                    @Override
                    public int getLayoutId() {
                        return R.layout.item_guide;
                    }
                }, INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);
    }

    @Override
    public void onItemClick(int position) {
        //如果点击的是最后一个
        if (position == INTEGERS.size() - 1) {
            WishPreference.setAppFlag(LauncherTag.HAS_FIRST_FINISHED.name(), true);
            //检查用户是否已经登录
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                  showToast("登陆");
                    startActivity(HomeActivity.class);
                }

                @Override
                public void onNotSignIn() {
                 showToast("没登陆");
                    startActivity(LoginActivity.class);
                }
            });
        }
    }
}
