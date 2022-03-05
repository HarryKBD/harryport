package com.harrykbd.util.harryport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MddViewAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<MddData> mData;

    public MddViewAdapter(Context context, ArrayList<MddData> data){
        mContext = context;
        mData = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount(){
        return mData.size();
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public MddData getItem(int position){
        return mData.get(position);
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent){
        View view = mLayoutInflater.inflate(R.layout.mdd_list_item, null);

        TextView code = (TextView)view.findViewById(R.id.code);
        TextView price = (TextView)view.findViewById(R.id.price);
        TextView priceDate = (TextView)view.findViewById(R.id.price_date);
        TextView mdd = (TextView)view.findViewById(R.id.mdd);
        TextView mddDate = (TextView)view.findViewById(R.id.mdd_date);
        TextView tmdd = (TextView)view.findViewById(R.id.tmdd);
        TextView tmddDate = (TextView)view.findViewById(R.id.tmdd_date);
        TextView peak = (TextView)view.findViewById(R.id.peak);
        TextView peakDate = (TextView)view.findViewById(R.id.peak_date);
        TextView maxMdd = (TextView)view.findViewById(R.id.mx_mdd);
        TextView maxMddDate = (TextView)view.findViewById(R.id.mx_mdd_date);


        code.setText(mData.get(position).getCode());
        price.setText(String.format("%10.1f", mData.get(position).getPrice()));
        mdd.setText(String.format("%10.1f", mData.get(position).getMdd()));
        tmdd.setText(String.format("%10.1f", mData.get(position).getTmdd()));
        peak.setText(String.format("%10.1f", mData.get(position).getPeak()));
        maxMdd.setText(String.format("%10.1f", mData.get(position).getMaxMdd()));

        priceDate.setText(String.format("%10s", mData.get(position).getPriceDate()));
        mddDate.setText(String.format("%10s", mData.get(position).getMddDate()));
        tmddDate.setText(String.format("%10s", mData.get(position).getTmddDate()));
        peakDate.setText(String.format("%10s", mData.get(position).getPeakDate()));
        maxMddDate.setText(String.format("%10s", mData.get(position).getMaxMddDate()));

        return view;
    }

}
