package com.chs.wish.main.publish;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.DatePicker;

import com.chs.core.Constant;
import com.chs.core.base.BaseActivity;
import com.chs.core.http.DialogCallback;
import com.chs.core.utils.BitCompressUtil;
import com.chs.wish.Api;
import com.chs.wish.R;
import com.chs.wish.R2;
import com.chs.wish.main.publish.entity.ImageItem;
import com.chs.wish.main.publish.ui.PublishImgAdapter;
import com.chs.wish.ui.GifSizeFilter;
import com.chs.wish.ui.Glide4Engine;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.PostRequest;
import com.lzy.okgo.request.base.Request;
import com.orhanobut.logger.Logger;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.zhihu.matisse.internal.utils.PathUtils;

import java.io.File;
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
    private PublishImgAdapter mAdapter = null;

    protected ArrayList<ImageItem> mImgData = new ArrayList<>();

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
        mAdapter = new PublishImgAdapter(R.layout.item_show_img,mImgData);
        mRvPic.setAdapter(mAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PubConst.REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {

            if (data!=null) {
                List<Uri> uris = Matisse.obtainResult(data);
                for (Uri uri : uris) {
                    if(!mImgData.contains(uri)){
                        mImgData.add(0,new ImageItem(uri,false, PathUtils.getPath(this,uri)));
                    }
                }
                mAdapter.notifyDataSetChanged();
            }
            Log.e("OnActivityResult ", String.valueOf(Matisse.obtainOriginalState(data)));
        }
    }

    @OnClick(R2.id.tv_right)
    void next(){
        upLoadFile();
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

    private void upLoadFile(){
        ArrayList<File> files = new ArrayList<>();
        ArrayList<ImageItem> images = new ArrayList<>(mImgData);
        images.remove(images.size()-1);
        for (ImageItem imageItem: images) {
            if(!imageItem.isVideo()){
                Bitmap bitmap = BitCompressUtil.getSmallBitmap(imageItem.getPath());
                File file = new File(Constant.BXT_IMAGE_COMPRESS);
                if (!file.exists()) {
                    file.mkdirs();
                }
                File imageFile = new File(file, String.valueOf(System.currentTimeMillis()) + ".jpg");
                BitCompressUtil.compressBmpToFile(bitmap, imageFile);
                files.add(imageFile);
            }else {
                files.add(new File(imageItem.getPath()));
            }
        }
        PostRequest<String> request = OkGo.<String>post(Api.WISH_UPLOAD)
                .tag(this);
        request.params("user_id","1");
        request.params("is_form","0");
        request.params("is_file","1");
        for (int i = 0; i < files.size(); i++) {
            request.params("my_files"+i,files.get(i));
        }
        request
                .execute(new DialogCallback<String>(String.class,this) {
                    @Override
                    public void onStart(Request<String, ? extends Request> request) {
                        super.onStart(request);
                        showToast("正在上传文件");
                    }

                    @Override
                    public void onSuccess(Response<String> response) {
                        String loadFile = response.body();
//                        String resCode = loadFile.getReturncode();
//                        if(resCode.equals("0")){
//                            fileIds = loadFile.getFile_ids();
//                            LogUtils.i(fileIds);
////                            showToast("上传完成");
////                            submit();
//                        }else {
//                            showToast("上传文件失败");
//                        }
                        Logger.i(loadFile);
                    }
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        showToast("上传文件错误："+response.message());
                    }
                });

    }

}
