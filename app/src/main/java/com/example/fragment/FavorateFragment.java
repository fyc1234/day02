package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.BaseApp;
import com.example.RvAdapter;
import com.example.bean.RecentBean;
import com.example.day02.R;
import com.example.greendao.RecentBeanDao;

import java.util.ArrayList;
import java.util.List;

public class FavorateFragment extends Fragment {
    private RecyclerView mRv;
    private List<RecentBean> datas;
    private RvAdapter adapter;
    private RecentBeanDao dao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, null);
        initView(view);
        dao = BaseApp.getInstance().getDaoSession().getRecentBeanDao();
        return view;
    }

    private void initView(View view) {
        mRv = (RecyclerView) view.findViewById(R.id.rv);
        mRv.setLayoutManager(new LinearLayoutManager(getContext()));
        datas = new ArrayList<>();
        adapter = new RvAdapter(datas,getContext());
        mRv.setAdapter(adapter);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            datas.addAll(dao.loadAll());
            adapter.notifyDataSetChanged();
        }
    }
}
