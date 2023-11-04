package com.example.myapplication.model.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.DangNhap;
import com.example.myapplication.R;

public class MainWait extends AppCompatActivity {
    int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_wait);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainWait.this, DangNhap.class);
                startActivity(intent);

                finish();
            }
        },SPLASH_TIME_OUT);

    }
}


