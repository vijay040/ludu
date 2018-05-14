package com.vsoftcoders.ludogame.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vsoftcoders.ludogame.R;
import com.vsoftcoders.ludogame.model.User;

import java.util.ArrayList;

/**
 * Created by VIJAY on 4/26/2018.
 */

public class TopScorersAdaptor extends BaseAdapter {
    ArrayList<User> list;
    Context context;
    public TopScorersAdaptor(Context context, ArrayList<User> list)
    {
        this.context=context;
        this.list=list;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.inf_topscorers, null);
        }

        TextView name= (TextView) view.findViewById(R.id.txtName);
        TextView score= (TextView) view.findViewById(R.id.txtScore);
        TextView country= (TextView) view.findViewById(R.id.txtCountry);

        name.setText("Name- "+list.get(i).getName());
        score.setText("Score- "+list.get(i).getScore());
        country.setText("Country- "+list.get(i).getCountry());
        return view;
    }
}
