package com.chs.wish.main.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;

import com.chs.core.base.BaseActivity;
import com.chs.wish.R;
import com.chs.wish.R2;

import butterknife.BindView;

/**
 * 作者：chs
 * 时间：2018-11-07 11:03
 * 描述：心愿列表详情
 */
public class WishDetailActivity extends BaseActivity {
    @BindView(R2.id.tv_left)
    AppCompatTextView mTvLeft;
    @BindView(R2.id.tv_title)
    AppCompatTextView mTvTitle;
    @BindView(R2.id.tv_right)
    AppCompatTextView mTvRight;
    @Override
    protected Object setContentLayout() {
        return R.layout.activity_detail_main;
    }

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        mTvLeft.setBackgroundResource(R.mipmap.top_bar_back);
        mTvTitle.setText(R.string.wish_list_detail_title);
        mTvRight.setBackgroundResource(R.mipmap.top_bar_modifier);
    }

}
