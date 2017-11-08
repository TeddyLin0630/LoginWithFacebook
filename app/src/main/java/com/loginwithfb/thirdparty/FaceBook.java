package com.loginwithfb.thirdparty;

import android.content.Intent;

import com.facebook.CallbackManager;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.loginwithfb.common.IThirdParty;
import com.loginwithfb.common.IThirdPartyCallback;
import com.loginwithfb.data.ThirdPartyProfile;

/**
 * Created by teddylin on 2017/11/3.
 */

public class FaceBook implements IThirdParty {
    CallbackManager callbackManager;
    ProfileTracker profileTracker;
    IThirdPartyCallback thirdPartyCallback;
    ThirdPartyProfile profile = new ThirdPartyProfile();

    @Override
    public void init(IThirdPartyCallback callback) {
        callbackManager = CallbackManager.Factory.create();
        thirdPartyCallback = callback;
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(
                    Profile oldProfile,
                    Profile currentProfile) {
                if (currentProfile != null) {
                    profile.setName(currentProfile.getName());
                    profile.setPicUrl(currentProfile.getProfilePictureUri(240, 240).toString());
                    profile.setThumbnailUrl(currentProfile.getProfilePictureUri(48, 48).toString());
                    thirdPartyCallback.onChange(profile);
                } else {
                    thirdPartyCallback.onReset(profile);
                }
            }
        };
    }

    public void returnCallbackManager(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
