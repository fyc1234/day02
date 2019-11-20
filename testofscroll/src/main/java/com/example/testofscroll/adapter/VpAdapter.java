package com.example.testofscroll.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.testofscroll.R;
import com.example.testofscroll.bean.Bean;

import java.util.List;

public class VpAdapter extends PagerAdapter {

    private Context context;
    private List<Bean.ResultsBean> results;

    public VpAdapter(Context context, List<Bean.ResultsBean> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public int getCount() {
        return results.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.vp_item, container,false);
        ImageView imageView = inflate.findViewById(R.id.vp_item_img);

        Glide.with(context).load(results.get(position).getUrl()).into(imageView);
        container.addView(inflate);
        return inflate;
    }



    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
