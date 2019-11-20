package com.example.testofscroll;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.testofscroll.adapter.RecAdapter;
import com.example.testofscroll.adapter.VpAdapter;
import com.example.testofscroll.bean.Bean;
import com.google.android.material.navigation.NavigationView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private List<Bean.ResultsBean> results;
    private RecAdapter adapter;
    private VpAdapter vpAdapter;
    private ViewPager mVp;
    private RecyclerView mRec;
    private NavigationView mNa;
    private Toolbar mTl;
    private LinearLayout mLl;
    private DrawerLayout mDl;
    private int offset;

    //	20/1
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ApiService.baseUrl)
                .build();
        retrofit.create(ApiService.class)
                .getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Bean>() {
                    @Override
                    public void accept(Bean bean) throws Exception {
                        if (bean != null) {
                            results.addAll(bean.getResults());

                            adapter.notifyDataSetChanged();
                            vpAdapter.notifyDataSetChanged();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i("retrofit", throwable.toString());
                    }
                });


    }

    private void initView() {

        mVp = (ViewPager) findViewById(R.id.vp);
        mRec = (RecyclerView) findViewById(R.id.rec);
        mNa = (NavigationView) findViewById(R.id.na);
        mTl = (Toolbar) findViewById(R.id.tl);
        setSupportActionBar(mTl);
        mLl = (LinearLayout) findViewById(R.id.ll);
        mDl = (DrawerLayout) findViewById(R.id.dl);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDl, mTl, R.string.app_name, R.string.app_name);
        toggle.syncState();
        mDl.addDrawerListener(toggle);
        mDl.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                mLl.setX(slideOffset * mNa.getWidth());
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        final LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRec.setLayoutManager(manager);
        adapter = new RecAdapter(this, results = new ArrayList<>());
        mRec.setAdapter(adapter);

        vpAdapter = new VpAdapter(this, results);
        mVp.setAdapter(vpAdapter);

        adapter.setItemClick(new RecAdapter.ItemClick() {
            @Override
            public void onItemClick(View view, int position) {
//                mVp.setCurrentItem(position);
                Intent intent = new Intent(MainActivity.this, VPActivity.class);
                intent.putExtra("index", position);
                intent.putExtra("bean", (Serializable) results);
                startActivity(intent);
            }
        });


        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mRec.scrollToPosition(position);
                manager.scrollToPositionWithOffset(position,0);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mRec.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }


            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mVp.setCurrentItem(manager.findFirstCompletelyVisibleItemPosition());
            }
        });


    }
}
