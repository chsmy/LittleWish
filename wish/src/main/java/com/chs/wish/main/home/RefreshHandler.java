package com.chs.wish.main.home;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chs.core.app.Wish;
import com.chs.core.http.JsonCallback;
import com.chs.core.recycler.ItemType;
import com.chs.wish.Api;
import com.chs.wish.main.home.entity.Banner;
import com.chs.wish.main.home.entity.HomeMultipleEntity;
import com.chs.wish.main.home.entity.WishList;
import com.chs.wish.ui.CustomLoadMoreView;
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

    private final SwipeRefreshLayout mSwipeRefreshLayout;
    private final PagingBean BEAN;
    private final RecyclerView mRecyclerView;
    private MultipleRecyclerAdapter mAdapter = null;
    private List<Banner.BannerData> bannerData;
    private List<WishList.DataBean> wishList;
    private boolean isBannerLoadOk = false;
    private boolean isListLoadOk = false;

    public RefreshHandler(SwipeRefreshLayout swipeRefreshLayout, RecyclerView recyclerView,PagingBean BEAN) {
        this.mSwipeRefreshLayout = swipeRefreshLayout;
        this.BEAN = BEAN;
        this.mRecyclerView = recyclerView;
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mAdapter = MultipleRecyclerAdapter.create(new ArrayList<HomeMultipleEntity>());
        mAdapter.setLoadMoreView(new CustomLoadMoreView());
        mAdapter.setOnLoadMoreListener(RefreshHandler.this, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(Wish.getApplicationContext(),"点击了"+position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static  RefreshHandler create(SwipeRefreshLayout swipeRefreshLayout,
                                         RecyclerView recyclerView){
      return new RefreshHandler(swipeRefreshLayout,recyclerView,new PagingBean());
    }

    public void firstPage(Context context) {
                        OkGo.<Banner>get(Api.BANNER)
                                .tag(this)
                                .params("adver_id","1")
                                .execute(new JsonCallback<Banner>(Banner.class) {
                                    @Override
                                    public void onSuccess(Response<Banner> response) {
                                        isBannerLoadOk = true;
                                        bannerData = response.body().getData();
                                        if(isListLoadOk) setAdapter();
                                    }
                                });
                        OkGo.<WishList>get(Api.WISH_LISTS)
                                .tag(this)
                                .execute(new JsonCallback<WishList>(WishList.class) {
                                    @Override
                                    public void onSuccess(Response<WishList> response) {
                                        wishList = response.body().getData();
                                        isListLoadOk = true;
                                        if(isBannerLoadOk) setAdapter();
                                    }
                                });
    }

    private void setAdapter(){
        mSwipeRefreshLayout.setRefreshing(false);
        ArrayList<HomeMultipleEntity> multipleItemEntities = new ArrayList<>();
        multipleItemEntities.add(new HomeMultipleEntity(bannerData,null,ItemType.BANNER));
        for (WishList.DataBean dataBean: wishList) {
            multipleItemEntities.add(new HomeMultipleEntity(null,dataBean,ItemType.CONTENT));
        }
        setData(true,multipleItemEntities);
    }

    @Override
    public void onRefresh() {
//        mAdapter.setEnableLoadMore(false);
        mSwipeRefreshLayout.setRefreshing(true);
        Wish.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                mAdapter.setEnableLoadMore(true);
                //进行一些网络请求
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 1000);
    }

    @Override
    public void onLoadMoreRequested() {
        BEAN.addIndex();
        boolean isRefresh =BEAN.getPageIndex() ==1;

    }

    private void setData(boolean isRefresh, List data) {
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            mAdapter.setNewData(data);
        } else {
            if (size > 0) {
                mAdapter.addData(data);
            }
        }
        if (size < BEAN.getPageSize()) {
            //第一页如果不够一页就不显示没有更多数据布局
            mAdapter.loadMoreEnd(isRefresh);
        } else {
            mAdapter.loadMoreComplete();
        }
    }
}
