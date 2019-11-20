package com.example.testofscroll;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.testofscroll.adapter.VpAdapter;
import com.example.testofscroll.bean.Bean;

import java.util.List;

public class VPActivity extends AppCompatActivity {

    private List<Bean.ResultsBean> results;
    private VpAdapter vpAdapter;
    private int index;
    private ViewPager mIVp;
    private TextView mITv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vp);
        Intent intent = getIntent();
        index = intent.getIntExtra("index", -1);
        results = (List<Bean.ResultsBean>) intent.getSerializableExtra("bean");

        initView();
    }

    private void initView() {
        mIVp = (ViewPager) findViewById(R.id.i_vp);
        mITv = (TextView) findViewById(R.id.i_tv);

        vpAdapter = new VpAdapter(this, results);
        mIVp.setAdapter(vpAdapter);
        mIVp.setCurrentItem(index);
        mITv.setText(setText(index + 1, results.size()));
        mIVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mITv.setText(setText(position + 1, results.size()));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public String setText(int index, int max) {
        return index + " 页 / " + max + " 页";
    }

}
