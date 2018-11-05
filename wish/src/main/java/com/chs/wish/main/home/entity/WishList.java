package com.chs.wish.main.home.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chs.core.base.BaseEntity;
import com.chs.core.recycler.ItemType;

import java.util.List;

/**
 * 作者：83734
 * 时间：2018/11/5
 * 描述：心愿列表
 */
public class WishList extends BaseEntity {
    /**
     * total_number : 1
     * number : 1
     * pages : 1
     * page : 1
     * returncode : 0
     * data : [{"id":"1","title":"小心愿标题","content":"小心愿内容","imgs":"1","user_id":"1","bg_music":"","labels":"","place_id":"0","longitude":"0.0000000000","latitude":"0.0000000000","a_num":"0","v_num":"0","c_time":"1539752748","target_place_id":"0","target_age_min":"0","target_age_max":"0","target_sex":"0","type":"1","state":"9","describe":"小心愿内容","send_name":"aacd630865d1c3b3cc4d9b41b5fe25e6","send_pic":"","imgs_arr":[{"id":"1","user_id":"7242","photo_file":"files/20180508/1001_15257726085824232.jpg","photo_type":"jpg","upload_time":"1525772606","photo_thumb_file":"","file_name":"","file_size":"0"}],"send_time_name":"2018.10.17","state_name":"","place_name":"北京","help_user_pics":[{"id":"1","user_id":"7242","photo_file":"files/20180508/1001_15257726085824232.jpg","photo_type":"jpg","upload_time":"1525772606","photo_thumb_file":"","file_name":"","file_size":"0"}],"help_num":10,"comment_num":0,"labels_arr":[]}]
     */

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
         * title : 小心愿标题
         * content : 小心愿内容
         * imgs : 1
         * user_id : 1
         * bg_music :
         * labels :
         * place_id : 0
         * longitude : 0.0000000000
         * latitude : 0.0000000000
         * a_num : 0
         * v_num : 0
         * c_time : 1539752748
         * target_place_id : 0
         * target_age_min : 0
         * target_age_max : 0
         * target_sex : 0
         * type : 1
         * state : 9
         * describe : 小心愿内容
         * send_name : aacd630865d1c3b3cc4d9b41b5fe25e6
         * send_pic :
         * imgs_arr : [{"id":"1","user_id":"7242","photo_file":"files/20180508/1001_15257726085824232.jpg","photo_type":"jpg","upload_time":"1525772606","photo_thumb_file":"","file_name":"","file_size":"0"}]
         * send_time_name : 2018.10.17
         * state_name :
         * place_name : 北京
         * help_user_pics : [{"id":"1","user_id":"7242","photo_file":"files/20180508/1001_15257726085824232.jpg","photo_type":"jpg","upload_time":"1525772606","photo_thumb_file":"","file_name":"","file_size":"0"}]
         * help_num : 10
         * comment_num : 0
         * labels_arr : []
         */

        private String id;
        private String title;
        private String content;
        private String imgs;
        private String user_id;
        private String bg_music;
        private String labels;
        private String place_id;
        private String longitude;
        private String latitude;
        private String a_num;
        private String v_num;
        private String c_time;
        private String target_place_id;
        private String target_age_min;
        private String target_age_max;
        private String target_sex;
        private String type;
        private String state;
        private String describe;
        private String send_name;
        private String send_pic;
        private String send_time_name;
        private String state_name;
        private String place_name;
        private int help_num;
        private int comment_num;
        private List<ImgsArrBean> imgs_arr;
        private List<HelpUserPicsBean> help_user_pics;
        private List<String> labels_arr;

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImgs() {
            return imgs;
        }

        public void setImgs(String imgs) {
            this.imgs = imgs;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getBg_music() {
            return bg_music;
        }

        public void setBg_music(String bg_music) {
            this.bg_music = bg_music;
        }

        public String getLabels() {
            return labels;
        }

        public void setLabels(String labels) {
            this.labels = labels;
        }

        public String getPlace_id() {
            return place_id;
        }

        public void setPlace_id(String place_id) {
            this.place_id = place_id;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getA_num() {
            return a_num;
        }

        public void setA_num(String a_num) {
            this.a_num = a_num;
        }

        public String getV_num() {
            return v_num;
        }

        public void setV_num(String v_num) {
            this.v_num = v_num;
        }

        public String getC_time() {
            return c_time;
        }

        public void setC_time(String c_time) {
            this.c_time = c_time;
        }

        public String getTarget_place_id() {
            return target_place_id;
        }

        public void setTarget_place_id(String target_place_id) {
            this.target_place_id = target_place_id;
        }

        public String getTarget_age_min() {
            return target_age_min;
        }

        public void setTarget_age_min(String target_age_min) {
            this.target_age_min = target_age_min;
        }

        public String getTarget_age_max() {
            return target_age_max;
        }

        public void setTarget_age_max(String target_age_max) {
            this.target_age_max = target_age_max;
        }

        public String getTarget_sex() {
            return target_sex;
        }

        public void setTarget_sex(String target_sex) {
            this.target_sex = target_sex;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public String getSend_name() {
            return send_name;
        }

        public void setSend_name(String send_name) {
            this.send_name = send_name;
        }

        public String getSend_pic() {
            return send_pic;
        }

        public void setSend_pic(String send_pic) {
            this.send_pic = send_pic;
        }

        public String getSend_time_name() {
            return send_time_name;
        }

        public void setSend_time_name(String send_time_name) {
            this.send_time_name = send_time_name;
        }

        public String getState_name() {
            return state_name;
        }

        public void setState_name(String state_name) {
            this.state_name = state_name;
        }

        public String getPlace_name() {
            return place_name;
        }

        public void setPlace_name(String place_name) {
            this.place_name = place_name;
        }

        public int getHelp_num() {
            return help_num;
        }

        public void setHelp_num(int help_num) {
            this.help_num = help_num;
        }

        public int getComment_num() {
            return comment_num;
        }

        public void setComment_num(int comment_num) {
            this.comment_num = comment_num;
        }

        public List<ImgsArrBean> getImgs_arr() {
            return imgs_arr;
        }

        public void setImgs_arr(List<ImgsArrBean> imgs_arr) {
            this.imgs_arr = imgs_arr;
        }

        public List<HelpUserPicsBean> getHelp_user_pics() {
            return help_user_pics;
        }

        public void setHelp_user_pics(List<HelpUserPicsBean> help_user_pics) {
            this.help_user_pics = help_user_pics;
        }

        public List<?> getLabels_arr() {
            return labels_arr;
        }

        public void setLabels_arr(List<String> labels_arr) {
            this.labels_arr = labels_arr;
        }

        public static class ImgsArrBean {
            /**
             * id : 1
             * user_id : 7242
             * photo_file : files/20180508/1001_15257726085824232.jpg
             * photo_type : jpg
             * upload_time : 1525772606
             * photo_thumb_file :
             * file_name :
             * file_size : 0
             */

            private String id;
            private String user_id;
            private String photo_file;
            private String photo_type;
            private String upload_time;
            private String photo_thumb_file;
            private String file_name;
            private String file_size;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getPhoto_file() {
                return photo_file;
            }

            public void setPhoto_file(String photo_file) {
                this.photo_file = photo_file;
            }

            public String getPhoto_type() {
                return photo_type;
            }

            public void setPhoto_type(String photo_type) {
                this.photo_type = photo_type;
            }

            public String getUpload_time() {
                return upload_time;
            }

            public void setUpload_time(String upload_time) {
                this.upload_time = upload_time;
            }

            public String getPhoto_thumb_file() {
                return photo_thumb_file;
            }

            public void setPhoto_thumb_file(String photo_thumb_file) {
                this.photo_thumb_file = photo_thumb_file;
            }

            public String getFile_name() {
                return file_name;
            }

            public void setFile_name(String file_name) {
                this.file_name = file_name;
            }

            public String getFile_size() {
                return file_size;
            }

            public void setFile_size(String file_size) {
                this.file_size = file_size;
            }
        }

        public static class HelpUserPicsBean {
            /**
             * id : 1
             * user_id : 7242
             * photo_file : files/20180508/1001_15257726085824232.jpg
             * photo_type : jpg
             * upload_time : 1525772606
             * photo_thumb_file :
             * file_name :
             * file_size : 0
             */

            private String id;
            private String user_id;
            private String photo_file;
            private String photo_type;
            private String upload_time;
            private String photo_thumb_file;
            private String file_name;
            private String file_size;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getPhoto_file() {
                return photo_file;
            }

            public void setPhoto_file(String photo_file) {
                this.photo_file = photo_file;
            }

            public String getPhoto_type() {
                return photo_type;
            }

            public void setPhoto_type(String photo_type) {
                this.photo_type = photo_type;
            }

            public String getUpload_time() {
                return upload_time;
            }

            public void setUpload_time(String upload_time) {
                this.upload_time = upload_time;
            }

            public String getPhoto_thumb_file() {
                return photo_thumb_file;
            }

            public void setPhoto_thumb_file(String photo_thumb_file) {
                this.photo_thumb_file = photo_thumb_file;
            }

            public String getFile_name() {
                return file_name;
            }

            public void setFile_name(String file_name) {
                this.file_name = file_name;
            }

            public String getFile_size() {
                return file_size;
            }

            public void setFile_size(String file_size) {
                this.file_size = file_size;
            }
        }
    }
}
