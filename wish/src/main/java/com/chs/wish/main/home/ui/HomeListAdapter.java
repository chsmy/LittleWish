package com.chs.wish.main.home.ui;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chs.wish.main.home.entity.WishList;

import java.util.List;

/**
 * 作者：chs
 * 时间：2018-11-06 18:36
 * 描述：
 */
public class HomeListAdapter extends BaseQuickAdapter<WishList.DataBean,BaseViewHolder> {

    public HomeListAdapter(int layoutResId, @Nullable List<WishList.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WishList.DataBean item) {

    }
}
