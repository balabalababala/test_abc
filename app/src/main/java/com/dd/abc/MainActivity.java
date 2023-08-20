package com.dd.abc;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener {
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
//                stopSelf();
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
            mediaPlayer.start();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_start) {
            //播放背景音乐
            Intent intent = new Intent(this, MusicService.class);
            startService(intent);
            //退出当前Activity
            this.finish();
        } else if (v.getId() == R.id.btn_stop) {
            //停止播放音乐
            Intent intent = new Intent(this, MusicService.class);
            stopService(intent);
        }
    }
}



