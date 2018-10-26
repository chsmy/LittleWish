package com.chs.wish.main;

import android.os.Bundle;
import android.view.View;

import com.chs.core.base.BaseFragment;
import com.chs.wish.R;

/**
 * 作者：chs
 * 时间：2018-10-26 10:28
 * 描述：我的
 */
public class MineFragment extends BaseFragment {

    public static MineFragment newInstance(){
        return new MineFragment();
    }

    @Override
    protected Object setLayoutId() {
        return R.layout.main_fragment_mine;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }
}
