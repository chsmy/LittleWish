package com.chs.core.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.text.TextUtils;

import com.chs.core.Constant;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by ysq on 2015/10/19.
 */
public class BitCompressUtil {

    // 根据路径获得图片并压缩，返回bitmap用于显示
    public static Bitmap getSmallBitmap(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        //手机系统小于5.0的基本上配置比较差，进一步降低图片的质量
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            options.inDither = true;
        }
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, 480, 800);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(filePath, options);
    }

    //计算图片的缩放值
    public static int calculateInSampleSize(BitmapFactory.Options options,int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height/ (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }


    public static void compressBmpToFile(Bitmap bmp,File file){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int options = 80;//个人喜欢从80开始,
        bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
        while (baos.toByteArray().length / 1024 > 100) {
            int s = baos.toByteArray().length;

            baos.reset();
            bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
            options -= 10;
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 存入sd卡
    public static void storeInSD(Bitmap bitmap) {
        File file = new File(Constant.BXT_IMAGE_COMPRESS);
        if (!file.exists()) {
            file.mkdir();
        }
        File imageFile = new File(file, "compressPic" + ".jpg");
        try {
            imageFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);//30 是压缩率，表示压缩70%; 如果不压缩是100，表示压缩率为0
            fos.flush();
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    //下面的四个方法是 我家云  收费模块带来的
//    public static ArrayList<ImageItem> getImageUrlItemList(String pathList) {
//        ArrayList<ImageItem> imgList = new ArrayList<ImageItem>();
//        if(pathList == null){
//            return imgList;
//        }
//        String[] arrPath = pathList.split(",");
//
//
//        if (arrPath != null && arrPath.length > 0 && !arrPath[0].equals("")) {
//            for (String img : arrPath) {
//                if (!img.equals("")) {
//                    ImageItem item = new ImageItem();
//                    item.urlPath = img;
//                    imgList.add(item);
//                }
//            }
//
//        }
//
//        return imgList;
//    }
    public static String getBase64TypeByFileName(String name) {
        int lastIndexOf = name.lastIndexOf(".");
        String sub = name.substring(lastIndexOf + 1, name.length()).toLowerCase();
        if (sub.equals("jpg") || sub.equals("jpeg")) {
            return "jpg";
        }
        if (sub.equals("png")) {
            return "png";
        }
        if (sub.equals("gif")) {
            return "gif";
        }
        return "jpg";
    }
    /**
     * 将图片转换成Base64编码的字符串
     *
     * @param path
     * @return base64编码的字符串
     */
    public static String imageToBase64(String path) {
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        InputStream is = null;
        byte[] data = null;
        String result = null;
        try {
            is = new FileInputStream(path);
            // 创建一个字符流大小的数组。
            data = new byte[is.available()];
            // 写入数组
            is.read(data);
            // 用默认的编码格式进行编码
            result = android.util.Base64.encodeToString(data, android.util.Base64.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return result;
    }

    /**
     * base64编码字符集转化成图片文件。
     *
     * @param base64Str
     * @param path
     *            文件存储路径
     * @return 是否成功
     */
    public static boolean base64ToFile(String base64Str, String path) {
        byte[] data = android.util.Base64.decode(base64Str, android.util.Base64.DEFAULT);
        for (int i = 0; i < data.length; i++) {
            if (data[i] < 0) {
                // 调整异常数据
                data[i] += 256;
            }
        }
        OutputStream os = null;
        try {
            os = new FileOutputStream(path);
            os.write(data);
            os.flush();
            os.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }
}
