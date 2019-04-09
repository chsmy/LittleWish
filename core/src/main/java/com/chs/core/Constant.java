package com.chs.core;

import android.os.Environment;

import java.io.File;

/**
 * @author chs
 * date：2019-04-09 10:57
 * des：
 */
public class Constant {
    //缓存的文件夹
    public static final String BXT_IMAGE_COMPRESS =
            Environment.getExternalStorageDirectory().getPath() + File.separator + "JCamera"+ File.separator+"cache";
}
