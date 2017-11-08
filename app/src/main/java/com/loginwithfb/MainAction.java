package com.loginwithfb;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import com.loginwithfb.common.IThirdPartyCallback;
import com.loginwithfb.data.ThirdPartyProfile;
import com.loginwithfb.database.ThirdPartyDatabase;

import java.util.List;

/**
 * Created by teddylin on 2017/11/8.
 */

public class MainAction {
    private static MainAction instance = null;

    ThirdPartyDatabase mDatabase;
    IThirdPartyCallback mCallback;

    public static MainAction getInstance(Context context, IThirdPartyCallback callback) {
        if(instance == null) {
            instance = new MainAction(context, callback);
        }
        return instance;
    }

    public MainAction (Context context, IThirdPartyCallback callback) {
        mCallback = callback;
        mDatabase = Room.databaseBuilder(
                context.getApplicationContext(),
                ThirdPartyDatabase.class,
                "Thirdparty.db")
                .build();
    }

    public void writeFBContent(ThirdPartyProfile facebookProfile) {
        new AsyncTask<ThirdPartyProfile, Integer, Boolean>() {
            @Override
            protected Boolean doInBackground(ThirdPartyProfile... thirdPartyProfiles) {
                mDatabase.thirdPartyDAO().insertAll(thirdPartyProfiles[0]);
                return true;
            }
        }.execute(facebookProfile);
    }

    public void readFBContent() {
        new AsyncTask<Void, Integer, ThirdPartyProfile>() {
            @Override
            protected ThirdPartyProfile doInBackground(Void... voids) {
                List<ThirdPartyProfile> profiles = mDatabase.thirdPartyDAO().getAll();
                return profiles!= null && profiles.size() > 0 ? profiles.get(0) : null;
            }

            @Override
            protected void onPostExecute(ThirdPartyProfile profile) {
                mCallback.onUpdateInfo(profile);
            }
        }.execute();
    }

    public void deleteFBContent(ThirdPartyProfile facebookProfile) {
        new AsyncTask<ThirdPartyProfile, Integer, Boolean>() {
            @Override
            protected Boolean doInBackground(ThirdPartyProfile... thirdPartyProfiles) {
                mDatabase.thirdPartyDAO().delete(thirdPartyProfiles[0]);
                return true;
            }
        }.execute(facebookProfile);
    }
}
