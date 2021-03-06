package com.ksu.lunmijo.guitartuner;

import android.Manifest;
import android.app.Application;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.annotation.UiThread;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ksu.lunmijo.guitartuner.audiorecorder.AndroidAudioConfig;
import com.ksu.lunmijo.guitartuner.audiorecorder.converter.PCMArrayConverter;
import com.ksu.lunmijo.guitartuner.audiorecorder.AndroidAudioRecorder;
import com.ksu.lunmijo.guitartuner.threads.AnalyzerThread;
import com.ksu.lunmijo.guitartuner.threads.AudioRecordThread;

import java.util.concurrent.ThreadPoolExecutor;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO},
                    123);
        }
        else {
                new AudioRecordThread().run();
                new AnalyzerThread().run();
        }

    }
}
