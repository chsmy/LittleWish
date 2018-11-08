package com.chs.wish.main.publish;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;

import com.chs.core.base.BaseActivity;
import com.chs.wish.R;
import com.chs.wish.R2;

import butterknife.BindView;

/**
 * 作者：chs
 * 时间：2018-11-07 11:05
 * 描述：发布心愿
 */
public class PublishWishActivity extends BaseActivity {
    @BindView(R2.id.tv_left)
    AppCompatTextView mTvLeft;
    @BindView(R2.id.tv_title)
    AppCompatTextView mTvTitle;
    @BindView(R2.id.tv_right)
    AppCompatTextView mTvRight;

    @Override
    protected Object setContentLayout() {
        return R.layout.activity_publish_main;
    }

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        mTvLeft.setText(R.string.publish_top_cancel);
        mTvTitle.setText(R.string.publish_publish_wish);
        mTvRight.setText(R.string.publish_next);
    }
}
