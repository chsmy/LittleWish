package com.chs.wish.main.home.ui;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chs.core.widget.CircleImageView;
import com.chs.core.widget.PileLayout;
import com.chs.wish.R;
import com.chs.wish.main.detail.WishDetailActivity;
import com.chs.wish.main.home.entity.WishList;

import java.util.List;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * 作者：chs
 * 时间：2018-11-06 18:36
 * 描述：
 */
public class HomeListAdapter extends BaseQuickAdapter<WishList.DataBean,BaseViewHolder> {
    private int mFrom = 0;
    private PopupWindow mPopupWindow;
    private int popHeight;
    private int popWidth;
    private String[] urls = {
            "http://img0.imgtn.bdimg.com/it/u=2263418180,3668836868&fm=206&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=2263418180,3668836868&fm=206&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=1939271907,257307689&fm=21&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=2263418180,3668836868&fm=206&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=2263418180,3668836868&fm=206&gp=0.jpg"};
    public HomeListAdapter(int layoutResId, @Nullable List<WishList.DataBean> data) {
        super(layoutResId, data);
    }

    public void setFrom(int from) {
        mFrom = from;
    }

    @Override
    protected void convert(BaseViewHolder helper, WishList.DataBean item) {
        LinearLayoutCompat commentView = helper.getView(R.id.ll_comment);
        if (mFrom == 1) commentView.setVisibility(View.GONE);

        PileLayout pileLayout = helper.getView(R.id.pl_head);
        initPraises(pileLayout,urls);
        final View comment = helper.getView(R.id.ll_comment);
        helper.getView(R.id.item_root).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, WishDetailActivity.class);
                mContext.startActivity(intent);
            }
        });
        helper.getView(R.id.rl_comment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showComment(comment);
            }
        });
    }

    /**
     * 评论人数头像
     */
    private void initPraises(PileLayout pileLayout, String[] urls) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        pileLayout.removeAllViews();
        for (String url : urls) {
            CircleImageView imageView = (CircleImageView) inflater.inflate(R.layout.item_help_person, pileLayout, false);
            Glide.with(mContext).load(url).into(imageView);
            pileLayout.addView(imageView);
        }
    }

    /**
     * 评论点赞
     */
    private void showComment(View viewParent) {
        if (mPopupWindow == null) {
            View commentView = LayoutInflater.from(mContext).inflate(R.layout.item_commet, null);
            mPopupWindow = new PopupWindow(commentView, WRAP_CONTENT, WRAP_CONTENT);
            mPopupWindow.setBackgroundDrawable(new ColorDrawable());
            mPopupWindow.setOutsideTouchable(true);
            mPopupWindow.setTouchable(true);
            mPopupWindow.setFocusable(true);
            commentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            popHeight = commentView.getMeasuredHeight();
            popWidth = commentView.getMeasuredWidth();
            TextView tvLike = commentView.findViewById(R.id.tv_like);
            TextView tvComment = commentView.findViewById(R.id.tv_comment);
            tvLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,"like",Toast.LENGTH_SHORT).show();
                }
            });
            tvComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        if(mPopupWindow.isShowing()){
            mPopupWindow.dismiss();
        }else {
            mPopupWindow.showAsDropDown(viewParent, (viewParent.getWidth()-popWidth-popWidth/2), -(popHeight + viewParent.getHeight()));
        }
    }
}
