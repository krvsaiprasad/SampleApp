package com.example.sampleapp;

import com.google.gson.annotations.SerializedName;

public class SampleData {

    @SerializedName("discount")
    String discount;
    @SerializedName("raing")
    String raing;
    @SerializedName("name")
    String name;
    @SerializedName("areaname")
    String areaname;
    @SerializedName("time")
    String time;

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getRaing() {
        return raing;
    }

    public void setRaing(String raing) {
        this.raing = raing;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
