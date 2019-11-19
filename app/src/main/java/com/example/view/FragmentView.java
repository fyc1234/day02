package com.example.view;

import com.example.bean.RecentBean;

import java.util.List;

public interface FragmentView {
    void onSuccessed(List<RecentBean> o);

    void onFailear(String s);
}
