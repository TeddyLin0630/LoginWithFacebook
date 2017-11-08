package com.loginwithfb.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by teddylin on 2017/11/3.
 */

@Entity (tableName = "profile")
public class ThirdPartyProfile {
    @PrimaryKey
    private int uid = 1;

    @ColumnInfo(name = "name")
    String name;

    @ColumnInfo(name = "picUrl")
    String picUrl;

    @ColumnInfo(name = "thumbnailUrl")
    String thumbnailUrl;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

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

    @Ignore
    public void reset() {
        name = null;
        picUrl = null;
        thumbnailUrl = null;
        uid = 0;
    }

    @Ignore
    public boolean check() {
        return name != null && picUrl != null && thumbnailUrl != null;
    }
}
