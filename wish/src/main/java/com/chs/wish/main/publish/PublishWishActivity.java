package com.chs.wish.main.publish;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chs.core.base.BaseActivity;
import com.chs.wish.R;
import com.chs.wish.R2;
import com.chs.wish.main.detail.WishDetailActivity;
import com.chs.wish.main.home.entity.WishList;
import com.chs.wish.main.home.ui.HomeListAdapter;
import com.chs.wish.main.publish.ui.PublishImgAdapter;
import com.chs.wish.ui.GifSizeFilter;
import com.chs.wish.ui.Glide4Engine;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.zhihu.matisse.listener.OnCheckedListener;
import com.zhihu.matisse.listener.OnSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：chs
 * 时间：2018-11-07 11:05
 * 描述：发布心愿
 */
public class PublishWishActivity extends BaseActivity {
    @BindView(R2.id.tv_left)
    AppCompatTextView mTvLeft;
    @BindView(R2.id.tv_title)
    AppCompatTextView mTvTitle;
    @BindView(R2.id.tv_right)
    AppCompatTextView mTvRight;
    @BindView(R2.id.tv_select_time)
    AppCompatTextView mTvSelectedTime;
    @BindView((R2.id.rv_pic))
    RecyclerView mRvPic;
    private String selectedDate = "";
    private static final int REQUEST_CODE_CHOOSE = 23;
    private PublishImgAdapter mAdapter = null;
    @Override
    protected Object setContentLayout() {
        return R.layout.activity_publish_main;
    }

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        mTvLeft.setText(R.string.publish_top_cancel);
        mTvTitle.setText(R.string.publish_publish_wish);
        mTvRight.setText(R.string.publish_next);
        initRecyclerView();
    }
    private void initRecyclerView() {
        final LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvPic.setLayoutManager(manager);
        mAdapter = new PublishImgAdapter(R.layout.item_show_img,new ArrayList<Uri>());
        mRvPic.setAdapter(mAdapter);
        mRvPic.addOnItemTouchListener(new com.chad.library.adapter.base.listener.OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
               showToast("点击了");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            if (data!=null)
            mAdapter.setNewData(Matisse.obtainResult(data));
            Log.e("OnActivityResult ", String.valueOf(Matisse.obtainOriginalState(data)));
        }
    }

    @OnClick(R2.id.tv_select_time)
    void selectTime(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, DatePickerDialog.THEME_DEVICE_DEFAULT_LIGHT,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                selectedDate = year + "/" + (month + 1) + "/" + dayOfMonth;
                mTvSelectedTime.setText(selectedDate);
            }
        },year,month,day);
        datePickerDialog.show();
    }

    @OnClick(R2.id.iv_select_img)
    void selectImage(){
        AndPermission.with(this)
                .runtime()
                .permission(Permission.Group.CAMERA)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        Matisse.from(PublishWishActivity.this)
                                .choose(MimeType.ofAll())
                                .countable(true)
                                .captureStrategy(new CaptureStrategy(true, "com.chs.wish.fileprovider","test"))
                                .capture(true)
                                .maxSelectable(9)
                                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                                .thumbnailScale(0.85f)
                                .imageEngine(new Glide4Engine())
                                .forResult(REQUEST_CODE_CHOOSE);
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
