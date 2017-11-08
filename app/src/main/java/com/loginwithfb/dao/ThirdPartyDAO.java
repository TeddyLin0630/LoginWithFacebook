package com.loginwithfb.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.loginwithfb.data.ThirdPartyProfile;

import java.util.List;

/**
 * Created by teddylin on 2017/11/8.
 */
@Dao
public interface ThirdPartyDAO {

    @Query("SELECT * FROM profile")
    List<ThirdPartyProfile> getAll();

    @Insert
    void insertAll(ThirdPartyProfile... profile);

    @Delete
    void delete(ThirdPartyProfile user);
}
