package com.harrykbd.util.harryport;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class AssetAllocFund implements Serializable {
    private String fundName;
    private String fundMeta;
    private float totalInvested;
    private float curValue;
    private float profitRate;
    private ArrayList<AssetAllocStock> stockList;

    public AssetAllocFund(String fundName, String fundMeta, float totalInvested, float curValue, float profitRate, ArrayList<AssetAllocStock> stockList) {
        this.fundName = fundName;
        this.fundMeta = fundMeta;
        this.totalInvested = totalInvested;
        this.curValue = curValue;
        this.profitRate = profitRate;
        this.stockList = stockList;
    }

    public AssetAllocFund(JSONObject item){
        try {
            fundName = item.getString("fund_name");
            fundMeta = item.getString("fund_meta");
            totalInvested = new Float(item.getString("total_price"));
            curValue = new Float(item.getString("cur_price"));
            profitRate = new Float(item.getString("profit_rate"));
            JSONArray stockItems = item.getJSONArray("stocks");
            stockList = new ArrayList<>();
            for (int i=0; i < stockItems.length(); i++) {
                JSONObject stockItem = stockItems.getJSONObject(i);
                AssetAllocStock assetAllocStock = new AssetAllocStock(stockItem);
                stockList.add(assetAllocStock);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public String getFundName() {
        return fundName;
    }

    public String getFundMeta() {
        return fundMeta;
    }

    public float getTotalInvested() {
        return totalInvested;
    }

    public float getCurValue() {
        return curValue;
    }

    public float getProfitRate() {
        return profitRate;
    }

    public ArrayList<AssetAllocStock> getStockList() {
        return stockList;
    }
}
