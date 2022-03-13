package com.harrykbd.util.harryport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class FundSummaryListAdaptor extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<AssetAllocFund> mData;

    public FundSummaryListAdaptor(Context context, ArrayList<AssetAllocFund> data){
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
    public AssetAllocFund getItem(int position){
        return mData.get(position);
    }
    @Override
    public View getView(int position, View converView, ViewGroup parent){
        View view = mLayoutInflater.inflate(R.layout.fund_status_item, null);

        TextView tvName = (TextView)view.findViewById(R.id.fund_status_item_name);
        TextView tvFundProfit = (TextView)view.findViewById(R.id.fund_status_item_profit_rate);
        TextView tvFundInvested = (TextView)view.findViewById(R.id.fund_status_item_invested);
        TextView tvFundPortion= (TextView)view.findViewById(R.id.fund_status_item_portion);

        tvName.setText(mData.get(position).getFundName());
        tvFundProfit.setText(String.format("%3.1f", mData.get(position).getProfitRate()));
        tvFundInvested.setText(String.format("%3.1f", mData.get(position).getTotalInvested()));
        //tvFundPortion.setText(String.format("%3.1f", mData.get(position).getCurRatio()));
        tvFundPortion.setText("TBD");

        return view;
    }

}
