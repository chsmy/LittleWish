package com.chs.wish.launcher;

import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;
import com.chs.wish.R;

/**
 * 作者：chs
 * 时间：2018-10-22 16:41
 * 描述：
 */
public class LauncherHolder extends Holder<Integer> {

    private AppCompatImageView mImageView;

    public LauncherHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void initView(View itemView) {
        mImageView = itemView.findViewById(R.id.ivPost);
    }

    @Override
    public void updateUI(Integer data) {
        mImageView.setBackgroundResource(data);
    }
}
