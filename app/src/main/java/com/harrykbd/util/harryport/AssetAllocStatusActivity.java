package com.harrykbd.util.harryport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class AssetAllocStatusActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private AssetAllocFundFragment fragmentAssetAllocFund;
    private FragmentTransaction transaction;
    private AssetAllocFund mAssetAllocFund;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_alloc_status);

        Intent i = getIntent();
        mAssetAllocFund = (AssetAllocFund)i.getSerializableExtra("asset_fund");

        fragmentManager = getSupportFragmentManager();

        fragmentAssetAllocFund = new AssetAllocFundFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("fund", mAssetAllocFund);
        fragmentAssetAllocFund.setArguments(bundle);

        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragmentAssetAllocFund).commitAllowingStateLoss();
    }

    public void clickHandler(View view)
    {
        transaction = fragmentManager.beginTransaction();

        switch(view.getId())
        {
            case R.id.btn_prev:
                //transaction.replace(R.id.frameLayout, fragmentA).commitAllowingStateLoss();
                break;
            case R.id.btn_next:
                //transaction.replace(R.id.frameLayout, fragmentB).commitAllowingStateLoss();
                break;
        }
    }
}