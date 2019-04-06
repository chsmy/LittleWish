package com.chs.wish.main.detail

import com.chs.core.base.BaseEntity

/**
 * author：chs
 * date：2019/4/6
 * des：
 */
data class WishInfo(
    val `data`: List<Data>
):BaseEntity()

data class Data(
    val a_num: String,
    val bg_music: String,
    val c_time: String,
    val comment_num: Int,
    val content: String,
    val describe: String,
    val help_num: Int,
    val help_user_pics: List<HelpUserPic>,
    val id: String,
    val imgs: String,
    val imgs_arr: List<ImgsArr>,
    val labels: String,
    val labels_arr: List<Any>,
    val latitude: String,
    val longitude: String,
    val place_id: String,
    val place_name: String,
    val send_name: String,
    val send_pic: String,
    val send_time_name: String,
    val state: String,
    val state_name: String,
    val target_age_max: String,
    val target_age_min: String,
    val target_place_id: String,
    val target_sex: String,
    val title: String,
    val type: String,
    val user_id: String,
    val v_num: String
)

data class HelpUserPic(
    val file_name: String,
    val file_size: String,
    val id: String,
    val photo_file: String,
    val photo_thumb_file: String,
    val photo_type: String,
    val upload_time: String,
    val user_id: String
)

data class ImgsArr(
    val file_name: String,
    val file_size: String,
    val id: String,
    val photo_file: String,
    val photo_thumb_file: String,
    val photo_type: String,
    val upload_time: String,
    val user_id: String
)