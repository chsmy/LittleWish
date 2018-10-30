package com.chs.core.recycler;

import android.support.v7.widget.GridLayoutManager;

import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

public class MultipleRecyclerAdapter extends BaseMultiItemQuickAdapter<MultipleItemEntity,MultipleViewHolder>
        implements
        BaseQuickAdapter.SpanSizeLookup,
        OnItemClickListener {
    //确保初始化一次Banner，防止重复Item加载
    private boolean mIsInitBanner = false;
    //设置glide图片加载策略
//    private static final RequestOptions RECYCLER_OPTIONS =
//            new RequestOptions()
//                    .centerCrop()
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .dontAnimate();
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MultipleRecyclerAdapter(List<MultipleItemEntity> data) {
        super(data);
        init();
    }
    private void init() {
        //设置不同的item布局
//        addItemType(ItemType.TEXT, R.layout.item_multiple_text);
//        addItemType(ItemType.IMAGE, R.layout.item_multiple_image);
//        addItemType(ItemType.TEXT_IMAGE, R.layout.item_multiple_image_text);
//        addItemType(ItemType.BANNER, R.layout.item_multiple_banner);
        //设置宽度监听
        setSpanSizeLookup(this);
        openLoadAnimation();
        //多次执行动画
        isFirstOnly(false);
    }
    @Override
    protected void convert(MultipleViewHolder helper, MultipleItemEntity item) {

    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        return 0;
    }
}
