package com.harrykbd.util.harryport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AssetAllocDetailListAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<AssetAllocStock> mData;

    public AssetAllocDetailListAdapter(Context context, ArrayList<AssetAllocStock> data){
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
        View view = mLayoutInflater.inflate(R.layout.asset_detail_item, null);

        TextView tvName = (TextView)view.findViewById(R.id.detail_fund_name);
        TextView tvCode = (TextView)view.findViewById(R.id.detail_fund_code);

        TextView tvCurPrice = (TextView)view.findViewById(R.id.asset_item_cur_price);
        TextView tvBuyPrice = (TextView)view.findViewById(R.id.asset_item_buy_price);
        TextView tvItemCount = (TextView)view.findViewById(R.id.asset_item_count);
        TextView tvItemProfit = (TextView)view.findViewById(R.id.asset_item_profit_ratio);

        tvName.setText(mData.get(position).getName());
        tvCode.setText(mData.get(position).getCode());
        tvCurPrice.setText(String.format("%10.1f", mData.get(position).getCurPrice()));
        tvBuyPrice.setText(String.format("%10.1f", mData.get(position).getBuyPrice()));
        tvItemCount.setText(String.format("%d", mData.get(position).getCnt()));
        tvItemProfit.setText(String.format("%3.1f", mData.get(position).getProfitRate()));
        float profitR = 0.0f;
        return view;
    }

}
