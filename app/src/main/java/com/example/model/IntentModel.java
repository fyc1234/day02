package com.example.model;

import android.util.Log;

import com.example.bean.Bean;
import com.example.bean.RecentBean;
import com.example.presenter.ResultCallBack;
import com.example.url.ApiService;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class IntentModel {

    public void getData(final ResultCallBack<List<RecentBean>> listResultCallBack) {
        new Retrofit.Builder().baseUrl(ApiService.sBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService.class)
                .getData().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bean bean) {
                        listResultCallBack.onSuccessed(bean.getRecent());
                    }

                    @Override
                    public void onError(Throwable e) {
                        listResultCallBack.onFailear(e.toString());
                        Log.i("test", "onError: "+e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
