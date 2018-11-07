package com.chs.core.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.chs.core.R;
import com.chs.core.utils.StatusBarCompat;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：chs
 * 时间：2018-10-22 14:32
 * 描述：
 */
public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder mUnbinder = null;

    protected abstract Object setContentLayout();
    protected abstract void init(@Nullable Bundle savedInstanceState);

    @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        if (setContentLayout() instanceof Integer) {
            setContentView((Integer) setContentLayout());
        } else if (setContentLayout() instanceof View) {
           setContentView((View) setContentLayout());
        } else {
            throw new ClassCastException("type of setLayout() must be int or View!");
        }
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.title_bg));
        StatusBarCompat.setLightMode(this);
        mUnbinder = ButterKnife.bind(this);
        init(savedInstanceState);
    }
    protected void startActivity(Class clazz){
        Intent intent = new Intent(this,clazz);
        startActivity(intent);
    }
    protected void showToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
    /**
     * @param view 标题栏左侧按钮点击事件
     */
    public void goBack(View view) {
        onBackPressed();
    }
}
