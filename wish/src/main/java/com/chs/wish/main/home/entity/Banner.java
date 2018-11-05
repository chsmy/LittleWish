package com.chs.wish.main.home.entity;

import com.chs.core.base.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：83734
 * 时间：2018/11/4
 * 描述：首页轮播图
 */
public class Banner extends BaseEntity {
    private List<BannerData> data;

    public List<BannerData> getData() {
        return data;
    }

    public void setData(ArrayList<BannerData> data) {
        this.data = data;
    }

    public class BannerData{

        /**
         * id : 78
         * title : 首页广告图
         * type : 1
         * pic : http://demo.51sousou.com/public/images/ads.png
         * is_show : 1
         * more : #
         * sort : 0
         * adver_id : 1
         */

        private String id;
        private String title;
        private String type;
        private String pic;
        private String is_show;
        private String more;
        private String sort;
        private String adver_id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getIs_show() {
            return is_show;
        }

        public void setIs_show(String is_show) {
            this.is_show = is_show;
        }

        public String getMore() {
            return more;
        }

        public void setMore(String more) {
            this.more = more;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getAdver_id() {
            return adver_id;
        }

        public void setAdver_id(String adver_id) {
            this.adver_id = adver_id;
        }
    }

}
