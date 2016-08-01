package com.library.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.library.R;

/**
 * Created by longbh on 16/6/14.
 */
public class WebViewActivity extends BasicActivity {

    private Toolbar toolbar;
    private WebView webview;
    private String title;
    private String url;

    @Override
    protected void onGetBundle(Bundle bundle) {

    }

    @Override
    protected int getViewId() {
        return R.layout.activity_webview;
    }

    @Override
    protected void init() {
        toolbar = (Toolbar) findViewById(R.id.title_toolbar);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            title = bundle.getString("title");
            url = bundle.getString("url");
            setToolbar(toolbar, title);
        }

        webview = (WebView) findViewById(R.id.web_view);
        //设置WebView属性，能够执行Javascript脚本
        webview.getSettings().setJavaScriptEnabled(false);
        webview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                if(isFinishing()){
                    return;
                }
                webview.getSettings().setBlockNetworkImage(true);
                loading.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if(isFinishing()){
                    return;
                }
                webview.getSettings().setBlockNetworkImage(false);
                loading.dismiss();
            }
        });
        //加载需要显示的网页
        webview.loadUrl(url);
    }
}
