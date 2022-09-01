package com.example.bookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Timer t = new Timer();
        getSupportActionBar().setTitle("书旗小说");
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent1 = new Intent(MainActivity.this,shujiaActivity.class);
                startActivity(intent1);
                finish();
            }
        },3000);
    }
}