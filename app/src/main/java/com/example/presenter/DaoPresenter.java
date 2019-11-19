package com.example.presenter;

import com.example.bean.RecentBean;
import com.example.fragment.MainFragment;
import com.example.greendao.DaoMaster;
import com.example.model.DaoModel;
import com.example.view.FragmentView;

public class DaoPresenter {
    private final DaoModel model;
    private FragmentView fragment;

    public DaoPresenter(FragmentView fragment) {
        model = new DaoModel();
        this.fragment = fragment;
    }

    public void insert(RecentBean recentBean) {
        model.insert(recentBean, new ResultCallBack() {
            @Override
            public void onSuccessed(Object o) {
                fragment.onSuccessed(null);
            }

            @Override
            public void onFailear(String s) {
                fragment.onFailear(s);
            }
        });
    }
}
