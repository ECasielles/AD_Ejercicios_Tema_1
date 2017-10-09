package com.example.usuario.ad_ejercicios_tema_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class UrlActivity extends AppCompatActivity {

    WebView mmiWV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url);
        Bundle bundle = getIntent().getExtras();
        String url = bundle.getString("url");
        mmiWV = (WebView) findViewById(R.id.miWV);
        mmiWV.setWebViewClient(new WebViewClient());
        mmiWV.loadUrl(url);
    }
}
