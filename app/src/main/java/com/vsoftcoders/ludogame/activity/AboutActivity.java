package com.vsoftcoders.ludogame.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.vsoftcoders.ludogame.R;
import com.vsoftcoders.ludogame.util.VsoftApp;

/**
 * Created by VIJAY on 4/11/2018.
 */

public class AboutActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
       // VsoftApp.Context().SetFullScreen(this);
       // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ImageView img= (ImageView) findViewById(R.id.logo);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate_anim);
        img.startAnimation(animation);
    }
}
