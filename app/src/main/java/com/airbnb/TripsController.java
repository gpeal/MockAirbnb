package com.airbnb;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public class TripsController implements ViewController {
    private final Context mContext;

    public TripsController(Context context) {
        mContext = context;
    }

    @Override
    public ViewGroup initialize(ViewGroup container) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.recycler_view, container, false);
        view.setBackgroundColor(Color.BLUE);
        return view;
    }


}