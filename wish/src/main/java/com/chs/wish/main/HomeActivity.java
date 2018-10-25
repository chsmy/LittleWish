package com.chs.wish.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;

import com.chs.core.base.BaseActivity;
import com.chs.wish.R;
import com.chs.wish.R2;

import butterknife.BindView;

/**
 * 作者：chs
 * 时间：2018-10-23 11:17
 * 描述：
 */
public class HomeActivity extends BaseActivity {
    @BindView(R2.id.container)
    FrameLayout mContainer;
    @Override
    protected Object setContentLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
//https://github.com/ccWenTian/notes
    }
}
