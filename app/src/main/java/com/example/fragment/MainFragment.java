package com.example.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.RvAdapter;
import com.example.bean.RecentBean;
import com.example.day02.ContentActivity;
import com.example.day02.R;
import com.example.presenter.DaoPresenter;
import com.example.presenter.Presenter;
import com.example.view.FragmentView;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment implements FragmentView {

    private RecyclerView mRv;
    private Presenter presenter;
    private List<RecentBean> datas;
    private RvAdapter adapter;
    private DaoPresenter daoPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, null);
        presenter = new Presenter(this);
        daoPresenter = new DaoPresenter(this);
        initView(view);
        presenter.getData();
        return view;
    }

    private void initView(View view) {
        mRv = (RecyclerView) view.findViewById(R.id.rv);
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));

        datas = new ArrayList<>();
        adapter = new RvAdapter(datas,getContext());
        mRv.setAdapter(adapter);
        adapter.setOnItemClickListener(new RvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                Intent intent = new Intent(getActivity(), ContentActivity.class);
                intent.putExtra("url",datas.get(pos).getUrl());
                startActivity(intent);
            }
        });
        adapter.setOnItemLongClickListener(new RvAdapter.OnItemLongClickListener() {
            @Override
            public void onItemClick(final int pos) {
                new AlertDialog.Builder(getContext())
                        .setTitle("是否插入数据库")
                        .setNegativeButton("取消",null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                daoPresenter.insert(datas.get(pos));
                            }
                        }).show();
            }
        });
    }

    @Override
    public void onSuccessed(List<RecentBean> bean) {
        if (bean!=null){
            datas.addAll(bean);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailear(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }
}
