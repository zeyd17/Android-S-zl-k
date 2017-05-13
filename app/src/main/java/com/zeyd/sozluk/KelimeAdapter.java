package com.zeyd.sozluk;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Zeyd on 23.5.2016.
 */
public class KelimeAdapter extends BaseAdapter {
    ArrayList<Kelime> kelimeArrayList;
    LayoutInflater layoutInflater;
    public KelimeAdapter(Activity activity,ArrayList<Kelime> kelimeList)
    {
        this.kelimeArrayList =kelimeList;
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return kelimeArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return kelimeArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v =layoutInflater.inflate(R.layout.satir,null);
        TextView tr = (TextView) v.findViewById(R.id.tr);
        TextView ing = (TextView) v.findViewById(R.id.ing);
        tr.setText(kelimeArrayList.get(position).getTr());
        ing.setText(kelimeArrayList.get(position).getIng());
        return v;
    }
}
