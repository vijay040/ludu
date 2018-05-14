package com.vsoftcoders.ludogame.util;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.vsoftcoders.ludogame.R;

import java.util.HashMap;

/**
 * Created by VIJAY on 4/12/2018.
 */

public class MySoundPool {

    public static final int boop = R.raw.boop1;
    public static final int boop2 = R.raw.boop3;
    public static final int coin = R.raw.coinsound;
    public static final int erase = R.raw.erasesound;
    private static SoundPool soundPool;
    private static HashMap soundPoolMap;

    /**
     * Populate the SoundPool
     */
    public static void initSounds(Context context) {
        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 100);
        soundPoolMap = new HashMap(3);
        soundPoolMap.put(boop, soundPool.load(context, R.raw.boop1, 1));
        soundPoolMap.put(boop2, soundPool.load(context, R.raw.boop3, 2));
        soundPoolMap.put(coin, soundPool.load(context, R.raw.coinsound, 3));
        soundPoolMap.put(erase, soundPool.load(context, R.raw.erasesound, 2));
    }

    /**
     * Play a given sound in the soundPool
     */
    public static void playSound(Context context, int soundID) {
        if (AudioPlayer.isSoundEnabled == false)
            return;

        if (soundPool == null || soundPoolMap == null) {
            initSounds(context);
        }
        float volume = 1.0f;// whatever in the range = 0.0 to 1.0
        // play sound with same right and left volume, with a priority of 1,
        // zero repeats (i.e play once), and a playback rate of 1f
        soundPool.play((int) soundPoolMap.get(soundID), volume, volume, 1, 0, 1f);
    }
}
