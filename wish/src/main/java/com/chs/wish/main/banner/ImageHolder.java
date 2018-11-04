package com.chs.wish.main.banner;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chs.core.app.Wish;
import com.chs.wish.R;

/**
 * 作者：83734
 * 时间：2018/11/3
 * 描述：
 */
public class ImageHolder extends Holder<String> {
    private AppCompatImageView mImageView;
    private static final RequestOptions BANNER_OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate()
            .centerCrop();

    public ImageHolder(View itemView) {
        super(itemView);
    }


    @Override
    protected void initView(View itemView) {
        mImageView = itemView.findViewById(R.id.ivPost);
    }

    @Override
    public void updateUI(String data) {
        Glide.with(Wish.getApplicationContext())
                .load(data)
                .apply(BANNER_OPTIONS)
                .into(mImageView);
    }
}
