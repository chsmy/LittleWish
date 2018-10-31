package com.chs.wish.main.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.chs.core.base.BaseActivity;
import com.chs.core.base.BaseFragment;
import com.chs.wish.R;
import com.chs.wish.R2;
import com.chs.wish.main.MineFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：chs
 * 时间：2018-10-23 11:17
 * 描述：
 */
public class HomeActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R2.id.container)
    FrameLayout mContainer;
    @BindView(R2.id.tv_home)
    TextView mTvHome;
    @BindView(R2.id.tv_mine)
    TextView mTvMine;
    private List<BaseFragment> mFragments = new ArrayList<>();
    private int currentId = R.id.tv_home;// 当前选中id,默认是主页

    @Override
    protected Object setContentLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
//        StatusBarUtil.setColor(this, getResources().getColor(R.color.tab_item_background));
        mFragments.add(HomeFragment.newInstance());
        getSupportFragmentManager().beginTransaction().add(R.id.container, mFragments.get(0)).commit();
        mTvHome.setOnClickListener(this);
        mTvMine.setOnClickListener(this);
        mTvHome.setSelected(true);
    }

    private void changeFragment(int resId) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();//开启一个Fragment事务
        hideFragments(transaction);//隐藏所有fragment
        if (resId == R.id.tv_home) {//主页
            transaction.show(mFragments.get(0));
        } else if (resId == R.id.tv_mine) {//动态
            if (mFragments.size() == 1) {
                mFragments.add(MineFragment.newInstance());
                transaction.add(R.id.container, mFragments.get(1));
            } else {
                transaction.show(mFragments.get(1));
            }
        }
        transaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction) {
        for (BaseFragment fragment : mFragments) {
            if (fragment != null)
                transaction.hide(fragment);
        }
    }

    /**
     * 改变TextView选中颜色
     *
     * @param resId
     */
    private void changeSelect(int resId) {
        mTvHome.setSelected(false);
        mTvMine.setSelected(false);
        if (resId == R.id.tv_home) {
            mTvHome.setSelected(true);
        } else if (resId == R.id.tv_mine) {
            mTvMine.setSelected(true);
        }
    }

    @Override
    public void onClick(View v) {
            changeSelect(v.getId());//改变图标跟文字颜色的选中
            changeFragment(v.getId());
            currentId = v.getId();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
