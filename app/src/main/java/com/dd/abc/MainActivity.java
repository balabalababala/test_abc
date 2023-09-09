package com.dd.abc;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.RadioGroup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.dd.abc.databinding.ActivityMainBinding;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private MediaPlayer mediaPlayer = null;
    private boolean isReady = false;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Intent intent = new Intent(this, MusicService.class);
//        startService(intent);


        mediaPlayer = MediaPlayer.create(this, R.raw.three_person_time);

        mediaPlayer.stop();
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                mp.release();
                //stopSelf();
                return false;
            }
        });

        try {
            mediaPlayer.prepare();
            isReady = true;
        } catch (IOException e) {
            e.printStackTrace();
            isReady = false;
        }

        if (isReady) {
            //将背景音乐设置为循环播放
            mediaPlayer.setLooping(true);
//            mediaPlayer.start();
        }





        WebView webView = findViewById(R.id.my_webview);

        if (savedInstanceState != null && false) {

            webView.restoreState(savedInstanceState);

        } else {
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    String pattern = "^http";
                    Pattern r = Pattern.compile(pattern);
                    Matcher m = r.matcher(url);
                    Log.w("url", "*****************" + url);
                    if (m.find()) {
                        view.loadUrl(url);
                        return true;
                    } else {
                        return true; // 阻止打开
                    }

                }

                @Override
                public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                    String url = request.getUrl().toString();

                    Log.w("url", "*****************" + url);
//                if (url.contains("example.com")) {
//                    // Load the URL normally
//                    return super.shouldInterceptRequest(view, request);
//                } else {
//                    // Block the URL
//                    return new WebResourceResponse("text/plain", "UTF-8", null);
//                }


                    return super.shouldInterceptRequest(view, request);

                }

            });

            // 播放视频要加载这个
            webView.setWebChromeClient(new WebChromeClient());

//        webView.


            webView.getSettings().setSupportMultipleWindows(true);
            webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            webView.getSettings().setJavaScriptEnabled(true); // 允许运行js
            webView.getSettings().setLoadsImagesAutomatically(true);
            webView.getSettings().setSupportMultipleWindows(true); // 可以加载多媒体，播放视频
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW); // 允许https页面加载http内容


            webView.getSettings().setDomStorageEnabled(true);  // 启用localstorage
//        webView.getSettings().setAppCacheMaxSize(1024*1024*8);
//        String appCachePath = getApplicationContext().getCacheDir().getAbsolutePath();
//        webView.getSettings().setAppCachePath(appCachePath);
            webView.getSettings().setAllowFileAccess(true);
//        webView.getSettings().setAppCacheEnabled(true);

            webView.getSettings().setUserAgentString("Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                webView.getSettings().setMediaPlaybackRequiresUserGesture(false);
            }

            webView.loadUrl("https://www.baidu.com/");


//        webView2 = new WebView(getActivity().getApplicationContext());
//
//        webView2.setWebViewClient(new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//        });
//
//        webView2.getSettings().setJavaScriptEnabled(true);
//        webView2.loadUrl("https://www.alipay.com");
//
//        setContentView(webView2);

        }


    }


    public void startNewActivity(View v){
        Intent intent = new Intent();
        intent.setClassName("com.dd.abc","com.dd.abc.ScrollingActivity");
        intent.putExtra("extra_data", "Hello World");
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_start) {
            if (!mediaPlayer.isPlaying()) {
                //播放音乐
                mediaPlayer.start();
            }
        } else if (v.getId() == R.id.btn_stop) {
            //停止播放音乐
           mediaPlayer.pause();
            //退出当前Activity
//            this.finish();
        }
    }



    public void startService(View v) {

//        if (v.getId() == R.id.btn_start) {
//            //播放背景音乐
//            Intent intent2 = new Intent(this, MusicService.class);
//            startService(intent2);
//            //退出当前Activity
//            this.finish();
//        } else if (v.getId() == R.id.btn_stop) {
//            //停止播放音乐
//            Intent intent2 = new Intent(this, MusicService.class);
//            stopService(intent2);
//        }
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i){
            case R.id.radioButton:
                break;
            case R.id.radioButton2:
                break;
        }
    }
}



