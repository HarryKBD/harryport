package com.harrykbd.util.harryport;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AssetAllocFundFragment extends Fragment {
    private AssetAllocFund mFund;
    private AssetAllocListAdapter mAssetAllocListSimpleAdapter;
    private AssetAllocDetailListAdapter mAssetAllocListDetailAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_asset_alloc_fund, container, false);
        if(getArguments() != null){
            mFund = (AssetAllocFund) getArguments().getSerializable("fund");
        }
        ArrayList<AssetAllocStock> assetStocks = mFund.getStockList();
        if(assetStocks == null){
            assetStocks = new ArrayList<>();
        }

        TextView tvTitle = (TextView)v.findViewById(R.id.fund_name);
        tvTitle.setText(mFund.getFundName());

        TextView tvInvested = (TextView)v.findViewById(R.id.invested_val);
        TextView tvCurrentVal = (TextView)v.findViewById(R.id.current_val);
        TextView tvProfit = (TextView)v.findViewById(R.id.profit_ratio);

        tvInvested.setText(String.format("%10.1f", mFund.getTotalInvested()));
        tvCurrentVal.setText(String.format("%10.1f", mFund.getCurValue()));
        tvProfit.setText(String.format("%10.1f", mFund.getProfitRate()));

        ListView listViewSimple = (ListView) v.findViewById(R.id.asset_list);
        final AssetAllocListAdapter adapterSimple = new AssetAllocListAdapter(getContext(), assetStocks);
        listViewSimple.setAdapter(adapterSimple);
        mAssetAllocListSimpleAdapter = adapterSimple;

        ListView listViewDetail = (ListView) v.findViewById(R.id.asset_detail_list);
        final AssetAllocDetailListAdapter adapterDetail = new AssetAllocDetailListAdapter(getContext(), assetStocks);
        listViewDetail.setAdapter(adapterDetail);
        mAssetAllocListDetailAdapter = adapterDetail;

        // Inflate the layout for this fragment
        return v;
    }
}