package com.example;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bean.RecentBean;
import com.example.day02.R;

import java.util.List;

public class RvAdapter extends RecyclerView.Adapter {
    List<RecentBean> datas;
    Context context;

    public RvAdapter(List<RecentBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }
    public interface OnItemClickListener{
        void onItemClick(int pos);
    }

    public interface OnItemLongClickListener{
        void onItemClick(int pos);
    }
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case 0:
                View view = View.inflate(context, R.layout.rv_item0, null);
                return new VH0(view);
            case 1:
                View view1 = View.inflate(context, R.layout.rv_item1, null);
                return new VH1(view1);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        VH vh = (VH) holder;
        RecentBean bean = datas.get(position);
        Glide.with(context).load(bean.getThumbnail()).into(vh.img);
        vh.title.setText(bean.getTitle());
        vh.url.setText(bean.getUrl());
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null){
                    onItemClickListener.onItemClick(position);
                }
            }
        });
        vh.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemLongClickListener!=null) onItemLongClickListener.onItemClick(position);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position%2;
    }

    class VH extends RecyclerView.ViewHolder{
        ImageView img;
        TextView title;
        TextView url;

        public VH(@NonNull View itemView) {
            super(itemView);
        }
    }

    class VH0 extends VH{

        public VH0(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.rec_0);
            title = itemView.findViewById(R.id.title0);
            url = itemView.findViewById(R.id.url0);
        }
    }

    class VH1 extends VH{

        public VH1(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.rec_1);
            title = itemView.findViewById(R.id.title1);
            url = itemView.findViewById(R.id.url1);
        }
    }
}
