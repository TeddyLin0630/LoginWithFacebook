package com.loginwithfb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.facebook.login.widget.LoginButton;
import com.loginwithfb.common.IThirdPartyCallback;
import com.loginwithfb.data.ThirdPartyProfile;
import com.loginwithfb.thirdparty.FaceBook;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements IThirdPartyCallback {

    private final String MY_PREFS_NAME = "login_with_fb";

    FaceBook mFB;
    ThirdPartyProfile facebookProfile;
    LoginButton loginButton;
    TextView name;
    ImageView pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFB = new FaceBook();
        mFB.init(this);
        loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("user_status"));

        name = findViewById(R.id.name);
        pic = findViewById(R.id.pic);

        if (readFBContent()) {
            updateInfo();
        }
    }

    private void updateInfo() {
        name.setText(facebookProfile.getName());
        RequestBuilder<Drawable> requestBuilder = Glide.with(getBaseContext())
                .load(facebookProfile.getPicUrl());

        requestBuilder
                .thumbnail(Glide.with(getBaseContext()).load(facebookProfile.getThumbnailUrl()))
                .load(facebookProfile.getPicUrl())
                .into(pic);
    }

    private void writeFBContent() {
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("name", facebookProfile.getName());
        editor.putString("picUrl240", facebookProfile.getPicUrl());
        editor.putString("picUrl48", facebookProfile.getThumbnailUrl());
        editor.apply();
    }

    private boolean readFBContent() {
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        facebookProfile.setName(prefs.getString("name", null));
        facebookProfile.setPicUrl(prefs.getString("picUrl240", null));
        facebookProfile.setThumbnailUrl(prefs.getString("picUrl48", null));
        return facebookProfile.check();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mFB.returnCallbackManager(requestCode, resultCode, data);
    }

    @Override
    public void onChange(ThirdPartyProfile profile) {
        facebookProfile = profile;
        writeFBContent();
    }
}
