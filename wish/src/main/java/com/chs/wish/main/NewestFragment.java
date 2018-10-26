package com.chs.wish.main;

import android.os.Bundle;
import android.view.View;

import com.chs.core.base.BaseFragment;
import com.chs.wish.R;

/**
 * 作者：chs
 * 时间：2018-10-26 15:53
 * 描述：最新
 */
public class NewestFragment extends BaseFragment {

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
}
