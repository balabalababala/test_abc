package com.dd.abc;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private MediaPlayer mediaPlayer = null;
    private boolean isReady = false;

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



