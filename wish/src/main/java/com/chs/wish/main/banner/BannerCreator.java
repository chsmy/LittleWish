package com.chs.wish.main.banner;

import android.content.Context;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.chs.wish.R;

import java.util.ArrayList;

/**
 * 作者：83734
 * 时间：2018/11/3
 * 描述：
 */
public class BannerCreator {
    public static void setDefault(ConvenientBanner<String> convenientBanner,
                                  ArrayList<String> banners,
                                  OnItemClickListener clickListener) {

        convenientBanner
                .setPages(new CBViewHolderCreator() {
                    @Override
                    public Holder createHolder(View itemView) {
                        return new ImageHolder(itemView);
                    }

                    @Override
                    public int getLayoutId() {
                        return R.layout.item_guide;
                    }
                }, banners)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(clickListener)
                .startTurning(3000)
                .setCanLoop(true);

    }
}
