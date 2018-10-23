package com.chs.core.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

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
        if (setContentLayout() instanceof Integer) {
            setContentView((Integer) setContentLayout());
        } else if (setContentLayout() instanceof View) {
           setContentView((View) setContentLayout());
        } else {
            throw new ClassCastException("type of setLayout() must be int or View!");
        }
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
}
