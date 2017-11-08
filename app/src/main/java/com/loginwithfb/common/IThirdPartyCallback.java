package com.loginwithfb.common;

import com.loginwithfb.data.ThirdPartyProfile;

/**
 * Created by teddylin on 2017/11/3.
 */

public interface IThirdPartyCallback {
    void onChange(ThirdPartyProfile profile);
}
