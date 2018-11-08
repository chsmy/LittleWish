package com.chs.wish.main.mine;

import android.os.Bundle;
import android.view.View;

import com.chs.core.base.BaseFragment;
import com.chs.wish.R;

/**
 * 作者：83734
 * 时间：2018/11/8
 * 描述：
 */
public class MineChildFragment extends BaseFragment {
    @Override
    protected Object setLayoutId() {
        return R.layout.main_fragment_mine_child;
    }
    public static MineChildFragment newInstance(){
        return new MineChildFragment();
    }
    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }
}
