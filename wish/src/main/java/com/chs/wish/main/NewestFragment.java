package com.chs.wish.main;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chs.core.base.BaseFragment;
import com.chs.core.recycler.BaseDecoration;
import com.chs.wish.R;
import com.chs.wish.R2;

import butterknife.BindView;

/**
 * 作者：chs
 * 时间：2018-10-26 15:53
 * 描述：最新
 */
public class NewestFragment extends BaseFragment {
    @BindView(R2.id.rv_home)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout = null;

    public static NewestFragment newInstance(){
        return new NewestFragment();
    }

    @Override
    protected Object setLayoutId() {
        return R.layout.main_fragment_newest;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void onFirstVisible() {
        super.onFirstVisible();
        initRefreshLayout();
        initRecyclerView();
    }

    private void initRecyclerView() {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration
                (BaseDecoration.create(ContextCompat.getColor(getActivityContext(), R.color.app_background), 5));

    }

    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        mRefreshLayout.setProgressViewOffset(true, 120, 300);
    }
}
