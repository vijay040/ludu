package com.vsoftcoders.ludogame.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vsoftcoders.ludogame.R;

public class PlayerSelect extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_select);
    }

    public void GotoGame(View view){

        Intent i = new Intent(PlayerSelect.this, Game.class);
        startActivity(i);

    }
}
