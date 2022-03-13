package com.harrykbd.util.harryport;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

class AssetAllocStock implements Serializable {
    private String name;
    private String code;
    private int cnt;
    private float curPrice;
    private float buyPrice;
    private float idealRatio;
    private float curRatio;
    private float profitRate;

    public AssetAllocStock(String name, String code, int cnt, float curPrice, float buyPrice, float idealRatio, float curRatio, float profitRate) {
        this.name = name;
        this.code = code;
        this.cnt = cnt;
        this.curPrice = curPrice;
        this.buyPrice = buyPrice;
        this.idealRatio = idealRatio;
        this.curRatio = curRatio;
        this.profitRate = profitRate;
    }

    public AssetAllocStock(JSONObject stockItem ) {
        try {
            this.name = stockItem.getString("name");
            this.code = stockItem.getString("code");
            this.cnt = new Integer(stockItem.getString("cnt"));
            this.curPrice = new Float(stockItem.getString("cur_price"));
            this.buyPrice = new Float(stockItem.getString("buy_price"));
            this.idealRatio = new Float(stockItem.getString("ideal_ratio"));
            this.curRatio = new Float(stockItem.getString("cur_ratio"));
            this.profitRate = new Float(stockItem.getString("profit_rate"));
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public int getCnt() {
        return cnt;
    }

    public float getCurPrice() {
        return curPrice;
    }

    public float getBuyPrice() {
        return buyPrice;
    }

    public float getIdealRatio() {
        return idealRatio;
    }

    public float getCurRatio() {
        return curRatio;
    }

    public float getProfitRate(){
        return profitRate;
    }
}
