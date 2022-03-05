package com.harrykbd.util.harryport;

public class MddData {
    private String code;
    private float price;
    private float mdd;
    private float tmdd;
    private float peak;
    private float max_mdd;
    private String price_date;
    private String mdd_date;
    private String tmdd_date;
    private String peak_date;
    private String max_mdd_date;

    public MddData(String code, float[] mdd_info, String[] mdd_date_info){
        this.code = code;
        price = mdd_info[0];
        mdd = mdd_info[1];
        tmdd = mdd_info[2];
        peak = mdd_info[3];
        max_mdd = mdd_info[4];

        price_date = mdd_date_info[0];
        mdd_date = mdd_date_info[1];
        tmdd_date = mdd_date_info[2];
        peak_date = mdd_date_info[3];
        max_mdd_date = mdd_date_info[4];
    }
    public MddData(String code, float price, float mdd, float tmdd, float peak, float max_mdd,
                   String price_date, String mdd_date, String tmdd_date, String peak_date, String max_mdd_date){
        this.code = code;
        this.price = price;
        this.mdd = mdd;
        this.tmdd = tmdd;
        this.peak = peak;
        this.max_mdd = max_mdd;

        this.price_date = price_date;
        this.mdd_date = mdd_date;
        this.tmdd_date = tmdd_date;
        this.peak_date = peak_date;
        this.max_mdd_date = max_mdd_date;
    }

    public String getCode(){
        return code;
    }
    public float getPrice() {
        return price;
    }

    public String getPriceDate() {
        return price_date;
    }

    public float getMdd() {
        return mdd;
    }

    public String getMddDate() {
        return mdd_date;
    }

    public float getPeak() {
        return peak;
    }

    public String getPeakDate() {
        return peak_date;
    }

    public float getMaxMdd() {
        return max_mdd;
    }

    public String getMaxMddDate() {
        return max_mdd_date;
    }

    public float getTmdd() {
        return tmdd;
    }

    public String getTmddDate() {
        return tmdd_date;
    }
}
