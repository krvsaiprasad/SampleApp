package com.example.sampleapp;

import android.app.ProgressDialog;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.spinner)
    Spinner spinner;

    List<SampleData> sampleModels;

    SampleData1 sampleData1;

    ProgressDialog progressDialog;

    ApiInterface apiInterface;

    String array[]={"Champapet","L.B.Nagar"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

        ButterKnife.bind(this);

        progressDialog=new ProgressDialog(this);

        ArrayAdapter<String> adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,array);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        if(hasNetwork())
            progressDialog.setMessage("Loading");
            progressDialog.show();
            getDatafromserver1();

    }


    public void getDatafromserver1(){

        apiInterface=ApiClient.getApiClient().create(ApiInterface.class);

        Call<SampleData1> call=apiInterface.getModel();

        call.enqueue(new Callback<SampleData1>() {
            @Override
            public void onResponse(Call<SampleData1> call, Response<SampleData1> response) {

                sampleData1=response.body();
                sampleModels=sampleData1.getSampleData();
                if(progressDialog.isShowing() || progressDialog!=null){
                    progressDialog.dismiss();
                }
                rv.setLayoutManager(new LinearLayoutManager(MainActivity2.this));
                rv.setAdapter(new Adapter1(sampleModels));
            }

            @Override
            public void onFailure(Call<SampleData1> call, Throwable t) {

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
        public Adapter1.VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v= LayoutInflater.from(MainActivity2.this).inflate(R.layout.inflater,parent,false);
            return new Adapter1.VH(v);
        }

        @Override
        public void onBindViewHolder(@NonNull Adapter1.VH holder, int position) {

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
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            flag = (networkInfo != null && networkInfo.isAvailable() && (
                    networkInfo.isConnectedOrConnecting() || networkInfo.isConnected()));


            if (flag) {
                return flag;
            } else {
                Toast.makeText(this, "No Internet", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {

        }

        return flag;


    }
}
