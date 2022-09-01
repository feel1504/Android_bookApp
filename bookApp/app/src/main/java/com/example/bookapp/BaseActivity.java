package com.example.bookapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    public void setBar(String title) {
        ActionBar bar = getSupportActionBar();
        bar.setTitle(title);
    }
}
