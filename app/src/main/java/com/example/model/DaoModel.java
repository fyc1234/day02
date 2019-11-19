package com.example.model;

import com.example.BaseApp;
import com.example.bean.RecentBean;
import com.example.greendao.RecentBeanDao;
import com.example.presenter.ResultCallBack;

public class DaoModel {
    private RecentBeanDao dao = BaseApp.getInstance().getDaoSession().getRecentBeanDao();

    public void insert(RecentBean recentBean, ResultCallBack resultCallBack) {
        if (dao.insertOrReplace(recentBean)>=0){
            resultCallBack.onSuccessed(null);
        }else{
            resultCallBack.onFailear("");
        }
    }
}
