package com.example.cc.notedemo.Control;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Cc on 2017/1/16.
 */

public class Myadapter extends ArrayAdapter {

    private TextView textView;

    public Myadapter(Context context, int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        return super.getView(position, convertView, parent);

    }

}
