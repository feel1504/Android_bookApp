package com.example.bookapp.wode;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.bookapp.R;

public class WodeExitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wode_exit);
        ActionBar bar = getSupportActionBar();
        bar.setTitle("注销");
    }
}