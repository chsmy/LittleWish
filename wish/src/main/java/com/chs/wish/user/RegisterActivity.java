package com.chs.wish.user;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;

import com.chs.core.base.BaseActivity;
import com.chs.wish.R;
import com.chs.wish.R2;

import butterknife.BindView;

/**
 * 作者：chs
 * 时间：2018-10-23 11:24
 * 描述：
 */
public class RegisterActivity extends BaseActivity {
    @BindView(R2.id.tv_goto_login)
    AppCompatTextView tvGotoLogin;
    @Override
    protected Object setContentLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        tvGotoLogin.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG );
    }
}
