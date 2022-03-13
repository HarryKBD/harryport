package com.harrykbd.util.harryport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.android.volley.toolbox.Volley;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<MddData> mMddDataList;
    private MddViewAdapter mMddAdapter;
    private ArrayList<AssetAllocFund> mAssetAllocFundList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMddData();
        ListView listView = (ListView) findViewById(R.id.mdd_list);
        final MddViewAdapter adapter = new MddViewAdapter(this, mMddDataList);
        listView.setAdapter(adapter);
        mMddAdapter = adapter;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Toast.makeText(getApplicationContext(),
                        String.format("%10.1f", adapter.getItem(position).getPrice()),
                        Toast.LENGTH_SHORT).show();
            }
        });

        Button buttonDataGet = (Button) findViewById(R.id.data_get);
        buttonDataGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMddDataList.clear();
                Toast.makeText(getApplicationContext(),
                        "Refreshing the list", Toast.LENGTH_SHORT).show();
                requestServerData();
            }
        });

        Button buttonShowFund = (Button) findViewById(R.id.show_asset_funds);
        buttonShowFund.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mMddDataList.clear();
                Toast.makeText(getApplicationContext(),
                        "Showing asset alloc funds", Toast.LENGTH_SHORT).show();
                //Intent i = new Intent(MainActivity.this, AssetAllocStatusActivity.class);
                Intent i = new Intent(MainActivity.this, FundSummaryActivity.class);
                i.putExtra("fund_list", mAssetAllocFundList);
                startActivity(i);
            }
        });
    }

    public void requestServerData(){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        //String url ="http://192.168.0.44:2786/geek9?type=carousell";
        //String url ="http://harrykbdq.iptime.org:2786/geek9?type=carousell";
        String url ="http://114.203.232.5:2786/geek9?type=carousell";
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Toast.makeText(getApplicationContext(),
                                "Got data from Server", Toast.LENGTH_SHORT).show();
                        parseServerResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void parseServerResponse(String resp){
//        String tmp = "{\"mdd_stocks\":[\"{\"code\":\"TQQQ\",\"update\":\"2022-02-18\",\"price\":\"50.40\",\"cur_down\":\"43.12\",\"this_max_mdd_date\":\"2022-02-18\",\"this_max_mdd\":\"43.12\"}\",\"{\"code\":\"QLD\",\"update\":\"2022-02-18\",\"price\":\"64.70\",\"cur_down\":\"29.98\",\"this_max_mdd_date\":\"2022-02-18\",\"this_max_mdd\":\"29.98\"}\",\"{\"code\":\"QQQ\",\"update\":\"2022-02-18\",\"price\":\"341.50\",\"cur_down\":\"15.47\",\"this_max_mdd_date\":\"2022-01-27\",\"this_max_mdd\":\"15.57\"}\",\"{\"code\":\"SPY\",\"update\":\"2022-02-18\",\"price\":\"434.20\",\"cur_down\":\"9.11\",\"this_max_mdd_date\":\"2022-01-27\",\"this_max_mdd\":\"9.73\"}\",\"{\"code\":\"SOXL\",\"update\":\"2022-02-18\",\"price\":\"39.60\",\"cur_down\":\"45.75\",\"this_max_mdd_date\":\"2022-01-27\",\"this_max_mdd\":\"50.14\"}\",\"{\"code\":\"UPRO\",\"update\":\"2022-02-18\",\"price\":\"57.20\",\"cur_down\":\"26.29\",\"this_max_mdd_date\":\"2022-01-27\",\"this_max_mdd\":\"26.93\"}\"],\"laa\":{\"labor\":\"True\",\"Spy\":\"False\"}}"

        try{
            StringBuffer strConverted = new StringBuffer();
            for(int i=0; i < resp.length(); i++){
                if(i == 0 || i == resp.length() -1){
                    continue;
                }
                char c = resp.charAt(i);
                if(c != '\\'){
                    strConverted.append(c);
               }
            }

            JSONObject jsonObject = new JSONObject(strConverted.toString());
            JSONArray mddItems = jsonObject.getJSONArray("mdd_stocks");

            mMddDataList.clear();
            for (int i=0; i < mddItems.length(); i++){
                JSONObject item = mddItems.getJSONObject(i);
                float dd[] = new float[5];
                String ddDate[] = new String[5];
                String code = item.getString("code");
                dd[0] = new Float(item.getString("price"));
                dd[1] = new Float(item.getString("cur_down"));
                dd[2] = new Float(item.getString("this_max_mdd"));
                dd[3] = new Float(item.getString("all_max_mdd"));;
                dd[4] = new Float(item.getString("last_peak"));;

                ddDate[0] = convertDate(item.getString("update"));
                ddDate[1] = convertDate(item.getString("update"));
                ddDate[2] = convertDate(item.getString("this_max_mdd_date"));
                ddDate[3] = convertDate(item.getString("all_max_mdd_date"));
                ddDate[4] = convertDate(item.getString("last_peak_date"));
                mMddDataList.add(new MddData(code, dd, ddDate));
            }

            JSONArray assetAllocFunds = jsonObject.getJSONArray("asset_alloc_funds");


            for (int i=0; i < assetAllocFunds.length(); i++) {
                JSONObject item = assetAllocFunds.getJSONObject(i);
                AssetAllocFund fund = new AssetAllocFund(item);
                mAssetAllocFundList.add(fund);
            }

            mMddAdapter.notifyDataSetChanged();
            //JSONObject laa = jsonObject.getJSONObject("laa");
            //String spyValue = laa.getString("Spy");
            //Toast.makeText(getApplicationContext(),
            //        "SPY Status: " + spyValue, Toast.LENGTH_SHORT).show();

        }
        catch (JSONException e){
            Toast.makeText(getApplicationContext(),
                    "Error: " + e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
    public String convertDate(String strDate){
        //From YYYY-MM-DD ==> YYMMDD
        StringBuffer str = new StringBuffer();
        str.append(strDate.charAt(2));
        str.append(strDate.charAt(3));
        str.append(strDate.charAt(5));
        str.append(strDate.charAt(6));
        str.append(strDate.charAt(8));
        str.append(strDate.charAt(9));
        return str.toString();
    }
    public void initMddData(){
        mMddDataList = new ArrayList<MddData>();
        /*
        for(int i=0; i < 10; i++) {
            float dd[] = new float[5];
            String ddDate[] = new String[5];
            for (int j=0; j < 5; j++) {
                dd[j] = (float)(Math.random() * 100.0);
                ddDate[j] = "220101";
            }
            mMddDataList.add(new MddData("Code", dd, ddDate));
        }
        */
    }
}