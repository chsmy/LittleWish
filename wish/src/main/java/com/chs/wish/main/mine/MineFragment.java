package com.chs.wish.main.mine;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.chs.core.base.BaseFragment;
import com.chs.wish.R;
import com.chs.wish.R2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：chs
 * 时间：2018-10-26 10:28
 * 描述：我的
 */
public class MineFragment extends BaseFragment {
    @BindView(R2.id.tv_left)
    AppCompatTextView mTvLeft;
    @BindView(R2.id.tv_title)
    AppCompatTextView mTvTitle;
    @BindView(R2.id.tv_right)
    AppCompatTextView mTvRight;
    @BindView(R2.id.tab_selector)
    TabLayout mTabLayout;
    @BindView(R2.id.vp_content)
    ViewPager mViewPager;

    private List<BaseFragment> mFragments = new ArrayList<>();
    private String[] mTitles = new String[]{"全部","发布的","申请的","助力的"};

    public static MineFragment newInstance(){
        return new MineFragment();
    }

    @Override
    protected Object setLayoutId() {
        return R.layout.main_fragment_mine;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mTvLeft.setBackgroundResource(R.mipmap.main_mine_top_left);
        mTvTitle.setText(R.string.main_mine_text);
        mTvRight.setBackgroundResource(R.mipmap.main_mine_top_right);

        mFragments.add(MineChildFragment.newInstance());
        mFragments.add(MineChildFragment.newInstance());
        mFragments.add(MineChildFragment.newInstance());
        mFragments.add(MineChildFragment.newInstance());
        PagerAdapter pagerAdapter = new PagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(pagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setInlineLabel(true);
    }

    @OnClick(R2.id.tv_left)
   void clickLeft(){
        showToast("left");
   }
    private class PagerAdapter extends FragmentStatePagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }
    }
}
