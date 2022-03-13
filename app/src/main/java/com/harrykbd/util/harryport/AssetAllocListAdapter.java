package com.harrykbd.util.harryport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AssetAllocListAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<AssetAllocStock> mData;

    public AssetAllocListAdapter(Context context, ArrayList<AssetAllocStock> data){
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
    public AssetAllocStock getItem(int position){
        return mData.get(position);
    }
    @Override
    public View getView(int position, View converView, ViewGroup parent){
        View view = mLayoutInflater.inflate(R.layout.asset_list_item, null);

        TextView tvName = (TextView)view.findViewById(R.id.asset_name);
        TextView tvIdealRatio = (TextView)view.findViewById(R.id.asset_ideal_ratio);
        TextView tvCurRatio = (TextView)view.findViewById(R.id.asset_cur_ratio);
        TextView tvAssetProfit = (TextView)view.findViewById(R.id.asset_profit);

        tvName.setText(mData.get(position).getName());
        tvIdealRatio.setText(String.format("%3.1f", mData.get(position).getIdealRatio()));
        tvCurRatio.setText(String.format("%3.1f", mData.get(position).getCurRatio()));
        tvAssetProfit.setText(String.format("%3.1f", mData.get(position).getProfitRate()));

        return view;
    }

}
