package com.vsoftcoders.ludogame.listener;

import android.view.View;

/**
 * Created by Vijay on 11/25/2017.
 */

public interface GameListener {

    public void OnGameResume(View view);
    public void OnGameRestart(View view);
    public void OnGamePause(View view);
    public void OnGameHome(View view);

}




