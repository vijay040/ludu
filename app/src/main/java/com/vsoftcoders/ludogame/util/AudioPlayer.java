package com.vsoftcoders.ludogame.util;

import android.content.Context;
import android.media.MediaPlayer;

import com.vsoftcoders.ludogame.R;

public class AudioPlayer {

    static MediaPlayer mBackground;
    static MediaPlayer mBackground2;
    static MediaPlayer mBackground3;
    static MediaPlayer mOver;
    static MediaPlayer mNegative;
    static MediaPlayer mSuffle;
    static MediaPlayer mBoop;
    static MediaPlayer mCoin;
    static MediaPlayer mErase;
    public static boolean isSoundEnabled = false;

    public AudioPlayer(Context aContext) {
      /*  mBackground = MediaPlayer.create(aContext, R.raw.bg);
        mBackground.setLooping(true);
        mBackground2 = MediaPlayer.create(aContext, R.raw.bg2);
        mBackground2.setLooping(true);
        mBackground3 = MediaPlayer.create(aContext, R.raw.vilen);
        mBackground3.setLooping(true);
        mOver = MediaPlayer.create(aContext, R.raw.dead);
        mNegative = MediaPlayer.create(aContext, R.raw.life_gone_sound);*/

        mSuffle = MediaPlayer.create(aContext, R.raw.boop3);
        mBoop = MediaPlayer.create(aContext, R.raw.boop1);
        mCoin = MediaPlayer.create(aContext, R.raw.coinsound);
        mErase = MediaPlayer.create(aContext, R.raw.erasesound);
        mBackground = MediaPlayer.create(aContext, R.raw.gameon);
    }

    public static void playBackground() {
        if (isSoundEnabled && !mBackground.isPlaying()) {
            try {
                mBackground.prepareAsync();
            } catch (Exception e) {
            }
            mBackground.start();
            mBackground.setLooping(true);
        }
    }

    public static void stopBackground() {
        if (mBackground.isPlaying())
            mBackground.stop();
    }

    public static void playBackground2() {
        if (isSoundEnabled)
            mBackground2.start();
    }

    public static void stopBackground2() {
        mBackground2.pause();
    }

    public static void playBackground3() {
        if (isSoundEnabled)
            mBackground3.start();
    }

    public static void stopBackground3() {
        mBackground3.pause();
    }

    public static void playNegative() {
        if (isSoundEnabled)
            mNegative.start();
    }

    public static void playGameOverSound() {
        if (isSoundEnabled)
            mOver.start();
    }


    public static void playSuffle() {
        if (isSoundEnabled)
            mSuffle.start();
    }

    public static void playBoop() {
        if (isSoundEnabled)
            mBoop.start();
    }

    public static void playCoin() {
        if (isSoundEnabled)
            mCoin.start();
    }

    public static void playErase() {
        if (isSoundEnabled)
            mErase.start();
    }


}
