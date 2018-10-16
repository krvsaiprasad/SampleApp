package com.example.sampleapp;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String array[]={"Champapet","L.B.Nagar"};

    @BindView(R.id.ll_home)
    LinearLayout ll_home;
    @BindView(R.id.ll_offers)
    LinearLayout ll_offers;
    @BindView(R.id.ll_history)
    LinearLayout ll_history;
    @BindView(R.id.ll_more)
    LinearLayout ll_more;

    @BindView(R.id.iv_home)
    ImageView iv_home;
    @BindView(R.id.iv_offers)
    ImageView iv_offers;
    @BindView(R.id.iv_history)
    ImageView iv_history;
    @BindView(R.id.iv_more)
    ImageView iv_more;
    @BindView(R.id.tv_home)
    TextView tv_home;
    @BindView(R.id.tv_offers)
    TextView tv_offers;
    @BindView(R.id.tv_history)
    TextView tv_history;
    @BindView(R.id.tv_more)
    TextView tv_more;

    @BindView(R.id.frame_container)
    FrameLayout frame_container;

    String Currentname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        tv_home.setTextColor(getResources().getColor(R.color.blue));
        iv_home.setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
        loadFragment(new Fragment_Home2(),"HomeFragment");

        ll_home.setOnClickListener(this);
        ll_history.setOnClickListener(this);
        ll_more.setOnClickListener(this);
        ll_offers.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
       // super.onBackPressed();

        if(Currentname.equalsIgnoreCase("HomeFragment")){
            finish();
        }else {
            tv_home.setTextColor(getResources().getColor(R.color.blue));
            iv_home.setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
            tv_offers.setTextColor(getResources().getColor(R.color.md_black_1000));
            iv_offers.setColorFilter(getResources().getColor(R.color.md_black_1000), PorterDuff.Mode.SRC_IN);
            tv_history.setTextColor(getResources().getColor(R.color.md_black_1000));
            iv_history.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
            tv_more.setTextColor(getResources().getColor(R.color.md_black_1000));
            iv_more.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
            loadFragment(new Fragment_Home(),"HomeFragment");
        }
    }

    @Override
    public void onClick(View v) {

        if(v == ll_home){
            tv_home.setTextColor(getResources().getColor(R.color.blue));
            iv_home.setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
            tv_offers.setTextColor(getResources().getColor(R.color.md_black_1000));
            iv_offers.setColorFilter(getResources().getColor(R.color.md_black_1000), PorterDuff.Mode.SRC_IN);
            tv_history.setTextColor(getResources().getColor(R.color.md_black_1000));
            iv_history.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
            tv_more.setTextColor(getResources().getColor(R.color.md_black_1000));
            iv_more.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
            loadFragment(new Fragment_Home2(),"HomeFragment");
        }else if(v == ll_history){
            tv_home.setTextColor(getResources().getColor(R.color.md_black_1000));
            iv_home.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
            tv_offers.setTextColor(getResources().getColor(R.color.md_black_1000));
            iv_offers.setColorFilter(getResources().getColor(R.color.md_black_1000), PorterDuff.Mode.SRC_IN);
            tv_history.setTextColor(getResources().getColor(R.color.md_green_700));
            iv_history.setColorFilter(getResources().getColor(R.color.md_green_700), PorterDuff.Mode.SRC_IN);
            tv_more.setTextColor(getResources().getColor(R.color.md_black_1000));
            iv_more.setColorFilter(getResources().getColor(R.color.md_black_1000), PorterDuff.Mode.SRC_IN);
            loadFragment(new Fragment_history(),"Fragment");
        }else if(v == ll_offers){
            tv_home.setTextColor(getResources().getColor(R.color.md_black_1000));
            iv_home.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
            tv_offers.setTextColor(getResources().getColor(R.color.md_green_700));
            iv_offers.setColorFilter(getResources().getColor(R.color.md_green_700), PorterDuff.Mode.SRC_IN);
            tv_history.setTextColor(getResources().getColor(R.color.md_black_1000));
            iv_history.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
            tv_more.setTextColor(getResources().getColor(R.color.md_black_1000));
            iv_more.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
            loadFragment(new Fragment_Offer(),"Fragment");
        }else if(v == ll_more){
            tv_home.setTextColor(getResources().getColor(R.color.md_black_1000));
            iv_home.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
            tv_offers.setTextColor(getResources().getColor(R.color.md_black_1000));
            iv_offers.setColorFilter(getResources().getColor(R.color.md_black_1000), PorterDuff.Mode.SRC_IN);
            tv_history.setTextColor(getResources().getColor(R.color.md_black_1000));
            iv_history.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
            tv_more.setTextColor(getResources().getColor(R.color.md_green_700));
            iv_more.setColorFilter(getResources().getColor(R.color.md_green_700), PorterDuff.Mode.SRC_IN);
            loadFragment(new Fragment_More(),"Fragment");
        }

    }

    public void loadFragment(Fragment fragment,String name){

        Currentname=name;

        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container,fragment);
        final int count = getFragmentManager().getBackStackEntryCount();
        if(name.equalsIgnoreCase("HomeFragment")){
            transaction.addToBackStack(name);
        }
        transaction.commit();

//        getFragmentManager().addOnBackStackChangedListener(new android.app.FragmentManager.OnBackStackChangedListener() {
//            @Override
//            public void onBackStackChanged() {
//                if( getFragmentManager().getBackStackEntryCount() <= count){
//                    // pop all the fragment and remove the listener
//                    getFragmentManager().popBackStack("Fragment", POP_BACK_STACK_INCLUSIVE);
//                    getFragmentManager().removeOnBackStackChangedListener(this);
//                    // set the home button selected
//
//                }
//            }
//        });

    }

}
