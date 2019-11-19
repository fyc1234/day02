package com.example.presenter;

import com.example.bean.RecentBean;
import com.example.fragment.MainFragment;
import com.example.model.IntentModel;
import com.example.view.FragmentView;

import java.util.List;

public class Presenter {
    private final IntentModel model;
    private FragmentView fragment;

    public Presenter(FragmentView fragment) {
        model = new IntentModel();
        this.fragment = fragment;
    }


    public void getData() {
        model.getData(new ResultCallBack<List<RecentBean>>() {
            @Override
            public void onSuccessed(List<RecentBean> recentBeans) {
                fragment.onSuccessed(recentBeans);
            }

            @Override
            public void onFailear(String s) {
                fragment.onFailear(s);
            }
        });
    }
}
