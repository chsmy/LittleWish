package com.chs.wish.main.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;

import com.chs.core.base.BaseActivity;
import com.chs.wish.R;
import com.chs.wish.R2;

import butterknife.BindView;

/**
 * 作者：83734
 * 时间：2018/11/11
 * 描述：
 */
public class MyMessageActivity extends BaseActivity {

    @BindView(R2.id.tv_left)
    AppCompatTextView mTvLeft;
    @BindView(R2.id.tv_title)
    AppCompatTextView mTvTitle;
    @BindView(R2.id.tv_right)
    AppCompatTextView mTvRight;

    @Override
    protected Object setContentLayout() {
        return R.layout.activity_my_message;
    }

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        mTvLeft.setBackgroundResource(R.mipmap.top_bar_back);
        mTvTitle.setText(R.string.main_mine_text);
        mTvRight.setText(R.string.mine_finish);
    }
}
