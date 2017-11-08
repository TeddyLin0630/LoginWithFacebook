package com.loginwithfb;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
    MainAction mainAction;
    FaceBook mFB;
    LoginButton loginButton;
    TextView name;
    ImageView pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainAction = MainAction.getInstance(this, this);

        mFB = new FaceBook();
        mFB.init(this);

        loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("user_status"));

        name = findViewById(R.id.name);
        pic = findViewById(R.id.pic);

        mainAction.readFBContent();
    }

    private void updateInfo(ThirdPartyProfile facebookProfile) {
        if (facebookProfile != null) {
            name.setText(facebookProfile.getName());
            name.setVisibility(View.VISIBLE);
            RequestBuilder<Drawable> requestBuilder = Glide.with(getBaseContext())
                    .load(facebookProfile.getPicUrl());
            pic.setVisibility(View.VISIBLE);
            requestBuilder
                    .thumbnail(Glide.with(getBaseContext()).load(facebookProfile.getThumbnailUrl()))
                    .load(facebookProfile.getPicUrl())
                    .into(pic);
        } else {
            name.setText(null);
            name.setVisibility(View.GONE);
            pic.setImageDrawable(null);
            pic.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mFB.returnCallbackManager(requestCode, resultCode, data);
    }

    @Override
    public void onChange(ThirdPartyProfile profile) {
        mainAction.writeFBContent(profile);
        updateInfo(profile);
    }

    @Override
    public void onUpdateInfo(ThirdPartyProfile profile) {
        updateInfo(profile);
    }

    @Override
    public void onReset(ThirdPartyProfile profile) {
        mainAction.deleteFBContent(profile);
        updateInfo(null);
    }
}