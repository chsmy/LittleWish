package com.chs.wish.main.publish.ui;

import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chs.wish.R;
import com.chs.wish.main.publish.entity.ImageItem;

import java.util.List;

/**
 * 作者：83734
 * 时间：2018/11/10
 * 描述：
 */
public class PublishImgAdapter extends BaseQuickAdapter<ImageItem,BaseViewHolder> {

    //设置图片加载策略
    private static final RequestOptions RECYCLER_OPTIONS =
            new RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate();

    public PublishImgAdapter(int layoutResId, @Nullable List<ImageItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ImageItem item) {
        AppCompatImageView imageView = helper.getView(R.id.iv_img);
        AppCompatImageView deleteView = helper.getView(R.id.delete_pic);
        Glide.with(mContext)
                .load(item.getPicUri())
                .apply(RECYCLER_OPTIONS)
                .into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"imageView",Toast.LENGTH_SHORT).show();
            }
        });
        deleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"delete",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
