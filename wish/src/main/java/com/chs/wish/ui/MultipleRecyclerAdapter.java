package com.chs.wish.ui;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chs.core.recycler.ItemType;
import com.chs.core.recycler.MultipleFields;
import com.chs.core.recycler.MultipleItemEntity;
import com.chs.wish.R;
import com.chs.wish.main.banner.BannerCreator;
import com.chs.wish.main.home.entity.Banner;
import com.chs.wish.main.home.entity.HomeMultipleEntity;
import com.chs.wish.main.home.entity.WishList;

import java.util.ArrayList;
import java.util.List;

public class MultipleRecyclerAdapter extends BaseMultiItemQuickAdapter<HomeMultipleEntity,MultipleViewHolder>
        implements
        OnItemClickListener {
    //确保初始化一次Banner，防止重复Item加载
    private boolean mIsInitBanner = false;
   // 设置glide图片加载策略
    private static final RequestOptions RECYCLER_OPTIONS =
            new RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate();

    public MultipleRecyclerAdapter(List<HomeMultipleEntity> data) {
        super(data);
        init();
    }
    public static MultipleRecyclerAdapter create(List<HomeMultipleEntity> data) {
        return new MultipleRecyclerAdapter(data);
    }
    private void init() {
        //设置不同的item布局
        addItemType(ItemType.BANNER, R.layout.item_multiple_banner);
        addItemType(ItemType.CONTENT, R.layout.item_home_list);
        openLoadAnimation();
        //多次执行动画
        isFirstOnly(false);
    }
    @Override
    protected void convert(MultipleViewHolder holder, HomeMultipleEntity entity) {
        switch (holder.getItemViewType()) {
            case ItemType.BANNER:
                if (!mIsInitBanner) {
                    List<Banner.BannerData> bannerData = entity.getBannerData();
                    ArrayList<String> banners = new ArrayList<>();
                    for (Banner.BannerData data : bannerData) {
                        banners.add(data.getPic());
                    }
                    final ConvenientBanner<String> convenientBanner = holder.getView(R.id.banner_recycler_item);
                    BannerCreator.setDefault(convenientBanner, banners, this);
                    mIsInitBanner = true;
                }
                break;
           case ItemType.CONTENT:
                WishList.DataBean wishData = entity.getWishData();
                holder.setText(R.id.tv_author,wishData.getTitle());
            default:
                break;
        }
    }

    @Override
    public void onItemClick(int position) {

    }

}
