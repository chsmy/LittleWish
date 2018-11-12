package com.chs.wish.main.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chs.core.base.BaseFragment;
import com.chs.core.http.JsonCallback;
import com.chs.wish.Api;
import com.chs.wish.R;
import com.chs.wish.R2;
import com.chs.wish.main.detail.WishDetailActivity;
import com.chs.wish.main.home.PagingBean;
import com.chs.wish.main.home.entity.WishList;
import com.chs.wish.main.home.ui.HomeListAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：83734
 * 时间：2018/11/8
 * 描述：
 */
public class MineChildFragment extends BaseFragment {
    @BindView(R2.id.rv_home)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout = null;
    private HomeListAdapter mAdapter;
    private PagingBean BEAN = new PagingBean();
    @Override
    protected Object setLayoutId() {
        return R.layout.main_fragment_mine_child;
    }
    public static MineChildFragment newInstance(){
        return new MineChildFragment();
    }
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
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
        mAdapter.setFrom(1);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new com.chad.library.adapter.base.listener.OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivityContext(),WishDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        mRefreshLayout.setProgressViewOffset(true, 120, 300);
        mRefreshLayout.setRefreshing(true);
    }
    private void initData() {
        OkGo.<WishList>get(Api.WISH_LISTS)
                .tag(this)
                .execute(new JsonCallback<WishList>(WishList.class) {
                    @Override
                    public void onSuccess(Response<WishList> response) {
                        List<WishList.DataBean> wishList = response.body().getData();
                        setData(true,wishList);
                    }
                });
    }

    private void setData(boolean isRefresh, List data) {
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

}
