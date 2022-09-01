package com.example.bookapp.admin;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.bookapp.Database.MyDatabaseHelper;
import com.example.bookapp.Database.UserDatabaseHelper;
import com.example.bookapp.R;
import com.example.bookapp.entity.Book;
import com.example.bookapp.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AdminIndexActivity extends AppCompatActivity {

    private List<User> userList;
    private UserDatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private RecyclerView recyclerView;
    private UserAdapter adapter;
    private LinearLayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_index);
        ActionBar bar = getSupportActionBar();
        bar.setTitle("管理员权限后台");
        userList = new ArrayList<>();

        dbHelper = new UserDatabaseHelper(this,"User.db",
                null,1);
        db = dbHelper.getWritableDatabase();

        queryUser();
        recyclerView = findViewById(R.id.admin_index);
        initData();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onResume() {
        super.onResume();
        userList.clear();
        queryUser();
        adapter.notifyDataSetChanged();//通知adapter更新
    }

    private void initData() {
        recyclerView = (RecyclerView) findViewById(R.id.admin_index);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new UserAdapter(userList);
        recyclerView.setAdapter(adapter);
    }

    //查询数据
    public void queryUser(){
        Cursor cursor = db.query("User", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                // 遍历Cursor对象， 取出数据并打印
                @SuppressLint("Range") int level = cursor
                        .getInt(cursor.getColumnIndex("level"));
                @SuppressLint("Range") String account = cursor
                        .getString(cursor.getColumnIndex("account"));
                @SuppressLint("Range") String password = cursor
                        .getString(cursor.getColumnIndex("password"));

                User user = new User();
                user.setLevel(level);
                user.setAccount(account);
                user.setPassword(password);
                Log.d("book_data",user.toString());
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }
    private void init(){
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setAccount("gzcc"+i);
            user.setPassword("123");
            user.setLevel(new Random().nextInt(2));
            userList.add(user);
        }
    }
}