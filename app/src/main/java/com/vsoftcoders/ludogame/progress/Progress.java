package com.vsoftcoders.ludogame.progress;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.vsoftcoders.ludogame.R;

/**
 * Created by Vijay on 1/19/2018.
 */

public class Progress {
    Dialog dialog;

    public Progress(Context context) {

        dialog = new Dialog(context, android.R.style.Theme_Black);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.requestWindowFeature(Window.FEATURE_PROGRESS);
        dialog.setContentView(R.layout.progress_lay);
        dialog.getWindow().setBackgroundDrawableResource(
                R.color.transparent);
        dialog.setCancelable(false);

        ImageView img= (ImageView) dialog.findViewById(R.id.img);
        img.startAnimation( AnimationUtils.loadAnimation(context, R.anim.rotate_animation) );
    }

    public void show() {
        dialog.show();
    }

    public void dismiss() {
        dialog.dismiss();
    }

    public void showTitle(boolean b) {
        TextView t = (TextView) dialog.findViewById(R.id.textView6);
        if (b)
            t.setVisibility(View.VISIBLE);
        else
            t.setVisibility(View.GONE);
    }

    public void showTitle(String b) {
        TextView t = (TextView) dialog.findViewById(R.id.textView6);
        t.setText(b);
    }

    public void setCancelable(boolean b)
    {
        dialog.setCancelable(b);
    }

}
