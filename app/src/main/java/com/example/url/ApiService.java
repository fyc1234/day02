package com.example.url;

import com.example.bean.Bean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    String sBaseUrl ="http://news-at.zhihu.com/api/4/news/";

    @GET("hot")
    Observable<Bean> getData();
}
