package com.vsoftcoders.ludogame.adaptor;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vsoftcoders.ludogame.R;
import com.vsoftcoders.ludogame.model.Game;

import java.util.ArrayList;

/**
 * Created by VIJAY on 4/26/2018.
 */

public class GameListAdaptor extends BaseAdapter {
    ArrayList<Game> list;
    Context context;

    public GameListAdaptor(Context context, ArrayList<Game> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.inf_gamelist, null);
        }

        TextView name = (TextView) view.findViewById(R.id.txtName);
        TextView txtDesc = (TextView) view.findViewById(R.id.txtDesc);
        TextView country = (TextView) view.findViewById(R.id.txtCountry);
        ImageView img = (ImageView) view.findViewById(R.id.img);
        RelativeLayout lay = (RelativeLayout) view.findViewById(R.id.lay);
        name.setText("" + list.get(i).getName());
        txtDesc.setText("" + list.get(i).getDesc());

        Picasso.with(context).load(list.get(i).getImage()).into(img);

        lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(list.get(i).getUrl())));
            }
        });

        return view;
    }
}
