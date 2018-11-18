package com.chs.wish.main.detail;

import com.chs.core.base.BaseEntity;

import java.util.List;

/**
 * 作者：83734
 * 时间：2018/11/18
 * 描述：
 */
public class MusicEntity extends BaseEntity {
    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * music_url : http://reg.51sousou.com/public/tmp/wt.mp3
         */

        private int id;
        private String music_url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMusic_url() {
            return music_url;
        }

        public void setMusic_url(String music_url) {
            this.music_url = music_url;
        }
    }
}
