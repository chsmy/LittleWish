package com.chs.littlewish;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.blankj.utilcode.util.LogUtils;
import com.chs.core.http.DialogCallback;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OkGo.<DouBan>get("http://api.douban.com/v2/movie/top250").tag(this)
                .execute(new DialogCallback<DouBan>(DouBan.class,this) {
                    @Override
                    public void onSuccess(Response<DouBan> response) {
                        LogUtils.i("DouBan",response.body().getTitle());
                    }
                });

    }
}
