package com.vsoftcoders.ludogame.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vsoftcoders.ludogame.R;
import com.vsoftcoders.ludogame.adaptor.TopScorersAdaptor;
import com.vsoftcoders.ludogame.model.User;
import com.vsoftcoders.ludogame.progress.Progress;
import com.vsoftcoders.ludogame.util.VsoftApp;

import java.util.ArrayList;
import java.util.NavigableSet;
import java.util.TreeSet;

/**
 * Created by VIJAY on 4/26/2018.
 */

public class TopScorersActivity extends Activity {
    ListView listScorers;
    ArrayList<User>list;
    Progress progress;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
      //  VsoftApp.Context().SetFullScreen(this);
      //  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topscorers);
        progress=new Progress(this);
        listScorers= (ListView) findViewById(R.id.listScorers);
        list=new ArrayList<>();

        getTopGobalScore();

        TopScorersAdaptor adaptor =new TopScorersAdaptor(TopScorersActivity.this,list);
        listScorers.setAdapter(adaptor);
        MobileAds.initialize(this, getString(R.string.ADMOB_APP_ID));
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    public static NavigableSet getSort (ArrayList list){
        TreeSet set =new TreeSet(list);
       NavigableSet<User> us= set.descendingSet();
        return us;
    }

    public void getTopGobalScore() {
        progress.show();
        final String android_id = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference reff = mDatabase.child("users");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot noteSnapshot : dataSnapshot.getChildren()) {
                    User note = noteSnapshot.getValue(User.class);
                   list.add(note);
                }
                progress.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progress.dismiss();
            }
        });


    }
}
