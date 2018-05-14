package com.vsoftcoders.ludogame.util;

import android.app.Activity;
import android.app.Application;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class VsoftApp extends Application {

    private static VsoftApp mContext;
    public MMitHttpHandling mHttpHanler = new MMitHttpHandling();
    public static Paint mPaint = null;
    public static AudioPlayer mPlayer;

    public class MMitHttpHandling {

        public String downloadDataFromServer(String aStrUrl) throws IOException {
            String data = "";
            InputStream iStream = null;
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL(aStrUrl);
                // Creating an http connection to communicate with url
                urlConnection = (HttpURLConnection) url.openConnection();

                // Connecting to url
                urlConnection.connect();

                // Reading data from url
                iStream = urlConnection.getInputStream();

                BufferedReader br = new BufferedReader(new InputStreamReader(
                        iStream));

                StringBuffer sb = new StringBuffer();

                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

                data = sb.toString();

                br.close();

            } catch (Exception e) {
                //Log.d("Exception while fetching data", e.toString());
            } finally {
                iStream.close();
                urlConnection.disconnect();
            }
            return data;
        }
    }

    public class MmitCommonFunctions {

    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        mContext = this;
        mPlayer = new AudioPlayer(mContext);
    }

    public static VsoftApp Context() {
        return mContext;
    }

    public static VsoftApp ContextMmit() {
        return mContext;
    }

    public static MMitHttpHandling ContextHttp() {
        return mContext.mHttpHanler;
    }

    public Bitmap getBitmapFromAssets(String fileName) {

        AssetManager assetManager = mContext.getAssets();

        InputStream istr = null;
        try {
            istr = assetManager.open(fileName);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(istr);
        return bitmap;
    }

    public int GetRandom(int aValue) {
        Random r = new Random();
        return r.nextInt(aValue);
    }

    public void SetFullScreen(Activity aActivity) {
        aActivity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        aActivity.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public Point GetScreenResolution() {
        Point p = new Point();
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        p.y = displayMetrics.heightPixels;
        p.x = displayMetrics.widthPixels;
        return p;
    }

    public static void DrawBitmap(Canvas aCanvas, Bitmap aBitmap, Rect aRect) {

        if (mPaint == null) {
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setFilterBitmap(true);
            mPaint.setDither(true);
        }
        aCanvas.drawBitmap(aBitmap, null, aRect, mPaint);
    }

    public float convertPixelsToDp(float aPixel) {
        float f = 0f;
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        final DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);

        if (metrics.density == .75)
            f = 120;
        else if (metrics.density == 1.33)
            f = 213;
        else if (metrics.density == 1.5)
            f = 240;
        else if (metrics.density == 2)
            f = 320;
        else if (metrics.density == 3)
            f = 480;
        else if (metrics.density == 3.5)
            f = 560;
        else if (metrics.density == 4)
            f = 640;
        else if (metrics.density == 4.3)
            f = 688;
        else
            f = 720;
        float dp = aPixel / (metrics.densityDpi / f);

        return dp;
    }

    public float GetWidthFromPixelPercentageIntoDP(float mWidthInPercentage) {
        float valueInPx = mWidthInPercentage * GetScreenResolution().x / 100;
        return convertPixelsToDp(valueInPx);
    }

    public float GetHeightFromPixelPercentageIntoDP(float mWidthInPercentage) {
        float valueInPx = mWidthInPercentage * GetScreenResolution().y / 100;
        return convertPixelsToDp(valueInPx);
    }

    public float GetWidthInDp() {
        return convertPixelsToDp(GetScreenResolution().x);

    }

    public float GetHeightInDp() {
        return convertPixelsToDp(GetScreenResolution().y);

    }

    public float GetWidthInDpFromPecentage(int aDpValue) {
        return aDpValue * convertPixelsToDp(GetScreenResolution().x) / 100;

    }

    public float GetHeightInDpFromPecentage(int aDpValue) {
        return aDpValue * convertPixelsToDp(GetScreenResolution().y) / 100;

    }

    public String GetTimeToString(int sec) {
        double hour = Math.floor(sec / (60 * 60));
        double divMin = sec % (60 * 60);
        double min = Math.floor(divMin / 60);
        double divSec = Math.ceil(divMin % 60);
        String.valueOf(hour);
        // return (hour < 10 ? "0" + hour.ToString() : hour.ToString()) + ":" +
        // (min < 10 ? "0" + min.ToString() : min.ToString()) + ":" + (divSec <
        // 10 ? "0" + divSec.ToString() : divSec.ToString());
        return (hour < 10 ? "0" + String.valueOf((int) hour) : String
                .valueOf((int) hour))
                + ":"
                + ((int) min < 10 ? "0" + String.valueOf((int) min) : String
                .valueOf((int) min))
                + ":"
                + (divSec < 10 ? "0" + String.valueOf((int) divSec) : String
                .valueOf((int) divSec));
        // return (hour < 10 ? "0" + String.valueOf((int)hour) :
        // String.valueOf((int)hour)) + ":" + (min < 10 ? "0" +
        // String.valueOf((int)min) : String.valueOf((int)min));
    }

}
