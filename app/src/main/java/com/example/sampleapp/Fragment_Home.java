package com.example.sampleapp;

import android.app.ProgressDialog;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class Fragment_Home extends Fragment {

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.spinner)
    Spinner spinner;

    List<SampleData> sampleModels;
    OkHttpClient okHttpClient;
    FormBody formBody;
    Request request;

    ProgressDialog progressDialog;

    ApiInterface apiInterface;

    String array[]={"Champapet","L.B.Nagar"};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this,view);

        progressDialog=new ProgressDialog(getActivity());
        sampleModels=new ArrayList<>();
        okHttpClient=new OkHttpClient();

        ArrayAdapter<String> adapter=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,array);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        if(hasNetwork())
        getDatafromserver();
    }



    public void getDatafromserver(){

        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        request=new Request.Builder().url("http://www.json-generator.com/api/json/get/bTBCsXfThe?indent=2").build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                if(progressDialog!=null || progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
                Log.e("Error",e.getMessage()+" ");
                Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if(response!=null){

                    try {
                        String res=response.body().string();
                        JSONObject jsonObject=new JSONObject(res);
                        JSONArray jsonArray=jsonObject.getJSONArray("SampleData1");

                        for(int i=0;i<jsonArray.length();i++){

                            JSONObject jsonObject1=jsonArray.getJSONObject(i);

                            SampleData sm=new SampleData();

                            sm.setAreaname(jsonObject1.getString("areaname"));
                            sm.setName(jsonObject1.getString("name"));
                            sm.setDiscount(jsonObject1.getString("discount"));
                            sm.setRaing(jsonObject1.getString("raing"));
                            sm.setTime(jsonObject1.getString("time"));

                            sampleModels.add(sm);
                        }

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(progressDialog!=null || progressDialog.isShowing()){
                                    progressDialog.dismiss();
                                }
                                rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                                rv.setAdapter(new Adapter());
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    public class Adapter1 extends RecyclerView.Adapter<Adapter1.VH>{


        private List<SampleData> sampleModels;
        public Adapter1(List<SampleData> sampleModels) {

            this.sampleModels=sampleModels;
        }

        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v=LayoutInflater.from(getActivity()).inflate(R.layout.inflater,parent,false);
            return new VH(v);
        }

        @Override
        public void onBindViewHolder(@NonNull VH holder, int position) {

            holder.tv_time.setText(sampleModels.get(position).getTime());
            holder.tv_rate.setText(sampleModels.get(position).getRaing());
            holder.tv_name.setText(sampleModels.get(position).getName());
            holder.tv_area.setText(sampleModels.get(position).getAreaname());
            holder.tv_offer.setText(sampleModels.get(position).getDiscount());

        }

        @Override
        public int getItemCount() {
            return sampleModels.size();
        }

        public class VH extends RecyclerView.ViewHolder{

            TextView tv_name,tv_area,tv_rate,tv_time,tv_offer;

            public VH(View itemView) {
                super(itemView);

                tv_name=itemView.findViewById(R.id.tv_saloon);
                tv_area=itemView.findViewById(R.id.tv_area);
                tv_rate=itemView.findViewById(R.id.rating);
                tv_time=itemView.findViewById(R.id.tv_time);
                tv_offer=itemView.findViewById(R.id.tv_offer);
            }
        }
    }

    public class Adapter extends RecyclerView.Adapter<Adapter.VH>{


        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v=LayoutInflater.from(getActivity()).inflate(R.layout.inflater,parent,false);
            return new VH(v);
        }

        @Override
        public void onBindViewHolder(@NonNull VH holder, int position) {

            holder.tv_time.setText(sampleModels.get(position).getTime());
            holder.tv_rate.setText(sampleModels.get(position).getRaing());
            holder.tv_name.setText(sampleModels.get(position).getName());
            holder.tv_area.setText(sampleModels.get(position).getAreaname());
            holder.tv_offer.setText(sampleModels.get(position).getDiscount());

        }

        @Override
        public int getItemCount() {
            return sampleModels.size();
        }

        public class VH extends RecyclerView.ViewHolder{

            TextView tv_name,tv_area,tv_rate,tv_time,tv_offer;

            public VH(View itemView) {
                super(itemView);

                tv_name=itemView.findViewById(R.id.tv_saloon);
                tv_area=itemView.findViewById(R.id.tv_area);
                tv_rate=itemView.findViewById(R.id.rating);
                tv_time=itemView.findViewById(R.id.tv_time);
                tv_offer=itemView.findViewById(R.id.tv_offer);
            }
        }
    }

    public boolean hasNetwork() {

        boolean flag = false;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            flag = (networkInfo != null && networkInfo.isAvailable() && (
                    networkInfo.isConnectedOrConnecting() || networkInfo.isConnected()));


            if (flag) {
                return flag;
            } else {
                Toast.makeText(getActivity(), "No Internet", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {

        }

        return flag;


    }
}
