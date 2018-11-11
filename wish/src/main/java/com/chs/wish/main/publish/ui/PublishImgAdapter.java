package com.chs.wish.main.publish.ui;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chs.wish.R;

import java.util.List;

/**
 * 作者：83734
 * 时间：2018/11/10
 * 描述：
 */
public class PublishImgAdapter extends BaseQuickAdapter<Uri,BaseViewHolder> {

    //设置图片加载策略
    private static final RequestOptions RECYCLER_OPTIONS =
            new RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate();

    public PublishImgAdapter(int layoutResId, @Nullable List<Uri> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Uri item) {
        AppCompatImageView imageView = helper.getView(R.id.iv_img);
        Glide.with(mContext)
                .load(item)
                .apply(RECYCLER_OPTIONS)
                .into(imageView);
    }
}