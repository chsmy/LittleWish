package com.chs.wish.main.home.ui;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chs.core.widget.CircleImageView;
import com.chs.core.widget.PileLayout;
import com.chs.wish.R;
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
        String[] urls = {
                "http://img0.imgtn.bdimg.com/it/u=2263418180,3668836868&fm=206&gp=0.jpg",
                "http://img0.imgtn.bdimg.com/it/u=2263418180,3668836868&fm=206&gp=0.jpg",
                "http://img2.imgtn.bdimg.com/it/u=1939271907,257307689&fm=21&gp=0.jpg",
                "http://img0.imgtn.bdimg.com/it/u=2263418180,3668836868&fm=206&gp=0.jpg",
                "http://img0.imgtn.bdimg.com/it/u=2263418180,3668836868&fm=206&gp=0.jpg"};

        PileLayout pileLayout = helper.getView(R.id.pl_head);
       initPraises(pileLayout,urls);
        Toast.makeText(mContext,item.getContent(),Toast.LENGTH_LONG).show();
    }
    public void initPraises(PileLayout pileLayout,String[] urls) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        for (String url : urls) {
            CircleImageView imageView = (CircleImageView) inflater.inflate(R.layout.item_help_person, pileLayout, false);
            Glide.with(mContext).load(url).into(imageView);
            pileLayout.addView(imageView);
        }
    }
}
