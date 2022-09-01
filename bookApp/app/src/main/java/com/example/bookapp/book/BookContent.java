package com.example.bookapp.book;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.bookapp.R;

public class BookContent extends AppCompatActivity {

    private TextView book_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_content);
        book_content = findViewById(R.id.book_content);
        book_content.setText(getIntent().getStringExtra("content"));
        ActionBar bar = getSupportActionBar();
        bar.setTitle(getIntent().getStringExtra("bookName"));
    }
}