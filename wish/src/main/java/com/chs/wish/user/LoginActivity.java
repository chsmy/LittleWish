package com.chs.wish.user;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;

import com.chs.core.base.BaseActivity;
import com.chs.core.utils.StatusBarCompat;
import com.chs.wish.R;
import com.chs.wish.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：chs
 * 时间：2018-10-23 11:24
 * 描述：
 */
public class LoginActivity extends BaseActivity {
    @BindView(R2.id.tv_goto_register)
    AppCompatTextView tvGotoRegister;
    @Override
    protected Object setContentLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        StatusBarCompat.translucentStatusBar(this);
        tvGotoRegister.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG );
    }
    @OnClick(R2.id.ll_goto_register)
    void onClickLink() {
        startActivity(RegisterActivity.class);
    }
}
