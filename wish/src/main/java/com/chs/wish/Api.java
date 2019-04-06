package com.chs.wish;

/**
 * 作者：83734
 * 时间：2018/11/4
 * 描述：
 */
public class Api {
    private static final String BASE_URL = "http://47.105.36.205/?c=Port&m=actionGet_Android_v2_Port";
    private static final String MODULE_ADS = BASE_URL + "&module=Ads&opt=";
    private static final String MODULE_WISH = BASE_URL + "&module=Wish&opt=";
    private static final String MODULE_USER= BASE_URL + "&module=User&opt=";

    /**
     * 首页轮播
     */
    public static final String BANNER = MODULE_ADS +  "ads_pic";
    /**
     * 心愿列表
     */
    public static final String WISH_LISTS = MODULE_WISH + "wish_lists";
    /**
     * 心愿详情
     */
    public static final String WISH_INFO = MODULE_WISH + "wish_info";
    /**
     * 详情中的音乐
     */
    public static final String WISH_INFO_MUSIC = MODULE_WISH + "music_lists";

    /**
     * 注册
     */
    public static final String REGISTER = MODULE_USER + "add_user";
    /**
     * 登录
     */
    public static final String LOGIN = MODULE_USER + "login";

    /**
     * 申请助力
     */
    public static final String WISH_APPLY = MODULE_WISH + "wish_apply";

}
