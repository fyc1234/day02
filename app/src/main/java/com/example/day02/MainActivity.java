package com.example.day02;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.VpMainAdapter;
import com.example.fragment.FavorateFragment;
import com.example.fragment.MainFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mVp;
    private TabLayout mTl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
        mTl = (TabLayout) findViewById(R.id.tl);
        List<String> titles = new ArrayList<>();
        List<Fragment> fs = new ArrayList<>();
        titles.add("我的");
        titles.add("收藏");
        fs.add(new MainFragment());
        fs.add(new FavorateFragment());
        VpMainAdapter adapter = new VpMainAdapter(getSupportFragmentManager(), fs, titles);
        mVp.setAdapter(adapter);
        mTl.setupWithViewPager(mVp);
    }
}
