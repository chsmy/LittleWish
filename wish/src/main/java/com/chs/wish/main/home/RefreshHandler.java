package com.chs.wish.main.home;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chs.core.http.DialogCallback;
import com.chs.core.recycler.ItemType;
import com.chs.core.recycler.MultipleFields;
import com.chs.core.recycler.MultipleItemEntity;
import com.chs.wish.Api;
import com.chs.wish.main.home.entity.Banner;
import com.chs.wish.main.home.entity.HomeMultipleEntity;
import com.chs.wish.main.home.entity.WishList;
import com.chs.wish.ui.MultipleRecyclerAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：83734
 * 时间：2018/10/31
 * 描述：下拉刷新 上啦加载控制类
 */
public class RefreshHandler implements
        SwipeRefreshLayout.OnRefreshListener
        , BaseQuickAdapter.RequestLoadMoreListener {

    private final SwipeRefreshLayout REFRESH_LAYOUT;
    private final PagingBean BEAN;
    private final RecyclerView RECYCLERVIEW;
    private MultipleRecyclerAdapter mAdapter = null;
    private List<Banner.BannerData> bannerData;
    private List<WishList.DataBean> wishList;
    private boolean isBannerLoadOk = false;
    private boolean isListLoadOk = false;

    public RefreshHandler(SwipeRefreshLayout REFRESH_LAYOUT, RecyclerView RECYCLERVIEW,PagingBean BEAN) {
        this.REFRESH_LAYOUT = REFRESH_LAYOUT;
        this.BEAN = BEAN;
        this.RECYCLERVIEW = RECYCLERVIEW;
    }

    public static  RefreshHandler create(SwipeRefreshLayout swipeRefreshLayout,
                                         RecyclerView recyclerView){
      return new RefreshHandler(swipeRefreshLayout,recyclerView,new PagingBean());
    }

    public void firstPage(Context context) {
                        OkGo.<Banner>get(Api.BANNER)
                                .tag(this)
                                .params("adver_id","1")
                                .execute(new DialogCallback<Banner>(Banner.class,context) {
                                    @Override
                                    public void onSuccess(Response<Banner> response) {
                                        isBannerLoadOk = true;
                                        bannerData = response.body().getData();
                                        if(isListLoadOk) setAdapter();
                                    }
                                });
                        OkGo.<WishList>get(Api.WISH_LISTS)
                                .tag(this)
                                .execute(new DialogCallback<WishList>(WishList.class,context) {
                                    @Override
                                    public void onSuccess(Response<WishList> response) {
                                        wishList = response.body().getData();
                                        isListLoadOk = true;
                                        if(isBannerLoadOk) setAdapter();
                                    }
                                });

    }

    private void setAdapter(){
        ArrayList<HomeMultipleEntity> multipleItemEntities = new ArrayList<>();
        multipleItemEntities.add(new HomeMultipleEntity(bannerData,null,ItemType.BANNER));
        for (WishList.DataBean dataBean: wishList) {
            multipleItemEntities.add(new HomeMultipleEntity(null,dataBean,ItemType.CONTENT));
        }
        mAdapter = MultipleRecyclerAdapter.create(multipleItemEntities);
        mAdapter.setOnLoadMoreListener(RefreshHandler.this, RECYCLERVIEW);
        RECYCLERVIEW.setAdapter(mAdapter);
        BEAN.addIndex();
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMoreRequested() {

    }
}
