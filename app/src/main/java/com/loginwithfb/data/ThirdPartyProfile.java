package com.loginwithfb.data;

/**
 * Created by teddylin on 2017/11/3.
 */

public class ThirdPartyProfile {
    String name;
    String picUrl;
    String thumbnailUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public void reset() {
        name = null;
        picUrl = null;
        thumbnailUrl = null;
    }

    public boolean check() {
        return name != null && picUrl != null && thumbnailUrl != null;
    }
}
