package com.example.ling.store.myinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.ling.R;

public class AddressSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_search);

        WebView webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new BridgeInterface(),"Android");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                webView.loadUrl("javascript:sample2_execDaumPostcode();");
            }
        });
        // Android -> Javascript 함수 호출!
        webView.loadUrl("http://192.168.0.28:8080/ling/address");
    }

    private class BridgeInterface {
        @JavascriptInterface
        public void processDATA(String data) {
            // 다음(카카오) 주소 검색 API의 결과 값이 브릿지 통로를 통해 전달 받는다. (from Javscript)
            Intent intent = new Intent();
            intent.putExtra("data",data);
            setResult(RESULT_OK,intent);
            finish();
        }

    }
}