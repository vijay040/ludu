package com.vsoftcoders.ludogame.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;

import com.vsoftcoders.ludogame.R;
import com.vsoftcoders.ludogame.util.VsoftApp;


/**
 * Created by Vijay on 10/10/2017.
 */

public class GameObject {

    public Bitmap mBitmapCoin;
    public Bitmap mBitmapSmiley;
    public Rect mRect;
    public Rect mRectCol;
    public int mId = 1;
    public int mWidth;
    public boolean isSelected = false;
    public int mX;
    public int mY;
    String mPath = "game/";
    public boolean rePosition = false;
    public boolean isIntersected = false;
    public boolean isRemoved = false;
    public static int widthInPix = 15;
    public static int gapInPix = 4;
    public static int gap = (int) VsoftApp.Context().GetWidthFromPixelPercentageIntoDP(gapInPix);
    public static int speed = (int) VsoftApp.Context().GetWidthFromPixelPercentageIntoDP(5);
    public int x, y;
    Paint p;
    Paint pText;
    public static int start = 1;
    public static int last = 5;
    Context context;
    public boolean isCoin = false;
    public Rect mRectCoin;
    public String[] arrColor = new String[]{"#FF991F", "#46B621", "#5082FA", "#ED2E3E", "#CCFFCC", "#CD913E", "#CCD7FF", "#CCD794", "#AAF5B2",
            "#FFFFFF", "#87CEFA", "#FFFFFF", "#D2CD1E", "#FF7F50",
            "#DAD4D5", "#FFFFFF", "#E9967A", "#FFFFFF", "#9ACD32", "#CD853F", "#B0C4DE", "#FFFFFF", "#FF6347", "#BA55D3", "#F5DEB3", "#BC8F8F",
            "#6495ED", "#FFFFFF", "#3CFFB3", "#FFFFFF", "#BAB76B"
    };

    public GameObject(int aX, int aY, Context context) {
        this.context = context;
        mId = start + VsoftApp.Context().GetRandom(last);
        p = new Paint();
        p.setColor(Color.parseColor(arrColor[mId - 1]));

        pText = new Paint();
        pText.setColor(Color.BLACK);
        pText.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        pText.setTextAlign(Paint.Align.CENTER);
        int scaledSize = context.getResources().getDimensionPixelSize(R.dimen._25sdp);
        pText.setTextSize(scaledSize);

        mX = aX;
        mY = aY;
        x = (int) VsoftApp.Context().GetWidthFromPixelPercentageIntoDP(mX);
        y = (int) VsoftApp.Context().GetHeightFromPixelPercentageIntoDP(mY);
        mWidth = (int) VsoftApp.Context().GetWidthFromPixelPercentageIntoDP(widthInPix);
        mBitmapCoin = VsoftApp.Context().getBitmapFromAssets(mPath + "coin.png"); //mPath+mId+".png"
      //  mBitmapSmiley= VsoftApp.Context().getBitmapFromAssets(mPath + "smiley/"+mId+".png");
        mRect = new Rect(x, y, x + mWidth, y + mWidth);
        mRectCoin = new Rect(x, y - mWidth / 4, x + mWidth, (y - mWidth / 3) + mWidth / 3);
        mRectCol = new Rect(x, y, x + mWidth, y + mWidth + gap);
    }

    public void destroy() {
        if (mBitmapCoin != null && mBitmapCoin.isRecycled() == false)
            mBitmapCoin.recycle();
      /*  if (mBitmapSmiley != null && mBitmapSmiley.isRecycled() == false)
            mBitmapSmiley.recycle();*/
        mRect = null;
    }

    Matrix mMatrix = new Matrix();

    public void draw(Canvas aCanvas) {
       /* mRect = new Rect(x, y, x + mWidth, y + mWidth);
           aCanvas.setMatrix(mMatrix);*/
      /*  mRect = new Rect(x, y, x + mWidth, y + mWidth);
        mRectCol = new Rect(x, y, x + mWidth, y + mWidth + gap);
        mRectCoin = new Rect(x, y - mWidth / 4, x + mWidth, (y - mWidth / 3) + mWidth / 3);*/

        mRect.set(x, y, x + mWidth, y + mWidth);
        mRectCol.set(x, y, x + mWidth, y + mWidth + gap);
        mRectCoin.set(x+20, y - mWidth / 4, x + (mWidth-20), (y - mWidth / 3) + mWidth / 3);
        if (mBitmapCoin != null && isSelected == false) {
            if (isCoin)
                VsoftApp.DrawBitmap(aCanvas, mBitmapCoin, mRectCoin);

            //aCanvas.drawRect(mRect, p);
            aCanvas.drawOval(new RectF(mRect), p);
            aCanvas.drawText(mId + "", x + mWidth / 2, y + (mWidth / 3) + speed, pText);

           // VsoftApp.DrawBitmap(aCanvas, mBitmapSmiley, mRect);
        }

        if (isIntersected == false)
            y += speed;

    }

    public void changeBitmap(int aId) {
        mBitmapCoin = VsoftApp.Context().getBitmapFromAssets(mPath + aId + ".png");
    }

    public void setRePosition(int y) {
        this.y = y - (mWidth + gap);
    }

}