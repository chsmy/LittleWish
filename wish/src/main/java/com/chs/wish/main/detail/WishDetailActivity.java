package com.chs.wish.main.detail;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.chs.core.base.BaseActivity;
import com.chs.core.http.JsonCallback;
import com.chs.wish.Api;
import com.chs.wish.R;
import com.chs.wish.R2;
import com.chs.wish.main.banner.BannerCreator;
import com.chs.wish.main.home.NewestFragment;
import com.chs.wish.main.home.entity.Banner;
import com.chs.wish.main.publish.ApplyForHelpActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：chs
 * 时间：2018-11-07 11:03
 * 描述：心愿列表详情
 */
public class WishDetailActivity extends BaseActivity {
    @BindView(R2.id.tv_left)
    AppCompatTextView mTvLeft;
    @BindView(R2.id.tv_title)
    AppCompatTextView mTvTitle;
    @BindView(R2.id.tv_right)
    AppCompatTextView mTvRight;
    @BindView(R2.id.iv_music)
    AppCompatImageView mIvMusic;
    private static MediaPlayer mMediaPlayer = null;
    @Override
    protected Object setContentLayout() {
        return R.layout.activity_detail_main;
    }

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        mTvLeft.setBackgroundResource(R.mipmap.top_bar_back);
        mTvTitle.setText(R.string.wish_list_detail_title);
        mTvRight.setBackgroundResource(R.mipmap.top_bar_modifier);
        if(mMediaPlayer == null){
            mMediaPlayer = new MediaPlayer();
        }
        initData();
    }

    private void initData() {
        OkGo.<MusicEntity>get(Api.WISH_INFO_MUSIC)
                .tag(this)
                .execute(new JsonCallback<MusicEntity>(MusicEntity.class) {
                    @Override
                    public void onSuccess(Response<MusicEntity> response) {
                        String url = response.body().getData().get(0).getMusic_url();
                        if(isAPPBroughtToBackground(WishDetailActivity.this)){
                            try {
                                mMediaPlayer.reset();
                                mMediaPlayer.setDataSource(url);
                                //3 准备播放
                                mMediaPlayer.prepareAsync();
                                //3.1 设置一个准备完成的监听
                                mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                    @Override
                                    public void onPrepared(MediaPlayer mp) {
                                        // 4 开始播放
                                        mMediaPlayer.start();
                                    }
                                });
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                });
    }

    @OnClick(R2.id.tv_apply)
    void applyForHelp(){
      startActivity(ApplyForHelpActivity.class);
    }
    @OnClick(R2.id.iv_music)
    void playMusic(){
      if(mMediaPlayer.isPlaying()){
          mMediaPlayer.pause();
          mIvMusic.setBackgroundResource(R.mipmap.detail_off_music);
      }else {
          mMediaPlayer.start();
          mIvMusic.setBackgroundResource(R.mipmap.detail_play_music);
      }
    }

    @Override
    protected void onStop() {
        super.onStop();
        OkGo.getInstance().cancelTag(this);
        mMediaPlayer.stop();
    }

}
