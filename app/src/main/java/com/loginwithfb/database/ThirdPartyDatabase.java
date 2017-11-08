package com.loginwithfb.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.loginwithfb.dao.ThirdPartyDAO;
import com.loginwithfb.data.ThirdPartyProfile;

/**
 * Created by teddylin on 2017/11/8.
 */

@Database(entities = {ThirdPartyProfile.class}, version = 1)
public abstract class ThirdPartyDatabase extends RoomDatabase {
    public abstract ThirdPartyDAO thirdPartyDAO();
}
