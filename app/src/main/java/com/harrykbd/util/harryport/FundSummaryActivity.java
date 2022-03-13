package com.harrykbd.util.harryport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FundSummaryActivity extends AppCompatActivity {
    private FundSummaryListAdaptor mFundSummaryAdaptor;
    private ArrayList<AssetAllocFund> mAssetAllocFundList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fund_summary);

        Intent i = getIntent();
        mAssetAllocFundList = (ArrayList<AssetAllocFund>)i.getSerializableExtra("fund_list");

        ListView listView = (ListView) findViewById(R.id.fund_list);
        final FundSummaryListAdaptor adapter = new FundSummaryListAdaptor(this, mAssetAllocFundList);
        listView.setAdapter(adapter);
        mFundSummaryAdaptor = adapter;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Toast.makeText(getApplicationContext(),
                        String.format("%s", mFundSummaryAdaptor.getItem(position).getFundName()),
                        Toast.LENGTH_SHORT).show();
                Intent i = new Intent(FundSummaryActivity.this, AssetAllocStatusActivity.class);
                i.putExtra("asset_fund", mFundSummaryAdaptor.getItem(position));
                startActivity(i);
            }
        });
    }

}

