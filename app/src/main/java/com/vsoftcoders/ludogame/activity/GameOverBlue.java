package com.vsoftcoders.ludogame.activity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.vsoftcoders.ludogame.R;

public class GameOverBlue extends Activity {
    Button btnTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over_blue);
        btnTitle= (Button) findViewById(R.id.btnTitle);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate_anim);
        btnTitle.startAnimation(animation);

        MobileAds.initialize(this, getString(R.string.ADMOB_APP_ID));
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    public void over(View view){
        Intent in= new Intent(GameOverBlue.this, PlayerSelect.class);
        startActivity(in);
    }

}
