package com.example.sampleapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("json/get/bTBCsXfThe?indent=2")
    Call<SampleData1> getModel();

//    @POST("json/get/bQryUvGuMi?indent=2")
//    Call<List<SampleData>> getModel();
}
