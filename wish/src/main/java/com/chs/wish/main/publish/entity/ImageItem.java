package com.chs.wish.main.publish.entity;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.net.URL;

/**
 * 作者：83734
 * 时间：2018/11/24
 * 描述：图片
 */
public class ImageItem implements Parcelable {
    private String pathUrl;
    private Uri picUri;
    private URL thumbUrl;
    private boolean isVideo;
    private Uri videoUri;

    private String path;

    public ImageItem(URL thumbUrl, String pathUrl, boolean isVideo) {
        this.thumbUrl = thumbUrl;
        this.pathUrl = pathUrl;
        this.isVideo = isVideo;
    }

    public ImageItem(Uri path) {
        this.picUri = path;
    }

    public ImageItem(Uri picUri, boolean isVideo, String path) {
        this.picUri = picUri;
        this.isVideo = isVideo;
        this.path = path;
    }

    public ImageItem(Uri picUri, boolean isVideo, Uri videoUri, String path) {
        this.picUri = picUri;
        this.isVideo = isVideo;
        this.videoUri = videoUri;
        this.path = path;
    }

    protected ImageItem(Parcel in) {
        pathUrl =  in.readString();
        thumbUrl = (URL) in.readSerializable();
        picUri = in.readParcelable(Uri.class.getClassLoader());
        isVideo = in.readByte() != 0;
        videoUri = in.readParcelable(Uri.class.getClassLoader());
        path = in.readString();
    }

    public static final Creator<ImageItem> CREATOR = new Creator<ImageItem>() {
        @Override
        public ImageItem createFromParcel(Parcel in) {
            return new ImageItem(in);
        }

        @Override
        public ImageItem[] newArray(int size) {
            return new ImageItem[size];
        }
    };

    public Uri getPicUri() {
        return picUri;
    }

    public void setPicUri(Uri picUri) {
        this.picUri = picUri;
    }

    public boolean isVideo() {
        return isVideo;
    }

    public void setVideo(boolean video) {
        isVideo = video;
    }

    public Uri getVideoUri() {
        return videoUri;
    }

    public void setVideoUri(Uri videoUri) {
        this.videoUri = videoUri;
    }

    public String getPathUrl() {
        return pathUrl;
    }

    public void setPathUrl(String pathUrl) {
        this.pathUrl = pathUrl;
    }

    public URL getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(URL thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(pathUrl);
        dest.writeSerializable(thumbUrl);
        dest.writeParcelable(picUri, 0);
        dest.writeByte((byte)(isVideo ?1:0));
        dest.writeParcelable(videoUri, 0);
        dest.writeString(path);
    }
}
