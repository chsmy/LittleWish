package com.chs.wish.main.home;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chs.core.app.Wish;
import com.chs.core.base.BaseFragment;
import com.chs.core.http.JsonCallback;
import com.chs.wish.Api;
import com.chs.wish.R;
import com.chs.wish.R2;
import com.chs.wish.main.banner.BannerCreator;
import com.chs.wish.main.home.entity.Banner;
import com.chs.wish.main.home.entity.WishList;
import com.chs.wish.main.home.ui.HomeListAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：chs
 * 时间：2018-10-26 15:53
 * 描述：最新
 */
public class NewestFragment extends BaseFragment implements OnItemClickListener
        ,SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R2.id.rv_home)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout = null;
    private HomeListAdapter mAdapter;
    private PagingBean BEAN = new PagingBean();
    private int mType;//0是左边 1是右边
    private boolean isFirstLoad = false;
    private boolean isRefresh = true;
    public static NewestFragment newInstance(int from){
        NewestFragment newestFragment = new NewestFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type",from);
        newestFragment.setArguments(bundle);
        return newestFragment;
    }
    @Override
    protected Object setLayoutId() {
        return R.layout.main_fragment_newest;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mType = getArguments().getInt("type");
        initRefreshLayout();
        initRecyclerView();
    }

    @Override
    protected void onFirstVisible() {
        super.onFirstVisible();
        initData();
    }

    private void initRecyclerView() {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new HomeListAdapter(R.layout.item_home_list,new ArrayList<WishList.DataBean>());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(this,mRecyclerView);
        if(mType == 0)
        addHeadView();
    }

    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        mRefreshLayout.setProgressViewOffset(true, 120, 300);
        mRefreshLayout.setRefreshing(true);
        mRefreshLayout.setOnRefreshListener(this);
    }

    private void initData() {
        if(mType == 0&&!isFirstLoad){
            OkGo.<Banner>get(Api.BANNER)
                    .tag(this)
                    .params("adver_id","1")
                    .execute(new JsonCallback<Banner>(Banner.class) {
                        @Override
                        public void onSuccess(Response<Banner> response) {
                            isFirstLoad = true;
                            List<Banner.BannerData> bannerData = response.body().getData();
                            ArrayList<String> banners = new ArrayList<>();
                            for (Banner.BannerData data : bannerData) {
                                banners.add(data.getPic());
                            }
                            final ConvenientBanner<String> convenientBanner = mAdapter.getHeaderLayout().findViewById(R.id.banner_recycler_item);
                            BannerCreator.setDefault(convenientBanner, banners, NewestFragment.this);
                        }
                    });
        }
        OkGo.<WishList>get(Api.WISH_LISTS)
                .tag(this)
                .execute(new JsonCallback<WishList>(WishList.class) {
                    @Override
                    public void onSuccess(Response<WishList> response) {
                        List<WishList.DataBean> wishList = response.body().getData();
                        wishList.addAll(response.body().getData());
                        wishList.addAll(response.body().getData());
                        wishList.addAll(response.body().getData());
                        setData(wishList);
                    }
                });
    }
    private void addHeadView() {
        View headView = getLayoutInflater().inflate(R.layout.item_multiple_banner, (ViewGroup) mRecyclerView.getParent(), false);
        headView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mAdapter.addHeaderView(headView);
    }
    private void setData(List data) {
        mRefreshLayout.setRefreshing(false);
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

    @Override
    public void onItemClick(int position) {
        Toast.makeText(Wish.getApplicationContext(),"点击了"+position,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        isRefresh = true;
       initData();
    }

    @Override
    public void onLoadMoreRequested() {
        isRefresh = false;
        BEAN.addIndex();
        initData();
    }
}
