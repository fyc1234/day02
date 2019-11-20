package com.example.testofscroll.adapter;


import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.testofscroll.R;
import com.example.testofscroll.bean.Bean;

import java.util.List;

public class RecAdapter extends RecyclerView.Adapter {
    private final Context context;
    private final List<Bean.ResultsBean> beans;

    public RecAdapter(Context context, List<Bean.ResultsBean> beans) {
        this.context = context;
        this.beans = beans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(context, R.layout.item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        ViewHolder holder1 = (ViewHolder) holder;

        Glide.with(context).load(beans.get(position).getUrl()).into(holder1.mItemImg);
        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.onItemClick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return beans.size();
    }

    ItemClick itemClick;

    public void setItemClick(ItemClick itemClick) {
        this.itemClick = itemClick;
    }

    public interface ItemClick {
        void onItemClick(View view, int position);
    }


    static
    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mItemImg;

        ViewHolder(View view) {
            super(view);
            mItemImg = view.findViewById(R.id.item_img);
        }
    }
}
