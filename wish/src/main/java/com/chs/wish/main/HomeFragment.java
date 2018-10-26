package com.chs.wish.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.chs.core.base.BaseFragment;
import com.chs.wish.R;
import com.chs.wish.R2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：chs
 * 时间：2018-10-26 10:27
 * 描述：首页
 */
public class HomeFragment extends BaseFragment {

    @BindView(R2.id.tab_selector)
    TabLayout mTabLayout;
    @BindView(R2.id.vp_content)
    ViewPager mViewPager;

    private List<BaseFragment> mFragments = new ArrayList<>();
    private String[] mTitles = new String[]{"最新","最热"};
    private NewsPagerAdapter mPagerAdapter;

    public static HomeFragment newInstance(){
        return new HomeFragment();
    }

    @Override
    protected Object setLayoutId() {
        return R.layout.main_fragment_home;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mFragments.add(NewestFragment.newInstance());
        mFragments.add(HottestFragment.newInstance());
        mPagerAdapter = new NewsPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private class NewsPagerAdapter extends FragmentStatePagerAdapter {

        public NewsPagerAdapter(FragmentManager fm) {
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
