package com.example.day02;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bean.WebBean;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

public class ContentActivity extends AppCompatActivity {


    private WebView mWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        initView();
    }

    private void initView() {
        mWeb = (WebView) findViewById(R.id.web);
 /*       mWeb.getSettings().setJavaScriptEnabled(true);
        mWeb.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);*/
        mWeb.setWebViewClient(new WebViewClient());

        String url = getIntent().getStringExtra("url");

        Request build = new Request.Builder().url(url)
                .get().build();

        new OkHttpClient().newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String s = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            mWeb.loadDataWithBaseURL(null,new Gson().fromJson(s, WebBean.class).getBody(), "text/html", "UTF-8",null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });

            }
        });


        //mWeb.loadUrl(url);
    }

}
