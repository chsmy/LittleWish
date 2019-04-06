package com.chs.wish.main.publish;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;

import com.chs.core.base.BaseActivity;
import com.chs.core.base.BaseEntity;
import com.chs.core.http.DialogCallback;
import com.chs.wish.Api;
import com.chs.wish.R;
import com.chs.wish.R2;
import com.chs.wish.main.publish.ui.PublishImgAdapter;
import com.chs.wish.ui.GifSizeFilter;
import com.chs.wish.ui.Glide4Engine;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：chs
 * 时间：2018-11-12 13:29
 * 描述：申请助力
 */
public class ApplyForHelpActivity extends BaseActivity {
    @BindView(R2.id.tv_left)
    AppCompatTextView mTvLeft;
    @BindView(R2.id.tv_title)
    AppCompatTextView mTvTitle;
    @BindView(R2.id.tv_right)
    AppCompatTextView mTvRight;
    @BindView((R2.id.rv_pic))
    RecyclerView mRvPic;
    @BindView(R2.id.et_content)
    EditText mEtContent;
    private PublishImgAdapter mAdapter = null;

    @Override
    protected Object setContentLayout() {
        return R.layout.activity_apply_for_help;
    }

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        mTvLeft.setText(R.string.publish_top_cancel);
        mTvTitle.setText(R.string.publish_apply_for_help);
        mTvRight.setText(R.string.publish_send);
        initRecyclerView();
    }

    private void initRecyclerView() {
        final LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvPic.setLayoutManager(manager);
        mAdapter = new PublishImgAdapter(R.layout.item_show_img, new ArrayList<Uri>());
        mRvPic.setAdapter(mAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PubConst.REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            if (data != null)
                mAdapter.setNewData(Matisse.obtainResult(data));
            Log.e("OnActivityResult ", String.valueOf(Matisse.obtainOriginalState(data)));
        }
    }

    @OnClick(R2.id.tv_right)
    void publish(){
        String content = mEtContent.getText().toString();
        OkGo.<BaseEntity>post(Api.WISH_APPLY).tag(this)
                .params("wish_id","1")
                .params("user_id","1")
                .params("content",content)
                .execute(new DialogCallback<BaseEntity>(BaseEntity.class,this) {
                    @Override
                    public void onSuccess(Response<BaseEntity> response) {
                        Logger.d(response.body().getReturncode());
                    }
                });
    }

    @OnClick(R2.id.iv_select_img)
    void selectImage() {
        AndPermission.with(this)
                .runtime()
                .permission(Permission.Group.CAMERA)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        Matisse.from(ApplyForHelpActivity.this)
                                .choose(MimeType.ofAll())
                                .countable(true)
                                .captureStrategy(new CaptureStrategy(true, "com.chs.wish.fileprovider", "test"))
                                .capture(true)
                                .maxSelectable(9)
                                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                                .thumbnailScale(0.85f)
                                .imageEngine(new Glide4Engine())
                                .forResult(PubConst.REQUEST_CODE_CHOOSE);
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        showToast("拍照权限被禁止");
                    }
                }).start();
    }
}
