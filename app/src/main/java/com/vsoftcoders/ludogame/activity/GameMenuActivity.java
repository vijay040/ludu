package com.vsoftcoders.ludogame.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vsoftcoders.ludogame.R;
import com.vsoftcoders.ludogame.adaptor.GameListAdaptor;
import com.vsoftcoders.ludogame.model.User;
import com.vsoftcoders.ludogame.progress.Progress;
import com.vsoftcoders.ludogame.util.AudioPlayer;
import com.vsoftcoders.ludogame.util.Shprefrences;
import com.vsoftcoders.ludogame.util.VsoftApp;
import com.vsoftcoders.ludogame.model.Game;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Vijay on 10/10/2017.
 */

public class GameMenuActivity extends Activity implements View.OnClickListener {

    Button btnTitle, btnSound, btnHelp, btnAbout, btnRate;
    RelativeLayout activity_menu;
    Shprefrences sh;
    TextView txtTopScore;
    Progress progress;
    ArrayList<com.vsoftcoders.ludogame.model.Game> gameList;
    ImageView imgGift;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        VsoftApp.Context().SetFullScreen(this);
        // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        progress = new Progress(this);
        activity_menu = (RelativeLayout) findViewById(R.id.activity_menu);
        btnSound = (Button) findViewById(R.id.btnSound);
        btnTitle = (Button) findViewById(R.id.btnTitle);
        btnHelp = (Button) findViewById(R.id.btnHelp);
        btnAbout = (Button) findViewById(R.id.butAbout);
        btnRate = (Button) findViewById(R.id.btnRate);
        btnAbout.setOnClickListener(this);
        btnHelp.setOnClickListener(this);
        btnSound.setOnClickListener(this);
        btnRate.setOnClickListener(this);
        txtTopScore = (TextView) findViewById(R.id.txtTopScore);
        imgGift = (ImageView) findViewById(R.id.imgGift);
        sh = new Shprefrences(this);
        AudioPlayer.isSoundEnabled = sh.getBoolean("ISENABLED", true);
        if (AudioPlayer.isSoundEnabled)
            AudioPlayer.playBackground();
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate_anim);
        btnTitle.startAnimation(animation);
        //getTopGobalScore();
        getAppsList();
        MobileAds.initialize(this, getString(R.string.ADMOB_APP_ID));
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


    }

    private boolean isNetworkConnected() {
        if (this != null) {
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            return cm.getActiveNetworkInfo() != null;
        }
        return false;
    }

    public View startGame(View v) {
        Intent i = new Intent(GameMenuActivity.this, com.vsoftcoders.ludogame.activity.Game.class);
        i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        return v;
    }

    public View topScorers(View v) {
        Intent i = new Intent(GameMenuActivity.this, TopScorersActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        return v;
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id) {
            case R.id.btnSound:
                if (AudioPlayer.isSoundEnabled) {
                    AudioPlayer.isSoundEnabled = false;
                    sh.setBoolean("ISENABLED", false);
                    btnSound.setBackground(getResources().getDrawable(R.drawable.circle_drawable_sound_off));
                    AudioPlayer.stopBackground();
                } else {
                    AudioPlayer.isSoundEnabled = true;
                    sh.setBoolean("ISENABLED", true);
                    btnSound.setBackground(getResources().getDrawable(R.drawable.circle_drawble_sound_on));
                    AudioPlayer.playBackground();
                }

                break;
            case R.id.btnHelp:
                startActivity(new Intent(GameMenuActivity.this, HelpActivity.class));
                break;

            case R.id.butAbout:
                startActivity(new Intent(GameMenuActivity.this, AboutActivity.class));
                break;

            case R.id.btnRate:
                rateApp();
                break;
        }
    }

    @Override
    protected void onResume() {
        if (AudioPlayer.isSoundEnabled)
            btnSound.setBackground(getResources().getDrawable(R.drawable.circle_drawble_sound_on));
        else
            btnSound.setBackground(getResources().getDrawable(R.drawable.circle_drawable_sound_off));
        int score = sh.getInt("TOPSCORE", 0);
        txtTopScore.setText("Best: " + score);
        super.onResume();
    }

    public void getTopGobalScore() {
        if (isNetworkConnected()) {
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
                        if (android_id.equals(note.getId())) {
                            int score = sh.getInt("TOPSCORE", 0);
                            int cscore = Integer.parseInt(note.getScore());
                            if (score > cscore)
                                updateScore(note, score);
                            else {
                                sh.setInt("TOPSCORE", Integer.parseInt(note.getScore()));
                                score = sh.getInt("TOPSCORE", 0);
                                txtTopScore.setText("Best: " + score);
                            }

                            sh.setUser("PLAYER", note);
                            sh.setBoolean("ISREGISTERED", true);
                        }

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

    public void updateScore(User user, int score) {
        user.setScore(score + "");
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("users").child("USER" + user.getId()).setValue(user);
        sh.setUser("PLAYER", user);

        sh.setInt("TOPSCORE", Integer.parseInt(user.getScore()));
        score = sh.getInt("TOPSCORE", 0);
        txtTopScore.setText("Best: " + score);
    }

    public void rateApp() {
        Uri uri = Uri.parse("market://details?id=" + getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
        }
    }


    AlertDialog alertDialog;
    ListView gameListView;

    public void openGameList(View view) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(GameMenuActivity.this);
        // ...Irrelevant code for customizing the buttons and title
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.game_list, null);
        gameListView = (ListView) dialogView.findViewById(R.id.listGames);
        // Button btnUpgrade = (Button) dialogView.findViewById(R.id.btnUpgrade);
        dialogBuilder.setView(dialogView);
        alertDialog = dialogBuilder.create();
        //alertDialog.setCancelable(false);
        alertDialog.show();
        GameListAdaptor adaptor = new GameListAdaptor(GameMenuActivity.this, gameList);
        gameListView.setAdapter(adaptor);
    }


    public ArrayList<Game> getAppsList() {
        gameList = new ArrayList<>();
        if (isNetworkConnected()) {
            progress.show();
              DatabaseReference mDatabase;
            mDatabase = FirebaseDatabase.getInstance().getReference();
            DatabaseReference reff = mDatabase.child("apps");
            Log.e("reff","************"+reff);
            reff.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Log.e("dataSnapshot","************"+dataSnapshot);
                    for (DataSnapshot noteSnapshot : dataSnapshot.getChildren()) {
                        Game note = noteSnapshot.getValue(Game.class);
                        if (!note.getName().equalsIgnoreCase(getString(R.string.app_name)))
                            gameList.add(note);
                        Log.e("Name", "******************" + note.getName());
                    }
                    if (gameList.size() > 0)
                        imgGift.setVisibility(View.VISIBLE);

                    progress.dismiss();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    progress.dismiss();
                }
            });
        }
        return gameList;

    }

    @Override
    public void onBackPressed() {
        cancel();
    }

    private void cancel() {

        new SweetAlertDialog(GameMenuActivity.this, SweetAlertDialog.WARNING_TYPE).setTitleText("Exit?").
                setContentText("Are you sure to Exit!").
                setConfirmText("Yes").
                setCancelText("Cancel").
                setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        AudioPlayer.stopBackground();
                        finish();
                    }
                }).
                show();
    }

}
