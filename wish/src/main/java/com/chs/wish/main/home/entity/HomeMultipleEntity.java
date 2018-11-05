package com.chs.wish.main.home.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * 作者：83734
 * 时间：2018/11/5
 * 描述：
 */
public class HomeMultipleEntity implements MultiItemEntity {
    private List<Banner.BannerData> mBannerData;
    private WishList.DataBean mWishData;
    private int mType;

    public HomeMultipleEntity(List<Banner.BannerData> bannerData, WishList.DataBean wishData, int mType) {
        this.mBannerData = bannerData;
        this.mWishData = wishData;
        this.mType = mType;
    }

    public List<Banner.BannerData> getBannerData() {
        return mBannerData;
    }

    public WishList.DataBean getWishData() {
        return mWishData;
    }

    @Override
    public int getItemType() {
        return mType;
    }
}
