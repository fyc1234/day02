package com.example.presenter;

public interface ResultCallBack<T> {
    void onSuccessed(T t);
    void onFailear(String s);
}
