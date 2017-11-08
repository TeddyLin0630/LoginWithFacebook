package com.loginwithfb.common;

import com.loginwithfb.data.ThirdPartyProfile;

/**
 * Created by teddylin on 2017/11/3.
 */

public interface IThirdPartyCallback {
    void onReset(ThirdPartyProfile profile);
    void onChange(ThirdPartyProfile profile);
    void onUpdateInfo(ThirdPartyProfile profile);
}
