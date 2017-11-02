package com.loginwithfb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.facebook.CallbackManager;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

public class MainActivity extends FragmentActivity {
    private final String MY_PREFS_NAME = "login_with_fb";
    CallbackManager callbackManager;
    LoginButton loginButton;
    ProfileTracker profileTracker;
    TextView name;
    ImageView pic;
    String nameStr;
    String picUrl240;
    String picUrl48;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callbackManager = CallbackManager.Factory.create();
        loginButton = findViewById(R.id.login_button);
        name = findViewById(R.id.name);
        pic = findViewById(R.id.pic);
        loginButton.setReadPermissions(Arrays.asList("user_status"));
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(
                    Profile oldProfile,
                    Profile currentProfile) {
                if (currentProfile != null) {
                    nameStr = currentProfile.getName();
                    picUrl240 = currentProfile.getProfilePictureUri(240, 240).toString();
                    picUrl48 = currentProfile.getProfilePictureUri(48, 48).toString();
                } else {
                    nameStr = null;
                    picUrl48 = null;
                    picUrl240 = null;
                }
                updateInfo();
                writeContent();
            }
        };

        if (readContent()) {
            updateInfo();
        }
    }

    private void updateInfo() {
        name.setText(nameStr);
        RequestBuilder<Drawable> requestBuilder = Glide.with(getBaseContext())
                .load(picUrl240);

        requestBuilder
                .thumbnail(Glide.with(getBaseContext()).load(picUrl48))
                .load(picUrl240)
                .into(pic);
    }

    private void writeContent() {
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("name", nameStr);
        editor.putString("picUrl240", picUrl240);
        editor.putString("picUrl48", picUrl48);
        editor.apply();
    }

    private boolean readContent() {
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        nameStr = prefs.getString("name", null);//"No name defined" is the default value.
        picUrl240 = prefs.getString("picUrl240", null); //0 is the default value.
        picUrl48 = prefs.getString("picUrl48", null); //0 is the default value.
        return nameStr != null && picUrl240 != null && picUrl48 != null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
