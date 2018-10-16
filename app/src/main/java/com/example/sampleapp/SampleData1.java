package com.example.sampleapp;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SampleData1 {

    @SerializedName("SampleData")
   List<SampleData> sampleData;

    public List<SampleData> getSampleData() {
        return sampleData;
    }

    public void setSampleData(List<SampleData> sampleData) {
        this.sampleData = sampleData;
    }
}
