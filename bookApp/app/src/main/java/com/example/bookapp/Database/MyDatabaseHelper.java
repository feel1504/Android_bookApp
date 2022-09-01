package com.example.bookapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_BOOK = "create table Book(" +
            "id integer primary key autoincrement," +
            "imageId integer," +
            "name text," +
            "type text," +
            "readRecord text," +
            "size text," +
            "author text," +
            "content text)";
    public static final String CREATE_BOOKS = "create table BookS(" +
            "id integer primary key autoincrement," +
            "imageId integer," +
            "name text," +
            "type text," +
            "readRecord text," +
            "size text," +
            "author text," +
            "content text)";

    private Context mContext;

    public MyDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext =  context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_BOOKS);
        Toast.makeText(mContext, "创建好数据库了 ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
