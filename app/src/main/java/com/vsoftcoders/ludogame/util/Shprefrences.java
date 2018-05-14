package com.vsoftcoders.ludogame.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.vsoftcoders.ludogame.model.User;

/**
 * Created by aphroecs on 8/23/2016.
 */
public class Shprefrences {

    public static final String PREFERENCES = "LUDU";
    Context context;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    public void clearData()
    {
        editor.remove("PREFERENCES");
        editor.clear();
        editor.commit();
        Log.e("Clearing**","Data is cleared***************************");
    }

    public Shprefrences(Context context) {
        this.context = context;
        sharedpreferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
    }

    public void setString(String key, String val) {
        editor.putString(key, val);
        editor.commit();
    }

    public void setBoolean(String key, boolean val) {
        editor.putBoolean(key, val);
        editor.commit();
    }

    public void setInt(String key, int val) {
        editor.putInt(key, val);
        editor.commit();
    }


    public String getString(String key, String val) {
        return sharedpreferences.getString(key,val).toString();
    }

    public boolean getBoolean(String key, boolean val) {
        return sharedpreferences.getBoolean(key,val);
    }


    public int getInt(String key, int val) {
        return sharedpreferences.getInt(key,val);
    }


    public void setUser(String key, User obj)
    {
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        editor.putString(key, json);
        editor.commit();
    }

    public User getUser(String key)
    {
        Gson gson = new Gson();
        String json = sharedpreferences.getString(key, "");
        User ob= gson.fromJson(json, User.class);
        return ob;
    }

}
